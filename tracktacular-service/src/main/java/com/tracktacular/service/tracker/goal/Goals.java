package com.tracktacular.service.tracker.goal;

import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.util.CollectionUtils;

import com.imadp.core.AbstractSerializable;


/**
 * Goals
 *
 * A collection of Goal entities.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Goals extends AbstractSerializable {
	private final List<Goal> goals;
    private final DateTime minDate;
    private final DateTime maxDate;

    // constructor
    public Goals(List<Goal> goals) {
    	DateTime min = null;
    	DateTime max = null;

    	for(Goal goal : goals)
    	{
    		if(min == null || goal.getStartDate().isBefore(min))
    			min = goal.getStartDate();

    		if(max == null || goal.getTargetDate().isAfter(max))
    			max = goal.getTargetDate();
    	}

    	this.minDate = min == null ? null : min.minusWeeks(1);
    	this.maxDate = max == null ? null : max.plusWeeks(1);
        this.goals = Collections.unmodifiableList(goals);
    }

    /**
     * Returns the count of all goals.
     *
     * @return in
     */
	public int getGoalCount() {
		return goals.size();
	}

    /**
	 * Returns the average progress for all active goals.
	 *
	 * @return int
	 */
	public int getAverageProgress() {
		if(CollectionUtils.isEmpty(goals))
			return 0;

		int progress = 0;

		for(Goal goal : goals)
			progress += goal.getProgress();

		return progress / goals.size();
	}

	// getters
	public List<Goal> getGoals() {
		return goals;
	}

	public DateTime getMinDate() {
		return minDate;
	}

	public DateTime getMaxDate() {
		return maxDate;
	}

}