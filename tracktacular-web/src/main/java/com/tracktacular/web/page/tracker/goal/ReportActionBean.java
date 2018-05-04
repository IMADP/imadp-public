package com.tracktacular.web.page.tracker.goal;

import net.sourceforge.stripes.action.UrlBinding;


/**
 * ReportActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-goal/report")
public class ReportActionBean extends GoalTrackerActionBean {

}