package com.tracktacular.web.page.tracker.birthday;

import net.sourceforge.stripes.action.UrlBinding;


/**
 * AboutActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-birthday/about")
public class AboutActionBean extends BirthdayTrackerActionBean {

}