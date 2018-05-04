package com.tracktacular.web.page;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;


/**
 * IndexMobileActionBean
 *
 * The ActionBean for the mobile index page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/m")
public class IndexMobileActionBean extends TracktacularActionBean {

	@DefaultHandler
	public Resolution index() {
		setMobile(true);
		return new ForwardResolution(IndexActionBean.class);
	}

	@Override
	public boolean isDisplayTrackerUserNotification() {
		return isMobile() && isPublicMode();
	}

}