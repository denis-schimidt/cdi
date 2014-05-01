package com.schimidtsolutions.log;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggingProducer {

	@Produces
	public Logger createLogger( final InjectionPoint injectionPoint ){
		return Logger.getLogger( injectionPoint.getMember().getDeclaringClass().getName() );
	}
}
