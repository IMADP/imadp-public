package com.tracktacular.web.page;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;


/**
 * PageNotFoundPage
 *
 * The ActionBean for the 404 page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/page-not-found")
public class PageNotFoundActionBean extends TracktacularActionBean {

	// views
	private static final String PAGE_NOT_FOUND_PAGE_VIEW = "/WEB-INF/jsp/page/pageNotFoundPage.jsp";

	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(PAGE_NOT_FOUND_PAGE_VIEW);
	}

}