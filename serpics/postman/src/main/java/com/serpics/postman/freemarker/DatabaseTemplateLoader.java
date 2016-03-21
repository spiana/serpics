package com.serpics.postman.freemarker;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import com.serpics.postman.model.TemplateStore;
import com.serpics.postman.repositories.TemplateStoreRepository;

import freemarker.cache.TemplateLoader;
import freemarker.template.TemplateNotFoundException;

/**
 * custom implementation of the freemarker template loader to exploit 
 * the cache mechanism implemented in the template engine framework.  
 * 
 * @author alessandro.marasco@tinvention.net
 *
 */
public class DatabaseTemplateLoader implements TemplateLoader {

	@Autowired
	private TemplateStoreRepository templateStoreRepository;

	@Override
	public void closeTemplateSource(Object arg0) throws IOException {
		// NOOP
	}

	@Override
	public Object findTemplateSource(final String arg0) throws IOException {

		TemplateStore templateStore = templateStoreRepository.findOne(new Specification<TemplateStore>() {

			@Override
			public Predicate toPredicate(Root<TemplateStore> paramRoot, CriteriaQuery<?> paramCriteriaQuery,
					CriteriaBuilder metaDataMailRepository) {
				return metaDataMailRepository.equal(paramRoot.get("uuid"), arg0);
			}
		});
		if (templateStore == null || StringUtils.isEmpty(templateStore.getTemplateMail())) {
			throw new TemplateNotFoundException(arg0, null, "No Template found for Store in context");
		}

		return templateStore;
	}

	@Override
	public long getLastModified(Object arg0) {
		Assert.isInstanceOf(TemplateStore.class, arg0, "Template in use is not a Template Store instance");
		TemplateStore template = (TemplateStore)arg0;
		
		template = templateStoreRepository.findOne(template.getId());
		return template.getUpdated().getTime();
	}

	@Override
	public Reader getReader(Object template, String locale) throws IOException {
		Assert.isInstanceOf(TemplateStore.class, template, "Template in use is not a Template Store instance");
		return new StringReader(((TemplateStore)template).getTemplateMail());
	}

}
