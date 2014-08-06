/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.mavenproject1.domain;


//import de.shop.util.persistence.AbstractVersionedAuditable;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Objects;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author tobias
 */
public class orte {
    
    private static final long serialVersionUID = -3700579190995722151L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
	
	private static final int name_LENGTH_MAX = 32;
	
	private static final String PREFIX = "ort."; 
	public static final String FIND_orte = PREFIX + "findorte";
	
	
	public static final String PARAM_name = "name";
        
        
	@Id
	@GeneratedValue
	@Basic(optional = false)
	@FormParam(value = "name")
	private String name;
        
        @Basic(optional = false)
	@FormParam(value = "longtitude")
	private Long longtitude;
        
        @Basic(optional = false)
	@FormParam(value = "latitude")
	private Long latitude;
        
        @Basic(optional = false)
	@FormParam(value = "beschreibung")
	private String beschreibung;
        
        @Basic(optional = false)
	@FormParam(value = "bild")
	private Blob bild;
        
        /*
        public ort() {
		super();
	}
        
        
        public ort(String name, Long latitude, Long longtitude ) {
		//super();
		this.name = name;
		this.latitude = latitude;
                this.longtitude = longtitude;
	}
        */

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the longtitude
     */
    public Long getLongtitude() {
        return longtitude;
    }

    /**
     * @param longtitude the longtitude to set
     */
    public void setLongtitude(Long longtitude) {
        this.longtitude = longtitude;
    }

    /**
     * @return the latitude
     */
    public Long getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the beschreibung
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * @param beschreibung the beschreibung to set
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * @return the bild
     */
    public Blob getBild() {
        return bild;
    }

    /**
     * @param bild the bild to set
     */
    public void setBild(Blob bild) {
        this.bild = bild;
    }
}
