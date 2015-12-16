package com.serpics.importexport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.opencsv.CSVReader;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.serpics.core.data.Repository;
import com.serpics.core.data.RepositoryInitializer;

public class SerpicsMapperStrategy<T> extends HeaderColumnNameMappingStrategy<T> {
	
	protected String[] unique ;
	protected Map<String,String> foreignKey ;
	protected Map<String,String> languageMapping ;
	
	
	@Override
	public void captureHeader(CSVReader reader) throws IOException {
		super.captureHeader(reader);
		
		String[] _h = this.header;
		Map<String,String> _f = new HashMap<String,String>();
		Map<String,String> _l = new HashMap<String,String>();
		List<String> _u = new ArrayList<String>(0);
		List<String> _c = new ArrayList<String>(0);
		
		for (String column : _h) {
			if(column.contains("[unique]")){
				_c.add(0,column.substring(0,column.indexOf("[")));
				_u.add(0,column.substring(0,column.indexOf("[")));
			}else if (column.contains("(")){
				_c.add(column.substring(0,column.indexOf("(")));
				_f.put(column.substring(0,column.indexOf("(")) , column.substring(column.indexOf("(")+1 , column.indexOf(")") ));
			}else if (column.contains("{")){
				_c.add(column.substring(0,column.indexOf("{")));
				_l.put(column.substring(0,column.indexOf("{")) , column.substring(column.indexOf("{")+1 , column.indexOf("}") ));
			}else
				_c.add(column);
		}
		this.foreignKey = _f;
		this.languageMapping = _l;
		this.unique = _u.toArray(new String[_u.size()]);
		this.header = _c.toArray(new String[_c.size()]);
		
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })

	public T createBean(final String[] line) throws InstantiationException, IllegalAccessException {
		if (unique.length <1)
			return super.createBean();
		
		final int colIndex = getColumnIndex(unique[0]);
		
		Repository r = RepositoryInitializer.getInstance()
				.getRepositoryForEntity(type);
		
		Object _v = r.findOne(new Specification() {

			@Override
			public Predicate toPredicate(Root arg0, CriteriaQuery arg1,
					CriteriaBuilder arg2) {
				
				return arg2.equal(arg0.get(unique[0]), line[colIndex]);
			}
		});
		
		if (_v == null)
			return super.createBean();
		else
			return (T) _v;
	}
}
