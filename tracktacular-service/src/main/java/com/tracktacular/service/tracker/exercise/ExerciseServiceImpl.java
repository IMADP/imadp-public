package com.tracktacular.service.tracker.exercise;

import org.apache.commons.lang.Validate;

import com.imadp.service.user.PersistableUserServiceImpl;


/**
 * ExerciseServiceImpl
 *
 * The standard implementation of the ExerciseService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class ExerciseServiceImpl extends PersistableUserServiceImpl<Exercise>
	implements ExerciseService {

	private EntryService entryService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(entryService);
	}

	@Override
	protected void onBeforeDelete(Exercise exercise) {
		super.onBeforeDelete(exercise);

		entryService.delete(exercise.getEntries());
	}

	@Override
	public void saveEntry(Entry entry) {
		entryService.save(entry);

		clearUserCaches(entry.getUser());
	}

	@Override
	public void deleteEntry(Entry entry) {
		entryService.delete(entry);

		clearUserCaches(entry.getUser());
	}

	// getters and setters
	public EntryService getEntryService() {
		return entryService;
	}

	public void setEntryService(EntryService entryService) {
		this.entryService = entryService;
	}

}