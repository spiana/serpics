package com.serpics.importexport.services;

import java.io.Reader;
import java.util.List;

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
	
	
}
