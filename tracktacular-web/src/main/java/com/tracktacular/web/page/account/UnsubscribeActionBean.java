package com.tracktacular.web.page.account;

import javax.annotation.security.RolesAllowed;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.web.stripes.AbstractAction;
import com.tracktacular.web.TracktacularActionBean;


/**
 * UnsubscribeActionBean
 *
 * The ActionBean for the unsubscribe page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/unsubscribe")
@RolesAllowed(value={"user", "admin"})
public class UnsubscribeActionBean extends TracktacularActionBean {

	// views
	private static final String UNSUBSCRIBE_PAGE_VIEW = "/WEB-INF/jsp/page.account/unsubscribePage.jsp";

	// actions
	private UnsubscribeAction unsubscribeAction;

	@DefaultHandler
	public Resolution index() {

		// redirect any users who are already unsubscribed
		if(!getUserSubscription().isActive())
			return new RedirectResolution(AccountActionBean.class);

		return new ForwardResolution(UNSUBSCRIBE_PAGE_VIEW);
	}

	/**
	 * Handles the unsubmission of the unsubscribe form.
	 *
	 * @return Resolution
	 */
	public final Resolution unsubscribe() {
		return unsubscribeAction.act(this);
	}

	// getters and setters
	public UnsubscribeAction getUnsubscribeAction() {
		return unsubscribeAction;
	}

	public void setUnsubscribeAction(UnsubscribeAction unsubscribeAction) {
		this.unsubscribeAction = unsubscribeAction;
	}

	/**
	 * UnsubscribeAction
	 *
	 * The action for unsubscribing tracktacular.
	 *
	 * @version 1.0
	 * @author Anthony DePalma
	 */
	public static class UnsubscribeAction extends AbstractAction<UnsubscribeActionBean> {

		@Override
		protected void doAction(UnsubscribeActionBean actionBean) {
			actionBean.getAccountFacade().cancelSubscription(actionBean.getAccountFacade().getSubscription(actionBean.getUser()));
			actionBean.getContext().getSession().clearUserSubscription();
		}

		@Override
		protected Resolution getResolution(UnsubscribeActionBean actionBean) {
			actionBean.getContext().addSuccessMessage("account.unsubscription.success");
			return new RedirectResolution(AccountActionBean.class);
		}

		@Override
		protected boolean isSessionTokenRequired() {
			return true;
		}

	}

}