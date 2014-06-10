package com.schimidtsolutions.rest;

import io.undertow.Undertow;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CarResourceTest {
	private UndertowJaxrsServer server;
	
	@BeforeClass
	public void init() throws Exception {
		server = new UndertowJaxrsServer();
		
		server.start( Undertow.builder().addHttpListener(8080, "localhost") );
		server.deploy(ApplicationForTest.class);
	}
	
	@Test( dataProvider="add-cars")
	public void testAddCar( String xml, int codeExpected ) {
		
		Client client = ResteasyClientBuilder.newClient();
				
		Response response = client.target( "http://localhost:8080/estudo-rest/rest/cars/add" )
			.request().put( Entity.xml( xml ) );
			
		Assert.assertEquals( codeExpected, response.getStatus() );
			
		response.close();
	}
		
	@DataProvider(name="add-cars")
	private Object[][] createXML(){
		return new Object[][]{
				{ "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><car><id>4</id><description>Kadet</description></car>", Response.Status.CREATED.getStatusCode() },
				{ "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><car><id>5</id><description>Polo</description></car>", Response.Status.CREATED.getStatusCode() },
				{ "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><car><id></id><description>Polo</description></car>", Response.Status.BAD_REQUEST.getStatusCode() }
		};
	}
	
	@AfterClass
	private void end(){
		server.stop();
	}
}
