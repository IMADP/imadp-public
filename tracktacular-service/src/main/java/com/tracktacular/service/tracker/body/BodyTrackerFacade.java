package com.tracktacular.service.tracker.body;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * ITracktacularBodyTrackerFacade
 *
 * Provides functionality for task tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface BodyTrackerFacade extends TrackerFacade {

	/**
	 * Gets a body by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return Body
	 */
	public Body getBody(User user, String uid);

	/**
	 * Finds a List of active Bodies for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Body>
	 */
	public List<Body> findActiveBodies(User user, CriteriaParams<Body> criteriaParams);

	/**
	 * Finds the count of all active Bodies for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findActiveBodyCount(User user);

	/**
	 * Finds a List of deleted Bodies for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Body>
	 */
	public List<Body> findDeletedBodies(User user, CriteriaParams<Body> criteriaParams);

	/**
	 * Finds the count of all deleted Bodies for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedBodyCount(User user);

	/**
	 * Saves a Body.
	 *
	 * @param body
	 */
	public void saveBody(Body body);

	/**
	 * Removes a Body.
	 *
	 * @param body
	 */
	public void deleteBody(Body body);

}