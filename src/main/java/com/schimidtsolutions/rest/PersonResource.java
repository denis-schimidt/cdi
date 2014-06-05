package com.schimidtsolutions.rest;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.schimidtsolutions.entity.Person;

@Path("/persons")
public class PersonResource {
	
	@Inject
	private EntityManager em;

	@GET
	@Path( "/{id}")
	@Produces( MediaType.APPLICATION_JSON )
	public Person getPersonById( @PathParam("id") Integer id ){
		return em.find( Person.class, id );
	}
}
