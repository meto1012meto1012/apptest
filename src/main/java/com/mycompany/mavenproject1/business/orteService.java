/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.mavenproject1.business;

//
import com.mycompany.mavenproject1.domain.orte;

import java.io.Serializable;
import java.util.List;
//import java.util.stream.Collectors;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
//import org.jboss.ejb3.annotation.SecurityDomain;
/**
 *
 * @author tobias
 */

    
@Stateless
public class orteService implements Serializable {
	private static final long serialVersionUID = 5292529185811096603L;
	
	@Inject
	private transient EntityManager em;
	
	/**
	 * Verfuegbare Orte ermitteln
	 * @return Liste der verfuegbaren Artikel
	 */
	@PermitAll
	public List<orte> findOrte() {
		return em.createNamedQuery(orte.FIND_orte, orte.class)
				 .getResultList();
	}	
}

    

