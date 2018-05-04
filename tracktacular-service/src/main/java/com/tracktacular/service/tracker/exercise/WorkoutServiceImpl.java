package com.tracktacular.service.tracker.exercise;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserServiceImpl;


/**
 * WorkoutServiceImpl
 *
 * The standard implementation of the WorkoutService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class WorkoutServiceImpl extends PersistableUserServiceImpl<Workout>
	implements WorkoutService {

	private ExerciseService exerciseService;
	private EntryService entryService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(exerciseService);
		Validate.notNull(entryService);
	}

	@Override
	protected void onBeforeDelete(Workout workout) {
		super.onBeforeDelete(workout);

		exerciseService.delete(workout.getExercises());
	}

	@Override
	public List<Workout> findByUser(User user, Routine routine, CriteriaParams<Workout> criteriaParams){
		FindCriteria<Workout> findCriteria = findCriteriaBuilder(criteriaParams)
			.whereEqualTo(Workout.ROUTINE, routine).build();

		return findByUser(user, findCriteria);
	}

	@Override
	public long findCountByUser(User user, Routine routine){
		FindCriteria<Workout> findCriteria = dao.findCriteriaBuilder(Results.ALL)
			.whereEqualTo(Workout.ROUTINE, routine).build();

		return findCountByUser(user, findCriteria);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void cloneWorkout(Workout originalWorkout) {
		User user = originalWorkout.getUser();
		Workout workout = new Workout(user, originalWorkout.getRoutine(), originalWorkout);

		save(workout);

		// clone the original exercises
		for(Exercise originalExercise : originalWorkout.getExercises())
		{
			Exercise exercise = new Exercise(user, workout, originalExercise);
			exerciseService.save(exercise);

			// clone the original entries
			for(Entry originalEntry : originalExercise.getEntries())
				entryService.save(new Entry(user, exercise, originalEntry));
		}

		clearUserCaches(originalWorkout.getUser());
	}

	@Override
	public void saveExercise(Exercise exercise) {
		exerciseService.save(exercise);

		clearUserCaches(exercise.getUser());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveExercises(List<Exercise> exercises) {
		for(Exercise exercise : exercises)
			saveExercise(exercise);
	}

	@Override
	public void deleteExercise(Exercise exercise) {
		exerciseService.delete(exercise);

		clearUserCaches(exercise.getUser());
	}

	@Override
	public void saveEntry(Entry entry) {
		exerciseService.saveEntry(entry);

		clearUserCaches(entry.getExercise().getUser());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveEntries(List<Entry> entries) {
		for(Entry entry : entries)
			saveEntry(entry);
	}

	@Override
	public void deleteEntry(Entry entry) {
		exerciseService.deleteEntry(entry);

		clearUserCaches(entry.getExercise().getUser());
	}

	// getters and setters
	public ExerciseService getExerciseService() {
		return exerciseService;
	}

	public void setExerciseService(ExerciseService exerciseService) {
		this.exerciseService = exerciseService;
	}

	public EntryService getEntryService() {
		return entryService;
	}

	public void setEntryService(EntryService entryService) {
		this.entryService = entryService;
	}

}