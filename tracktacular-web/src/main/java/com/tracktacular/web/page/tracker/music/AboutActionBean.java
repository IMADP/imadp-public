package com.tracktacular.web.page.tracker.music;

import net.sourceforge.stripes.action.UrlBinding;


/**
 * AboutActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-music/about")
public class AboutActionBean extends MusicTrackerActionBean {

}