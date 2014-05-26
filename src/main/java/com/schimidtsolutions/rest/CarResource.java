package com.schimidtsolutions.rest;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.schimidtsolutions.interceptor.annotations.Tracked;
import com.schimidtsolutions.model.Car;
import com.schimidtsolutions.rest.helper.ParserXML;

@Path( "cars" )
@Tracked
public class CarResource {
	private static final Map<Integer, Car> DB = new ConcurrentHashMap<Integer, Car>();
	
	static{
		DB.put(1, new Car(1, "Fuscao Preto" ) );
		DB.put(2, new Car(2, "Astra SS" ) );
	}
	
	@Context
	private UriInfo uriInfo;
	
	
	@PUT
	@Path( "add" )
	@Consumes( MediaType.APPLICATION_XML )
	@Produces( MediaType.APPLICATION_XML )
	public Response add( InputStream is ){

		Car car = ParserXML.fromXML( is, Car.class );
		
		if( is == null || car == null || car.getId() == null ){
			return Response.status( Response.Status.BAD_GATEWAY ).build();
		}
		
		DB.put(car.getId(), car );
		
		URI uri = uriInfo.getAbsolutePathBuilder()
			.path( "/{id}" )
			.resolveTemplate("id", car.getId() ).build();
		
		return Response.created( uri ).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCars(){
	
		List<Car> cars = new ArrayList<>();
		cars.addAll( DB.values() );
		
		return Response.ok( cars ).link(uriInfo.getAbsolutePath(), "cars").build();
	}
}
