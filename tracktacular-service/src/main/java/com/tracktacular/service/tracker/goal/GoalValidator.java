package com.tracktacular.service.tracker.goal;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * GoalValidator
 *
 * Ensures that the journal is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.startDate.invalidNull
 *       {objectName}.targetDate.invalidNull
 *       {objectName}.name.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *       {objectName}.description.invalidNull
 *       {objectName}.description.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class GoalValidator extends AbstractValidator<Goal> {

	// length
	private static final int MAX_NAME_LENGTH = 35;
	private static final int MAX_NOTES_LENGTH = 25000;
	private static final int MAX_DESCRIPTION_LENGTH = 256;

	// constructor
	public GoalValidator(String objectName, Goal goal) {
		super(objectName, goal);
	}

	@Override
	protected void validate(String objectName, Goal goal, List<ValidationFailure> failures) {
		String startDateName = join(objectName, Goal.START_DATE);
		String targetDateName = join(objectName, Goal.TARGET_DATE);
		String nameName = join(objectName, Goal.NAME);
		String descriptionName = join(objectName, Goal.DESCRIPTION);
		String notesName = join(objectName, Goal.NOTES);

		// add all validation failures for a null goal
		if(goal == null)
		{
			addValidationResult(NotNullValidator.invalidNull(startDateName));
			addValidationResult(NotNullValidator.invalidNull(targetDateName));
			addValidationResult(NotNullValidator.invalidNull(nameName));
			addValidationResult(NotNullValidator.invalidNull(descriptionName));
			return;
		}

		// validate the goal
		addValidationResult(new NotNullValidator(startDateName, goal.getStartDate()));
		addValidationResult(new NotNullValidator(targetDateName, goal.getTargetDate()));
		addValidationResult(new NotNullValidator(nameName, goal.getName()));
		addValidationResult(new MaxLengthValidator(nameName, goal.getName(), MAX_NAME_LENGTH));
		addValidationResult(new NotNullValidator(descriptionName, goal.getDescription()));
		addValidationResult(new MaxLengthValidator(descriptionName, goal.getDescription(), MAX_DESCRIPTION_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, goal.getNotes(), MAX_NOTES_LENGTH));
	}

}