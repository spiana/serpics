package com.serpics.core.facade;

import java.util.List;

import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public  class  ConvertUtil{
	private static Logger logger = LoggerFactory.getLogger(ConvertUtil.class);

	public static <T> Object convert(Root<T> root, Object target) {
		return target;
	}
	
	public static <TARGET, SOURCE>  List<TARGET> listCovert(List<SOURCE> source , AbstractConverter<SOURCE, TARGET> converter ){
	
		return null;
	}
}
