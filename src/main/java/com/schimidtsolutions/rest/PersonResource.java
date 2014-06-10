package com.schimidtsolutions.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.schimidtsolutions.ejb.PersonService;
import com.schimidtsolutions.entity.Person;

@Path("/persons")
public class PersonResource {
	
	@Context
	private UriInfo uriInfo;
	
	@EJB
	private PersonService personService;

	@GET
	@Path( "/{id}")
	@Produces( MediaType.APPLICATION_JSON )
	public Response getPersonById( @PathParam("id") Integer id ){
		 Person person = personService.findById( id );
		 
		 return Response.ok( person )
				 .links( createLink(person) )
				 .build();
	}
	
	@PUT
	@Path( "/update")
	@Consumes( MediaType.APPLICATION_JSON )
	@Produces( MediaType.APPLICATION_JSON )
	public Response update( Person person ){
		personService.update( person );
		
		return Response.ok( person )
				.links( createLink(person) )
				.build();
	}

	
	@POST
	@Path( "/add")
	@Consumes( MediaType.APPLICATION_JSON )
	@Produces( MediaType.APPLICATION_JSON )
	public Response insert( Person person ){
		personService.insert( person );
		
		return Response.created( createLink(person).getUri() ).build();
	}

	
	@DELETE
	@Path( "/{id}")
	@Produces( MediaType.APPLICATION_JSON )
	public Response delete( @PathParam("id") Integer id ){
		personService.delete( id );
		
		return Response.ok().build();
	}
	
	private Link createLink(Person person) {
		return Link.fromUri( uriInfo.getBaseUri().toString().concat( "persons/{id}" ) )
				.rel( "self" ).build( person.getId() );
	}
}
