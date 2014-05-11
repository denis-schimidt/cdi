package com.schimidtsolutions.interceptor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Priority;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.schimidtsolutions.interceptor.annotations.ObjectObservable;

@Interceptor
@ObjectObservable
@Priority( Interceptor.Priority.APPLICATION )
public class ObjectInterceptor {

	@PostConstruct
	private Object init(InvocationContext ic) throws Exception {
		System.out.println( String.format( "Creating object... %s", ic.getTarget() ) );
		
		return ic.proceed();
	}
	
	@PreDestroy
	private Object finalize( InvocationContext ic ) throws Exception{
		System.out.println( String.format( "Destroing object... %s", ic.getTarget() ) );
		
		return ic.proceed();
	}
}
