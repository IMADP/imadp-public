package com.tracktacular.service.tracker.exercise;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.joda.time.Interval;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerDemo;
import com.tracktacular.service.tracker.calendar.CalendarEntry;


/**
 * ExerciseTrackerFacadeImpl
 *
 * The standard implementation of the ExerciseTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class ExerciseTrackerFacadeImpl extends AbstractTrackerFacade
	implements ExerciseTrackerFacade {

	private ExerciseService exerciseService;
	private RoutineService routineService;
	private EntryService entryService;
	private WorkoutService workoutService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(exerciseService);
		Validate.notNull(routineService);
		Validate.notNull(entryService);
		Validate.notNull(workoutService);
	}

	@Override
	public Routine getRoutine(User user, String uid) {
		return routineService.findFirstByUser(user, uid);
	}

	@Override
	public void saveRoutine(Routine routine) {
		new RoutineValidator("routine", routine).validate();
		routineService.save(routine);
	}

	@Override
	public void deleteRoutine(Routine routine) {
		List<Workout> workouts = workoutService.findByUser(routine.getUser(), routine, CriteriaParams.<Workout>of(Results.ALL));
		workoutService.delete(workouts);
		routineService.delete(routine);
	}

	@Override
	public List<RoutineDto> findActiveRoutines(User user, CriteriaParams<Routine> criteriaParams) {
		return getRoutineDtos(user, routineService.findByUser(user, PersistableState.ACTIVE, criteriaParams));
	}

	@Override
	public long findActiveRoutineCount(User user) {
		return routineService.findCountByUser(user, PersistableState.ACTIVE);
	}

	@Override
	public List<RoutineDto> findCompletedRoutines(User user, CriteriaParams<Routine> criteriaParams) {
		return getRoutineDtos(user, routineService.findByUser(user, PersistableState.ARCHIVED, criteriaParams));
	}

	@Override
	public long findCompletedRoutineCount(User user) {
		return routineService.findCountByUser(user, PersistableState.ARCHIVED);
	}

	@Override
	public List<RoutineDto> findDeletedRoutines(User user, CriteriaParams<Routine> criteriaParams) {
		return getRoutineDtos(user, routineService.findByUser(user, PersistableState.DELETED, criteriaParams));
	}

	@Override
	public long findDeletedRoutineCount(User user) {
		return routineService.findCountByUser(user, PersistableState.DELETED);
	}

	/**
	 * Gets a list of RoutineDtos from a list of routines.
	 *
	 * @param user
	 * @param routines
	 * @return List<RoutineDto>
	 */
	private List<RoutineDto> getRoutineDtos(User user, List<Routine> routines) {
		List<RoutineDto> routineDtos = new ArrayList<>(routines.size());

		for(Routine routine : routines)
		{
			CriteriaParams<Workout> criteriaParams = CriteriaParams.of(Results.ALL, Order.desc(Workout.DATE));
			List<Workout> workouts = workoutService.findByUser(user, routine, criteriaParams);
			routineDtos.add(new RoutineDto(routine, workouts));
		}

		return routineDtos;
	}

	@Override
	public Workout getWorkout(User user, String uid) {
		return workoutService.findFirstByUser(user, uid);
	}

	@Override
	public void saveWorkout(Workout workout) {
		new WorkoutValidator("workout", workout).validate();
		workoutService.save(workout);
	}

	@Override
	public void cloneWorkout(Workout workout) {
		workoutService.cloneWorkout(workout);
	}

	@Override
	public void deleteWorkout(Workout workout) {
		workoutService.delete(workout);
	}

	@Override
	public List<Workout> findWorkouts(User user, Routine routine, CriteriaParams<Workout> criteriaParams) {
		return workoutService.findByUser(user, routine, criteriaParams);
	}

	@Override
	public long findWorkoutCount(User user, Routine routine) {
		return workoutService.findCountByUser(user, routine);
	}

	@Override
	public Exercise getExercise(User user, String uid) {
		return exerciseService.findFirstByUser(user, uid);
	}

	@Override
	public void saveExercise(Exercise exercise) {
		new ExerciseValidator("exercise", exercise).validate();
		workoutService.saveExercise(exercise);
	}

	@Override
	public void saveExercises(List<Exercise> exercises) {
		workoutService.saveExercises(exercises);
	}

	@Override
	public void deleteExercise(Exercise exercise) {
		workoutService.deleteExercise(exercise);
	}

	@Override
	public Entry getEntry(User user, String uid) {
		return entryService.findFirstByUser(user, uid);
	}

	@Override
	public void saveEntry(Entry entry) {
		workoutService.saveEntry(entry);
	}

	@Override
	public void saveEntries(List<Entry> entries) {
		workoutService.saveEntries(entries);
	}

	@Override
	public void deleteEntry(Entry entry) {
		workoutService.deleteEntry(entry);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Routine> routines = routineService.findByUser(user, CriteriaParams.<Routine>of(Results.ALL));

		for(Routine routine : routines)
			deleteRoutine(routine);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new ExerciseTrackerDemo(this);
	}

	@Override
	public ExerciseTrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		return new ExerciseTrackerReport(findActiveRoutines(user, CriteriaParams.<Routine>of(Results.ALL)));
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();

		// active routines
		for(RoutineDto routineDto : findActiveRoutines(user, CriteriaParams.<Routine>of(Results.ALL)))
		{
			// starting routines
			if(interval.contains(routineDto.getRoutine().getStartDate()))
			{
				CalendarEntry calendarEntry = new CalendarEntry(user, routineDto.getRoutine().getStartDate());
				calendarEntry.setTracker(Tracker.EXERCISE);
				calendarEntry.setName(String.format("Routine started: %s", routineDto.getRoutine().getName()));
				calendarEntry.setDescription(routineDto.getRoutine().getDescription());
				calendarEntries.add(calendarEntry);
			}

		}

		return calendarEntries;
	}

	// getters and setters
	public ExerciseService getExerciseService() {
		return exerciseService;
	}

	public void setExerciseService(ExerciseService exerciseService) {
		this.exerciseService = exerciseService;
	}

	public RoutineService getRoutineService() {
		return routineService;
	}

	public void setRoutineService(RoutineService routineService) {
		this.routineService = routineService;
	}

	public EntryService getEntryService() {
		return entryService;
	}

	public void setEntryService(EntryService entryService) {
		this.entryService = entryService;
	}

	public WorkoutService getWorkoutService() {
		return workoutService;
	}

	public void setWorkoutService(WorkoutService workoutService) {
		this.workoutService = workoutService;
	}

}