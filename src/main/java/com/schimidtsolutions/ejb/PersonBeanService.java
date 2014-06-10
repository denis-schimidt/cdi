package com.schimidtsolutions.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.schimidtsolutions.entity.Person;

@Stateless
public class PersonBeanService implements PersonService {

	@PersistenceContext(unitName="estudo")
	private EntityManager em;
	
	@TransactionAttribute( TransactionAttributeType.SUPPORTS )
	@Override
	public Person findById( Integer id ) {
		Person person = em.find( Person.class, id );
		
		if( person != null ) {
			return person;
		}
		
		return  new Person();
	}

	@Override
	public void update( Person person ) {
		em.merge( person );
	}

	@Override
	public Person insert( Person person ) {
		em.persist( person );
		
		return person;
	}

	@Override
	public void delete( Integer id ) {
		em.remove( findById( id ) );
	}
}
