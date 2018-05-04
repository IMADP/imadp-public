package com.tracktacular.web.page.account;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.service.account.Account;
import com.imadp.service.account.credentials.Credentials;
import com.imadp.web.stripes.AbstractAction;
import com.tracktacular.web.TracktacularActionBean;
import com.tracktacular.web.page.GettingStartedActionBean;
import com.tracktacular.web.page.IndexActionBean;


/**
 * JoinPage
 *
 * The ActionBean for the join page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/join")
public class JoinActionBean extends TracktacularActionBean {

	// views
	private static final String JOIN_PAGE_VIEW = "/WEB-INF/jsp/page.account/joinPage.jsp";
	private static final String JOIN_MOBILE_PAGE_VIEW = "/WEB-INF/jsp/page.account/joinPageMobile.jsp";

	// actions
	private JoinAction joinAction;

	private Account account;

	@DefaultHandler
	public Resolution index() {

		// redirect any users who are already logged in
		if(getContext().isLoggedIn())
			return new RedirectResolution(IndexActionBean.class);

		return new ForwardResolution(isMobile() ? JOIN_MOBILE_PAGE_VIEW : JOIN_PAGE_VIEW);
	}

	@Override
	public boolean isDisplayTrackerUserNotification() {
		return isMobile() && isPublicMode();
	}

	/**
	 * Handles the submission of the join form.
	 *
	 * @return Resolution
	 */
	public final Resolution join() {
		return joinAction.act(this);
	}

	// getters and setters
	public JoinAction getJoinAction() {
		return joinAction;
	}

	public void setJoinAction(JoinAction joinAction) {
		this.joinAction = joinAction;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * JoinAction
	 *
	 * The action for joining tracktacular.
	 *
	 * @version 1.0
	 * @author Anthony DePalma
	 */
	public static class JoinAction extends AbstractAction<JoinActionBean> {

		@Override
		protected void doAction(JoinActionBean actionBean) {
			Account account = actionBean.getAccount();
			account.setReferrerUserEid(actionBean.getContext().getReferrerUserEid());
			Credentials credentials = actionBean.getAccountFacade().createUserAccount(account, actionBean.getLocale());
			actionBean.getContext().completeLogin(credentials);
			actionBean.getContext().getSession().clearUserPreferences();
		}

		@Override
		protected Resolution getResolution(JoinActionBean actionBean) {
			actionBean.getContext().addSuccessMessage("account.joinSuccess");
			actionBean.setTrackerUserUsername(actionBean.getUserUsername());
			return new RedirectResolution(GettingStartedActionBean.class);
		}

	}

}