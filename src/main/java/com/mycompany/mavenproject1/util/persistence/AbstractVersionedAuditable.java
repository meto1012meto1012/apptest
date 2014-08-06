

package com.mycompany.mavenproject1.util.persistence;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.ws.rs.FormParam;

import static de.shop.util.Constants.ERSTE_VERSION;


@MappedSuperclass
public abstract class AbstractVersionedAuditable extends AbstractAuditable implements Cloneable {
	private static final long serialVersionUID = -239222312857331551L;
	
	@Version
	@Basic(optional = false)
	@FormParam("version")
	private int version = ERSTE_VERSION;

	public int getVersion() {
		return version;
	}
	
	/**
	 * Fuer die Uebernahme von Werten aus einem PUT-Request in das aus der DB gelesene Objekt
	 * @param newValues das Objekt aus dem PUT-Request
	 */
	public void setValues(AbstractVersionedAuditable newValues) {
		version = newValues.version;		
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		final AbstractVersionedAuditable neuesObjekt = (AbstractVersionedAuditable) super.clone();
		neuesObjekt.version = getVersion();
		return neuesObjekt;
	}

	@Override
	public String toString() {
		return "AbstractVersionedAuditable [version=" + version + ", " + super.toString() + "]";
	}
}
