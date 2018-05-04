package com.tracktacular.web.page.tracker.recipe;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.web.TracktacularActionBean;
import com.tracktacular.web.page.PageNotFoundActionBean;


/**
 * RecipeActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-recipe/recipe-{recipe=null}/{nameSlug=null}")
public class RecipeActionBean extends RecipeTrackerActionBean {

	@Override
	public Resolution index() {
		if(getRecipe() == null)
			return new ForwardResolution(PageNotFoundActionBean.class);

		return super.index();
	}

	@Override
	public String getTrackerPageTitle() {
		return getRecipe().getName();
	}

	@Override
	protected Class<? extends TracktacularActionBean> getUserToggleClass() {
		return getDefaultActionBean();
	}

}