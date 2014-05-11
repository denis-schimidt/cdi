package com.schimidtsolutions.interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.schimidtsolutions.interceptor.annotations.Proffiled;

@Interceptor
@Proffiled
@Priority( Interceptor.Priority.APPLICATION + 20 )
public class ProfillingInterceptor {
	
	@AroundInvoke
	public Object profile(InvocationContext ic) throws Exception {
		long initTime = System.currentTimeMillis();
		Object response = null;
		
		try {
			response = ic.proceed();
			
		} finally {
			long diffTime = System.currentTimeMillis() - initTime;
			System.out.println( ic.getMethod() + " takes " + diffTime + " millis");
		}
		
		return response;
	}
}
