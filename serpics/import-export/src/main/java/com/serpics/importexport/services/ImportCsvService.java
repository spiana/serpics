package com.serpics.importexport.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.zip.ZipFile;

import org.dom4j.DocumentException;

import com.opencsv.CSVParser;

public interface ImportCsvService {

	public void importCsv(Reader reader , Class<?> type);
	public void importCsv(Reader reader , Class<?> type , CSVParser parser);
	public void importCsv(Reader reader , Class<?> type ,int line, CSVParser parser);
	
	public void importFromXml(String fileName , String basePath) throws FileNotFoundException, DocumentException ;
	
	public void importFromZip(ZipFile file) throws IOException;
	public void importFromZip(String filePath) throws IOException;
}
