package com.serpics.importexport.services;

import java.io.Reader;

import com.opencsv.CSVParser;

public interface ImportCsvService {

	public void importCsv(Reader reader , Class<?> type);
	public void importCsv(Reader reader , Class<?> type , CSVParser parser);
	public void importCsv(Reader reader , Class<?> type ,int line, CSVParser parser);
}
