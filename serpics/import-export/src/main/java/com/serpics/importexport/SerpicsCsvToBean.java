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
package com.serpics.importexport;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import javax.persistence.Entity;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.MappingStrategy;
import com.serpics.base.MultiValueField;
import com.serpics.base.Multilingual;
import com.serpics.core.data.Repository;
import com.serpics.core.data.RepositoryInitializer;

public class SerpicsCsvToBean<T> extends CsvToBean<T> {

	SerpicsMapperStrategy<T> mapper;

	@Override
	@SuppressWarnings("rawtypes")
	protected Object convertValue(final String value,
			final PropertyDescriptor prop) throws InstantiationException,
			IllegalAccessException {

		 if (prop.getPropertyType().getAnnotation(Entity.class) != null && !Multilingual.class.isAssignableFrom(prop.getPropertyType())) {
			Repository r = RepositoryInitializer.getInstance()
					.getRepositoryForEntity(prop.getPropertyType());
			@SuppressWarnings("unchecked")
			Object _v = r.findOne(new Specification() {
				@Override
				public Predicate toPredicate(Root arg0, CriteriaQuery arg1,
						CriteriaBuilder arg2) {
					return arg2.equal(
							arg0.get(mapper.foreignKey.get(prop.getName())),
							value);
				}
			});
			if (_v == null)
				throw new RuntimeException(String.format(
						"invalid reference for property [%s] with value [%s]",
						prop.getName(), value));
			return _v;
		}
		return super.convertValue(value, prop);

	}

	@Override
	protected T processLine(MappingStrategy<T> mapper, String[] line) throws IllegalAccessException, InvocationTargetException,
			InstantiationException, IntrospectionException {
		if (this.mapper == null)
			this.mapper = (SerpicsMapperStrategy) mapper;
	
		T bean = this.mapper.createBean(line);

		for (int col = 0; col < line.length; ++col) {
			processProperty(mapper, line, bean, col);
		}

		return bean;
	}

	private void processProperty(MappingStrategy<T> mapper, String[] line, T bean, int col) throws IntrospectionException,
			InstantiationException, IllegalAccessException, InvocationTargetException {
		PropertyDescriptor prop = mapper.findDescriptor(col);
		if (null != prop) {
			String value = checkForTrim(line[col], prop);
			Object obj = convertValue(value, prop);
			if (Multilingual.class.isAssignableFrom(prop.getPropertyType())){
				Multilingual m = (Multilingual) prop.getReadMethod().invoke(bean, null);
				if (m != null)
					m.addText(this.mapper.languageMapping.get(this.mapper.getColumnName(col)), value);
				else
					prop.getWriteMethod().invoke(bean, new Object[] { addMultilingualFiled(this.mapper.languageMapping.get(this.mapper.getColumnName(col)), value, prop) });
				
			}else if (MultiValueField.class.isAssignableFrom(prop.getPropertyType())){
				MultiValueField m = (MultiValueField) prop.getReadMethod().invoke(bean, null);
				if (m != null)
					m.setStringValue(value);
				else
					prop.getWriteMethod().invoke(bean, new Object[] { addMultivalueField(value, prop) });
			}else
				prop.getWriteMethod().invoke(bean, new Object[] { obj });
		}

	}
	
	private MultiValueField addMultivalueField(String text , PropertyDescriptor prop) throws IntrospectionException,
		InstantiationException, IllegalAccessException, InvocationTargetException{
		MultiValueField m = (MultiValueField) prop.getPropertyType().newInstance();
		m.setStringValue(text);
		return m;
	}
	private Multilingual addMultilingualFiled(String language , String text , PropertyDescriptor prop) throws IntrospectionException,
	InstantiationException, IllegalAccessException, InvocationTargetException{
		Multilingual m = (Multilingual) prop.getPropertyType().newInstance();
		
		m.addText(language, text);
		return m;
	}
}
