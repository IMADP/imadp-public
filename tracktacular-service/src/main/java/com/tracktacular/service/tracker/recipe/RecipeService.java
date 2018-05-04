package com.tracktacular.service.tracker.recipe;

import java.util.List;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.user.User;
import com.imadp.service.user.PersistableUserService;

/**
 * IRecipeService
 *
 * Provides common retrieval operations for Recipe objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface RecipeService extends PersistableUserService<Recipe> {

	/**
	 * Finds a list of all favorite recipes by user, persistableState, and criteriaParams.
	 *
	 * @param user
	 * @param persistableState
	 * @param criteriaParams
	 * @return List<Recipe>
	 */
	public List<Recipe> findFavoriteByUser(User user, PersistableState persistableState, CriteriaParams<Recipe> criteriaParams);

	/**
	 * Finds the count of all favorite recipes by user and persistableState.
	 *
	 * @param user
	 * @param persistableState
	 * @return long
	 */
	public long findFavoriteCountByUser(User user, PersistableState persistableState);

}