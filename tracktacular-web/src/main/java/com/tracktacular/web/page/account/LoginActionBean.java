package com.tracktacular.web.page.account;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.service.account.credentials.CredentialsResetPassword;
import com.imadp.web.stripes.AbstractAction;
import com.imadp.web.stripes.account.LoginAction;
import com.tracktacular.web.TracktacularActionBean;
import com.tracktacular.web.page.IndexActionBean;


/**
 * LoginPage
 *
 * The ActionBean for the login page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/login")
public class LoginActionBean extends TracktacularActionBean {

	// views
	private static final String LOGIN_PAGE_VIEW = "/WEB-INF/jsp/page.account/loginPage.jsp";
	private static final String LOGIN_MOBILE_PAGE_VIEW = "/WEB-INF/jsp/page.account/loginPageMobile.jsp";

	// actions
	private LoginAction loginAction;
	private ResetPasswordAction resetPasswordAction;

	private CredentialsResetPassword credentialsResetPassword;

	@DefaultHandler
	public Resolution index() {

		// redirect any users who are already logged in
		if(getContext().isLoggedIn())
			return new RedirectResolution(IndexActionBean.class);

		return new ForwardResolution(isMobile() ? LOGIN_MOBILE_PAGE_VIEW : LOGIN_PAGE_VIEW);
	}

	@Override
	public boolean isDisplayTrackerUserNotification() {
		return isMobile() && isPublicMode();
	}

	/**
	 * Handles the submission of the login form.
	 *
	 * @return Resolution
	 */
	public final Resolution login() {
		return loginAction.act(this);
	}

	/**
	 * Handles the submission of the reset password form.
	 *
	 * @return Resolution
	 */
	public final Resolution resetPassword() {
		return resetPasswordAction.act(this);
	}

	// getters and setters
	public LoginAction getLoginAction() {
		return loginAction;
	}

	public void setLoginAction(LoginAction loginAction) {
		this.loginAction = loginAction;
	}

	public ResetPasswordAction getResetPasswordAction() {
		return resetPasswordAction;
	}

	public void setResetPasswordAction(ResetPasswordAction resetPasswordAction) {
		this.resetPasswordAction = resetPasswordAction;
	}

	public CredentialsResetPassword getCredentialsResetPassword() {
		return credentialsResetPassword;
	}

	public void setCredentialsResetPassword(
			CredentialsResetPassword credentialsResetPassword) {
		this.credentialsResetPassword = credentialsResetPassword;
	}

	/**
	 * ResetPasswordAction
	 *
	 * The action for resetting your tracktacular password.
	 *
	 * @version 1.0
	 * @author Anthony DePalma
	 */
	public static class ResetPasswordAction extends AbstractAction<LoginActionBean> {

		@Override
		protected void doAction(LoginActionBean actionBean) {
			actionBean.getAccountFacade().resetPassword(actionBean.getCredentialsResetPassword(), actionBean.getLocale());
		}

		@Override
		protected Resolution getResolution(LoginActionBean actionBean) {
			actionBean.getContext().addSuccessMessage("account.resetPassword.success");
			return actionBean.getAjaxSourceResolution();
		}

	}

}