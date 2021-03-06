package com.schimidtsolutions.rest;

import javax.inject.Inject;
import javax.interceptor.ExcludeClassInterceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Form;

import com.schimidtsolutions.ejb.UserService;
import com.schimidtsolutions.interceptor.annotations.Tracked;
import com.schimidtsolutions.model.Contact;
import com.schimidtsolutions.model.User;

@Path("contact")
@Tracked
public class ContactResource {

	@Inject
	private UserService userService;

	@Path("add/user")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add( @Form User user ) {
		userService.addUser( user );

		return Response.ok( userService.getUser().getId() ).build();
	}

	@Path("add/user/contact")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add( @Form Contact contact ) {
		userService.addContact( contact );
		
		return Response.ok().build();
	}

	@Path("this")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ExcludeClassInterceptors
	public Response get() {
		return Response.ok( userService.getUser() ).build();
	}
}
