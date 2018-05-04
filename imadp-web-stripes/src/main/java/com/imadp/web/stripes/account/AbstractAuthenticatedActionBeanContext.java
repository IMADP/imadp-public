package com.imadp.web.stripes.account;

import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.Resolution;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.RememberMeServices;

import com.imadp.core.concurrent.GlobalMutex;
import com.imadp.service.account.AccountFacade;
import com.imadp.service.account.credentials.Credentials;
import com.imadp.service.user.User;
import com.imadp.web.cookie.CookieUtil;
import com.imadp.web.stripes.AbstractActionBean;
import com.imadp.web.stripes.AbstractActionBeanContext;

/**
 * AbstractActionBeanContext
 *
 * Custom action bean context for session data.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractAuthenticatedActionBeanContext<T extends AuthenticatedStripesSession>
	extends AbstractActionBeanContext<T> {

	// cookie names
	private static final String USER_COOKIE = "USER_COOKIE";
	private static final String REFERRAL_COOKIE = "REFERRAL_COOKIE";

	/**
	 * Returns the resolution for an access denied event.
	 *
	 * @return Resolution
	 */
	public abstract Resolution getAccessDeniedResolution();

	/**
	 * Returns the resolution for a successful sign in, in the event the lastUrl was not provided.
	 *
	 * @return Resolution
	 */
	public abstract Resolution getSuccessfulLoginResolution();

	/**
	 * Returns the action bean to redirect to when the user needs to login to proceed.
	 *
	 * @return Class<? extends AbstractActionBean<? extends AbstractActionBeanContext<T>>>
	 */
	public abstract Class<? extends AbstractActionBean<? extends AbstractActionBeanContext<T>>> getLoginPage();

	/**
	 * Returns the account facade for the application.
	 *
	 * @return AccountFacade<?>
	 */
	protected abstract AccountFacade<?> getAccountFacade();

	/**
	 * Returns the rememberMeServices for the application, used for automatic authentication of users through
	 * remember-me cookies. If this functionality is not supported, the NullRememberMeServices can be returned.
	 *
	 * @return RememberMeServices
	 */
	protected abstract RememberMeServices getRememberMeServices();

	/**
	 * After session is created, we attempt to authenticate the user automatically through remember me services,
	 * if possible. Otherwise, we see if a user is using the site as an unauthenticated guest using cookies. If so,
	 * the user is stored into session, but the user remains unauthenticated.
	 *
	 * @param session
	 */
	@Override
	protected final void onSessionCreated(T session) {
		// attempt to log in the user automatically through the rememberMeService.
		Authentication authentication = getRememberMeServices().autoLogin(getRequest(), getResponse());

		if(authentication != null)
		{
			// if authentication was successful, store the principal and user in session
			completeLogin((Credentials)authentication.getPrincipal());
		}
		else
		{
			// if the authentication was not successful, check for an anonymous cookied user
			try
			{
				// if a user was not found, attempt to find the user from a cookie
				String userIdValue = encryptor.decrypt(CookieUtil.getCookieValue(USER_COOKIE, getRequest()));

				// if a value was found, try to locate the user through the user service
				if(userIdValue != null)
				{
					User user = getAccountFacade().getUser(Long.valueOf(userIdValue));

					// if a user was found, set the user into session and return
					if(user != null)
						session.setUser(user);
				}
			}
			catch(Exception exception)
			{
				logger.warn("An exception occurred retrieving a cookied user", exception);
				CookieUtil.deleteCookie(USER_COOKIE, getRequest(), getResponse());
			}
		}

		onSessionCreated(session, isLoggedIn());
	}

	/**
	 * Hook into after the authenticated session has been created and initialized.
	 *
	 * @param session
	 * @param loggedIn
	 */
	protected void onSessionCreated(T session, boolean loggedIn) {

	}

	/**
	 * Attempts to authenticate a user that has provided the given username and password.
	 * If rememberMe is true, a remember me cookie will be stored for future authentication.
	 *
	 * @param username
	 * @param password
	 * @param rememberMe
	 * @return <code>true</code> if authentication succeeds, <code>false</code> otherwise
	 */
	public final boolean login(String username, String password, boolean rememberMe) {

		// create an Acegi authentication request
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

		// attempt authentication
		try
		{
			Authentication authResult = getAccountFacade().authenticate(authentication);

			// if remember me is enabled, store the cookie
			if(rememberMe)
				getRememberMeServices().loginSuccess(getRequest(), getResponse(), authResult);

			// complete the authentication
			return completeLogin((Credentials)authResult.getPrincipal());
		}
		catch (Exception exception)
		{
			logger.debug("Invalid login for username=[{}], exception=[{}]", username, exception.getMessage());
			return false;
		}
	}

	/**
	 * Completes the authentication process via the credentials and hook into a successful authentication.
	 * This can also be used as a shortcut to the sign-in process if the credentials are available.
	 *
	 * @param credentials
	 * @return boolean
	 */
	public final boolean completeLogin(Credentials credentials) {
		logger.debug("Successful login for username=[{}]", credentials.getUsername());

		// update last successful login date, synchronized to prevent concurrent logins
		synchronized(GlobalMutex.getMutex(getClass(), "completeLogin", credentials.getUsername()))
		{
			getAccountFacade().updateLastLoginDate(credentials.getUser());
		}

		// set the authentication properties into session
		getSession().setCredentials(credentials);
		getSession().setLoggedIn(true);

		// hook into a successful authentication
		onSuccessfulAuthentication(credentials);

		return true;
	}

	/**
	 * Hook into a successful authentication.
	 *
	 * @param credentials
	 */
	public void onSuccessfulAuthentication(Credentials credentials) {

	}

	/**
	 * Signs out by invalidating the remember me cookie and the session itself.
	 *
	 */
	public void logout() {
		// invalidate the remember me cookie
		getRememberMeServices().loginFail(getRequest(), getResponse());

		// invalidate the user cookie
		CookieUtil.deleteCookie(USER_COOKIE, getRequest(), getResponse());

		// invalidate the session
		HttpSession session = getHttpSession();

		if(session != null)
			session.invalidate();
	}

	/**
	 * Returns true if the user is in session, false otherwise.
	 *
	 * @note This method only determines if a user is in session, which may be from manual/auto authentication, or it
	 * 		 may just be an anonymous user. Use isLoggedIn() to determine if a user was actually authenticated.
	 *
	 * @return boolean
	 */
	public boolean hasUser() {
		return getSession().hasUser();
	}

	/**
	 * Returns the user, or throws an UnsupportedOperationException if the user was not found in session. This is
	 * to ensure that callers realize that a user may not always be in session. Use hasUser() to determine if a user
	 * is actually available, or call getUserOrNull() if null is an acceptable return object.
	 *
	 * @throws UnsupportedOperationException if the user is null.
	 * @return User
	 */
	public User getUser() {
		return getSession().getUser();
	}

	/**
	 * Returns the user, or null if no user was found. This method will not throw an UnsupportedOperationException
	 * if the user was not found in session. Callers should use this when null is an acceptable return object.
	 *
	 * @return User
	 */
	public User getUserOrNull() {
		return getSession().hasUser() ? getSession().getUser() : null;
	}

	/**
	 * Returns the username of the currently logged in user, or null if the user is not logged in.
	 *
	 * @return String
	 */
	public String getUsername() {
		return getSession().getUsername();
	}

	/**
	 * Returns the email of the currently logged in user, or null if the user is not logged in.
	 *
	 * @return String
	 */
	public String getEmail() {
		return getSession().getEmail();
	}

	/**
	 * Returns true if the email is verified, false otherwise.
	 *
	 * @return String
	 */
	public boolean isEmailVerified() {
		return getSession().isEmailVerified();
	}

	/**
	 * Returns true if the user is signed in, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isLoggedIn() {
		T session = getSession();
		return session!= null && getSession().isLoggedIn();
	}

	/**
	 * Returns the user object from session if one was found.
	 * If no user was found, a new user is created, saved, stored into session and returned.
	 *
	 * @note This method is guaranteed to return a user, and will never return null.
	 *
	 * @return User
	 */
	public User getOrCreateUser() {
		synchronized(getHttpSession())
		{
			// if a user is already in session we simply return
			if(hasUser())
				return getUser();

			// if no user was found, create one and store it in a cookie
			User user = new User();
			getAccountFacade().saveUser(user);
			getSession().setUser(user);

			// set the userId in a cookie
			CookieUtil.addCookie(USER_COOKIE, encryptor.encrypt(String.valueOf(user.getId())),
					Integer.MAX_VALUE, getRequest(), getResponse());

			return user;
		}
	}

	/**
	 * Converts a User.
	 *
	 * @param uid
	 * @return User
	 */
	public User convertUser(String uid) {
		return getAccountFacade().getUser(uid);
	}

	/**
	 * Refreshes the credentials that are stored in session.
	 * This method should be called when any state regarding the credentials object has been changed.
	 *
	 */
	public final void refreshCredentialsInSession() {
		getSession().setCredentials(getCredentials());
	}

	/**
	 * Returns the credentials for a signed in user.
	 *
	 * @throws UnsupportedOperationException if the user is not in session or no credentials could be found.
	 * @return Credentials
	 */
	private Credentials getCredentials() {
		Credentials credentials = getAccountFacade().getCredentials(getUser());

		if(credentials == null)
			throw new UnsupportedOperationException("No credentials were found by the given user");

		return credentials;
	}

	/**
	 * Returns true if the user is authenticated with the given authority.
	 *
	 * @return boolean
	 */
	protected boolean hasAuthority(String authority) {
		for(GrantedAuthority grantedAuthority : getSession().getAuthorities())
			if(grantedAuthority.getAuthority().equalsIgnoreCase(authority))
				return true;

		return false;
	}

	/**
	 * Retrieves the referral user uid from session or a referral cookie.
	 *
	 * @return String
	 */
	public String getReferrerUserEid() {
		String referrerUserEid = getSession().getReferrerUserEid();
		return referrerUserEid != null ? referrerUserEid : CookieUtil.getCookieValue(REFERRAL_COOKIE, getRequest());
	}

	/**
	 * Sets a referral user uid into session and a referral cookie.
	 *
	 * @param referrerUserEid
	 */
	public void setReferrerUserEid(String referrerUserEid) {
		getSession().setReferrerUserEid(referrerUserEid);
		CookieUtil.addCookie(REFERRAL_COOKIE, referrerUserEid, Integer.MAX_VALUE, getRequest(), getResponse());
	}

	/**
	 * Removes the referral user encoded id from session and the referral cookie.
	 *
	 */
	public void removeReferrer() {
		getSession().setReferrerUserEid(null);
		CookieUtil.deleteCookie(REFERRAL_COOKIE, getRequest(), getResponse());
	}

}