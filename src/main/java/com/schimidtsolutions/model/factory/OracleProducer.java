package com.schimidtsolutions.model.factory;

import java.util.logging.Logger;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Alternative
public class OracleProducer {
	
	@Inject
	private Logger log;

	@Produces
	@PersistenceContext(unitName = "estudo")
	public EntityManager entityManager;


	public void close( @Disposes EntityManager em ){

		if( em.isOpen() ){
			em.close();
			
			log.info( "Fechando entity manager...");
		}
	}
}
