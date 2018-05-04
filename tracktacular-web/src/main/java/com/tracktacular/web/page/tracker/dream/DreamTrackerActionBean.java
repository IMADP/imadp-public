package com.tracktacular.web.page.tracker.dream;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.dream.Dream;
import com.tracktacular.service.tracker.dream.DreamTrackerFacade;
import com.tracktacular.service.tracker.dream.DreamTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * DreamTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class DreamTrackerActionBean extends TrackerActionBean<DreamTrackerFacade, DreamTrackerPreferences> {

	// static properties
	protected static final int TAG_CLOUD_COUNT = 50;
	protected static final double TAG_CLOUD_MIN_WEIGHT = 3;
	protected static final double TAG_CLOUD_MAX_WEIGHT = 14;

	// properties
	private Dream dream;

	@Override
	public Tracker getTracker() {
		return Tracker.DREAM;
	}

	/**
	 * Save or updates a Dream.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveDream() {
		Dream dream = getDream();
		populatePersistableUser(dream);
		getTrackerFacade().saveDream(dream);

		if(dream.isActiveState())
			getContext().addSuccessMessage("dream.saveDream.success", dream.getTitle());

		else if(dream.isDeletedState())
			getContext().addSuccessMessage("dream.deleteDream.success", dream.getTitle());

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Dream.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteDream() {
		Dream dream = getDream();
		getTrackerFacade().deleteDream(dream);
		getContext().addSuccessMessage("dream.deleteDream.success", dream.getTitle());
		return getAjaxSourceResolution();
	}

	/**
	 * Toggles the Category collapsed property.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution toggleDreamFavorite() {
		Dream dream = getDream();
		dream.setFavorite(!dream.isFavorite());
		getTrackerFacade().saveDream(dream);
		return getAjaxSourceResolution();
	}

	// getters and setters
	public Dream getDream() {
		return dream;
	}

	public void setDream(Dream dream) {
		this.dream = dream;
	}

}