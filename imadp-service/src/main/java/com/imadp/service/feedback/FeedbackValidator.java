package com.imadp.service.feedback;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * FeedbackValidator
 *
 * Ensures that the feedback is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.content.invalidNull
 *       {objectName}.content.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class FeedbackValidator extends AbstractValidator<Feedback> {

	// length
	private static final int DEFAULT_MAX_CONTENT_LENGTH = 5000;

	private int maxContentLength;

	// constructor
	public FeedbackValidator(String objectName, Feedback feedback) {
		super(objectName, feedback);
		this.maxContentLength = DEFAULT_MAX_CONTENT_LENGTH;
	}

	@Override
	protected void validate(String objectName, Feedback feedback, List<ValidationFailure> failures) {
		String contentName = join(objectName, Feedback.CONTENT);

		// add all validation failures for a null feedback
		if(feedback == null)
		{
			addValidationResult(NotNullValidator.invalidNull(contentName));
			return;
		}

		// validate the feedback
		addValidationResult(new NotNullValidator(contentName, feedback.getContent()));
		addValidationResult(new MaxLengthValidator(contentName, feedback.getContent(), maxContentLength));
	}

	// setters
	public void setMaxContentLength(int maxContentLength) {
		this.maxContentLength = maxContentLength;
	}

}