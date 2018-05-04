package com.tracktacular.web.page.tracker.tv;

import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.tracker.tv.Shows;


/**
 * ShowsActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-tv/shows/by-{sortProperty=title}")
public class ShowsActionBean extends TvTrackerActionBean {

	// properties
	private String sortProperty;
	private Shows shows;

	/**
	 * Returns a list of shows.
	 *
	 * @return Shows
	 */
	public Shows getShows() {
		if(shows == null)
			shows = getTrackerFacade().findActiveShows(getTrackerUser(), sortProperty);

		return shows;
	}

	// getter and setters
	public String getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}

}