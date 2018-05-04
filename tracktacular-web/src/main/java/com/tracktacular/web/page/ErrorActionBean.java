package com.tracktacular.web.page;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;


/**
 * ErrorPage
 *
 * The ActionBean for the error page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/error")
public class ErrorActionBean extends TracktacularActionBean {

	// views
	private static final String ERROR_PAGE_VIEW = "/WEB-INF/jsp/page/errorPage.jsp";

	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(ERROR_PAGE_VIEW);
	}

	@Override
	public boolean isError() {
		return true;
	}

}