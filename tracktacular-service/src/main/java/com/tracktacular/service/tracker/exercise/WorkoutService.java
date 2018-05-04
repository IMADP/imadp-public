package com.tracktacular.service.tracker.exercise;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserService;

/**
 * IWorkoutService
 *
 * Provides common retrieval operations for Workout objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface WorkoutService extends PersistableUserService<Workout> {

	/**
	 * Finds a List of Workouts by user, routine, and criteriaParams.
	 *
	 * @param user
	 * @param routine
	 * @param criteriaParams
	 * @return List<Workout>
	 */
	public List<Workout> findByUser(User user, Routine routine, CriteriaParams<Workout> criteriaParams);

	/**
	 * Finds the count of Workouts by user, and routine.
	 *
	 * @param user
	 * @param routine
	 * @return long
	 */
	public long findCountByUser(User user, Routine routine);

	/**
	 * Clones a previous workout, and saves a copy with identical workout, exercise, and set values.
	 * The date of the workout is not cloned.
	 *
	 * @param originalWorkout
	 */
	public void cloneWorkout(Workout originalWorkout);

	/**
	 * Saves an exercise.
	 *
	 * @param exercise
	 */
	public void saveExercise(Exercise exercise);

	/**
	 * Saves a list of exercises.
	 *
	 * @param exercises
	 */
	public void saveExercises(List<Exercise> exercises);

	/**
	 * Deletes a exercise.
	 *
	 * @param exercise
	 */
	public void deleteExercise(Exercise exercise);

	/**
	 * Saves a entry.
	 *
	 * @param entry
	 */
	public void saveEntry(Entry entry);

	/**
	 * Saves a list of entries.
	 *
	 * @param entries
	 */
	public void saveEntries(List<Entry> entries);

	/**
	 * Deletes a entry.
	 *
	 * @param entry
	 */
	public void deleteEntry(Entry entry);

}