package com.tracktacular.web.page.tracker.bucket;

import net.sourceforge.stripes.action.UrlBinding;


/**
 * ReportActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-bucket/report")
public class ReportActionBean extends BucketTrackerActionBean {

}