package com.tracktacular.service.tracker.cholesterol;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * ITracktacularCholesterolTrackerFacade
 *
 * Provides functionality for task tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface CholesterolTrackerFacade extends TrackerFacade {

	/**
	 * Gets a cholesterol by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Cholesterol
	 */
	public Cholesterol getCholesterol(User user, String uid);

	/**
	 * Finds a List of active Cholesterols for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Cholesterol>
	 */
	public List<Cholesterol> findActiveCholesterols(User user, CriteriaParams<Cholesterol> criteriaParams);

	/**
	 * Finds the count of all active Cholesterols for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findActiveCholesterolCount(User user);

	/**
	 * Finds a List of deleted Cholesterols for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Cholesterol>
	 */
	public List<Cholesterol> findDeletedCholesterols(User user, CriteriaParams<Cholesterol> criteriaParams);

	/**
	 * Finds the count of all deleted Cholesterols for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedCholesterolCount(User user);

	/**
	 * Saves a Cholesterol.
	 *
	 * @param cholesterol
	 */
	public void saveCholesterol(Cholesterol cholesterol);

	/**
	 * Removes a Cholesterol.
	 *
	 * @param cholesterol
	 */
	public void deleteCholesterol(Cholesterol cholesterol);

}