package com.schimidtsolutions.entity.factory;

import java.util.logging.Logger;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class EntityManagerProducer {
	
	@Inject
	private Logger log;
	
	@PersistenceUnit(unitName="estudo")
	public EntityManagerFactory factory;

	@Produces
	public EntityManager create(){
		EntityManager entityManager = factory.createEntityManager();
		
		log.info( "Criando entity manager...");
		
		return entityManager;
	}

	public void close( @Disposes EntityManager em ){

		if( em.isOpen() ){
			em.close();
			
			log.info( "Fechando entity manager...");
		}
	}
}
