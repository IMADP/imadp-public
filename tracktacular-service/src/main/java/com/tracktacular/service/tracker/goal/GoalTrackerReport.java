package com.tracktacular.service.tracker.goal;

import java.util.List;

import com.google.common.collect.Lists;
import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * GoalTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class GoalTrackerReport extends AbstractTrackerReport {
	private final List<Goal> goals;
	private final List<Goal> lateGoals;
	private final List<Objective> lateObjectives;
	private final int averageProgress;

    // constructor
    public GoalTrackerReport(GoalTrackerPreferences preferences, Goals goals) {
    	this.goals = goals.getGoals();
    	this.lateGoals = Lists.newArrayList();
    	this.lateObjectives = Lists.newArrayList();
    	this.averageProgress = goals.getAverageProgress();

    	for(Goal goal : goals.getGoals())
    	{
    		// add late goals
    		if(preferences.isAlertOnGoalTargetDates())
    			if(isDue(goal.getTargetDate()))
    				lateGoals.add(goal);

			// add late objectives
    		if(preferences.isAlertOnObjectiveTargetDates())
        		for(Objective objective : goal.getObjectives())
	    			if(!objective.isCompleted() && isDue(objective.getTargetDate()))
	    				lateObjectives.add(objective);
    	}

    }

    @Override
	public boolean isEnabled() {
    	return !goals.isEmpty();
    }

    @Override
    public int getAlertCount() {
    	return lateGoals.size() + lateObjectives.size();
    }

    /**
     * Returns the count of all goals.
     *
     * @return long
     */
    public long getGoalCount() {
		return goals.size();
	}

    public long getLateGoalsCount() {
		return lateGoals.size();
	}

	public long getLateObjectivesCount() {
		return lateObjectives.size();
	}

	// getters
    public List<Goal> getGoals() {
		return goals;
	}

    public int getAverageProgress() {
		return averageProgress;
	}

	public List<Goal> getLateGoals() {
		return lateGoals;
	}

	public List<Objective> getLateObjectives() {
		return lateObjectives;
	}

}