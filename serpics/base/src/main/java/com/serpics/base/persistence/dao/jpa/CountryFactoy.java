package com.serpics.base.persistence.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.serpics.base.persistence.Country;

public class CountryFactoy {
	
	
	
	public Country create(Country newCountry){
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("casper-core");
	EntityManager em = emf.createEntityManager();
	em.getTransaction().begin();
	em.persist(newCountry);
	em.getTransaction().commit();
	return newCountry;
	}
}
