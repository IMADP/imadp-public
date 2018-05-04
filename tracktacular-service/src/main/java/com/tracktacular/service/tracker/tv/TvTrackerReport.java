package com.tracktacular.service.tracker.tv;

import java.util.List;

import com.google.common.collect.Lists;
import com.tracktacular.service.tracker.AbstractTrackerReport;

/**
 * TvTrackerReport
 *
 * Contains report information.
 *
 * @author Anthony DePalma
 * @version 1.0
 */
public final class TvTrackerReport extends AbstractTrackerReport {
	private final Shows shows;
	private final List<Show> unratedShows;
	private final List<Show> targetDateShows;
	private final List<Episode> targetDateEpisodes;

    // constructor
    public TvTrackerReport(TvTrackerPreferences preferences, Shows shows) {
    	this.shows = shows;
    	this.unratedShows = shows.getUnratedShows();
    	this.targetDateShows = Lists.newArrayList();
    	this.targetDateEpisodes = Lists.newArrayList();

    	if(preferences.isAlertOnTargetDates())
    	{
	    	for(ShowCategory showCategory : shows.getShowCategories())
	    		for(Show show : showCategory.getItems())
	    		{
	    			if(isCurrentDate(show.getTargetDate()))
	    				targetDateShows.add(show);

	    			if(show.getEpisodes() != null)
		    			for(Episode episode : show.getEpisodes())
		    				if(isCurrentDate(episode.getTargetDate()))
		    					targetDateEpisodes.add(episode);
	    		}
    	}
    }

    @Override
	public boolean isEnabled() {
    	return getShowCount() > 0;
    }

    @Override
    public int getAlertCount() {
    	return targetDateShows.size() + targetDateEpisodes.size();
    }

    /**
     * Returns the count of shows.
     *
     * @return int
     */
    public int getShowCount() {
		return shows.getShowCount();
	}

    /**
     * Returns the count of unrated shows.
     *
     * @return int
     */
    public int getUnratedShowCount() {
		return unratedShows.size();
	}

    // getters
	public List<Show> getUnratedShows() {
		return unratedShows;
	}

	public List<Show> getTargetDateShows() {
		return targetDateShows;
	}

	public List<Episode> getTargetDateEpisodes() {
		return targetDateEpisodes;
	}

}