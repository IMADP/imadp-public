package com.tracktacular.web.page.tracker;

import com.tracktacular.service.tracker.Tracker;


/**
 * TrackerPage
 *
 * Interface implemented by a tracker's page enumeration.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public interface TrackerPage {

	/**
	 * Returns the name of the tracker page.
	 *
	 * @return String
	 */
	public String name();

	/**
	 * Returns the associated tracker.
	 *
	 * @return Tracker
	 */
	public Tracker getTracker();

	/**
	 * Returns the default tracker page.
	 *
	 * @return TrackerPage
	 */
	public TrackerPage getDefault();

	/**
	 * Returns the tracker page key associated with the trackerPage.
	 *
	 * @return String
	 */
	public String getTrackerPageKey();

	/**
	 * Returns the associated actionBean.
	 *
	 * @return Class<? extends TrackerActionBean<?,?>>
	 */
	public Class<? extends TrackerActionBean<?,?>> getActionBean();

}