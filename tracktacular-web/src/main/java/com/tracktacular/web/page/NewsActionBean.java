package com.tracktacular.web.page;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;


/**
 * NewsActionBean
 *
 * The ActionBean for the news page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/news")
public class NewsActionBean extends TracktacularActionBean {

	// views
	private static final String NEWS_PAGE_VIEW = "/WEB-INF/jsp/page/newsPage.jsp";

	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(NEWS_PAGE_VIEW);
	}

}