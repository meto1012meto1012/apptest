/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.mavenproject1.business;

import com.mycompany.mavenproject1.util.AbstractShopException;
/**
 *
 * @author tobias
 */
public abstract class AbstractOrteServiceException extends AbstractShopException {
	private static final long serialVersionUID = 5999208465631860486L;

	public AbstractOrteServiceException(String msg) {
		super(msg);
	}

	public AbstractOrteServiceException(String msg, Throwable t) {
		super(msg, t);
	}
}
    

