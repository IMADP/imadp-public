package com.tracktacular.web.page;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;


/**
 * ReferralActionBean
 *
 * The ActionBean for the referral page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/r{referrerUserEid}/")
public class ReferralActionBean extends TracktacularActionBean {
	private String referrerUserEid;

	@DefaultHandler
	public Resolution index() {
		getContext().setReferrerUserEid(referrerUserEid);
		return new RedirectResolution(IndexActionBean.class);
	}

	// getters and setters
	public String getReferrerUserEid() {
		return referrerUserEid;
	}

	public void setReferrerUserEid(String referrerUserEid) {
		this.referrerUserEid = referrerUserEid;
	}

}