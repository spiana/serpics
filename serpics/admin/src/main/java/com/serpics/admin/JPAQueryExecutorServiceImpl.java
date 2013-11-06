package com.serpics.admin;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Scope("prototype")
public class JPAQueryExecutorServiceImpl implements JPAQueryExecutorService {
	
	
	@Override
	@Transactional(readOnly=true)
	public List<?> getResultList(Query q) {
		return q.getResultList();
	}



}
