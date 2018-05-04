package com.tracktacular.service.tracker.gift;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * GiftValidator
 *
 * Ensures that the gift is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.name.invalidNull
 *       {objectName}.name.invalidMaxLength
 *       {objectName}.sender.invalidNull
 *       {objectName}.sender.invalidMaxLength
 *       {objectName}.receiver.invalidNull
 *       {objectName}.receiver.invalidMaxLength
 *       {objectName}.date.invalidNull
 *       {objectName}.occasion.invalidNull
 *       {objectName}.occasion.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class GiftValidator extends AbstractValidator<Gift> {

	// length
	private static final int MAX_NAME_LENGTH = 256;
	private static final int MAX_SENDER_LENGTH = 256;
	private static final int MAX_RECEIVER_LENGTH = 256;
	private static final int MAX_OCCASION_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;

	// constructor
	public GiftValidator(String objectName, Gift gift) {
		super(objectName, gift);
	}

	@Override
	protected void validate(String objectName, Gift gift, List<ValidationFailure> failures) {
		String nameName = join(objectName, Gift.NAME);
		String senderName = join(objectName, Gift.SENDER);
		String receiverName = join(objectName, Gift.RECEIVER);
		String dateName = join(objectName, Gift.DATE);
		String occasionName = join(objectName, Gift.OCCASION);
		String notesName = join(objectName, Gift.NOTES);

		// add all validation failures for a null gift
		if(gift == null)
		{
			addValidationResult(NotNullValidator.invalidNull(nameName));
			addValidationResult(NotNullValidator.invalidNull(senderName));
			addValidationResult(NotNullValidator.invalidNull(receiverName));
			addValidationResult(NotNullValidator.invalidNull(dateName));
			addValidationResult(NotNullValidator.invalidNull(occasionName));
			return;
		}

		// gifts must have either a sender or a receiver
		if(gift.getSender() == null && gift.getReceiver() == null)
		{
			addValidationResult(NotNullValidator.invalidNull(receiverName));
			addValidationResult(NotNullValidator.invalidNull(senderName));
		}

		// validate the gift
		addValidationResult(new NotNullValidator(nameName, gift.getName()));
		addValidationResult(new NotNullValidator(dateName, gift.getDate()));
		addValidationResult(new NotNullValidator(occasionName, gift.getOccasion()));
		addValidationResult(new MaxLengthValidator(nameName, gift.getName(), MAX_NAME_LENGTH));
		addValidationResult(new MaxLengthValidator(senderName, gift.getSender(), MAX_SENDER_LENGTH));
		addValidationResult(new MaxLengthValidator(receiverName, gift.getReceiver(), MAX_RECEIVER_LENGTH));
		addValidationResult(new MaxLengthValidator(occasionName, gift.getOccasion(), MAX_OCCASION_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, gift.getNotes(), MAX_NOTES_LENGTH));
	}

}