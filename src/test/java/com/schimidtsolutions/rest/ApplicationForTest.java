package com.schimidtsolutions.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.schimidtsolutions.ejb.PersonService;

@ApplicationPath("/estudo-rest/rest")
public class ApplicationForTest extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(CarResource.class);
		classes.add(PersonResource.class);
		
		return classes;
	}
	
	 @Override
	public Set<Object> getSingletons() {
		Set<Object> classes = new HashSet<>();
		EntityManager entityManager = Persistence.createEntityManagerFactory( "estudo" ).createEntityManager();
		classes.add( entityManager );

		Properties propertiesJNDI = new Properties();
		Path path = Paths.get( "C:\\Users\\Schimidt\\workspace-spring\\estudo-rest\\src\\test\\resources\\META-INF\\jndi.properties" );
		
		try {
			propertiesJNDI.load( Files.newInputStream(path) );
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	    EJBContainer container = EJBContainer.createEJBContainer( propertiesJNDI );
	    PersonService service;
	    
		try {
			service = (PersonService)container.getContext().lookup("java:global/estudo-rest/PersonBeanService!com.schimidtsolutions.ejb.PersonService");
			classes.add( service );
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return classes;
	}
}
