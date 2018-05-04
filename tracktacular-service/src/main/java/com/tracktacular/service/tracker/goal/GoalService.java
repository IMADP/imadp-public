package com.tracktacular.service.tracker.goal;

import com.imadp.service.user.PersistableUserService;

/**
 * GoalService
 *
 * Provides common retrieval operations for Goal objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface GoalService extends PersistableUserService<Goal> {

	/**
	 * Saves an objective.
	 *
	 * @param objective
	 */
	public void saveObjective(Objective objective);

	/**
	 * Deletes a objective.
	 *
	 * @param objective
	 */
	public void deleteObjective(Objective objective);

}