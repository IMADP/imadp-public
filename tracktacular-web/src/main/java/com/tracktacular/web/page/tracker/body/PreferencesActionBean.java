package com.tracktacular.web.page.tracker.body;

import net.sourceforge.stripes.action.UrlBinding;


/**
 * PreferencesActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-body/preferences")
public class PreferencesActionBean extends BodyTrackerActionBean {

}