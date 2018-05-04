package com.tracktacular.web.page;

import java.util.Map;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.google.common.collect.Maps;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TracktacularReport;
import com.tracktacular.service.tracker.TracktacularTrackersFacade;
import com.tracktacular.web.TracktacularActionBean;


/**
 * TracktacularReportPage
 *
 * The ActionBean for the tracktacular report page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracktacular-report")
public class TracktacularReportActionBean extends TracktacularActionBean {

	@SpringBean
	private TracktacularTrackersFacade trackersFacade;

	// views
	public static final String TRACKTACULAR_REPORT_PAGE_VIEW = "/WEB-INF/jsp/page/tracktacularReportPage.jsp";

	// properties
	private TracktacularReport tracktacularReport;
	private Map<Tracker, String> trackersWithReports;

	@Override
	public boolean isDisplayTrackerUserNotification() {
		return isPublicMode();
	}

	@Override
	public boolean isMobile() {
		return false;
	}

	@DefaultHandler
	public Resolution index() {

		// if no tracker user was found, redirect to a page not found
		if(getTrackerUser() == null)
			return new ForwardResolution(PageNotFoundActionBean.class);

		return new ForwardResolution(TRACKTACULAR_REPORT_PAGE_VIEW);
	}

	/**
	 * Returns a TracktacularReport for the current user.
	 *
	 * @return TracktacularReport
	 */
	public TracktacularReport getTracktacularReport() {
		if(tracktacularReport == null)
			tracktacularReport = trackersFacade.generateTracktacularReport(getTrackerUserUsername(), getTrackerUserPreferences(), isPublicMode());

		return tracktacularReport;
	}

	/**
	 * Returns a map of all trackers with reports along with their associated page tracker version.
	 *
	 * @return Map<Tracker, String>
	 */
	public Map<Tracker, String> getTrackersWithReports() {
		if(trackersWithReports == null)
		{
			trackersWithReports = Maps.newHashMap();

			for(Tracker tracker : getTrackerUserPreferences().getTrackers().getTrackersWithReports(isPublicMode()))
				trackersWithReports.put(tracker, getTracktacularVersion().getPageTrackerVersion(tracker));
		}

		return trackersWithReports;
	}

}