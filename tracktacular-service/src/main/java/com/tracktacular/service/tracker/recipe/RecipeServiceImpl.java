package com.tracktacular.service.tracker.recipe;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.util.CollectionUtils;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.dao.criteria.find.FindCriteria;
import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserServiceImpl;


/**
 * RecipeServiceImpl
 *
 * The standard implementation of the RecipeService.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class RecipeServiceImpl extends PersistableUserServiceImpl<Recipe> implements RecipeService {
	private RecipeTagService recipeTagService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(recipeTagService);
	}

	@Override
	protected void onAfterSave(Recipe recipe, boolean initialSave) {
		super.onAfterSave(recipe, initialSave);

		if(!initialSave)
			deleteRecipeTags(recipe);

		if(!CollectionUtils.isEmpty(recipe.getTags()))
			for(RecipeTag tag : recipe.getTags())
				recipeTagService.save(new RecipeTag(recipe.getUser(), recipe, tag.getName(), recipe.getPersistableState()));
	}

	@Override
	protected void onBeforeDelete(Recipe recipe) {
		super.onBeforeDelete(recipe);

		deleteRecipeTags(recipe);
	}

	@Override
	public List<Recipe> findFavoriteByUser(User user, PersistableState persistableState, CriteriaParams<Recipe> criteriaParams) {
		FindCriteria<Recipe> findCriteria = findCriteriaBuilder(criteriaParams)
				.whereEqualTo(Recipe.USER, user)
				.whereEqualTo(Recipe.PERSISTABLE_STATE, persistableState)
				.whereEqualTo(Recipe.FAVORITE, true).build();

		return findByUser(user, findCriteria);
	}

	@Override
	public long findFavoriteCountByUser(User user, PersistableState persistableState) {
		FindCriteria<Recipe> findCriteria = findCriteriaBuilder(CriteriaParams.<Recipe>of(Results.ONE))
				.whereEqualTo(Recipe.USER, user)
				.whereEqualTo(Recipe.PERSISTABLE_STATE, persistableState)
				.whereEqualTo(Recipe.FAVORITE, true).build();

		return findCountByUser(user, findCriteria);
	}

	/**
	 * Deletes all recipe tags associated with a recipe.
	 *
	 * @param recipe
	 */
	private void deleteRecipeTags(Recipe recipe) {
		List<RecipeTag> recipeTags = recipeTagService.findBy(recipe, CriteriaParams.<RecipeTag>of(Results.ALL));
		recipeTagService.delete(recipeTags);
	}

	// getters and setters
	public RecipeTagService getRecipeTagService() {
		return recipeTagService;
	}

	public void setRecipeTagService(RecipeTagService recipeTagService) {
		this.recipeTagService = recipeTagService;
	}

}