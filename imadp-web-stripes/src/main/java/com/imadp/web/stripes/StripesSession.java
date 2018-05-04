package com.imadp.web.stripes;

import java.io.Serializable;

/**
 * StripesSession
 * 
 * An class providing base functionality for session implementations.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class StripesSession implements Serializable {
	private String sessionToken;

	/**
	 * Retrieves the unique token for this session instance.
	 * 
	 * @return String
	 */
	public synchronized String getSessionToken() {
		return sessionToken;
	}

	// setters
	synchronized void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}	
	
}