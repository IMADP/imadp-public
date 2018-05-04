package com.imadp.web.stripes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationError;

import com.imadp.core.AbstractSerializable;
import com.imadp.core.validation.ValidationFailure;


/**
 * AjaxValidationError
 *
 * Holds the result of a validation performed by a validator.
 *
 * @note This class is immutable and thread-safe.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class AjaxValidationError extends AbstractSerializable {
	private final String validationMessage;
	private final List<String> objectNames;

	// constructor
	public AjaxValidationError(ValidationFailure failure, Locale locale) {
		ValidationError error = new LocalizableError(failure.getKey(), failure.getParameters().toArray());

		this.objectNames = new ArrayList<>();
		this.validationMessage = error.getMessage(locale);

		for(String objectName : failure.getObjectNames())
			objectNames.add(objectName);
	}

	// getters
	public String getValidationMessage() {
		return validationMessage;
	}

	public List<String> getObjectNames() {
		return objectNames;
	}

}