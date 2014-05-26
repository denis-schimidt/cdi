package com.schimidtsolutions.rest.helper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class ParserXML {

	public static <E> E fromXML( String xml, Class<E> clazz ){
		return JAXB.unmarshal( new ByteArrayInputStream( xml.getBytes( Charset.forName( "UTF-8" ) ) ), clazz );
	}
	
	public static <E> E fromXML( InputStream xml, Class<E> clazz ){
		return JAXB.unmarshal( xml, clazz );
	}
	
	public static String toXML( Object object ){
		StringWriter response = new StringWriter();
		
		try {
			JAXBContext context = JAXBContext.newInstance( object.getClass() );
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false );
			marshaller.marshal(object, response );
			 
		} catch (JAXBException e) {
			e.printStackTrace();	
		}
		
		return response.toString();
	}
}
