

package com.mycompany.mavenproject1.util.rest;

import com.mycompany.mavenproject1.util.interceptor.Log;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.CONFLICT;


/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@Provider
@ApplicationScoped
@Log
public class OptimisticLockExceptionMapper implements ExceptionMapper<OptimisticLockException> {
	private static final String MESSAGE_KEY = "persistence.concurrentUpdate";
	
	@Context
	private HttpHeaders headers;
	
	@Inject
	private Messages messages;
	
	@Inject
	private EntityManager em;
	
	@Override
	public Response toResponse(OptimisticLockException e) {
		final Object id = e.getEntity() == null
				          ? null
				          : em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(e.getEntity());
		final String msg = messages.getMessage(headers, MESSAGE_KEY, id);
		final Response response = Response.status(CONFLICT)
		                                  .type(TEXT_PLAIN)
		                                  .entity(msg)
		                                  .build();
		return response;
	}
}
