package com.tracktacular.service.tracker;

import com.imadp.core.AbstractSerializable;

/**
 * AbstractTrackerPreferences
 *
 * Base class for tracker preferences.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public abstract class AbstractTrackerPreferences extends AbstractSerializable implements TrackerPreferences {
	private boolean trackerPublic;
    private boolean trackerEnabled = true;
    private boolean trackerTutorial = true;

    // getters and setters

	@Override
	public boolean isTrackerEnabled() {
		return trackerEnabled;
	}

	@Override
	public void setTrackerEnabled(boolean trackerEnabled) {
		this.trackerEnabled = trackerEnabled;
	}

	@Override
	public boolean isTrackerPublic() {
		return trackerPublic;
	}

	@Override
	public void setTrackerPublic(boolean trackerPublic) {
		this.trackerPublic = trackerPublic;
	}

	@Override
	public boolean isTrackerTutorial() {
		return trackerTutorial;
	}

	@Override
	public void setTrackerTutorial(boolean trackerTutorial) {
		this.trackerTutorial = trackerTutorial;
	}

}