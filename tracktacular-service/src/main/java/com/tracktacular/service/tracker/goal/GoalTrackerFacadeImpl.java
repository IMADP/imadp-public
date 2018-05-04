package com.tracktacular.service.tracker.goal;

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
 * GoalTrackerFacadeImpl
 *
 * The standard implementation of the GoalTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class GoalTrackerFacadeImpl extends AbstractTrackerFacade
	implements GoalTrackerFacade {

	private GoalService goalService;
	private ObjectiveService objectiveService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(goalService);
		Validate.notNull(objectiveService);
	}

	@Override
	public Goal getGoal(User user, String uid) {
		return goalService.findFirstByUser(user, uid);
	}

	@Override
	public void saveGoal(Goal goal) {
		new GoalValidator("goal", goal).validate();
		goalService.save(goal);
	}

	@Override
	public void deleteGoal(Goal goal) {
		goalService.delete(goal);
	}

	@Override
	public Goals findActiveGoals(User user) {
		List<Goal> activeGoals = goalService.findByUser(user, PersistableState.ACTIVE, CriteriaParams.<Goal>of(
				Results.ALL, Order.asc(Goal.TARGET_DATE)));

		return new Goals(activeGoals);
	}

	@Override
	public List<Goal> findCompletedGoals(User user, CriteriaParams<Goal> criteriaParams) {
		return goalService.findByUser(user, PersistableState.ARCHIVED, criteriaParams);
	}

	@Override
	public long findCompletedGoalCount(User user) {
		return goalService.findCountByUser(user, PersistableState.ARCHIVED);
	}

	@Override
	public List<Goal> findDeletedGoals(User user, CriteriaParams<Goal> criteriaParams) {
		return goalService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedGoalCount(User user) {
		return goalService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	public Objective getObjective(User user, String uid) {
		return objectiveService.findFirstByUser(user, uid);
	}

	@Override
	public void saveObjective(Objective objective) {
		new ObjectiveValidator("objective", objective).validate();
		goalService.saveObjective(objective);
	}

	@Override
	public void deleteObjective(Objective objective) {
		goalService.deleteObjective(objective);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Goal> goals = goalService.findByUser(user, CriteriaParams.<Goal>of(Results.ALL));

		for(Goal goal : goals)
			deleteGoal(goal);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new GoalTrackerDemo(this);
	}

	@Override
	public GoalTrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		return new GoalTrackerReport(preferences.getTrackers().getGoalTrackerPreferences(), findActiveGoals(user));
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();

		// active goals
		for(Goal goal : findActiveGoals(user).getGoals())
		{
			// starting goals
			if(interval.contains(goal.getStartDate()))
			{
				CalendarEntry calendarEntry = new CalendarEntry(user, goal.getStartDate());
				calendarEntry.setTracker(Tracker.GOAL);
				calendarEntry.setName(String.format("Goal started: %s", goal.getName()));
				calendarEntry.setDescription(goal.getDescription());
				calendarEntries.add(calendarEntry);
			}

			// ending goals
			if(interval.contains(goal.getTargetDate()))
			{
				CalendarEntry calendarEntry = new CalendarEntry(user, goal.getTargetDate());
				calendarEntry.setTracker(Tracker.GOAL);
				calendarEntry.setName(String.format("Goal target: %s", goal.getName()));
				calendarEntry.setDescription(goal.getDescription());
				calendarEntries.add(calendarEntry);
			}

			// goal objectives
			for(Objective objective : goal.getObjectives())
			{
				// ending objectives
				if(interval.contains(objective.getTargetDate()))
				{
					CalendarEntry calendarEntry = new CalendarEntry(user, objective.getTargetDate());
					calendarEntry.setTracker(Tracker.GOAL);
					calendarEntry.setName("Goal objective");
					calendarEntry.setDescription(objective.getName());
					calendarEntries.add(calendarEntry);
				}
			}

		}

		return calendarEntries;
	}

	// getters and setters
	public GoalService getGoalService() {
		return goalService;
	}

	public void setGoalService(GoalService goalService) {
		this.goalService = goalService;
	}

	public ObjectiveService getObjectiveService() {
		return objectiveService;
	}

	public void setObjectiveService(ObjectiveService objectiveService) {
		this.objectiveService = objectiveService;
	}

}