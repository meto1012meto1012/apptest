/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.mavenproject1.ressource;

import com.mycompany.mavenproject1.business.orteService;
import com.mycompany.mavenproject1.domain.orte;
import com.mycompany.mavenproject1.util.rest.UriHelper;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author tobias
 */

@Path("/orte")
@Stateless
public class orteRessource {
    
    private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
	
	public static final String ORTE_PARAM = "id";
	
	@Context
	private UriInfo uriInfo;
	
	@Inject
	private orteService os;
	
	@Inject
	private UriHelper uriHelper;
	
	@Inject
	private HttpServletRequest request;
        
        @GET
	@Path("{" + ORTE_PARAM + ":[1-9][0-9]*}")
	public Response findOrte(@PathParam("id")) {
		final Orte orte = os.findOrte();
		return Response.ok(orte)
	                   .links(getTransitionalLinks(orte, uriInfo))
	                   .build();
	}
}
