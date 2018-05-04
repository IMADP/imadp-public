package com.tracktacular.web.page.tracker.recipe;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.recipe.Recipe;


/**
 * DeletedActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-recipe/deleted/page-{itemsPageProvider.pageNumber=1}")
public class DeletedActionBean extends RecipeTrackerActionBean {

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findDeletedRecipeCount(user);
	}

	@Override
	protected List<Recipe> findItems(Results results, User user) {
		CriteriaParams<Recipe> params = CriteriaParams.<Recipe>of(results, Order.<Recipe>desc(Recipe.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findDeletedRecipes(user, params);
	}

}