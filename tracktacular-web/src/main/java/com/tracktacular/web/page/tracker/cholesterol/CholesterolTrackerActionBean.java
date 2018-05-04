package com.tracktacular.web.page.tracker.cholesterol;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.cholesterol.Cholesterol;
import com.tracktacular.service.tracker.cholesterol.CholesterolTrackerFacade;
import com.tracktacular.service.tracker.cholesterol.CholesterolTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * CholesterolTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class CholesterolTrackerActionBean extends TrackerActionBean<CholesterolTrackerFacade, CholesterolTrackerPreferences> {
	private Cholesterol cholesterol;

	@Override
	public Tracker getTracker() {
		return Tracker.CHOLESTEROL;
	}

	/**
	 * Save or updates a Cholesterol.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveCholesterol() {
		Cholesterol cholesterol = getCholesterol();
		populatePersistableUser(cholesterol);
		getTrackerFacade().saveCholesterol(cholesterol);

		if(cholesterol.isActiveState())
			getContext().addSuccessMessage("cholesterol.saveCholesterol.success");

		else if(cholesterol.isDeletedState())
			getContext().addSuccessMessage("cholesterol.deleteCholesterol.success");

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Cholesterol.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteCholesterol() {
		Cholesterol cholesterol = getCholesterol();
		getTrackerFacade().deleteCholesterol(cholesterol);
		getContext().addSuccessMessage("cholesterol.deleteCholesterol.success");
		return getAjaxSourceResolution();
	}

	// getters and setters
	public CholesterolTrackerFacade getCholesterolTrackerFacade() {
		return getTrackerFacade();
	}

	public Cholesterol getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(Cholesterol cholesterol) {
		this.cholesterol = cholesterol;
	}

}