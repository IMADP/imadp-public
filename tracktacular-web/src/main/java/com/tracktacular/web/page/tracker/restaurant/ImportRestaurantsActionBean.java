	package com.tracktacular.web.page.tracker.restaurant;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.imadp.core.Property;
import com.imadp.core.validation.ValidationResult;
import com.imadp.service.user.AbstractPersistableUser;
import com.tracktacular.service.tracker.restaurant.Restaurant;
import com.tracktacular.service.tracker.restaurant.RestaurantValidator;
import com.tracktacular.service.tracker.restaurant.Restaurants;
import com.tracktacular.web.TracktacularActionBean;


/**
 * ImportRestaurantsActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-restaurant/import-restaurants")
public class ImportRestaurantsActionBean extends RestaurantTrackerActionBean {

	@Override
	public String getTrackerPageTitle() {
		return "Import Restaurants";
	}

	@Override
	public Resolution index() {
		if(isPublicMode())
			return new RedirectResolution(getDefaultActionBean());

		return super.index();
	}

	@Override
	protected Class<? extends TracktacularActionBean> getUserToggleClass() {
		return getDefaultActionBean();
	}

	@Override
	public Class<? extends AbstractPersistableUser> getImportClass() {
		return Restaurant.class;
	}

	@Override
	public Property<?,?>[] getImportProperties() {
		return new Property[] {Restaurant.NAME, Restaurant.CITY, Restaurant.STATE, Restaurant.TAG, Restaurant.RATING, Restaurant.NOTES};
	}

	@Override
	protected ValidationResult getImportValidationResult(AbstractPersistableUser item) {
		Restaurants restaurants = getTrackerFacade().findActiveRestaurants(getTrackerUser(), Restaurant.NAME.getName());
		return new RestaurantValidator("restaurant", (Restaurant) item, restaurants).getValidationResult();
	}

}