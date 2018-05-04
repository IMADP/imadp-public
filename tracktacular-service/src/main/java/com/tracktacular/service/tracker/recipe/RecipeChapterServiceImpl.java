package com.tracktacular.service.tracker.recipe;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imadp.service.user.PersistableUserServiceImpl;


/**
 * RecipeChapterServiceImpl
 *
 * The standard implementation of the RecipeChapterService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class RecipeChapterServiceImpl extends PersistableUserServiceImpl<RecipeChapter>
	implements RecipeChapterService {

	private RecipeService recipeService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(recipeService);
	}

	@Override
	protected void onBeforeDelete(RecipeChapter chapter) {
		super.onBeforeDelete(chapter);

		recipeService.delete(chapter.getRecipes());
	}

	@Override
	public boolean isTitleInUse(RecipeChapter chapter) {
		if(!chapter.hasUser())
			throw new IllegalArgumentException("RecipeChapter must have a user to determine if chapter title is in use");

		RecipeChapter existingChapter = findFirstByUser(chapter.getUser(), RecipeChapter.TITLE, chapter.getTitle());

		// if a chapter was found that does not match the one we are validating, we have a duplicate
		return existingChapter != null && !chapter.equals(existingChapter);
	}

	@Override
	public void saveRecipe(Recipe recipe) {
		recipeService.save(recipe);

		clearUserCaches(recipe.getUser());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveRecipes(List<Recipe> recipes) {
		for(Recipe recipe : recipes)
			saveRecipe(recipe);
	}

	@Override
	public void deleteRecipe(Recipe recipe) {
		recipeService.delete(recipe);

		clearUserCaches(recipe.getUser());
	}

	// getters and setters
	public RecipeService getRecipeService() {
		return recipeService;
	}

	public void setRecipeService(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

}