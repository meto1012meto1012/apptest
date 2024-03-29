

package com.mycompany.mavenproject1.util;


public abstract class AbstractShopException extends RuntimeException {
	private static final long serialVersionUID = -1030863258479949134L;
	
	public AbstractShopException(String msg) {
		super(msg);
	}

	public AbstractShopException(String msg, Throwable t) {
		super(msg, t);
	}

	public abstract String getMessageKey();
}
