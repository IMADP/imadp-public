package com.tracktacular.web.page;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;


/**
 * MobileActionBean
 *
 * The ActionBean for the mobile page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/mobile")
public class MobileActionBean extends TracktacularActionBean {

	// views
	private static final String MOBILE_PAGE_VIEW = "/WEB-INF/jsp/page/mobilePage.jsp";

	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(MOBILE_PAGE_VIEW);
	}

	@Override
	public boolean isMobile() {
		return false;
	}

}