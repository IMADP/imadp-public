package com.tracktacular.service.tracker.game;

import java.util.List;

import com.imadp.core.validation.AbstractValidator;
import com.imadp.core.validation.MaxLengthValidator;
import com.imadp.core.validation.NotNullValidator;
import com.imadp.core.validation.ValidationFailure;


/**
 * GameValidator
 *
 * Ensures that the game is valid.
 *
 * @note ValidationFailures are identified via the following failure keys:
 *       {objectName}.invalidUnique
 *       {objectName}.platform.invalidNull
 *       {objectName}.platform.invalidMaxLength
 *       {objectName}.title.invalidNull
 *       {objectName}.title.invalidMaxLength
 *       {objectName}.tag.invalidMaxLength
 *       {objectName}.notes.invalidMaxLength
 *
 * @version 1.0
 * @platform Anthony DePalma
 */
public class GameValidator extends AbstractValidator<Game> {

	// length
	private static final int MAX_PLATFORM_LENGTH = 256;
	private static final int MAX_TITLE_LENGTH = 256;
	private static final int MAX_TAG_LENGTH = 256;
	private static final int MAX_NOTES_LENGTH = 25000;

	// keys
	private static final String INVALID_UNIQUE = "invalidUnique";

	// properties
	private Games games;

	// constructor
	public GameValidator(String objectName, Game game, Games games) {
		super(objectName, game);
		this.games = games;
	}

	@Override
	protected void validate(String objectName, Game game, List<ValidationFailure> failures) {
		String platformName = join(objectName, Game.PLATFORM);
		String titleName = join(objectName, Game.TITLE);
		String tagName = join(objectName, Game.TAG);
		String notesName = join(objectName, Game.NOTES);

		// add all validation failures for a null game
		if(game == null)
		{
			addValidationResult(NotNullValidator.invalidNull(platformName));
			addValidationResult(NotNullValidator.invalidNull(titleName));
			return;
		}

		// validate the game
		if(games.hasDuplicate(game))
			failures.add(new ValidationFailure(join(objectName, INVALID_UNIQUE), titleName, platformName));

		addValidationResult(new NotNullValidator(platformName, game.getPlatform()));
		addValidationResult(new NotNullValidator(titleName, game.getTitle()));
		addValidationResult(new MaxLengthValidator(platformName, game.getPlatform(), MAX_PLATFORM_LENGTH));
		addValidationResult(new MaxLengthValidator(titleName, game.getTitle(), MAX_TITLE_LENGTH));
		addValidationResult(new MaxLengthValidator(tagName, game.getTag(), MAX_TAG_LENGTH));
		addValidationResult(new MaxLengthValidator(notesName, game.getNotes(), MAX_NOTES_LENGTH));
	}

}