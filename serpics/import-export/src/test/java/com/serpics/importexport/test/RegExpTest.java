package com.serpics.importexport.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class RegExpTest {

	public static void main(String[] args) {
		
		
	//	String column = "col1[ unique ](code){it}";
		String column = "col1[ unique ]";
		
		String result = StringUtils.removePattern(column , "\\[.*\\]\\(.*\\)\\{.*\\}");
	
		System.out.println(result);
		
		Pattern p =Pattern.compile("\\[.*\\]");
		
		Matcher m = p.matcher(column);
		if (m.find()){
			System.out.println(m.group().substring(1 , m.group().length()-1).trim());
		}
		
		Pattern p1 =Pattern.compile("\\(.*\\)");
		
		Matcher m1 = p1.matcher(column);
		if (m1.find()){
			System.out.println(m1.group());
		}
		
		Pattern p2 =Pattern.compile("\\{.*\\}");
		
		Matcher m2 = p2.matcher(column);
		if (m2.find()){
			System.out.println(m2.group());
		}
	}

}
