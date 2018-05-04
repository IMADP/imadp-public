package com.tracktacular.web.page;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;


/**
 * FaqsActionBean
 *
 * The ActionBean for the faqs page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/faqs")
public class FaqsActionBean extends TracktacularActionBean {

	// views
	private static final String FAQS_PAGE_VIEW = "/WEB-INF/jsp/page/faqsPage.jsp";

	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(FAQS_PAGE_VIEW);
	}

}