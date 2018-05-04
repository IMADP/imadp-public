package com.tracktacular.web.page.tracker.exercise;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.tracktacular.service.tracker.exercise.Routine;
import com.tracktacular.service.tracker.exercise.RoutineDto;


/**
 * RoutinesActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-exercise/routines")
public class RoutinesActionBean extends ExerciseTrackerActionBean {
	private List<RoutineDto> activeRoutines;

	/**
	 * Returns the count of active routines.
	 *
	 * @return long
	 */
	public long getRoutinesWorkoutCount() {
		long count = 0;

		for(RoutineDto routineDto : getRoutines())
			count+= routineDto.getWorkoutCount();

		return count;
	}

	/**
	 * Returns a list of active routines.
	 *
	 * @return List<RoutineDto>
	 */
	public List<RoutineDto> getRoutines() {
		if(activeRoutines == null)
			activeRoutines = getTrackerFacade().findActiveRoutines(getTrackerUser(), CriteriaParams.<Routine>of(Results.ALL));

		return activeRoutines;
	}

}