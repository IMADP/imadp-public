package com.tracktacular.service.tracker.exercise;

import java.util.List;

import org.joda.time.DateTime;

import com.google.common.collect.Lists;
import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * ExerciseTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class ExerciseTrackerReport extends AbstractTrackerReport {
	private final List<RoutineDto> routines;
	private final List<Routine> lateRoutines;

    // constructor
    public ExerciseTrackerReport(List<RoutineDto> routines) {
    	this.routines = routines;
    	this.lateRoutines = Lists.newArrayList();

    	for(RoutineDto routineDto : routines)
		{
    		Routine routine = routineDto.getRoutine();
			Workout lastWorkout = routineDto.getLastWorkout();

			// if there aren't any workouts or recurrence period, there are no alerts
			if(lastWorkout == null || !routine.isAlertRecurring())
				continue;

			DateTime nextOccurrence = routine.getAlertRecurrence().getNextOccurrence(lastWorkout.getDate());

			if(isDue(nextOccurrence))
				lateRoutines.add(routine);
		}
    }

	@Override
    public boolean isEnabled() {
    	return !routines.isEmpty();
    }

	@Override
	public int getAlertCount() {
		return lateRoutines.size();
	}

	/**
     * Returns the count of all Active routines.
     *
     * @return long
     */
    public long getRoutineCount() {
		return routines.size();
	}

	// getters
	public List<RoutineDto> getRoutines() {
		return routines;
	}

	public List<Routine> getLateRoutines() {
		return lateRoutines;
	}

}