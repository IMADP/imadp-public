package com.tracktacular.service.tracker.blood;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * BloodTrackerFacade
 *
 * Provides functionality for blood pressure tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface BloodTrackerFacade extends TrackerFacade {

	/**
	 * Gets a bloodPressure by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return BloodPressure
	 */
	public BloodPressure getBloodPressure(User user, String uid);

	/**
	 * Finds a List of active BloodPressures for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<BloodPressure>
	 */
	public List<BloodPressure> findActiveBloodPressures(User user, CriteriaParams<BloodPressure> criteriaParams);

	/**
	 * Finds the count of all active BloodPressures for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findActiveBloodPressureCount(User user);

	/**
	 * Finds a List of deleted BloodPressures for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<BloodPressure>
	 */
	public List<BloodPressure> findDeletedBloodPressures(User user, CriteriaParams<BloodPressure> criteriaParams);

	/**
	 * Finds the count of all deleted BloodPressures for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedBloodPressureCount(User user);

	/**
	 * Saves a BloodPressure.
	 *
	 * @param bloodPressure
	 */
	public void saveBloodPressure(BloodPressure bloodPressure);

	/**
	 * Removes a BloodPressure.
	 *
	 * @param bloodPressure
	 */
	public void deleteBloodPressure(BloodPressure bloodPressure);

}