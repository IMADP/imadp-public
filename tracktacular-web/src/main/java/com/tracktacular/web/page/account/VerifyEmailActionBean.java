package com.tracktacular.web.page.account;

import javax.annotation.security.RolesAllowed;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;


/**
 * VerifyEmailActionBean
 *
 * The ActionBean for the email verification page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/verify-email/{verificationId}")
@RolesAllowed(value={"user", "admin"})
public class VerifyEmailActionBean extends TracktacularActionBean {
	private String verificationId;

	@DefaultHandler
	public Resolution index() {
		boolean verified = getAccountFacade().verifyEmail(getContext().getUser(), verificationId);

		if(verified)
		{
			getContext().addSuccessMessage("tracktacular.verifyEmail.success");
			getContext().refreshCredentialsInSession();
		}
		else
			getContext().addErrorMessage("tracktacular.verifyEmail.failure");

		return new RedirectResolution(AccountActionBean.class);
	}

	// getters and setters
	public String getVerificationId() {
		return verificationId;
	}

	public void setVerificationId(String verificationId) {
		this.verificationId = verificationId;
	}

}