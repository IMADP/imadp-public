package com.tracktacular.web.page.tracker.dream;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;
import com.tracktacular.web.page.PageNotFoundActionBean;


/**
 * DreamActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-dream/dream-{dream=null}/{titleSlug=null}")
public class DreamActionBean extends DreamTrackerActionBean {

	@Override
	public Resolution index() {
		if(getDream() == null)
			return new ForwardResolution(PageNotFoundActionBean.class);

		return super.index();
	}

	@Override
	public String getTrackerPageTitle() {
		return getDream().getTitle();
	}

	@Override
	protected Class<? extends TracktacularActionBean> getUserToggleClass() {
		return getDefaultActionBean();
	}

}