package com.schimidtsolutions.oracle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.schimidtsolutions.entity.Person;


public class OracleJpaTest{
	private EntityManager em;
	private EntityTransaction transaction;
	private Person person;
	
	@BeforeClass
	private void init(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory( "estudo" );
		em = factory.createEntityManager();
		transaction = em.getTransaction();
	}
	
	@Test
	public void testInsert() {
		transaction.begin();
		person = new Person( "Teste", (short) 25 );
		em.persist(person);
		transaction.commit();
	}
	
	@Test(dependsOnMethods="testInsert") 
	public void testRetrieve() {
		Person person = getPerson();
		
		Assert.assertNotNull( person );
		Assert.assertEquals( person.getName(), "Teste" );
	}

	@Test(dependsOnMethods="testRetrieve") 
	public void testUpdate() {
		transaction.begin();
		person.setAge( (short) 50 ); 
		person.setName( "Teste 123...");
		em.merge( person );
		transaction.commit();
	}
	
	@Test(dependsOnMethods="testUpdate") 
	public void testDelete() {
		transaction.begin();
		em.remove( person );
		transaction.commit();
	}
	
	private Person getPerson() {
		return em.find( Person.class, person.getId() );
	}
	
	@AfterClass
	private void finish(){
		em.close();
	}
}
