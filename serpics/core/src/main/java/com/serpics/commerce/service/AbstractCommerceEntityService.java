package com.serpics.commerce.service;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.data.Repository;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.core.service.EntityService;
import com.serpics.core.session.SessionContext;

@Transactional(readOnly = true)
public abstract class AbstractCommerceEntityService<T, ID extends Serializable> extends 
					AbstractEntityService< T ,ID , CommerceSessionContext>  implements EntityService<T, ID>{

}
