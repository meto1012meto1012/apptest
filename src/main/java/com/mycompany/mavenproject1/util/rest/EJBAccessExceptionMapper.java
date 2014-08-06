

package com.mycompany.mavenproject1.util.rest;

import com.mycompany.mavenproject1.util.interceptor.Log;
import javax.ejb.EJBAccessException;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.FORBIDDEN;


@Provider
@ApplicationScoped
@Log
public class EJBAccessExceptionMapper implements ExceptionMapper<EJBAccessException> {
	@Override
	public Response toResponse(EJBAccessException e) {
		return Response.status(FORBIDDEN).build();
	}	
}
