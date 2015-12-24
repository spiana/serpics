package com.serpics.importexport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
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
		foreignKey = new HashMap<String, String>(0);
		languageMapping = new HashMap<String, String>(0);
		List<String> _u = new ArrayList<String>(0);
		List<String> _c = new ArrayList<String>(0);

		for (String column : _h) {
			addHeader(column, _c);

			if (column.contains("[")) {
				addunique(column, _u);
			}
			if (column.contains("(")) {
				addConstraint(column, foreignKey);
			}
			if (column.contains("{")) {
				addLanguage(column, languageMapping);
			}
		}
		this.unique = _u.toArray(new String[_u.size()]);
		this.header = _c.toArray(new String[_c.size()]);
	}
	
	private String extractColumnName(String column){
		  column =StringUtils.removePattern(column , "\\[.*\\]");
		  column =StringUtils.removePattern(column , "\\(.*\\)");
		  column =StringUtils.removePattern(column , "\\{.*\\}");
	  return column;
	}
	
	private void addConstraint(String column , Map<String, String> constrain){
		String field = extractColumnName(column);
		Pattern p =Pattern.compile("\\(.*\\)");
		Matcher m = p.matcher(column);
		if (m.find()){
			String match = m.group();
			if(constrain == null)
				constrain = new HashMap<String, String>();
			constrain.put(field,match.substring(1, match.length()-1).trim());
		}
	}
	private void addLanguage(String column , Map<String, String> languages){
		String field = extractColumnName(column);
		Pattern p =Pattern.compile("\\{.*\\}");
		Matcher m = p.matcher(column);
		if (m.find()){
			String match = m.group();
			if(languages == null)
				languages = new HashMap<String, String>();
			languages.put(field,match.substring(1, match.length()-1).trim());
		}
		
	}
	
	private void addunique(String column , List<String> unique){
		Pattern p =Pattern.compile("\\[.*\\]");
		Matcher m = p.matcher(column);
		if (m.find()){
			String match = m.group();
			if(unique == null){
				unique = new ArrayList<String>();
			}
			if ("unique".equals(match.substring(1,match.length()-1).trim().toLowerCase()))
				unique.add(extractColumnName(column));
			}
		
		
	}
	
	private void addHeader(String column , List<String> header){
			header.add(extractColumnName(column));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T createBean(final String[] line) throws InstantiationException, IllegalAccessException {
		if (unique.length <1)
			return super.createBean();
		
		Repository r = RepositoryInitializer.getInstance()
				.getRepositoryForEntity(type);
		
		Object _v = r.findOne(new Specification() {

			@Override
			public Predicate toPredicate(Root arg0, CriteriaQuery arg1,
					CriteriaBuilder arg2) {
				
				List<Predicate> predicates = new ArrayList<Predicate>();
				for (String code : unique) {
					Predicate p = null;
					if (foreignKey.containsKey(code)){
						p = arg2.equal(arg0.join(code).get(foreignKey.get(code)),line[getColumnIndex(code)]);
					}else	{
						 p = arg2.equal(arg0.get(code), line[getColumnIndex(code)]);
				}
					predicates.add(p);
				}
				
				return arg2.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
		
		if (_v == null)
			return super.createBean();
		else
			return (T) _v;
	}
}
