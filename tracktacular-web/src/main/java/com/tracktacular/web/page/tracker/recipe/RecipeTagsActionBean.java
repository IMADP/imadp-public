package com.tracktacular.web.page.tracker.recipe;

import java.util.List;

import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.tag.TagCloud;
import com.imadp.service.user.User;
import com.imadp.web.stripes.link.Link;
import com.tracktacular.service.tracker.recipe.RecipeTag;


/**
 * RecipeTagsActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-recipe/tags/{recipeTag=all}/page-{itemsPageProvider.pageNumber=1}")
public class RecipeTagsActionBean extends RecipeTrackerActionBean {

	// default dreamsign param
	private static final String DEFAULT_TAG_PARAM = "all";

	// properties
	private String recipeTag;

	/**
	 * Returns the recipeTag tag cloud for a user.
	 *
	 * @return TagCloud
	 */
	public TagCloud getTagCloud() {
		return getTrackerFacade().findRecipeTagCloud(getTrackerUser(),
				TAG_CLOUD_COUNT, TAG_CLOUD_MIN_WEIGHT, TAG_CLOUD_MAX_WEIGHT);
	}

	/**
	 * Returns true if a recipeTag is selected, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean isRecipeTagSelected() {
		return recipeTag != null;
	}

	@Override
	protected int getItemsPerPage() {
		return 25;
	}

	@Override
	protected long findItemCount(User user) {
		return getTrackerFacade().findRecipeTagCount(user, recipeTag);
	}

	@Override
	protected List<RecipeTag> findItems(Results results, User user) {
		CriteriaParams<RecipeTag> params = CriteriaParams.<RecipeTag>of(results, Order.<RecipeTag>desc(RecipeTag.PERSISTABLE_STATE_DATE));
		return getTrackerFacade().findRecipeTags(user, recipeTag, params);
	}

	@Override
	protected void addLinkParameters(Link link) {
		link.addParameter("recipeTag", recipeTag);
	}

	// getter and setters
	public String getRecipeTag() {
		return recipeTag;
	}

	public void setRecipeTag(String recipeTag) {
		if(DEFAULT_TAG_PARAM.equalsIgnoreCase(recipeTag))
			return;

		this.recipeTag = recipeTag;
	}

}