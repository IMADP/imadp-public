package com.tracktacular.service.tracker.budget;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * BudgetValidator
 *
 * Ensures that the budget is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.name.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *       {objectName}.description.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class BudgetValidator extends AbstractValidator<Budget> {

	// length
	private static final int MAX_NAME_LENGTH = 35;
	private static final int MAX_DESCRIPTION_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;

	// constructor
	public BudgetValidator(String objectName, Budget budget) {
		super(objectName, budget);
	}

	@Override
	protected void validate(String objectName, Budget budget, List<ValidationFailure> failures) {
		String nameName = join(objectName, Budget.NAME);
		String descriptionName = join(objectName, Budget.DESCRIPTION);
		String notesName = join(objectName, Budget.NOTES);

		// add all validation failures for a null budget
		if(budget == null)
		{
			addValidationResult(NotNullValidator.invalidNull(nameName));
			return;
		}

		addValidationResult(new NotNullValidator(nameName, budget.getName()));
		addValidationResult(new MaxLengthValidator(nameName, budget.getName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(descriptionName, budget.getDescription(), MAX_DESCRIPTION_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, budget.getNotes(), MAX_NOTES_LENGTH));
	}

}