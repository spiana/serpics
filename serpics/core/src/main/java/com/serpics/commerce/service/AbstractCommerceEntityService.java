package com.serpics.commerce.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.serpics.commerce.session.CommerceSessionContext;
import com.serpics.core.service.AbstractEntityService;
import com.serpics.core.service.EntityService;

@Transactional(readOnly = true)
public abstract class AbstractCommerceEntityService<T, ID extends Serializable> extends 
					AbstractEntityService< T ,ID , CommerceSessionContext>  implements EntityService<T, ID>{

}
