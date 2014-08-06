

package com.mycompany.mavenproject1.util.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import static javax.persistence.TemporalType.TIMESTAMP;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;


@MappedSuperclass
@XmlAccessorType(FIELD)
public abstract class AbstractAuditable implements Serializable {
	private static final long serialVersionUID = -6592056535978766351L;

	@Temporal(TIMESTAMP)
	@Basic(optional = false)
	@XmlTransient
	private Date erzeugt;

	@Temporal(TIMESTAMP)
	@Basic(optional = false)
	@XmlTransient
	private Date aktualisiert;
	
	@PrePersist
	protected void prePersist() {
		erzeugt = new Date();
		aktualisiert = new Date();
	}
	
	@PreUpdate
	protected void preUpdate() {
		aktualisiert = new Date();
	}
	
	public Date getErzeugt() {
		return erzeugt == null ? null : (Date) erzeugt.clone();
	}

	public void setErzeugt(Date erzeugt) {
		this.erzeugt = erzeugt == null ? null : (Date) erzeugt.clone();
	}

	public Date getAktualisiert() {
		return aktualisiert == null ? null : (Date) aktualisiert.clone();
	}

	public void setAktualisiert(Date aktualisiert) {
		this.aktualisiert = aktualisiert == null ? null : (Date) aktualisiert.clone();
	}
	
	@Override
	public String toString() {
		return "AbstractAuditable [erzeugt=" + erzeugt + ", aktualisiert=" + aktualisiert + "]";
	}
}
