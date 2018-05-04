package com.tracktacular.web.page.tracker.birthday;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.birthday.Birthday;
import com.tracktacular.service.tracker.birthday.BirthdayTrackerFacade;
import com.tracktacular.service.tracker.birthday.BirthdayTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * BirthdayTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class BirthdayTrackerActionBean extends TrackerActionBean<BirthdayTrackerFacade, BirthdayTrackerPreferences> {
	private Birthday birthday;

	@Override
	public Tracker getTracker() {
		return Tracker.BIRTHDAY;
	}

	/**
	 * Save or updates a Bucket.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveBirthday() {
		Birthday birthday = getBirthday();
		populatePersistableUser(birthday);
		getTrackerFacade().saveBirthday(birthday);

		if(birthday.isActiveState())
			getContext().addSuccessMessage("birthday.saveBirthday.success", birthday.getFullName());

		else if(birthday.isDeletedState())
			getContext().addSuccessMessage("birthday.deleteBirthday.success", birthday.getFullName());

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Bucket.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteBirthday() {
		Birthday birthday = getBirthday();
		getTrackerFacade().deleteBirthday(birthday);
		getContext().addSuccessMessage("birthday.deleteBirthday.success", birthday.getFullName());
		return getAjaxSourceResolution();
	}

	// getters and setters
	public void setBirthday(Birthday birthday) {
		this.birthday = birthday;
	}

	public Birthday getBirthday() {
		return birthday;
	}

}