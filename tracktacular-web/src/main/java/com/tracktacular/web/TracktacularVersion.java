package com.tracktacular.web;

import java.util.Map;

import com.tracktacular.service.tracker.Tracker;


/**
 * TracktacularVersion
 *
 * Provides versioning information for tracktacular resources.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class TracktacularVersion {

    // static keys
	private static final String VERSION_PAGE_KEY = "version.page";
	private static final String VERSION_PAGE_MOBILE_KEY = "version.page.mobile";
	private static final String VERSION_TRACKER_PAGE_KEY_PREFIX = "version.page.tracker.";

    // properties
    private Map<String, String> versions;

    /**
     * Returns the page version.
     *
     * @return String
     */
    public String getPageVersion() {
        return versions.get(VERSION_PAGE_KEY);
    }

    /**
     * Returns the page mobile version.
     *
     * @return String
     */
    public String getPageMobileVersion() {
        return versions.get(VERSION_PAGE_MOBILE_KEY);
    }

    /**
     * Returns the page tracker version.
     *
     * @param tracker
     * @return String
     */
    public String getPageTrackerVersion(Tracker tracker) {
    	if(tracker == null)
    		return null;

    	return versions.get(VERSION_TRACKER_PAGE_KEY_PREFIX + tracker.getName());
    }

    // setter
    public void setVersions(Map<String, String> versions) {
        this.versions = versions;
    }

}