package com.tracktacular.service.tracker.tv;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.joda.time.Interval;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerDemo;
import com.tracktacular.service.tracker.calendar.CalendarEntry;


/**
 * TvTrackerFacadeImpl
 *
 * The standard implementation of the TvTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class TvTrackerFacadeImpl extends AbstractTrackerFacade
	implements TvTrackerFacade {

	private ShowService showService;
	private EpisodeService episodeService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(showService);
		Validate.notNull(episodeService);
	}

	@Override
	public Show getShow(User user, String uid) {
		return showService.findFirstByUser(user, uid);
	}

	@Override
	public void saveShow(Show show) {
		new ShowValidator("show", show, findActiveShows(show.getUser(), Show.TITLE.getName())).validate();
		showService.save(show);
	}

	@Override
	public void deleteShow(Show show) {
		showService.delete(show);
	}

	@Override
	public Episode getEpisode(User user, String uid) {
		return episodeService.findFirstByUser(user, uid);
	}

	@Override
	public void saveEpisode(Episode episode) {
		validateEpisode(episode);
		showService.saveEpisode(episode);
	}

	@Override
	public void saveEpisodes(List<Episode> episodes) {
		for(Episode episode : episodes)
			validateEpisode(episode);

		showService.saveEpisodes(episodes);
	}

	/**
	 * Validates a Episode.
	 *
	 * @param episode
	 */
	private void validateEpisode(Episode episode) {
		new EpisodeValidator("episode", episode).validate();
	}

	@Override
	public void deleteEpisode(Episode episode) {
		showService.deleteEpisode(episode);
	}

	@Override
	public Shows findActiveShows(User user, String sortProperty) {
		List<Show> activeShows = showService.findByUser(
				user, PersistableState.ACTIVE, CriteriaParams.<Show>of(Results.ALL));

		return new Shows(activeShows, sortProperty);
	}

	@Override
	public List<Show> findDeletedShows(User user, CriteriaParams<Show> criteriaParams) {
		return showService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedShowCount(User user) {
		return showService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Show> shows = showService.findByUser(user, CriteriaParams.<Show>of(Results.ALL));

		for(Show show : shows)
			deleteShow(show);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new TvTrackerDemo(this);
	}

	@Override
	public TvTrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		Shows shows = findActiveShows(user, Show.TITLE.getName());
		return new TvTrackerReport(preferences.getTrackers().getTvTrackerPreferences(), shows);
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();

		// active shows
		Shows shows = findActiveShows(user, Show.TITLE.getName());

		for(ShowCategory category : shows.getShowCategories())
			for(Show show : category.getItems())
			{
				if(show.getTargetDate() != null)
				{
					CalendarEntry calendarEntry = new CalendarEntry(user, show.getTargetDate());
					calendarEntry.setTracker(Tracker.TV);
					calendarEntry.setName(String.format("Show: %s", show.getTitle()));
					calendarEntries.add(calendarEntry);
				}

				if(show.getEpisodes() != null)
					for(Episode episode : show.getEpisodes())
					{
						if(episode.getTargetDate() != null)
						{
							CalendarEntry calendarEntry = new CalendarEntry(user, episode.getTargetDate());
							calendarEntry.setTracker(Tracker.TV);
							calendarEntry.setName(episode.getTitle());
							calendarEntry.setDescription(show.getTitle());
							calendarEntries.add(calendarEntry);
						}
					}
			}

		return calendarEntries;
	}

	// getters and setters
	public ShowService getShowService() {
		return showService;
	}

	public void setShowService(ShowService showService) {
		this.showService = showService;
	}

	public EpisodeService getEpisodeService() {
		return episodeService;
	}

	public void setEpisodeService(EpisodeService episodeService) {
		this.episodeService = episodeService;
	}

}