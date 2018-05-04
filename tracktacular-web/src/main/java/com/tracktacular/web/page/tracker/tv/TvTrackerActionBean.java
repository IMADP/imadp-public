package com.tracktacular.web.page.tracker.tv;

import java.util.List;

import net.sourceforge.stripes.action.Resolution;

import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.tv.Episode;
import com.tracktacular.service.tracker.tv.Show;
import com.tracktacular.service.tracker.tv.TvTrackerFacade;
import com.tracktacular.service.tracker.tv.TvTrackerPreferences;
import com.tracktacular.web.IgnoreInPublicMode;
import com.tracktacular.web.page.tracker.TrackerActionBean;


/**
 * TvTrackerActionBean
 *
 * Base tracker action bean.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class TvTrackerActionBean extends TrackerActionBean<TvTrackerFacade, TvTrackerPreferences> {
	private Show show;
	private Episode episode;
	private String sortedEpisodes;

	@Override
	public Tracker getTracker() {
		return Tracker.TV;
	}

	/**
	 * Save or updates a Show.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveShow() {
		Show show = getShow();
		populatePersistableUser(show);
		getTrackerFacade().saveShow(show);

		if(show.isActiveState())
			getContext().addSuccessMessage("tv.saveShow.success", show.getTitle());

		else if(show.isDeletedState())
			getContext().addSuccessMessage("tv.deleteShow.success", show.getTitle());

		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Show.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteShow() {
		Show show = getShow();
		getTrackerFacade().deleteShow(show);
		getContext().addSuccessMessage("tv.deleteShow.success", show.getTitle());
		return getAjaxSourceResolution();
	}

	/**
	 * Save or updates a Episode.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution saveEpisode() {
		Episode episode = getEpisode();
		populatePersistableUser(episode);
		getTrackerFacade().saveEpisode(episode);
		getContext().addSuccessMessage("tv.saveEpisode.success", episode.getTitle());
		return getAjaxSourceResolution();
	}

	/**
	 * Deletes a Episode.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution deleteEpisode() {
		Episode episode = getEpisode();
		getTrackerFacade().deleteEpisode(episode);
		getContext().addSuccessMessage("tv.deleteEpisode.success", episode.getTitle());
		return getAjaxSourceResolution();
	}

	/**
	 * Sorts Episodes.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution sortEpisodes() {
		List<Episode> sortedCategoriesList = convertObjectList(sortedEpisodes, Episode.class);
		List<Episode> sortedList = getResortedList(sortedCategoriesList);
		getTrackerFacade().saveEpisodes(sortedList);
		getContext().addSuccessMessage("tv.sortEpisodes.success");
		return getAjaxSourceResolution();
	}

	// getters and setters
	public void setShow(Show show) {
		this.show = show;
	}

	public Show getShow() {
		return show;
	}

	public Episode getEpisode() {
		return episode;
	}

	public void setEpisode(Episode episode) {
		this.episode = episode;
	}

	public String getSortedEpisodes() {
		return sortedEpisodes;
	}

	public void setSortedEpisodes(String sortedEpisodes) {
		this.sortedEpisodes = sortedEpisodes;
	}

}