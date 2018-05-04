package com.tracktacular.web.page.tracker.goal;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.goal.Goal;
import com.tracktacular.service.tracker.goal.GoalTrackerFacade;
import com.tracktacular.service.tracker.goal.GoalTrackerPreferences;
import com.tracktacular.service.tracker.goal.Objective;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * GoalTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class GoalTrackerActionBean extends TrackerActionBean<GoalTrackerFacade, GoalTrackerPreferences> {
	private Goal goal;
	private Objective objective;

	@Override
	public Tracker getTracker() {
		return Tracker.GOAL;
	}

	/**
	 * Save or updates a Goal.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveGoal() {
		Goal goal = getGoal();
		populatePersistableUser(goal);
		getTrackerFacade().saveGoal(goal);

		if(goal.isActiveState())
			getContext().addSuccessMessage("goal.saveGoal.success", goal.getName());

		else if(goal.isArchivedState())
			getContext().addSuccessMessage("goal.completeGoal.success", goal.getName());

		else if(goal.isDeletedState())
			getContext().addSuccessMessage("goal.deleteGoal.success", goal.getName());

		return getAjaxSourceResolution();
	}

	/**
	 * Toggles the Goal collapsed property.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution toggleGoalCollapse() {
		Goal goal = getGoal();
		goal.setCollapsed(!goal.isCollapsed());
		getTrackerFacade().saveGoal(goal);
		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Goal.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteGoal() {
		Goal goal = getGoal();
		getTrackerFacade().deleteGoal(goal);
		getContext().addSuccessMessage("goal.deleteGoal.success", goal.getName());
		return getAjaxSourceResolution();
	}

	/**
	 * Save or updates a Objective.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveObjective() {
		Objective objective = getObjective();
		populatePersistableUser(objective);
		getTrackerFacade().saveObjective(objective);

		if(objective.isActiveState())
			getContext().addSuccessMessage("goal.saveObjective.success", objective.getName());

		else if(objective.isArchivedState())
			getContext().addSuccessMessage("goal.completeObjective.success", objective.getName());

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Objective.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteObjective() {
		Objective objective = getObjective();
		getTrackerFacade().deleteObjective(objective);
		getContext().addSuccessMessage("goal.deleteObjective.success", objective.getName());
		return getAjaxSourceResolution();
	}

	// getters and setters
	public GoalTrackerFacade getGoalTrackerFacade() {
		return getTrackerFacade();
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public Objective getObjective() {
		return objective;
	}

	public void setObjective(Objective objective) {
		this.objective = objective;
	}

}