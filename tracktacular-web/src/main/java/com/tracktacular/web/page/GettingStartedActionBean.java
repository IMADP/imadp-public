package com.tracktacular.web.page;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;


/**
 * GettingStartedActionBean
 *
 * The ActionBean for the getting started page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/getting-started")
public class GettingStartedActionBean extends TracktacularActionBean {

	// views
	private static final String GETTING_STARTED_PAGE_VIEW = "/WEB-INF/jsp/page/gettingStartedPage.jsp";
	private static final String GETTING_STARTED_MOBILE_PAGE_VIEW = "/WEB-INF/jsp/page/gettingStartedPageMobile.jsp";

	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(isMobile() ? GETTING_STARTED_MOBILE_PAGE_VIEW : GETTING_STARTED_PAGE_VIEW);
	}

}