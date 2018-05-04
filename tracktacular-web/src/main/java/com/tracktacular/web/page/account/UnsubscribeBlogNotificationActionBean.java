package com.tracktacular.web.page.account;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;
import com.tracktacular.web.page.TracktacularReportActionBean;


/**
 * UnsubscribeActionBean
 *
 * The ActionBean for the unsubscribe page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/unsubscribe-blog-notification/{preferencesUid}")
public class UnsubscribeBlogNotificationActionBean extends TracktacularActionBean {

	// actions
	private String preferencesUid;

	@DefaultHandler
	public Resolution index() {
		getAccountFacade().unsubscribeBlogNotification(preferencesUid);
		getContext().getSession().clearUserPreferences();
		getContext().addSuccessMessage("account.unsubscribeBlogNotification.success");
		return new RedirectResolution(getContext().isLoggedIn() ? AccountActionBean.class : TracktacularReportActionBean.class);
	}

	// getters and setters
	public String getPreferencesUid() {
		return preferencesUid;
	}

	public void setPreferencesUid(String preferencesUid) {
		this.preferencesUid = preferencesUid;
	}

}