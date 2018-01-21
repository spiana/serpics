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
package com.serpics.catalog.services;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import com.serpics.base.commerce.Customer;
import com.serpics.base.commerce.session.CommerceSessionContext;
import com.serpics.base.data.model.Store;
import com.serpics.catalog.data.model.MemberPricelistRelation;
import com.serpics.catalog.data.model.Pricelist;
import com.serpics.catalog.data.repositories.MemberPricelistRepository;
import com.serpics.catalog.data.repositories.PriceListRepository;
import com.serpics.core.service.AbstractService;
import com.serpics.membership.data.model.Member;
import com.serpics.stereotype.StoreService;


/**
 * @author spiana
 *
 */
@StoreService("priceListService")
public class PriceListserviceImpl extends AbstractService<CommerceSessionContext> implements PriceListService{
	public transient static String DEFAULT_LIST_NAME = "default-list";
	public transient static String SESSION_PRICELIST = "session.pricelist";

	@Resource
	private PriceListRepository priceListRepository;
	
	@Resource
	private MemberPricelistRepository memberPriceListRelation;
	
	
	
	/* (non-Javadoc)
	 * @see com.serpics.catalog.services.PriceListService#getDefaultPriceList()
	 */
	@Override
	public Pricelist getCurrentPriceList() {
		Pricelist currentPricelist = (Pricelist) getCurrentContext().getAttribute(SESSION_PRICELIST);
		
		if (currentPricelist == null)
			currentPricelist = getCurrentUserPricelist(); // try get priceList specific per user
		
		if (currentPricelist ==  null)
			currentPricelist=getStoreDefaultPricelist(); // get default priceList
		
		setCurrentPriceList(currentPricelist); // set current priceList in session
		
		return currentPricelist;
	}
	
	protected Pricelist getCurrentUserPricelist(){
		CommerceSessionContext context = getCurrentContext();
		Customer customer = context.getCustomer();
		if (customer instanceof Member){
			List<MemberPricelistRelation> relations = memberPriceListRelation.findAll(getMemberPricelistRelationSpec((Member) customer));
			if (!relations.isEmpty())
				return relations.get(0).getPriceList();
		}
			
		return null;
	}

	protected Pricelist getStoreDefaultPricelist(){
		Store store = (Store) getCurrentContext().getStoreRealm();
		List<Pricelist> l = priceListRepository.findDefaultList(store);
		assert !l.isEmpty() : "missing default price list !";
        return l.get(0);
	}
	
	protected Specification<MemberPricelistRelation> getMemberPricelistRelationSpec(final Member member){
		return new Specification<MemberPricelistRelation>() {
			@Override
			public Predicate toPredicate(Root<MemberPricelistRelation> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
			
				return cb.equal(root.get("member") , member);
			}
		};
	}

	@Override
	public void setCurrentPriceList(Pricelist pricelist) {
		Assert.notNull(pricelist);
		getCurrentContext().setAttribute(SESSION_PRICELIST, pricelist);
	}
}
