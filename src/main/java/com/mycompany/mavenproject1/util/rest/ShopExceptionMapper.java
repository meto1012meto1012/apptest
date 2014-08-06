

package com.mycompany.mavenproject1.util.rest;

import com.mycompany.mavenproject1.util.AbstractShopException;
import com.mycompany.mavenproject1.util.interceptor.Log;
import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.util.logging.Level.WARNING;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;



@Provider
@ApplicationScoped
@Log
public class ShopExceptionMapper implements ExceptionMapper<AbstractShopException> {
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
	
	@Override
	public Response toResponse(AbstractShopException e) {
		LOGGER.log(WARNING, "Kein ExceptionMapper fuer die Exception " + e.getClass().getSimpleName(), e);
		final Response response = Response.status(INTERNAL_SERVER_ERROR)
		                                  .build();
		return response;
	}

}
