package com.schimidtsolutions.ejb;

import javax.ejb.Local;

import com.schimidtsolutions.entity.Person;

@Local
public interface PersonService{

	public Person findById( Integer id );
	
	public void update( Person person );
	
	public Person insert( Person person );
	
	public void delete( Integer id );
}
