package com.tracktacular.service.tracker.exercise;

import com.imadp.service.user.PersistableUserService;

/**
 * IExerciseService
 *
 * Provides common retrieval operations for Exercise objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface ExerciseService extends PersistableUserService<Exercise> {

	/**
	 * Saves a entry.
	 *
	 * @param entry
	 */
	public void saveEntry(Entry entry);

	/**
	 * Deletes a entry.
	 *
	 * @param entry
	 */
	public void deleteEntry(Entry entry);

}