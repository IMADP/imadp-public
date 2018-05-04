package com.tracktacular.service.admin;

import com.imadp.service.blog.BlogCategory;
import com.imadp.service.blog.BlogEntry;
import com.imadp.service.metrics.MetricsSummaries;
import com.tracktacular.service.admin.report.StatusReport;
import com.tracktacular.service.tracker.Tracker;



/**
 * TracktacularAdminFacade
 *
 * Facade encapsulating the admin operations for Tracktacular.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface TracktacularAdminFacade {

	/**
	 * Clears all application caches.
	 *
	 */
	public void clearAllCaches();

	/**
	 * Inserts all demo data for all trackers.
	 *
	 */
	public void insertTrackerDemoData();

	/**
	 * Inserts all demo data for a given tracker.
	 *
	 * @param tracker
	 */
	public void insertTrackerDemoData(Tracker tracker);

	/**
	 * Synchronizes all user's steam library and game tracker library.
	 *
	 */
	public void synchronizeSteamGames();

	/**
	 * Generates a status report.
	 *
	 * @return StatusReport
	 */
	public StatusReport generateStatusReport();

	/**
	 * Generates and sends a status report.
	 *
	 */
	public void generateAndSendStatusReport();

	/**
	 * Generates and sends all Tracktacular Reports for users who opted to receive it.
	 *
	 */
	public void generateAndSendTracktacularReports();

	/**
	 * Saves a blog category.
	 *
	 * @param blogCategory
	 */
	public void saveBlogCategory(BlogCategory blogCategory);

	/**
	 * Deletes a blog category.
	 *
	 * @param blogCategory
	 */
	public void deleteBlogCategory(BlogCategory blogCategory);

	/**
	 * Saves a blog entry.
	 *
	 * @param blogEntry
	 */
	public void saveBlogEntry(BlogEntry blogEntry);

	/**
	 * Deletes a blog entry.
	 *
	 * @param blogEntry
	 */
	public void deleteBlogEntry(BlogEntry blogEntry);

	/**
	 * Sends blog email notifications to users and returns the amount of emails sent.
	 *
	 * @param blogEntry
	 * @return int
	 */
	public int sendBlogEntryNotification(BlogEntry blogEntry);

	/**
	 * Returns the recent metrics summary for the day.
	 *
	 * @return MetricsSummaries
	 */
	public MetricsSummaries getRecentMetricsSummary();

}