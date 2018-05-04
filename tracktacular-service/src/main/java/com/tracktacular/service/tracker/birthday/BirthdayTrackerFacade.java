package com.tracktacular.service.tracker.birthday;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * BirthdayTrackerFacade
 *
 * Provides functionality for birthday tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface BirthdayTrackerFacade extends TrackerFacade {

	/**
	 * Gets a birthday by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Birthday
	 */
	public Birthday getBirthday(User user, String uid);

	/**
	 * Saves a birthday.
	 *
	 * @param birthday
	 */
	public void saveBirthday(Birthday birthday);

	/**
	 * Deletes a birthday.
	 *
	 * @param birthday
	 */
	public void deleteBirthday(Birthday birthday);

	/**
	 * Finds a List of active Birthdays for a User.
	 *
	 * @param user
	 * @return Birthdays
	 */
	public Birthdays findActiveBirthdays(User user);

	/**
	 * Finds a List of deleted Birthdays for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Birthday>
	 */
	public List<Birthday> findDeletedBirthdays(User user, CriteriaParams<Birthday> criteriaParams);

	/**
	 * Finds the count of all deleted Birthdays for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedBirthdayCount(User user);

}