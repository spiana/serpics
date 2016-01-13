package com.serpics.initialsetup.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.junit.Before;
import org.junit.Test;

public class TestComparator {

	private List<DataTest> elenco;
	@Before
	public void setUp(){
		elenco = new ArrayList<DataTest>();
		elenco.add(new DataTest("AAA","AAA",10));
		elenco.add(new DataTest("AAA","ABB",12));
		elenco.add(new DataTest("ZZZ","AAA",15));
		elenco.add(new DataTest("AAA","AAA",8));
		elenco.add(new DataTest("AAA","ABB",1));
		elenco.add(new DataTest("AAA","AAA",10));
		elenco.add(new DataTest("BBB","AAA",1));
		elenco.add(new DataTest("AAA","AAA",1));
		elenco.add(new DataTest("AAA","AAA",-1));
		elenco.add(new DataTest("AAA","ABB",1));
		elenco.add(new DataTest("BBB","AAA",1));
		elenco.add(new DataTest("ZZZ","AAA",1));
		
	}
	
	@Test
	public void test() {
		Collections.sort(elenco, new Comparator<DataTest>() {

			@Override
			public int compare(DataTest o1, DataTest o2) {
				return new CompareToBuilder().append(o1.getUno(), o2.getUno()).append(o1.getDue(), o2.getDue()).append(o1.getOrder(), o2.getOrder()).toComparison();
			}
		});
		
		for(DataTest data : elenco){
			System.out.println(data.toString());
		}
	}

}

class DataTest{
	private String uno;
	private String due;
	private int order;
	
	public String getUno() {
		return uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

	public String getDue() {
		return due;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public DataTest(String uno, String due, int order) {
		super();
		this.uno = uno;
		this.due = due;
		this.order = order;
	}

	@Override
	public String toString() {
		return "DataTest [uno=" + uno + ", due=" + due + ", order=" + order + "]";
	}
	
	
}
