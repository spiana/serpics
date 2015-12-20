package com.serpics.importexport.services;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.serpics.core.data.Repository;
import com.serpics.core.data.RepositoryInitializer;
import com.serpics.importexport.SerpicsCsvToBean;
import com.serpics.importexport.SerpicsMapperStrategy;

@Service("importCsvService")
public class ImportCsvServiceImpl implements ImportCsvService {

	Logger LOG = LoggerFactory.getLogger(ImportCsvService.class);
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void importCsv(Reader reader , Class<?> type ,int line, CSVParser parser) {
		SerpicsMapperStrategy mapper =  new SerpicsMapperStrategy();
		mapper.setType(type);
		CSVReader cvsReader = new CSVReader(reader,0,parser);
		SerpicsCsvToBean p = new SerpicsCsvToBean();
		List<Object> l = p.parse(mapper, cvsReader);
		LOG.info("load {} records", l.size() );
		Repository repository = RepositoryInitializer.getInstance().getRepositoryForEntity(type);
		int xx= 0;
		for (Object object : l) {
			LOG.info("processing record {}/{} " , ++xx, l.size() );
			repository.saveAndFlush(object);
		}

	}

	@Override
	public void importCsv(Reader reader, Class<?> type) {
		importCsv(reader, type , new CSVParser(';' ));
		
	}

	@Override
	public void importCsv(Reader reader, Class<?> type, CSVParser parser) {
		importCsv(reader, type , 0 , parser);
		
	}
	

	@Override
	public void importFromXml(String url, String basePath) throws FileNotFoundException, DocumentException  {
			importFileFromXml(new FileInputStream(url), basePath);
		
	}
	
	protected void importFileFromXml(InputStream in , String basePath) throws DocumentException{
		
		if(!basePath.endsWith(File.separator))
			basePath = basePath+ File.separator;
		
		SAXReader reader = new SAXReader();
		Document document = reader.read(in);
		Element root = document.getRootElement();
		for (Iterator<?> i = root.elementIterator("importentry"); i.hasNext();) {
			Element importEntry = (Element) i.next();
			final String cvsentry = basePath +importEntry.attributeValue("cvs");
			final String clazzname = importEntry.attributeValue("class");
			
			LOG.info("try to import file for  entity : [{}]", clazzname);
			try {
				Class<?> _entityClass = Class.forName(clazzname);
				LOG.info("start import entity {} from file {}" , clazzname , cvsentry);
				importCsv(new FileReader(cvsentry), _entityClass);
				LOG.info("end import entity {} from file {}" , clazzname , cvsentry);
			} catch (ClassNotFoundException e) {
				LOG.error("entity class not found for definition [{}]" , clazzname);
			}catch (IOException e) {
				LOG.error("error reading from file [{}]" , cvsentry);
			}
		}
	}

	@Override
	public void importFromZip(ZipFile file)  throws IOException{
		String zipPath = FileUtils.getTempDirectoryPath()+String.valueOf(new Date().getTime());
		File tmp = new File(zipPath) ; // create temporary directory
		try{
			extractZipFile(file, zipPath);
			Collection<File> to_process = FileUtils.listFiles(tmp, new String[]{"xml"}, false);
			for (File curretFile : to_process) {
				try {
					importFromXml(curretFile.getAbsolutePath(), tmp.getAbsolutePath());
				} catch (DocumentException e) {
					LOG.error(e.getMessage() , e);
				}
			}
			
		}finally{
			FileUtils.deleteDirectory(tmp);

		}
		
	}


	
	@Override
	public void importFromZip(String filePath) throws IOException{
			ZipFile _f = new ZipFile(filePath);
			importFromZip(_f);
	}
	
	
	private void extractZipFile(ZipFile zipFile , String zipPath) throws IOException{

		// get an enumeration of the ZIP file entries
		Enumeration<? extends ZipEntry> e = zipFile.entries();

		while (e.hasMoreElements()) {

			ZipEntry entry = e.nextElement();

			File destinationPath = new File(zipPath, entry.getName());

			// create parent directories
			destinationPath.getParentFile().mkdirs();

			// if the entry is a file extract it
			if (entry.isDirectory()) {
				continue;
			} else {

				BufferedInputStream bis = new BufferedInputStream(
						zipFile.getInputStream(entry));

				int b;
				byte buffer[] = new byte[1024];

				FileOutputStream fos = new FileOutputStream(destinationPath);

				BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);

				while ((b = bis.read(buffer, 0, 1024)) != -1) {
					bos.write(buffer, 0, b);
				}

				bos.close();
				bis.close();

			}

		}

		zipFile.close();
	}
		
	
	
}