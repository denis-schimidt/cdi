package com.schimidtsolutions.util.factory;

import java.util.Random;
import java.util.logging.Logger;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class NumberProducer {

	@Inject
	private Logger log;
	
	@Produces
	private String prefix13digits = "13-";
	
	@Produces
	private int editorNumber = 84356;
	
	@Produces
	public double random(){
		return Math.abs( new Random().nextInt() );
	}
	
	public void destroyRandom( @Disposes double number ){
		log.info( "Destruindo número " + number );
	}
}
