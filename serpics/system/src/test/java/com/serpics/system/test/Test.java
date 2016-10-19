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
package com.serpics.system.test;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Test {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub

	DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
	InputStream i = Test.class.getClassLoader().getResourceAsStream("META-INF/test-smc.xml")	;
	Document d = df.newDocumentBuilder().parse(i);
	NodeList l = d.getElementsByTagName("class");
	for (int x = 0 ; x < l.getLength() ;x++){
		Node n = l.item(x);
		NodeList l1 = n.getChildNodes();
		for (int xx = 0 ; xx < l1.getLength() ;xx++){
			Node nn = l1.item(xx);
			System.out.println(nn.getNodeName());
		}
	}
	}
}
