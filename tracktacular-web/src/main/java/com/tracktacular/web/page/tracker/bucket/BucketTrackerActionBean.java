package com.tracktacular.web.page.tracker.bucket;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.bucket.BucketTrackerFacade;
import com.tracktacular.service.tracker.bucket.BucketTrackerPreferences;
import com.tracktacular.service.tracker.bucket.Item;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * BucketTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class BucketTrackerActionBean extends TrackerActionBean<BucketTrackerFacade, BucketTrackerPreferences> {
	private Item item;

	@Override
	public Tracker getTracker() {
		return Tracker.BUCKET;
	}

	/**
	 * Save or updates a Item.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveItem() {
		Item item = getItem();
		populatePersistableUser(item);
		getTrackerFacade().saveItem(item);

		if(item.isActiveState())
			getContext().addSuccessMessage("bucket.saveItem.success", item.getName());

		else if(item.isDeletedState())
			getContext().addSuccessMessage("bucket.deleteItem.success", item.getName());

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Item.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteItem() {
		Item item = getItem();
		getTrackerFacade().deleteItem(item);
		getContext().addSuccessMessage("bucket.deleteItem.success", item.getName());
		return getAjaxSourceResolution();
	}

	// getters and setters
	public void setItem(Item item) {
		this.item = item;
	}

	public Item getItem() {
		return item;
	}

}