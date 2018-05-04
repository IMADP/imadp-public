package com.tracktacular.web.page;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;


/**
 * TermsAndConditionsPage
 * 
 * The ActionBean for the terms and conditions page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/terms-and-conditions")
public class TermsAndConditionsActionBean extends TracktacularActionBean {

	// views
	private static final String TERMS_AND_CONDITIONS_PAGE_VIEW = "/WEB-INF/jsp/page/termsAndConditionsPage.jsp";
	
	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(TERMS_AND_CONDITIONS_PAGE_VIEW);
	}
	
}