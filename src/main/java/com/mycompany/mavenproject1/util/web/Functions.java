
package com.mycompany.mavenproject1.util.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public final class Functions {
	public static Date aktuellesDatum() {
		return new Date();
	}
	
	public static TimeZone timeZone(String value) {
		return TimeZone.getTimeZone(value);
	}
	
	public static <T> List<T> asList(Collection<T> collection) {
		if (collection == null) {
			return null;
		}
		return new ArrayList<>(collection);
	}
	
	private Functions() {}
}
