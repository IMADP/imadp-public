package com.tracktacular.web.page.tracker.gift;

import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.tracker.gift.Gifts;


/**
 * GivenActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-gift/given/by-{sortProperty=occasion}")
public class GivenActionBean extends GiftTrackerActionBean {

	// properties
	private String sortProperty;
	private Gifts gifts;

	/**
	 * Returns a list of gifts.
	 *
	 * @return Gifts
	 */
	public Gifts getGifts() {
		if(gifts == null)
			gifts = getTrackerFacade().findGivenGifts(getTrackerUser(), sortProperty);

		return gifts;
	}

	// getter and setters
	public String getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}

}