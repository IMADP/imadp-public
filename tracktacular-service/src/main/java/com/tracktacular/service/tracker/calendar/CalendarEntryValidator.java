package com.tracktacular.service.tracker.calendar;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * CalendarEntryValidator
 *
 * Ensures that the entry is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.startDate.invalidNull
 *       {objectName}.endDate.invalidEndDate
 *       {objectName}.name.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.description.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class CalendarEntryValidator extends AbstractValidator<CalendarEntry> {

	// key
	private static final String INVALID_END_DATE = "invalidEndDate";

	// length
	private static final int MAX_NOTES_LENGTH = 25000;
	private static final int MAX_NAME_LENGTH = 128;
	private static final int MAX_DESCRIPTION_LENGTH = 256;

	// constructor
	public CalendarEntryValidator(String objectName, CalendarEntry entry) {
		super(objectName, entry);
	}

	@Override
	protected void validate(String objectName, CalendarEntry entry, List<ValidationFailure> failures) {
		String startDateName = join(objectName, CalendarEntry.START_DATE);
		String endDateName = join(objectName, CalendarEntry.END_DATE);
		String nameName = join(objectName, CalendarEntry.NAME);
		String notesName = join(objectName, CalendarEntry.NOTES);
		String descriptionName = join(objectName, CalendarEntry.DESCRIPTION);

		// add all validation failures for a null entry
		if(entry == null)
		{
			addValidationResult(NotNullValidator.invalidNull(startDateName));
			addValidationResult(NotNullValidator.invalidNull(nameName));
			return;
		}

		// validate the date range - startDate is mandatory and endDate cannot precede it
		if(addValidationResult(new NotNullValidator(startDateName, entry.getStartDate())))
			if(entry.getEndDate() != null && entry.getEndDate().isBefore(entry.getStartDate()))
				failures.add(new ValidationFailure(join(endDateName, INVALID_END_DATE), endDateName));

		// validate the entry
		addValidationResult(new NotNullValidator(nameName, entry.getName()));
		addValidationResult(new MaxLengthValidator(nameName, entry.getName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(descriptionName, entry.getDescription(), MAX_DESCRIPTION_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, entry.getNotes(), MAX_NOTES_LENGTH));
	}

}