package com.imadp.web.stripes.account;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;

import com.imadp.service.account.credentials.Credentials;
import com.imadp.service.user.User;
import com.imadp.web.stripes.StripesSession;

/**
 * AuthenticatedStripesSession
 *
 * Provides common functionality for an authentication web session.
 * Should be extended for any authenticated web application that needs to store additional session information.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class AuthenticatedStripesSession extends StripesSession {

	// properties
	private User user;
	private String email;
	private String username;
	private String referrerUserEid;
	private boolean emailVerified;
	private boolean loggedIn;
	private Collection<GrantedAuthority> authorities;

	// constructor
	public AuthenticatedStripesSession() {

	}

	/**
	 * Sets the loggedIn flag in session.
	 *
	 * @param user
	 */
	synchronized void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * Sets a user in session.
	 *
	 * @param user
	 */
	synchronized void setUser(User user) {
		this.user = user;
	}

	/**
	 * Stores users credentials information in session.
	 *
	 * @param credentials
	 */
	synchronized void setCredentials(Credentials credentials) {
		this.user = credentials.getUser();
		this.email = credentials.getEmail();
		this.username = credentials.getUsername();
		this.emailVerified = credentials.isEmailVerified();
		this.authorities = credentials.getAuthorities();
	}

	/**
	 * Returns true if the user is in session, false otherwise.
	 *
	 * @note This method only determines if a user is in session, which may be from manual/auto authentication, or it
	 * 		 may just be an anonymous user. Use isLoggedIn() to determine if a user was actually authenticated.
	 *
	 * @return boolean
	 */
	synchronized boolean hasUser() {
		return user != null;
	}

	/**
	 * Returns the user.
	 *
	 * @throws UnsupportedOperationException if the user is null.
	 * @return User
	 */
	synchronized User getUser() {
		if(user == null)
			throw new UnsupportedOperationException(
					"No user was found in session. Check session.hasUser() first, or getUserOrNull if null is acceptable");

		return user;
	}

	/**
	 * Returns true if the user is signed in, false otherwise.
	 *
	 * @return boolean
	 */
	synchronized boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * Returns the authorities for a given user.
	 *
	 * @return Collection<GrantedAuthority>
	 */
	synchronized Collection<GrantedAuthority> getAuthorities() {
		if(authorities == null)
			return Collections.emptyList();

		return authorities;
	}

	/**
	 * Returns the username in session.
	 *
	 * @return String
	 */
	synchronized String getUsername() {
		return username;
	}

	/**
	 * Returns the email in session.
	 *
	 * @return String
	 */
	synchronized String getEmail() {
		return email;
	}

	/**
	 * Returns true if the email is verified, false otherwise.
	 *
	 * @return String
	 */
	synchronized boolean isEmailVerified() {
		return emailVerified;
	}

	/**
	 * Returns the referrer user encoded id in session.
	 *
	 * @return String
	 */
	synchronized String getReferrerUserEid() {
		return referrerUserEid;
	}

	/**
	 * Sets the referrer user encoded id in session.
	 *
	 * @param referrerUserEid
	 */
	synchronized void setReferrerUserEid(String referrerUserEid) {
		this.referrerUserEid = referrerUserEid;
	}

}