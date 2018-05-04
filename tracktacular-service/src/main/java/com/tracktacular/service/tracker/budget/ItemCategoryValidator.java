package com.tracktacular.service.tracker.budget;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.ChainValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;
import com.imadp.core.validation.Validator;


/**
 * ItemCategoryValidator
 *
 * Ensures that the itemCategory is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.name.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.name.invalidNameInUse
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ItemCategoryValidator extends AbstractValidator<ItemCategory> {

	// length
	private static final int MAX_NAME_LENGTH = 35;

	// keys
	private static final String INVALID_NAME_IN_USE = "invalidNameInUse";

	private BudgetService budgetService;

	// constructor
	public ItemCategoryValidator(String objectName, ItemCategory itemCategory,
			BudgetService budgetService) {
		super(objectName, itemCategory);
		this.budgetService = budgetService;
	}

	@Override
	protected void validate(String objectName, ItemCategory itemCategory, List<ValidationFailure> failures) {
		String nameName = join(objectName, ItemCategory.NAME);

		// add all validation failures for a null itemCategory
		if(itemCategory == null)
		{
			addValidationResult(NotNullValidator.invalidNull(nameName));
			return;
		}

		Validator nameInUseValidator = new AbstractValidator<ItemCategory>(nameName, itemCategory) {
			@Override
			protected void validate(String objectName, ItemCategory category, List<ValidationFailure> validationFailures) {
				if(budgetService.isItemCategoryNameInUse(category))
					validationFailures.add(new ValidationFailure(join(objectName, INVALID_NAME_IN_USE), objectName));
			}
		};

		// validate the itemCategory
		Validator nameValidator = new ChainValidator.Builder()
			.add(new NotNullValidator(nameName, itemCategory.getName()))
			.add(new MaxLengthValidator(nameName, itemCategory.getName(), MAX_NAME_LENGTH))
			.add(nameInUseValidator).build();

		addValidationResult(nameValidator);
	}

}