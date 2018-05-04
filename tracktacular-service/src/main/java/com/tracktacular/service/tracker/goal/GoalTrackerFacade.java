package com.tracktacular.service.tracker.goal;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * ITracktacularGoalTrackerFacade
 *
 * Provides functionality for goal tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface GoalTrackerFacade extends TrackerFacade {

	/**
	 * Gets a Goal by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return <T> goal or <code>null</code> if no goal was found.
	 */
	public Goal getGoal(User user, String uid);

	/**
	 * Saves or updates a Goal.
	 *
	 * @param goal
	 */
	public void saveGoal(Goal goal);

	/**
	 * Deletes a Goal.
	 *
	 * @param goal
	 */
	public void deleteGoal(Goal goal);

	/**
	 * Finds a List of active Goals for a User.
	 *
	 * @param user
	 * @return Goals
	 */
	public Goals findActiveGoals(User user);

	/**
	 * Finds a List of completed Goals for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Goal>
	 */
	public List<Goal> findCompletedGoals(User user, CriteriaParams<Goal> criteriaParams);

	/**
	 * Finds the count of all completed Goals for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findCompletedGoalCount(User user);

	/**
	 * Finds a List of deleted Goals for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<Goal>
	 */
	public List<Goal> findDeletedGoals(User user, CriteriaParams<Goal> criteriaParams);

	/**
	 * Finds the count of all deleted Goals for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedGoalCount(User user);

	/**
	 * Gets a Objective by user and id.
	 *
	 * @param user
	 * @param uid
	 * @return <T> objective or <code>null</code> if no objective was found.
	 */
	public Objective getObjective(User user, String uid);

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