package com.schimidtsolutions.rest.helper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.schimidtsolutions.model.Car;
import com.schimidtsolutions.rest.helper.ParserXML;

public class ParserXMLTest {

	@Test(dataProvider="xml")
	public void fromXML( String xml, Integer idExpected, String descriptionExpected ) {
		Car car = ParserXML.fromXML( xml, Car.class);
		
		Assert.assertNotNull( car );
		Assert.assertEquals( car.getId(), idExpected );
		Assert.assertEquals( car.getDescription(), descriptionExpected );
	}
	
	@Test(dataProvider="inputStream")
	public void fromXML( InputStream xml, Integer idExpected, String descriptionExpected ) {
		Car car = ParserXML.fromXML( xml, Car.class);
		
		Assert.assertNotNull( car );
		Assert.assertEquals( car.getId(), idExpected );
		Assert.assertEquals( car.getDescription(), descriptionExpected );
	}
	
	@Test(dataProvider="object")
	public void toXML( Object object, String xmlExpected ) {
		String xmlParsed = ParserXML.toXML( object );
		
		Assert.assertNotNull( xmlParsed );
		Assert.assertEquals( xmlParsed, xmlExpected );
	}
	
	@DataProvider(name="xml")
	private Object[][] createXML(){
		return new Object[][]{ 
			{"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><car>" +
				"<id>" + 1 + "</id>" +
				"<description>Fuscão Preto</description>" +
			 "</car>", 1, "Fuscão Preto"},
		
			{"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><car>" +
				"<id>" + 200 + "</id>" +
				"<description>Astra SS</description>" +
			"</car>", 200, "Astra SS"}
		};
	}
	
	@DataProvider(name="inputStream")
	private Object[][] createInputStream(){
		return new Object[][]{ 
			{ new ByteArrayInputStream( ( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><car>" +
				"<id>" + 1 + "</id>" +
				"<description>Fuscão Preto</description>" +
			 "</car>" ).getBytes( Charset.forName("UTF-8") ) ), 1, "Fuscão Preto"},
		
			{ new ByteArrayInputStream( ( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><car>" +
				"<id>" + 200 + "</id>" +
				"<description>Astra SS</description>" +
			"</car>" ).getBytes( Charset.forName("UTF-8") ) ), 200, "Astra SS"}
		};
	}
	
	@DataProvider(name="object")
	private Object[][] createObject(){
		return new Object[][]{
			{ new Car( 1, "Fuscão Preto" ), "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><car><id>1</id><description>Fuscão Preto</description></car>" }, 
			{ new Car( 200, "Astra SS" ), "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><car><id>200</id><description>Astra SS</description></car>" }
		};                              
	}
}
