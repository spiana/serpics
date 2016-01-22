package com.serpics.importexport.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.zip.ZipFile;

import org.dom4j.DocumentException;

import com.opencsv.CSVParser;

public interface ImportCsvService {

	public interface ImportPorgressListener{
		public void init();
		public void process(int curentRecord , int maxrecord);
		public void end();
		
	}
	public void importCsv(Reader reader , Class<?> type );
	public void importCsv(Reader reader , Class<?> type , CSVParser parser);
	
	public void importCsv(Reader reader , Class<?> type , ImportPorgressListener listener);
	public void importCsv(Reader reader , Class<?> type , CSVParser parser , ImportPorgressListener listener);
	public void importCsv(Reader reader , Class<?> type ,int line, CSVParser parser , ImportPorgressListener listener);
	
	public void importFromXml(String fileName , String basePath) throws FileNotFoundException, DocumentException ;
	public void importFromXml(String fileName , String basePath , ImportPorgressListener listener ) throws FileNotFoundException, DocumentException ;
	
	public void importFromZip(String filePath) throws IOException;
	
	public void importFromZip(ZipFile file ,  ImportPorgressListener listener) throws IOException;
	public void importFromZip(String filePath , ImportPorgressListener listener) throws IOException;
	
	
}
