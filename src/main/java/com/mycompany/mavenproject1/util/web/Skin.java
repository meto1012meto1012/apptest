
package com.mycompany.mavenproject1.util.web;

import com.mycompany.mavenproject1.util.interceptor.Log;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@SessionScoped
@Named
@Log
public class Skin implements Serializable {
	private static final long serialVersionUID = -7795677531657784822L;
	
	private static final String BLUESKY_STRING = "blueSky";
	private static final String DEEPMARINE_STRING = "deepMarine";
	private static final String EMARALDTOWN_STRING = "emeraldTown";
	private static final String JAPANCHERRY_STRING = "japanCherry";
	private static final String RUBY_STRING = "ruby";
	private static final String WINE_STRING = "wine";
	private static final SkinType DEFAULT = SkinType.BLUESKY;
	
	private enum SkinType {
		BLUESKY(BLUESKY_STRING),
		DEEPMARINE(DEEPMARINE_STRING),
		EMARALDTOWN(EMARALDTOWN_STRING),
		JAPANCHERRY(JAPANCHERRY_STRING),
		RUBY(RUBY_STRING),
		WINE(WINE_STRING);
		
		private final String value;

		private SkinType(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
		public static SkinType build(String value) {
			switch (value) {
				case BLUESKY_STRING:
					return BLUESKY;
					
				case DEEPMARINE_STRING:
					return DEEPMARINE;
					
				case EMARALDTOWN_STRING:
					return EMARALDTOWN;
					
				case JAPANCHERRY_STRING:
					return JAPANCHERRY;
					
				case RUBY_STRING:
					return RUBY;
					
				case WINE_STRING:
					return WINE;
					
				default:
					return DEFAULT;
			}
		}
	}
	
	private SkinType value = DEFAULT;
	
	public String getValue() {
		return value.getValue();
	}
	
	public void setValue(String value) {
		this.value = SkinType.build(value);
	}
	
	public void change(String skinStr) {
		final SkinType newSkin = SkinType.build(skinStr);
		if (newSkin.equals(value)) {
			return;
		}
		
		value = newSkin;
		FacesContext.getCurrentInstance().renderResponse();
	}
}
