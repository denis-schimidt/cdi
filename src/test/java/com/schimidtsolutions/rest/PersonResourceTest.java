package com.schimidtsolutions.rest;

import io.undertow.Undertow;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.schimidtsolutions.entity.Person;

public class PersonResourceTest { 
	private UndertowJaxrsServer server;
	
	@BeforeClass
	public void init() throws Exception {
		server = new UndertowJaxrsServer();
		
		server.start( Undertow.builder().addHttpListener(8080, "localhost") );
		server.deploy(ApplicationForTest.class);
	}
	
	@Test( dataProvider="add")
	public void testAddCar( String json, int codeExpected ) {
		
		Client client = new ResteasyClientBuilder().build();
			
		Response response = client.target( "http://localhost:8080/estudo-rest/rest/persons/add" )
			.request().post( Entity.json( json ) );
			
		Assert.assertEquals( codeExpected, response.getStatus() );
			
		response.close();
	}
		
	@DataProvider(name="add")
	private Object[][] createJson() throws JAXBException{
		Person person = new Person( "Teteu", (short) 71 );
		
		String json = transformToJson(person);
		
		return new Object[][]{ { json, Response.Status.CREATED.getStatusCode() } };
	}

	private String transformToJson(Person person) throws JAXBException {
		//Seria muito mais fácil com GSON (1 linha), mas vamos usar JSR-353 (Json-P)
		StringWriter writer = new StringWriter();
		JsonGenerator generator = Json.createGenerator(writer)
			.writeStartObject()
				.writeStartObject( "person");
		
		if( person.getId() != null ) {
			generator.write( "id", person.getId() );
			
		}else {
			generator.writeNull( "id" );
		}
		
		generator.write( "name", person.getName() )
				.write( "age", person.getAge() )
					.writeEnd()
						.writeEnd();	
		
		generator.close();
		
		return writer.toString().trim();
	}
	
	@AfterClass
	private void end(){
		server.stop();
	}
}
