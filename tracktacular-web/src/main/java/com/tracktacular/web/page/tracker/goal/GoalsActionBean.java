package com.tracktacular.web.page.tracker.goal;

import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.tracker.goal.Goals;


/**
 * GoalsActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-goal/goals/goal-{selectedGoal=all}")
public class GoalsActionBean extends GoalTrackerActionBean {
	private Goals goals;
	private String selectedGoal;

	/**
	 * Returns a list of active goals.
	 *
	 * @return Goals
	 */
	public Goals getGoals() {
		if(goals == null)
			goals = getTrackerFacade().findActiveGoals(getTrackerUser());

		return goals;
	}

	// getter and setters
	public String getSelectedGoal() {
		return selectedGoal;
	}

	public void setSelectedGoal(String selectedGoal) {
		this.selectedGoal = selectedGoal;
	}

}