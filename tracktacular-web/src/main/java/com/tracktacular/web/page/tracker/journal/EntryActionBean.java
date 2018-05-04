package com.tracktacular.web.page.tracker.journal;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;
import com.tracktacular.web.page.PageNotFoundActionBean;


/**
 * EntryActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-journal/entry-{entry=null}/{titleSlug=null}")
public class EntryActionBean extends JournalTrackerActionBean {

	@Override
	public Resolution index() {
		if(getEntry() == null)
			return new ForwardResolution(PageNotFoundActionBean.class);

		return super.index();
	}

	@Override
	public String getTrackerPageTitle() {
		return getEntry().getTitle();
	}

	@Override
	protected Class<? extends TracktacularActionBean> getUserToggleClass() {
		return getDefaultActionBean();
	}

}