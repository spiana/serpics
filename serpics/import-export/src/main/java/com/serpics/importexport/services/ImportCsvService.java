/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.importexport.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.zip.ZipFile;

import org.dom4j.DocumentException;

import com.opencsv.CSVParser;

public interface ImportCsvService {

	public interface ImportProgressListener{
		public void init();
		public void process(int curentRecord , int maxrecord);
		public void end();
		
	}
	public void importCsv(Reader reader , Class<?> type );
	public void importCsv(Reader reader , Class<?> type , CSVParser parser);
	
	public void importCsv(Reader reader , Class<?> type , ImportProgressListener listener);
	public void importCsv(Reader reader , Class<?> type , CSVParser parser , ImportProgressListener listener);
	public void importCsv(Reader reader , Class<?> type ,int line, CSVParser parser , ImportProgressListener listener);
	
	public void importFromXml(String fileName , String basePath) throws FileNotFoundException, DocumentException ;
	public void importFromXml(String fileName , String basePath , ImportProgressListener listener ) throws FileNotFoundException, DocumentException ;
	
	public void importFromZip(String filePath) throws IOException;
	
	public void importFromZip(ZipFile file ,  ImportProgressListener listener) throws IOException;
	public void importFromZip(String filePath , ImportProgressListener listener) throws IOException;
	
	
}
