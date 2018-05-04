package com.tracktacular.web.page.tracker.blood;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.blood.BloodPressure;
import com.tracktacular.service.tracker.blood.BloodTrackerFacade;
import com.tracktacular.service.tracker.blood.BloodTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * BloodTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class BloodTrackerActionBean extends TrackerActionBean<BloodTrackerFacade, BloodTrackerPreferences> {
	private BloodPressure bloodPressure;

	@Override
	public Tracker getTracker() {
		return Tracker.BLOOD;
	}

	/**
	 * Save or updates a BloodPressure.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveBloodPressure() {
		BloodPressure bloodPressure = getBloodPressure();
		populatePersistableUser(bloodPressure);
		getTrackerFacade().saveBloodPressure(bloodPressure);

		if(bloodPressure.isActiveState())
			getContext().addSuccessMessage("blood.saveBloodPressure.success");

		else if(bloodPressure.isDeletedState())
			getContext().addSuccessMessage("blood.deleteBloodPressure.success");

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a BloodPressure.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteBloodPressure() {
		BloodPressure bloodPressure = getBloodPressure();
		getTrackerFacade().deleteBloodPressure(bloodPressure);
		getContext().addSuccessMessage("blood.deleteBloodPressure.success");
		return getAjaxSourceResolution();
	}

	// getters and setters
	public BloodTrackerFacade getBloodTrackerFacade() {
		return getTrackerFacade();
	}

	public BloodPressure getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(BloodPressure bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

}