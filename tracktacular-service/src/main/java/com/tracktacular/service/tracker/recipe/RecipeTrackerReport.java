package com.tracktacular.service.tracker.recipe;

import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * RecipeTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class RecipeTrackerReport extends AbstractTrackerReport {
	private final RecipeBook recipeBook;

    // constructor
    public RecipeTrackerReport(RecipeBook recipeBook) {
        this.recipeBook = recipeBook;
    }

    @Override
	public boolean isEnabled() {
    	return recipeBook.getChapterCount() > 0;
    }

    // getters
    public RecipeBook getRecipeBook() {
		return recipeBook;
	}

}