package com.imadp.web.stripes.account;

import java.lang.reflect.Method;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

import org.stripesstuff.plugin.security.J2EESecurityManager;
import org.stripesstuff.plugin.security.SecurityHandler;

/**
 * AuthenticatedSecurityManager
 *
 * Base class for implementing a stripes Security manager.
 *
 * @param <T>
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public class AuthenticatedSecurityManager<T extends AbstractAuthenticatedActionBeanContext<V>,
	V extends AuthenticatedStripesSession> extends J2EESecurityManager implements SecurityHandler {

	// parameters
	public static final String LAST_URL_PARAMETER = "loginAction.lastUrl";

	@Override
	@SuppressWarnings("unchecked")
	protected final Boolean hasRole(ActionBean actionBean, Method handler, String role) {
		AbstractAuthenticatedActionBeanContext<V> context =
			((AbstractAuthenticatedActionBeanContext<V>)actionBean.getContext());

		return context.isLoggedIn() && context.hasAuthority(role);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected final Boolean isUserAuthenticated(ActionBean actionBean, Method handler) {
		AbstractAuthenticatedActionBeanContext<V> context =
			((AbstractAuthenticatedActionBeanContext<V>)actionBean.getContext());

		return context.isLoggedIn();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Resolution handleAccessDenied(ActionBean actionBean, Method handler) {
		AbstractAuthenticatedActionBeanContext<V> context =
			((AbstractAuthenticatedActionBeanContext<V>)actionBean.getContext());

		// if the user is authenticated but does not have access, return the access denied resolution
		if(isUserAuthenticated(actionBean, handler))
			return context.getAccessDeniedResolution();

		String lastUrl = context.getLastUrl();
		RedirectResolution resolution = new RedirectResolution(context.getLoginPage());

		// add the lastUrl if the request is a GET request
		if(lastUrl != null && actionBean.getContext().getRequest().getMethod().equalsIgnoreCase("GET"))
			resolution.addParameter(LAST_URL_PARAMETER, lastUrl);

		return resolution;
	}

}