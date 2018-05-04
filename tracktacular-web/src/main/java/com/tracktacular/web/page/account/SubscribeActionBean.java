package com.tracktacular.web.page.account;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.joda.time.DateTime;

import com.imadp.web.stripes.AbstractAction;
import com.stripe.exception.CardException;
import com.tracktacular.web.TracktacularActionBean;


/**
 * SubscribeActionBean
 *
 * The ActionBean for the subscribe page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/subscribe")
@RolesAllowed(value={"user", "admin"})
public class SubscribeActionBean extends TracktacularActionBean {

	// views
	private static final String SUBSCRIBE_PAGE_VIEW = "/WEB-INF/jsp/page.account/subscribePage.jsp";

	// actions
	private String customerId;
	private SubscribeAction subscribeAction;

	@DefaultHandler
	public Resolution index() {

		// redirect any users who are already subscribed
		if(getUserSubscription().isActive())
		{
			getContext().addErrorMessage("account.alreadySubscribed");
			return new RedirectResolution(AccountActionBean.class);
		}

		// redirect if the user's email is not verified
		if(!getContext().isEmailVerified())
		{
			getContext().addErrorMessage("account.verifyEmailBeforeSubscribing");
			return new RedirectResolution(AccountActionBean.class);
		}

		return new ForwardResolution(SUBSCRIBE_PAGE_VIEW);
	}

	/**
	 * Handles the submission of the subscribe form.
	 *
	 * @return Resolution
	 */
	public final Resolution subscribe() {
		return subscribeAction.act(this);
	}

	/**
	 * Returns a list of expiration months.
	 *
	 * @return List<Integer>
	 */
	public List<Integer> getExpirationMonths() {
		List<Integer> expirationMonths = new ArrayList<>(12);

		for(int i=1; i<=12; i++)
			expirationMonths.add(i);

		return expirationMonths;
	}

	/**
	 * Returns a list of expiration years.
	 *
	 * @return List<Integer>
	 */
	public List<Integer> getExpirationYears() {
		List<Integer> expirationYears = new ArrayList<>(10);
		DateTime dateTime = new DateTime();

		for(int i=0; i<10; i++)
			expirationYears.add(dateTime.getYear() + i);

		return expirationYears;
	}

	// getters and setters
	public SubscribeAction getSubscribeAction() {
		return subscribeAction;
	}

	public void setSubscribeAction(SubscribeAction subscribeAction) {
		this.subscribeAction = subscribeAction;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * SubscribeAction
	 *
	 * The action for subscribing tracktacular.
	 *
	 * @version 1.0
	 * @author Anthony DePalma
	 */
	public static class SubscribeAction extends AbstractAction<SubscribeActionBean> {
		private String customerId;
		private CardException cardException;

		@Override
		protected void doAction(SubscribeActionBean actionBean) {
			this.customerId = actionBean.getCustomerId();

			try
			{
				actionBean.getAccountFacade().updateSubscription(actionBean.getUserSubscription(), customerId);
				actionBean.getContext().getSession().clearUserSubscription();
				actionBean.getContext().removeReferrer();
			}
			catch (CardException cardException)
			{
				this.cardException = cardException;
			}
		}

		@Override
		protected Resolution getResolution(SubscribeActionBean actionBean) {

			// redirect with a declined message
			if(cardException != null)
			{
				actionBean.getContext().addErrorMessage("account.subscription.denied", cardException.getMessage());
				return new RedirectResolution(SubscribeActionBean.class);
			}

			actionBean.getContext().addSuccessMessage("account.subscription.success");
			return new RedirectResolution(AccountActionBean.class);
		}

		@Override
		protected boolean isSessionTokenRequired() {
			return true;
		}

	}

}