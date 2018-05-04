package com.tracktacular.web;

import java.util.List;
import java.util.Locale;

import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationError;

import com.google.common.collect.Lists;
import com.imadp.core.validation.ValidationFailure;
import com.imadp.core.validation.ValidationResult;



/**
 * ImportedItem
 *
 * A wrapper for an item and a flag to determine whether its valid.
 *
 * @param <V>
 * @version 1.0
 * @author Anthony DePalma
 */
public final class ImportedItem<V> {
	private final V item;
	private final ValidationResult validationResult;
	private final List<String> messages;

	// constructor
	public ImportedItem(V item, ValidationResult validationResult, Locale locale) {
		this.item = item;
		this.validationResult = validationResult;
		this.messages = Lists.newArrayList();

		for(ValidationFailure failure : validationResult.getFailures())
    	{
    		ValidationError error = new LocalizableError(failure.getKey(), failure.getParameters().toArray());
    		messages.add(error.getMessage(locale));
    	}
	}

	/**
	 * Returns true if the validation was valid, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isValid() {
		return validationResult.isValid();
	}

	// getters and setters
	public V getItem() {
		return item;
	}

	public ValidationResult getValidationResult() {
		return validationResult;
	}

	public List<String> getMessages() {
		return messages;
	}

}