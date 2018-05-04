package com.tracktacular.service.tracker.music;

import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;



/**
 * MusicTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class MusicTrackerDemo extends AbstractTrackerDemo {
	private MusicTrackerFacade trackerFacade;

	// constructor
	public MusicTrackerDemo(MusicTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		// albums

		// fall out boy
		addAlbum(user, "Take This To Your Grave", "Fall Out Boy", "Pop punk", 9);
		addAlbum(user, "From Under The Cork Tree", "Fall Out Boy", "Pop punk", 8);
		addAlbum(user, "Infinity on High", "Fall Out Boy", "Pop punk", 7);
		addAlbum(user, "Folie a Deux", "Fall Out Boy", "Pop punk", 7);
		addAlbum(user, "Save Rock and Roll", "Fall Out Boy", "Pop punk", 7);

		// weezer
		addAlbum(user, "The Blue Album", "Weezer", "Alternative", 10);
		addAlbum(user, "Pinkerton", "Weezer", "Alternative", 5);
		addAlbum(user, "The Green Album", "Weezer", "Alternative", 6);

		// panic
		addAlbum(user, "A Fever You Can't Sweat Out", "Panic! At the Disco", "Synthrock", 9);
		addAlbum(user, "Pretty. Odd.", "Panic! At the Disco", "Synthrock", 7);
		addAlbum(user, "Vices & Virtues", "Panic! At the Disco", "Synthrock", 7);

		// owl city
		addAlbum(user, "Maybe I'm Dreaming", "Owl City", "Synthpop", 9);
		addAlbum(user, "Ocean Eyes", "Owl City", "Synthpop", 9);
		addAlbum(user, "All Things Bright and Beautiful", "Owl City", "Synthpop", 6);
		addAlbum(user, "The Midsummer Station", "Owl City", "Synthpop", 6);

		// postal service
		addAlbum(user, "Give Up", "Postal Service", "Synthpop", 10);

		// death cab
		addAlbum(user, "Something About Airplanes", "Death Cab for Cutie", "Indie Rock", 6);
		addAlbum(user, "The Photo Album", "Death Cab for Cutie", "Indie Rock", 6);
		addAlbum(user, "Transatlanticism", "Death Cab for Cutie", "Indie Rock", 6);
		addAlbum(user, "Plans", "Death Cab for Cutie", "Indie Rock", 9);
		addAlbum(user, "Narrow Stairs", "Death Cab for Cutie", "Indie Rock", 7);
		addAlbum(user, "Codes and Keys", "Death Cab for Cutie", "Indie Rock", 7);

		// songs
		addSong(user, "Stairway to Heaven", "Led Zeppelin", null, null, 6, "Guitar", 100);
		addSong(user, "The House of the Rising Sun", "The Animals", null, null, 6, "Guitar", 100);
		addSong(user, "Wonderwall", "Oasis", null, null, 6, "Guitar", 100);
		addSong(user, "Hotel California", "The Eagles", null, null, null, "Guitar", 50);
		addSong(user, "Fur Elise", "Beethoven", null, null, null, "Piano", 75);

	}

	/**
	 * Adds an album.
	 *
	 * @param user
	 * @param title
	 * @param artist
	 * @param tag
	 * @param rating
	 */
	private void addAlbum(User user, String title, String artist, String tag, Integer rating) {
		Album album = new Album(user);
		album.setTitle(title);
		album.setArtist(artist);
		album.setTag(tag);
		album.setRating(rating);
		trackerFacade.saveAlbum(album);
	}

	/**
	 * Adds a song.
	 *
	 * @param user
	 * @param title
	 * @param artist
	 * @param album
	 * @param tag
	 * @param instrument
	 * @param progress
	 * @param rating
	 */
	private void addSong(User user, String title, String artist, String album, String tag, Integer rating, String instrument, int progress) {
		Song song = new Song(user);
		song.setTitle(title);
		song.setArtist(artist);
		song.setAlbum(album);
		song.setTag(tag);
		song.setRating(rating);
		song.setInstrument(instrument);
		song.setProgress(progress);
		trackerFacade.saveSong(song);
	}

}
