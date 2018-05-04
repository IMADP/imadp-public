package com.tracktacular.web.page.tracker.gift;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.gift.Gift;
import com.tracktacular.service.tracker.gift.GiftTrackerFacade;
import com.tracktacular.service.tracker.gift.GiftTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * GiftTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class GiftTrackerActionBean extends TrackerActionBean<GiftTrackerFacade, GiftTrackerPreferences> {
	private Gift gift;

	@Override
	public Tracker getTracker() {
		return Tracker.GIFT;
	}

	/**
	 * Save or updates a Gift.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveGift() {
		Gift gift = getGift();
		populatePersistableUser(gift);
		getTrackerFacade().saveGift(gift);

		if(gift.isActiveState())
			getContext().addSuccessMessage("gift.saveGift.success", gift.getName());

		else if(gift.isDeletedState())
			getContext().addSuccessMessage("gift.deleteGift.success", gift.getName());

		return getAjaxSourceResolution();
	}

	/**
	 * Toggles the favorite property.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution toggleGiftFavorite() {
		Gift gift = getGift();
		gift.setFavorite(!gift.isFavorite());
		getTrackerFacade().saveGift(gift);
		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Gift.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteGift() {
		Gift gift = getGift();
		getTrackerFacade().deleteGift(gift);
		getContext().addSuccessMessage("gift.deleteGift.success", gift.getName());
		return getAjaxSourceResolution();
	}

	// getters and setters
	public void setGift(Gift gift) {
		this.gift = gift;
	}

	public Gift getGift() {
		return gift;
	}

}