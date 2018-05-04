package com.tracktacular.web.page;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;


/**
 * IndexPage
 *
 * The ActionBean for the index page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/index")
public class IndexActionBean extends TracktacularActionBean {

	// views
	private static final String INDEX_PAGE_VIEW = "/WEB-INF/jsp/page/indexPage.jsp";
	private static final String INDEX_MOBILE_PAGE_VIEW = "/WEB-INF/jsp/page/indexPageMobile.jsp";

	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(isMobile() ? INDEX_MOBILE_PAGE_VIEW : INDEX_PAGE_VIEW);
	}

	@Override
	public boolean isDisplayTrackerUserNotification() {
		return isMobile() && isPublicMode();
	}

}