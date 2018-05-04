package com.tracktacular.service.tracker.dream;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * DreamValidator
 *
 * Ensures that the dream is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.date.invalidNull
 *       {objectName}.content.invalidNull
 *       {objectName}.content.invalidMaxLength
 *       {objectName}.title.invalidNull
 *       {objectName}.title.invalidMaxLength
 *       {objectName}.analysis.invalidMaxLength
 *       {objectName}.dreamsignsAsString.invalidMaxLength
 *       {objectName}.dreamsignsAsString.invalidNameMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class DreamValidator extends AbstractValidator<Dream> {

	// length
	private static final int MAX_ANALYSIS_LENGTH = 25000;
	private static final int MAX_CONTENT_LENGTH = 25000;
	private static final int MAX_TITLE_LENGTH = 256;
	private static final int MAX_DREAMSIGN_NAME_LENGTH = 35;
	private static final int MAX_DREAMSIGNS_AS_STRING_LENGTH = 1024;

	// keys
	private static final String INVALID_NAME_MAX_LENGTH = "invalidNameMaxLength";

	// constructor
	public DreamValidator(String objectName, Dream dream) {
		super(objectName, dream);
	}

	@Override
	protected void validate(String objectName, Dream dream, List<ValidationFailure> failures) {
		String dateName = join(objectName, Dream.DATE);
		String contentName = join(objectName, Dream.CONTENT);
		String analysisName = join(objectName, Dream.ANALYSIS);
		String titleName = join(objectName, Dream.TITLE);
		String dreamsignsAsStringName = join(objectName, "dreamsignsAsString");

		// add all validation failures for a null dream
		if(dream == null)
		{
			addValidationResult(NotNullValidator.invalidNull(titleName));
			addValidationResult(NotNullValidator.invalidNull(dateName));
			addValidationResult(NotNullValidator.invalidNull(contentName));
			return;
		}

		// validate the dream
		addValidationResult(new NotNullValidator(titleName, dream.getTitle()));
		addValidationResult(new NotNullValidator(dateName, dream.getDate()));
		addValidationResult(new NotNullValidator(contentName, dream.getContent()));
		addValidationResult(new MaxLengthValidator(contentName, dream.getContent(), MAX_CONTENT_LENGTH));
		addValidationResult(new MaxLengthValidator(titleName, dream.getTitle(), MAX_TITLE_LENGTH));
		addValidationResult(new MaxLengthValidator(analysisName, dream.getAnalysis(), MAX_ANALYSIS_LENGTH));

		// validate the dreamsigns
		boolean validDreamsignsAsStringLength = addValidationResult(
				new MaxLengthValidator(dreamsignsAsStringName, dream.getDreamsignsAsString(), MAX_DREAMSIGNS_AS_STRING_LENGTH));

		if(validDreamsignsAsStringLength && dream.getDreamsigns() != null)
			for(Dreamsign dreamsign : dream.getDreamsigns())
				if(dreamsign.getName().length() > MAX_DREAMSIGN_NAME_LENGTH)
					failures.add(new ValidationFailure(join(dreamsignsAsStringName, INVALID_NAME_MAX_LENGTH),
							new Object[] {MAX_DREAMSIGN_NAME_LENGTH}, dreamsignsAsStringName));
	}

}