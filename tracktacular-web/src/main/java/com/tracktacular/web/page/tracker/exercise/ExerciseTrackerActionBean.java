package com.tracktacular.web.page.tracker.exercise;

import java.util.List;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.exercise.Entry;
import com.tracktacular.service.tracker.exercise.Exercise;
import com.tracktacular.service.tracker.exercise.ExerciseTrackerFacade;
import com.tracktacular.service.tracker.exercise.ExerciseTrackerPreferences;
import com.tracktacular.service.tracker.exercise.Routine;
import com.tracktacular.service.tracker.exercise.Workout;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * ExerciseTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class ExerciseTrackerActionBean extends TrackerActionBean<ExerciseTrackerFacade, ExerciseTrackerPreferences> {
	private Routine routine;
	private Workout workout;
	private Exercise exercise;
	private Entry entry;
	private String sortedExercises;
	private String sortedEntries;

	@Override
	public Tracker getTracker() {
		return Tracker.EXERCISE;
	}

	/**
	 * Save or updates a Routine.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveRoutine() {
		Routine routine = getRoutine();
		populatePersistableUser(routine);
		getTrackerFacade().saveRoutine(routine);

		if(routine.isActiveState())
			getContext().addSuccessMessage("exercise.saveRoutine.success", routine.getName());

		else if(routine.isArchivedState())
			getContext().addSuccessMessage("exercise.completeRoutine.success", routine.getName());

		else if(routine.isDeletedState())
			getContext().addSuccessMessage("exercise.deleteRoutine.success", routine.getName());

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Routine.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteRoutine() {
		Routine routine = getRoutine();
		getTrackerFacade().deleteRoutine(routine);
		getContext().addSuccessMessage("exercise.deleteRoutine.success", routine.getName());
		return getAjaxSourceResolution();
	}

	/**
	 * Save or updates a Workout.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveWorkout() {
		Workout workout = getWorkout();
		populatePersistableUser(workout);
		getTrackerFacade().saveWorkout(workout);
		getContext().addSuccessMessage("exercise.saveWorkout.success", getWorkout().getName());
		return getAjaxSourceResolution();
	}

	/**
	 * Clones a Workout.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution cloneWorkout() {
		Workout workout = getWorkout();
		getTrackerFacade().cloneWorkout(workout);
		getContext().addSuccessMessage("exercise.cloneWorkout.success", getWorkout().getName());
		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Workout.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteWorkout() {
		Workout workout = getWorkout();
		getTrackerFacade().deleteWorkout(workout);
		getContext().addSuccessMessage("exercise.deleteWorkout.success", getWorkout().getName());
		return getAjaxSourceResolution();
	}

	/**
	 * Toggles the Workout collapsed property.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution toggleWorkoutCollapse() {
		Workout workout = getWorkout();
		workout.setCollapsed(!workout.isCollapsed());
		getTrackerFacade().saveWorkout(workout);
		return getAjaxSourceResolution();
	}

	/**
	 * Save or updates a Exercise.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveExercise() {
		Exercise exercise = getExercise();
		populatePersistableUser(exercise);
		getTrackerFacade().saveExercise(exercise);
		getContext().addSuccessMessage("exercise.saveExercise.success", exercise.getName());
		resetWorkout();
		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Exercise.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteExercise() {
		Exercise exercise = getExercise();
		getTrackerFacade().deleteExercise(exercise);
		getContext().addSuccessMessage("exercise.deleteExercise.success", exercise.getName());
		resetWorkout();
		return getAjaxSourceResolution();
	}

	/**
	 * Save or updates a Entry.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveEntry() {
		Entry entry = getEntry();
		populatePersistableUser(entry);
		getTrackerFacade().saveEntry(entry);
		getContext().addSuccessMessage("exercise.saveEntry.success");
		resetWorkout();
		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Entry.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteEntry() {
		Entry entry = getEntry();
		getTrackerFacade().deleteEntry(entry);
		getContext().addSuccessMessage("exercise.deleteEntry.success");
		resetWorkout();
		return getAjaxSourceResolution();
	}

	/**
	 * Sorts Exercises.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution sortExercises() {
		List<Exercise> sortedCategoriesList = convertObjectList(sortedExercises, Exercise.class);
		List<Exercise> sortedList = getResortedList(sortedCategoriesList);
		getTrackerFacade().saveExercises(sortedList);
		getContext().addSuccessMessage("exercise.sortExercises.success");
		resetWorkout();
		return getAjaxSourceResolution();
	}

	/**
	 * Sorts Entries.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution sortEntries() {
		List<Entry> sortedEntriesList = convertObjectList(sortedEntries, Entry.class);
		List<Entry> sortedList = getResortedList(sortedEntriesList);
		getTrackerFacade().saveEntries(sortedList);
		getContext().addSuccessMessage("exercise.sortEntries.success");
		resetWorkout();
		return getAjaxSourceResolution();
	}

	/**
	 * Resets the workout by retrieving it from the facade.
	 *
	 */
	protected final void resetWorkout() {
		if(workout != null)
			setWorkout(getTrackerFacade().getWorkout(getContext().getUser(), workout.getUid()));
	}

	// getters and setters
	public ExerciseTrackerFacade getExerciseTrackerFacade() {
		return getTrackerFacade();
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public Routine getRoutine() {
		return routine;
	}

	public void setRoutine(Routine routine) {
		this.routine = routine;
	}

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	public String getSortedExercises() {
		return sortedExercises;
	}

	public void setSortedExercises(String sortedExercises) {
		this.sortedExercises = sortedExercises;
	}

	public String getSortedEntries() {
		return sortedEntries;
	}

	public void setSortedEntries(String sortedEntries) {
		this.sortedEntries = sortedEntries;
	}

}