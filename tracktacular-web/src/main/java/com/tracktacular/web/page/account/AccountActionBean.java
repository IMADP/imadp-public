package com.tracktacular.web.page.account;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.imadp.service.account.credentials.CredentialsEmail;
import com.imadp.service.account.credentials.CredentialsPasswordChange;
import com.imadp.service.commerce.subscription.Subscription;
import com.imadp.web.stripes.AbstractAction;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TracktacularTrackersFacade;
import com.tracktacular.web.TracktacularActionBean;


/**
 * AccountActionBean
 *
 * The ActionBean for the account page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/account")
@RolesAllowed(value={"user", "admin"})
public class AccountActionBean extends TracktacularActionBean {

	@SpringBean
	private TracktacularTrackersFacade trackersFacade;

	// views
	private static final String ACCOUNT_PAGE_VIEW = "/WEB-INF/jsp/page.account/accountPage.jsp";

	// properties
	private CredentialsEmail credentialsEmail;
	private CredentialsPasswordChange credentialsPasswordChange;
	private ExportAction exportAction;
	private List<Subscription> referrals;

	@DefaultHandler
	public Resolution index() {

		if(!getContext().isEmailVerified())
			getContext().addWarnMessage("account.emailNotVerified");

		if(getUserSubscription().isExpired())
			getContext().addInfoMessage("tracktacular.accountExpired");

		credentialsEmail = new CredentialsEmail();
		credentialsEmail.setEmail(getContext().getEmail());
		return new ForwardResolution(ACCOUNT_PAGE_VIEW);
	}

	@Override
	public boolean isMobile() {
		return false;
	}

	/**
	 * Returns an array of all trackers.
	 *
	 * @return Tracker[]
	 */
	public Tracker[] getTrackers() {
		return Tracker.values();
	}

	/**
	 * Returns the list of referrals for the given user.
	 *
	 * @return List<Subscription>
	 */
	public List<Subscription> getReferrals() {
		if(referrals == null)
			referrals = getAccountFacade().getReferrals(getContext().getUser());

		return referrals;
	}

	/**
	 * Returns the list of referrals for the given user.
	 *
	 * @return List<Subscription>
	 */
	public long getActiveReferralCount() {
		int activeReferralCount = 0;

		for(Subscription subscription : getReferrals())
			if(subscription.isActive())
				activeReferralCount++;

		return activeReferralCount;
	}

	/**
	 * Changes a users email.
	 *
	 * @return Resolution
	 */
	public Resolution changeEmail() {
		CredentialsEmail credentialsEmail = getCredentialsEmail();

		if(credentialsEmail != null)
			credentialsEmail.setUser(getContext().getUser());

		getAccountFacade().changeCredentialsEmail(credentialsEmail, getLocale());
		getContext().addWarnMessage("account.emailNotVerified");
		getContext().addSuccessMessage("account.changeEmail.success");
		getContext().refreshCredentialsInSession();
		return getAjaxSourceResolution();
	}

	/**
	 * Changes a users password.
	 *
	 * @return Resolution
	 */
	public Resolution changePassword() {
		CredentialsPasswordChange credentialsPasswordChange = getCredentialsPasswordChange();

		if(credentialsPasswordChange != null)
			credentialsPasswordChange.setUser(getContext().getUser());

		getAccountFacade().changeCredentialsPassword(credentialsPasswordChange);
		getContext().addSuccessMessage("account.changePassword.success");
		return getAjaxSourceResolution();
	}

	/**
	 * Resends the verification email for the user's credentials.
	 *
	 * @return Resolution
	 */
	public Resolution sendVerificationEmail() {
		getAccountFacade().sendAccountVerificationEmail(getContext().getUser(), getContext().getLocale());
		getContext().addSuccessMessage("account.sendVerificationEmail", getContext().getEmail());
		return new RedirectResolution(AccountActionBean.class);
	}

	/**
	 * Returns a zip file export containing a comma separated list of values.
	 *
	 * @return Resolution
	 */
	public final Resolution exportData() {
		return exportAction.act(this);
	}

	/**
	 * Overridden to allow saves in public mode.
	 *
	 * @return Resolution
	 */
	@Override
	public Resolution savePreferences() {
		return super.savePreferences();
	}

	// getters and setters
	public CredentialsEmail getCredentialsEmail() {
		return credentialsEmail;
	}

	public void setCredentialsEmail(CredentialsEmail credentialsEmail) {
		this.credentialsEmail = credentialsEmail;
	}

	public CredentialsPasswordChange getCredentialsPasswordChange() {
		return credentialsPasswordChange;
	}

	public void setCredentialsPasswordChange(CredentialsPasswordChange credentialsPasswordChange) {
		this.credentialsPasswordChange = credentialsPasswordChange;
	}

	public ExportAction getExportAction() {
		return exportAction;
	}

	public void setExportAction(ExportAction exportAction) {
		this.exportAction = exportAction;
	}

	/**
	 * ExportAction
	 *
	 * The action for subscribing tracktacular.
	 *
	 * @version 1.0
	 * @author Anthony DePalma
	 */
	public static class ExportAction extends AbstractAction<AccountActionBean> {

		@Override
		protected void doAction(AccountActionBean actionBean) {

		}

		@Override
		protected Resolution getResolution(final AccountActionBean actionBean) {
			return new StreamingResolution("application/zip") {
			    @Override
				public void stream(HttpServletResponse response) throws Exception {
			    	actionBean.trackersFacade.getTrackerExport(actionBean.getUser(), response.getOutputStream());
			    }
			}.setFilename("Tracktacular.zip");
		}

		@Override
		protected boolean isSessionTokenRequired() {
			return true;
		}

	}

}