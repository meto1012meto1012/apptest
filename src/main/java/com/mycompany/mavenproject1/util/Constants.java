

package com.mycompany.mavenproject1.util;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
public final class Constants {
	public static final String REST_PATH = "/rest";
	
	// Bean Validation
	public static final String EMAIL_PATTERN = "[\\w.%-]+@[\\w.%-]+\\.[A-Za-z]{2,4}";
	
	// Header-Links
	public static final String SELF_LINK = "self";
	public static final String LIST_LINK = "list";
	public static final String ADD_LINK = "add";
	public static final String UPDATE_LINK = "update";
	public static final String REMOVE_LINK = "remove";
	public static final String FIRST_LINK = "first";
	public static final String LAST_LINK = "last";
	
	// JPA
	public static final String LOADGRAPH = "javax.persistence.loadgraph";
	public static final int ERSTE_VERSION = 0;
	public static final int MAX_AUTOCOMPLETE = 10;
	
	// JSF
	public static final String JSF_INDEX = "/rf/index";
	public static final String JSF_DEFAULT_ERROR = "/rf/error/defaultError";
	public static final String JSF_REDIRECT_SUFFIX = "?faces-redirect=true";
	public static final int BESTELLVORGANG_TIMEOUT = 5;
	public static final int BESTAETIGUNG_TIMEOUT = 5;
	public static final TimeUnit TIMEOUT_UNIT = MINUTES;
	
	public static final String SHOP_DOMAIN = "shop";
	
	private Constants() {
	}
}
