package com.schimidtsolutions.interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;

import com.schimidtsolutions.interceptor.annotations.Loggable;

@Interceptor
@Loggable
@Priority( Interceptor.Priority.APPLICATION + 10 )
public class LoggingInterceptor {

	@AroundInvoke
	public Object logInvocation(InvocationContext ic) throws Exception {

		StringBuilder builder = new StringBuilder(String.format(
				" da chamada da classe %s, método %s(", ic.getTarget()
						.toString(), ic.getMethod().getName()));
		int parametersSize = 1;

		for (Object parameter : ic.getParameters()) {
			builder.append(parameter.toString());

			if (parametersSize < ic.getParameters().length) {
				builder.append(", ");
			}

			parametersSize++;
		}

		builder.append(")");

		System.out.printf("Antes %s\n", builder.toString());
		Response response = null;

		try {
			response = (Response) ic.proceed();

		} finally {
			System.out.printf("Depois %s - Retorno %s\n", builder.toString(),
					response.getEntity() != null ? response.getEntity()
							.toString() : "void");
		}
		
		return response;
	}
}
