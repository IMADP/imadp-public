package com.tracktacular.service.tracker.task;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.ChainValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;
import com.imadp.core.validation.Validator;


/**
 * TaskCategoryValidator
 *
 * Ensures that the taskCategory is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.name.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.name.invalidNameInUse
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TaskCategoryValidator extends AbstractValidator<TaskCategory> {

	// length
	private static final int MAX_NAME_LENGTH = 35;

	// keys
	private static final String INVALID_NAME_IN_USE = "invalidNameInUse";

	private TaskCategoryService taskCategoryService;

	// constructor
	public TaskCategoryValidator(String objectName, TaskCategory taskCategory,
			TaskCategoryService taskCategoryService) {
		super(objectName, taskCategory);
		this.taskCategoryService = taskCategoryService;
	}

	@Override
	protected void validate(String objectName, TaskCategory taskCategory, List<ValidationFailure> failures) {
		String nameName = join(objectName, TaskCategory.NAME);

		// add all validation failures for a null taskCategory
		if(taskCategory == null)
		{
			addValidationResult(NotNullValidator.invalidNull(nameName));
			return;
		}

		Validator nameInUseValidator = new AbstractValidator<TaskCategory>(nameName, taskCategory) {
			@Override
			protected void validate(String objectName, TaskCategory category, List<ValidationFailure> validationFailures) {
				if(taskCategoryService.isCategoryNameInUse(category))
					validationFailures.add(new ValidationFailure(join(objectName, INVALID_NAME_IN_USE), objectName));
			}
		};

		// validate the taskCategory
		Validator nameValidator = new ChainValidator.Builder()
			.add(new NotNullValidator(nameName, taskCategory.getName()))
			.add(new MaxLengthValidator(nameName, taskCategory.getName(), MAX_NAME_LENGTH))
			.add(nameInUseValidator).build();

		addValidationResult(nameValidator);
	}

}