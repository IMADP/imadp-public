package com.tracktacular.web.page.tracker.exercise;

import net.sourceforge.stripes.action.UrlBinding;


/**
 * ReportActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-exercise/report")
public class ReportActionBean extends ExerciseTrackerActionBean {

}