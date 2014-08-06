
package com.mycompany.mavenproject1.util.web;

import com.mycompany.mavenproject1.util.interceptor.Log;
import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;



@Named
@SessionScoped
@Log
public class Sprache implements Serializable {
	private static final long serialVersionUID = 1986565724093259408L;
	
	@Produces
	@Named
	@Client
	private Locale locale;

	
	@PostConstruct
	private void postConstruct() {
		locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
	}

	public void change(String localeStr) {
		final Locale newLocale = new Locale(localeStr);
		if (newLocale.equals(locale)) {
			return;
		}
		
		locale = newLocale;
		
		final FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.getViewRoot().setLocale(locale);
		ctx.renderResponse();
	}
	
//    @Produces
//    @Faces
//    public Locale getLocale() {
//		final FacesContext ctx = FacesContext.getCurrentInstance();
//    	final UIViewRoot viewRoot = ctx.getViewRoot();
//        return viewRoot != null
//        	   ? viewRoot.getLocale()
//        	   : ctx.getApplication().getViewHandler().calculateLocale(ctx);
//    }
	
	@Override
	public String toString() {
		return "Sprache [locale=" + locale + "]";
	}
}
