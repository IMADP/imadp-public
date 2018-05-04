package com.tracktacular.web.page;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;


/**
 * PricingActionBean
 *
 * The ActionBean for the pricing page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/pricing")
public class PricingActionBean extends TracktacularActionBean {

	// views
	private static final String PRICING_PAGE_VIEW = "/WEB-INF/jsp/page/pricingPage.jsp";

	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(PRICING_PAGE_VIEW);
	}

}