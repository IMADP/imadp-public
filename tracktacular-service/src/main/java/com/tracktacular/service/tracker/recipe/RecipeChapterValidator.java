package com.tracktacular.service.tracker.recipe;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.ChainValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;
import com.imadp.core.validation.Validator;


/**
 * RecipeChapterValidator
 *
 * Ensures that the recipeChapter is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.title.invalidNull
 *       {objectName}.title.invalidMaxLength
 *       {objectName}.title.invalidNameInUse
 *       {objectName}.description.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RecipeChapterValidator extends AbstractValidator<RecipeChapter> {

	// length
	private static final int MAX_TITLE_LENGTH = 35;
	private static final int MAX_DESCRIPTION_LENGTH = 256;

	// keys
	private static final String INVALID_TITLE_IN_USE = "invalidTitleInUse";

	private RecipeChapterService recipeChapterService;

	// constructor
	public RecipeChapterValidator(String objectName, RecipeChapter recipeChapter,
			RecipeChapterService recipeChapterService) {
		super(objectName, recipeChapter);
		this.recipeChapterService = recipeChapterService;
	}

	@Override
	protected void validate(String objectName, RecipeChapter recipeChapter, List<ValidationFailure> failures) {
		String titleName = join(objectName, RecipeChapter.TITLE);
		String descriptionName = join(objectName, RecipeChapter.DESCRIPTION);

		// add all validation failures for a null recipeChapter
		if(recipeChapter == null)
		{
			addValidationResult(NotNullValidator.invalidNull(titleName));
			return;
		}

		Validator nameInUseValidator = new AbstractValidator<RecipeChapter>(titleName, recipeChapter) {
			@Override
			protected void validate(String objectName, RecipeChapter chapter, List<ValidationFailure> validationFailures) {
				if(recipeChapterService.isTitleInUse(chapter))
					validationFailures.add(new ValidationFailure(join(objectName, INVALID_TITLE_IN_USE), objectName));
			}
		};

		// validate the recipeChapter
		Validator nameValidator = new ChainValidator.Builder()
			.add(new NotNullValidator(titleName, recipeChapter.getTitle()))
			.add(new MaxLengthValidator(titleName, recipeChapter.getTitle(), MAX_TITLE_LENGTH))
			.add(nameInUseValidator).build();

		addValidationResult(nameValidator);
		addValidationResult(new MaxLengthValidator(descriptionName, recipeChapter.getDescription(), MAX_DESCRIPTION_LENGTH));
	}

}