package com.tracktacular.service.tracker.goal;

import org.apache.commons.lang.Validate;

import com.imadp.service.user.PersistableUserServiceImpl;


/**
 * GoalServiceImpl
 *
 * The standard implementation of the GoalService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class GoalServiceImpl extends PersistableUserServiceImpl<Goal> implements GoalService {
	private ObjectiveService objectiveService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(objectiveService);
	}

	@Override
	protected void onBeforeDelete(Goal goal) {
		super.onBeforeDelete(goal);

		objectiveService.delete(goal.getObjectives());
	}

	@Override
	public void saveObjective(Objective objective) {
		objectiveService.save(objective);

		clearUserCaches(objective.getUser());
	}

	@Override
	public void deleteObjective(Objective objective) {
		objectiveService.delete(objective);

		clearUserCaches(objective.getUser());
	}

	// getters and setters
	public ObjectiveService getObjectiveService() {
		return objectiveService;
	}

	public void setObjectiveService(ObjectiveService objectiveService) {
		this.objectiveService = objectiveService;
	}

}