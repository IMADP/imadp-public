package com.tracktacular.web.page.tracker.recipe;

import net.sourceforge.stripes.action.UrlBinding;


/**
 * AboutActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-recipe/about")
public class AboutActionBean extends RecipeTrackerActionBean {

}