package com.schimidtsolutions.rest;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CarResourceTest {
	private Undertow server;
	
	@BeforeClass
	private void init(){
		server = Undertow.builder()
				.addHttpListener(8080, "localhost")
				.setHandler( new HttpHandler() {
					
					@Override
					public void handleRequest(HttpServerExchange exchange) throws Exception {
						exchange.getResponseHeaders().put( Headers.CONTENT_TYPE, MediaType.APPLICATION_XML );
					}
					
				} ).build();
        server.start();
	}
	
	@Test( dataProvider="add-cars")
	public void testAddCar( String xml, int codeExpected ) {
		Client client = new ResteasyClientBuilder().build();
			
		Response response = client.target( "http://localhost:8080/estudo-rest/rest/cars/add" )
			.request().put( Entity.xml( xml ) );
			
		Assert.assertEquals( codeExpected, response.getStatus() );
			
		response.close();
	}
		
	@DataProvider(name="add-cars")
	private Object[][] createXML(){
		return new Object[][]{
				{ "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><car><id>4</id><description>Kadet</description></car>", Response.Status.OK.getStatusCode() },
				{ "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><car><id>5</id><description>Polo</description></car>", Response.Status.OK.getStatusCode() }
		};
	}
	
	@AfterClass
	private void end(){
		server.stop();
	}
}
