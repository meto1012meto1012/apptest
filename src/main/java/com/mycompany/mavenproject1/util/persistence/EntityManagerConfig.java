
package com.mycompany.mavenproject1.util.persistence;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Dependent
public class EntityManagerConfig {
	@PersistenceContext
	@Produces
	private EntityManager em;
}
