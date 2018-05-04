package com.tracktacular.service.tracker.tv;

import org.joda.time.DateTime;

import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;



/**
 * TvTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TvTrackerDemo extends AbstractTrackerDemo {

	private TvTrackerFacade trackerFacade;

	// constructor
	public TvTrackerDemo(TvTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		Show show;
		Episode episode;

		// target date
		addShow(user, "Mad Men", "Drama", null, new DateTime().plusDays(3));

		// uncompleted
		addShow(user, "Breaking Bad", "Crime, Drama", null, null);

		// completed
		addShow(user, "The Shield", "Crime, Drama", 9, null);
		addShow(user, "Arrested Development", "Comedy", 10, null);
		addShow(user, "Band of Brothers", "War, History", 9, null);
		addShow(user, "Dexter", "Crime, Drama", 5, null);
		addShow(user, "From the Earth to the Moon", "Action, Drama, History", 7, null);
		addShow(user, "The Office", "Comedy", 7, null);
		addShow(user, "Sopranos", "Crime, Drama", 10, null);
		addShow(user, "Boardwalk Empire", "Crime, Drama", 6, null);

		show = addShow(user, "Lost", "Adventure, Drama, Fantasy", 4, null);
		show.setNotes("This show started off strong but I started to lose interest with all the unanswered questions.");
		trackerFacade.saveShow(show);

		// shows
		show = addShow(user, "Game of Thrones", "Adventure, Drama, Fantasy", 10, null);

		episode = new Episode(user, show);
		episode.setTitle("Season 1");
		episode.setRating(6);
		trackerFacade.saveEpisode(episode);

		episode = new Episode(user, show);
		episode.setTitle("Season 2");
		episode.setRating(6);
		trackerFacade.saveEpisode(episode);

		episode = new Episode(user, show);
		episode.setTitle("Season 3");
		trackerFacade.saveEpisode(episode);

		show = addShow(user, "Planet Earth", "Documentary", 9, null);

		episode = new Episode(user, show);
		episode.setTitle("From Pole to Pole");
		episode.setRating(8);
		trackerFacade.saveEpisode(episode);

		episode = new Episode(user, show);
		episode.setTitle("Mountains");
		episode.setRating(5);
		trackerFacade.saveEpisode(episode);

		episode = new Episode(user, show);
		episode.setTitle("Fresh Water");
		episode.setRating(8);
		trackerFacade.saveEpisode(episode);

		episode = new Episode(user, show);
		episode.setTitle("Caves");
		episode.setRating(4);
		trackerFacade.saveEpisode(episode);

		episode = new Episode(user, show);
		episode.setTitle("Deserts");
		episode.setRating(7);
		trackerFacade.saveEpisode(episode);

		episode = new Episode(user, show);
		episode.setTitle("Ice Worlds");
		episode.setRating(6);
		trackerFacade.saveEpisode(episode);

		episode = new Episode(user, show);
		episode.setTitle("Great Plains");
		episode.setRating(8);
		trackerFacade.saveEpisode(episode);

		episode = new Episode(user, show);
		episode.setTitle("Jungles");
		episode.setRating(6);
		trackerFacade.saveEpisode(episode);

		episode = new Episode(user, show);
		episode.setTitle("Shallow Seas");
		episode.setRating(7);
		trackerFacade.saveEpisode(episode);

		episode = new Episode(user, show);
		episode.setTitle("Seasonal Forests");
		episode.setRating(4);
		trackerFacade.saveEpisode(episode);

		episode = new Episode(user, show);
		episode.setTitle("Ocean Deep");
		episode.setRating(8);
		trackerFacade.saveEpisode(episode);
	}

	/**
	 * Adds a show.
	 *
	 * @param user
	 * @param title
	 * @param tag
	 * @param rating
	 * @param targetDate
	 * @return show
	 */
	private Show addShow(User user, String title, String tag, Integer rating, DateTime targetDate) {
		Show show = new Show(user);
		show.setTitle(title);
		show.setTag(tag);
		show.setRating(rating);
		show.setTargetDate(targetDate);

		trackerFacade.saveShow(show);

		return show;
	}

}
