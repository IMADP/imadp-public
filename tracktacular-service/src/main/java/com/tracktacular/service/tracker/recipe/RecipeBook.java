package com.tracktacular.service.tracker.recipe;

import java.util.List;

import com.imadp.core.AbstractSerializable;


/**
 * RecipeBook
 *
 * A recipe book containing a chapters consisting of recipes.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RecipeBook extends AbstractSerializable {
	private final List<RecipeChapter> chapters;

	// constructor
	public RecipeBook(List<RecipeChapter> chapters) {
		this.chapters = chapters;
	}

	// getters and setters
	public List<RecipeChapter> getChapters() {
		return chapters;
	}

	/**
	 * Returns the number of recipe chapters in this book.
	 *
	 * @return int
	 */
	public int getChapterCount() {
		return chapters.size();
	}

	/**
	 * Returns the count of all recipes.
	 *
	 * @return boolean
	 */
	public int getRecipeCount() {
		int recipeCount = 0;

		for(RecipeChapter recipeChapter : chapters)
			recipeCount += recipeChapter.getRecipeCount();

		return recipeCount;
	}

}