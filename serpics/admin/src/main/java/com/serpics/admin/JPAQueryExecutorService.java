package com.serpics.admin;

import java.util.List;

import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface JPAQueryExecutorService {

	@Transactional
	public List<?> getResultList(Query q);
	
}
