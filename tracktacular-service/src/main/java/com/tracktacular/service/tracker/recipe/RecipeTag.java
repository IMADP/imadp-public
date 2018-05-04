package com.tracktacular.service.tracker.recipe;

import com.imadp.dao.PersistableState;
import com.imadp.service.tag.AbstractTag;
import com.imadp.service.user.User;


/**
 * RecipeTag
 *
 * Represents a recipe tag.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RecipeTag extends AbstractTag<Recipe> {

	// constructor
	public RecipeTag() {

	}

	// constructor
	public RecipeTag(User user, Recipe recipe, String name, PersistableState persistableState) {
		super(user, recipe, name, persistableState);
	}

}
