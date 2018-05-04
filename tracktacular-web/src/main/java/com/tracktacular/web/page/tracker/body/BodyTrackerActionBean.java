package com.tracktacular.web.page.tracker.body;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.body.Body;
import com.tracktacular.service.tracker.body.BodyTrackerFacade;
import com.tracktacular.service.tracker.body.BodyTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * BodyTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class BodyTrackerActionBean extends TrackerActionBean<BodyTrackerFacade, BodyTrackerPreferences> {
	private Body body;

	@Override
	public Tracker getTracker() {
		return Tracker.BODY;
	}

	/**
	 * Save or updates a Body.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveBody() {
		Body body = getBody();
		populatePersistableUser(body);
		getTrackerFacade().saveBody(body);

		if(body.isActiveState())
			getContext().addSuccessMessage("body.saveBody.success");

		else if(body.isDeletedState())
			getContext().addSuccessMessage("body.deleteBody.success");

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Body.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteBody() {
		Body body = getBody();
		getTrackerFacade().deleteBody(body);
		getContext().addSuccessMessage("body.deleteBody.success");
		return getAjaxSourceResolution();
	}

	// getters and setters
	public BodyTrackerFacade getBodyTrackerFacade() {
		return getTrackerFacade();
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}