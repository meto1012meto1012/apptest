

package com.mycompany.mavenproject1.util.persistence;

import com.mycompany.mavenproject1.util.AbstractShopException;
import javax.ejb.ApplicationException;


@ApplicationException(rollback = true)
public class ConcurrentDeletedException extends AbstractShopException {
	private static final long serialVersionUID = 3061376326827538106L;
	private static final String MESSAGE_KEY = "persistence.concurrentDelete";
	
	private final Object id;

	public ConcurrentDeletedException(Object id) {
		super("Das Objekt mit der ID " + id + " wurde konkurrierend geloescht");
		this.id = id;
	}
	
	public Object getId() {
		return id;
	}
	
	@Override
	public String getMessageKey() {
		return MESSAGE_KEY;
	}
}
