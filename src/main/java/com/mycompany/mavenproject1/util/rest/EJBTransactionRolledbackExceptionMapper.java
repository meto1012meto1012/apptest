

package com.mycompany.mavenproject1.util.rest;

import com.mycompany.mavenproject1.util.interceptor.Log;
import javax.ejb.EJBAccessException;
import javax.ejb.EJBTransactionRolledbackException;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.FORBIDDEN;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;


@Provider
@ApplicationScoped
@Log
public class EJBTransactionRolledbackExceptionMapper implements ExceptionMapper<EJBTransactionRolledbackException> {
	@Override
	public Response toResponse(EJBTransactionRolledbackException e) {
		final Throwable cause = e.getCause();
		if (cause != null && cause.getClass().equals(EJBAccessException.class)) {
			return Response.status(FORBIDDEN).build();
		}
		
		return Response.status(INTERNAL_SERVER_ERROR).build();
	}
}
