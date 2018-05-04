package com.tracktacular.web;

import java.util.List;

import com.google.common.collect.Lists;
import com.imadp.service.commerce.subscription.Subscription;
import com.imadp.service.user.User;
import com.imadp.web.stripes.account.AuthenticatedStripesSession;
import com.tracktacular.service.account.Preferences;

/**
 * TracktacularSession
 *
 * Tracktacular session information.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TracktacularSession extends AuthenticatedStripesSession {
	private boolean mobile;

	// user properties
	private Preferences userPreferences;
	private Subscription userSubscription;
	private String userUsername;

	// tracker user properties
	private User trackerUser;
	private String trackerUserUsername;

	// a list of imported items
	private List<?> importedItems;

	/**
	 * Clears user preferences from session.
	 *
	 */
	public synchronized void clearUserPreferences() {
		this.userPreferences = null;
	}

	/**
	 * Clears user subscription from session.
	 *
	 */
	public synchronized void clearUserSubscription() {
		this.userSubscription = null;
	}

	/**
	 * Returns true if the userUsername property is in session, false otherwise.
	 *
	 * @return boolean
	 */
	synchronized boolean isUserUsernameInSession() {
		return userUsername != null;
	}

	/**
	 * Returns true if the any trackerUserUsername is already in session, false otherwise.
	 *
	 * @return boolean
	 */
	synchronized boolean isTrackerUserUsernameInSession() {
		return trackerUserUsername != null;
	}

	/**
	 * Returns true if the given username is already in session, false otherwise.
	 *
	 * @param username
	 * @return boolean
	 */
	synchronized boolean isTrackerUserUsernameInSession(String username) {
		return username.equalsIgnoreCase(trackerUserUsername);
	}

	// getters and setters
	synchronized User getTrackerUser() {
		return trackerUser;
	}

	synchronized void setTrackerUser(User trackerUser) {
		this.trackerUser = trackerUser;
	}

	synchronized String getTrackerUserUsername() {
		return trackerUserUsername;
	}

	synchronized void setTrackerUserUsername(String trackerUserUsername) {
		this.trackerUserUsername = trackerUserUsername;
	}

	synchronized String getUserUsername() {
		return userUsername;
	}

	synchronized void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	synchronized boolean isMobile() {
		return mobile;
	}

	synchronized void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

	synchronized Preferences getUserPreferences() {
		return userPreferences;
	}

	synchronized void setUserPreferences(Preferences userPreferences) {
		this.userPreferences = userPreferences;
	}

	synchronized Subscription getUserSubscription() {
		return userSubscription;
	}

	synchronized void setUserSubscription(Subscription userSubscription) {
		this.userSubscription = userSubscription;
	}

	@SuppressWarnings("unchecked")
	public synchronized <V> List<V> getImportedItems() {
		if(importedItems == null)
			importedItems = Lists.newArrayList();

		return (List<V>) importedItems;
	}

}