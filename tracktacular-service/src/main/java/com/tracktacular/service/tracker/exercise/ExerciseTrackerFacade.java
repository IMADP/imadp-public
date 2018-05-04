package com.tracktacular.service.tracker.exercise;

import java.util.List;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.TrackerFacade;

/**
 * ExerciseTrackerFacade
 *
 * Provides functionality for exercise tracking.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface ExerciseTrackerFacade extends TrackerFacade {

	/**
	 * Gets a routine by user and id.
	 *
	 * @param uid
	 * @param user
	 * @return Routine
	 */
	public Routine getRoutine(User user, String uid);

	/**
	 * Saves a Routine.
	 *
	 * @param routine
	 */
	public void saveRoutine(Routine routine);

	/**
	 * Deletes a Routine.
	 *
	 * @param routine
	 */
	public void deleteRoutine(Routine routine);

	/**
	 * Finds a List of active RoutineDtos for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<RoutineDto>
	 */
	public List<RoutineDto> findActiveRoutines(User user, CriteriaParams<Routine> criteriaParams);

	/**
	 * Finds the count of all active Routines for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findActiveRoutineCount(User user);

	/**
	 * Finds a List of completed RoutineDtos for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<RoutineDto>
	 */
	public List<RoutineDto> findCompletedRoutines(User user, CriteriaParams<Routine> criteriaParams);

	/**
	 * Finds the count of all completed Routines for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findCompletedRoutineCount(User user);

	/**
	 * Finds a List of deleted RoutineDtos for a User.
	 *
	 * @param user
	 * @param criteriaParams
	 * @return List<RoutineDto>
	 */
	public List<RoutineDto> findDeletedRoutines(User user, CriteriaParams<Routine> criteriaParams);

	/**
	 * Finds the count of all deleted Routines for a user
	 *
	 * @param user
	 * @return long
	 */
	public long findDeletedRoutineCount(User user);

	/**
	 * Gets a workout by user and id
	 *
	 * @param uid
	 * @param user
	 * @return Workout
	 */
	public Workout getWorkout(User user, String uid);

	/**
	 * Gets a list a Workouts by routine and criteriaParams.
	 *
	 * @param user
	 * @param criteriaParams
	 * @param routine
	 * @return List<Workout>
	 */
	public List<Workout> findWorkouts(User user, Routine routine, CriteriaParams<Workout> criteriaParams);

	/**
	 * Returns the count of all workouts found by routine.
	 *
	 * @param user
	 * @param routine
	 * @return long
	 */
	public long findWorkoutCount(User user, Routine routine);

	/**
	 * Saves a workout.
	 *
	 * @param workout
	 */
	public void saveWorkout(Workout workout);

	/**
	 * Deletes a workout.
	 *
	 * @param workout
	 */
	public void deleteWorkout(Workout workout);

	/**
	 * Clones and saves a workout and associated exercises and sets.
	 *
	 * @param workout
	 */
	public void cloneWorkout(Workout workout);

	/**
	 * Gets an exercise by user and id.
	 *
	 * @param uid
	 * @param user
	 * @return Exercise
	 */
	public Exercise getExercise(User user, String uid);

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
	 * Deletes an exercise.
	 *
	 * @param exercise
	 */
	public void deleteExercise(Exercise exercise);

	/**
	 * Gets an entry by user and id.
	 *
	 * @param uid
	 * @param user
	 * @return Entry
	 */
	public Entry getEntry(User user, String uid);

	/**
	 * Saves an entry.
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
	 * Deletes an entry.
	 *
	 * @param entry
	 */
	public void deleteEntry(Entry entry);

}