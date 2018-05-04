package com.tracktacular.service.tracker;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Set;

import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;


/**
 * TracktacularTrackersFacade
 *
 * Facade encapsulating access to the individual trackers for Tracktacular.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface TracktacularTrackersFacade {

	/**
	 * Returns a collection of all  tracker facades.
	 *
	 * @return Collection<TrackerFacade>
	 */
	public Collection<TrackerFacade> getTrackerFacades();

	/**
	 * Returns the tracker facade for the given tracker.
	 *
	 * @param <V>
	 * @param tracker
	 * @return V
	 */
	public <V extends TrackerFacade> V getTrackerFacade(Tracker tracker);

	/**
     * Finds all distinct users for a tracker.
     *
     * @param tracker
	 * @return Set<User>
     */
    public Set<User> findDistinctUsers(Tracker tracker);

    /**
     * Finds all recent distinct users for a tracker.
     *
     * @param tracker
	 * @return Set<User>
     */
    public Set<User> findRecentDistinctUsers(Tracker tracker);

	/**
	 * Returns an export of all tracker data as a zip file with csv files for a given user.
	 *
	 * @param user
	 * @param outputStream
	 * @throws IOException
	 */
	public void getTrackerExport(User user, OutputStream outputStream) throws IOException;

	/**
	 * Generates a TracktacularReport based on a users's preferences.
	 *
	 * @param username
	 * @param preferences
	 * @param publicOnly
	 * @return TracktacularReport
	 */
	public TracktacularReport generateTracktacularReport(String username, Preferences preferences, boolean publicOnly);

}