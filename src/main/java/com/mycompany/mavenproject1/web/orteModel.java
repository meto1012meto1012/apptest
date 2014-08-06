/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.mavenproject1.web;

import com.mycompany.mavenproject1.business.orteService;
import com.mycompany.mavenproject1.domain.orte;
import static com.mycompany.mavenproject1.util.Constants.BESTELLVORGANG_TIMEOUT;
import static com.mycompany.mavenproject1.util.Constants.TIMEOUT_UNIT;
import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;
import static java.util.logging.Level.FINER;
import static java.util.logging.Level.FINEST;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author tobias
 */

@Named
@ConversationScoped
@Stateful
public class orteModel implements Serializable {
	private static final long serialVersionUID = 1564024850446471639L;
	
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
	
        
        
        private List<orte> ort;
        
        
        @Inject
	private orteService os;
	
	@Inject
	private transient Conversation conversation;
        
        public List<orte> getOrte() {
		return ort;
	}
        
        
	private void beginConversation() {
		if (!conversation.isTransient()) {
			if (LOGGER.isLoggable(FINER)) {
				LOGGER.finer("Die Conversation ist bereits gestartet");
			}
			return;
		}
		
		if (LOGGER.isLoggable(FINER)) {
			LOGGER.finer("Neue Conversation wird gestartet");
		}
		conversation.begin();
		conversation.setTimeout(TIMEOUT_UNIT.toMillis(BESTELLVORGANG_TIMEOUT));
		if (LOGGER.isLoggable(FINEST)) {
			LOGGER.finest("Neue Conversation beginnt");
		}
	}
    
        @Override
	public String toString() {
		return "OrteModel [orte=" + ort + "]";
	}
    
}
