package com.tracktacular.web.page.tracker.budget;

import net.sourceforge.stripes.action.UrlBinding;


/**
 * ReportActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-budget/report")
public class ReportActionBean extends BudgetTrackerActionBean {

}