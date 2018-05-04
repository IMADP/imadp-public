package com.tracktacular.web.page.account;

import java.io.IOException;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.web.stripes.resolution.EmptyResolution;
import com.tracktacular.web.TracktacularActionBean;


/**
 * SubscriptionEventActionBean
 *
 * The ActionBean for the subscribe page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/subscription-event")
public class SubscriptionEventActionBean extends TracktacularActionBean {

	@Override
	protected boolean isHandleSessionExpirationOnPost() {
		return false;
	}

	@DefaultHandler
	public Resolution index() throws IOException {
		getAccountFacade().processSubscriptionEvent(getContext().getRequest().getReader());
		return EmptyResolution.get();
	}

}