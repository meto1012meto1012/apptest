

package com.mycompany.mavenproject1.util.persistence;


public enum MultimediaType {
	IMAGE("I"),
	VIDEO("V"),
	AUDIO("A");

	private final String value;
	
	private MultimediaType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static MultimediaType build(String value) {
		switch (value) {
			case "I":
			case "IMAGE":
				return IMAGE;
			case "V":
			case "VIDEO":
				return VIDEO;
			case "A":
			case "AUDIO":
				return AUDIO;
			default:
				throw new RuntimeException(value + " ist kein gueltiger Wert fuer MultimediaType");
		}
	}
}
