package com.tracktacular.web.page.tracker.bucket;

import net.sourceforge.stripes.action.UrlBinding;

import com.tracktacular.service.tracker.bucket.Items;


/**
 * BucketListActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-bucket/bucket-list")
public class BucketListActionBean extends BucketTrackerActionBean {
	private Items items;

	/**
	 * Returns a list of items.
	 *
	 * @return Items
	 */
	public Items getItems() {
		if(items == null)
			items = getTrackerFacade().findActiveItems(getTrackerUser());

		return items;
	}

}