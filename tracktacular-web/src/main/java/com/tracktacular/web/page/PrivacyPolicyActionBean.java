package com.tracktacular.web.page;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;


/**
 * PrivacyPolicyPage
 * 
 * The ActionBean for the privacy policy page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/privacy-policy")
public class PrivacyPolicyActionBean extends TracktacularActionBean {

	// views
	private static final String PRIVACY_POLICY_PAGE_VIEW = "/WEB-INF/jsp/page/privacyPolicyPage.jsp";
	
	@DefaultHandler
	public Resolution index() {
		return new ForwardResolution(PRIVACY_POLICY_PAGE_VIEW);
	}
	
}