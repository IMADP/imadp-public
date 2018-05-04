package com.imadp.web.stripes.account;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

import com.imadp.core.validation.ValidationResult;
import com.imadp.web.stripes.AbstractAction;

/**
 * LoginAction
 *
 * The action for logging into the application.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class LoginAction extends AbstractAction<AbstractAuthenticatedActionBean
	<? extends AbstractAuthenticatedActionBeanContext<?>>> {

	// error key
	private static final String LOGIN_ERROR_KEY = "loginAction.invalidCredentials";

	// form properties
	private String lastUrl;
	private String username;
	private String password;
	private boolean rememberMe = true;

	// status properties
	private boolean successfulLogin;

	@Override
	protected void doAction(AbstractAuthenticatedActionBean<? extends AbstractAuthenticatedActionBeanContext<?>> actionBean) {
		successfulLogin = actionBean.getContext().login(username, password, rememberMe);
	}

	@Override
	protected Resolution getResolution(AbstractAuthenticatedActionBean<? extends AbstractAuthenticatedActionBeanContext<?>> actionBean) {
		if(!successfulLogin)
		{
			actionBean.getContext().addValidationResult(
					new ValidationResult(LOGIN_ERROR_KEY, "loginAction.username", "loginAction.password"));

			return new ForwardResolution(actionBean.getContext().getLoginPage());
		}

		if(lastUrl != null)
			return new RedirectResolution(lastUrl);

		return actionBean.getContext().getSuccessfulLoginResolution();
	}

	// getters and setters
	public String getLastUrl() {
		return lastUrl;
	}

	public void setLastUrl(String lastUrl) {
		this.lastUrl = lastUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

}