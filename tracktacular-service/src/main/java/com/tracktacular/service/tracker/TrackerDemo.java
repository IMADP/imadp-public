
package com.tracktacular.service.tracker;

import com.imadp.service.user.User;

/**
 * TrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface TrackerDemo {

	/**
	 * Inserts tracker demo data.
	 *
	 * @param user
	 * @param preferences
	 */
	public void insertDemoData(User user, TrackerPreferences preferences);

}
