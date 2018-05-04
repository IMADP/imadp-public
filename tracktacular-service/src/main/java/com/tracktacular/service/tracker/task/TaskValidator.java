package com.tracktacular.service.tracker.task;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * TaskValidator
 *
 * Ensures that the task is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.name.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TaskValidator extends AbstractValidator<Task> {

	// length
	private static final int MAX_NAME_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;

	// constructor
	public TaskValidator(String objectName, Task task) {
		super(objectName, task);
	}

	@Override
	protected void validate(String objectName, Task task, List<ValidationFailure> failures) {
		String nameName = join(objectName, Task.NAME);
		String notesName = join(objectName, Task.NOTES);

		// add all validation failures for a null task
		if(task == null)
		{
			addValidationResult(NotNullValidator.invalidNull(nameName));
			return;
		}

		// validate the task
		addValidationResult(new NotNullValidator(nameName, task.getName()));
		addValidationResult(new MaxLengthValidator(nameName, task.getName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, task.getNotes(), MAX_NOTES_LENGTH));
	}

}