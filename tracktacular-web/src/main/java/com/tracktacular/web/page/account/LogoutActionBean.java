package com.tracktacular.web.page.account;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;
import com.tracktacular.web.page.IndexActionBean;
import com.tracktacular.web.page.IndexMobileActionBean;


/**
 * LogoutPage
 *
 * The ActionBean for the logout page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/logout")
public class LogoutActionBean extends TracktacularActionBean {

	@DefaultHandler
	public Resolution index() {
		boolean isMobile = isMobile();
		getContext().logout();
		return new RedirectResolution(isMobile ? IndexMobileActionBean.class : IndexActionBean.class);
	}

}