package com.tracktacular.web.page.tracker.game;

import net.sourceforge.stripes.action.UrlBinding;


/**
 * AboutActionBean
 *
 * ActionBean for a tracker page.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@UrlBinding("/user-{trackerUserUsername=demo}/tracker-game/about")
public class AboutActionBean extends GameTrackerActionBean {

}