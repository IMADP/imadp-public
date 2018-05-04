package com.tracktacular.service.tracker.music;

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
 * MusicTrackerFacadeImpl
 *
 * The standard implementation of the MusicTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class MusicTrackerFacadeImpl extends AbstractTrackerFacade
	implements MusicTrackerFacade {

	private AlbumService albumService;
	private SongService songService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(albumService);
		Validate.notNull(songService);
	}

	@Override
	public Song getSong(User user, String uid) {
		return songService.findFirstByUser(user, uid);
	}

	@Override
	public void saveSong(Song song) {
		validateSong(song);
		songService.save(song);
	}

	/**
	 * Validates a Song.
	 *
	 * @param song
	 */
	private void validateSong(Song song) {
		new SongValidator("song", song).validate();
	}

	@Override
	public void deleteSong(Song song) {
		songService.delete(song);
	}

	@Override
	public Songs findActiveSongs(User user, String sortProperty) {
		List<Song> activeSongs = songService.findByUser(
				user, PersistableState.ACTIVE, CriteriaParams.<Song>of(Results.ALL));

		return new Songs(activeSongs, sortProperty);
	}

	@Override
	public List<Song> findDeletedSongs(User user, CriteriaParams<Song> criteriaParams) {
		return songService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedSongCount(User user) {
		return songService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	public Album getAlbum(User user, String uid) {
		return albumService.findFirstByUser(user, uid);
	}

	@Override
	public void saveAlbum(Album album) {
		new AlbumValidator("album", album, findActiveAlbums(album.getUser(), Album.TITLE.getName())).validate();
		albumService.save(album);
	}

	@Override
	public void deleteAlbum(Album album) {
		albumService.delete(album);
	}

	@Override
	public Albums findActiveAlbums(User user, String sortProperty) {
		List<Album> activeAlbums = albumService.findByUser(
				user, PersistableState.ACTIVE, CriteriaParams.<Album>of(Results.ALL));

		return new Albums(activeAlbums, sortProperty);
	}

	@Override
	public List<Album> findDeletedAlbums(User user, CriteriaParams<Album> criteriaParams) {
		return albumService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedAlbumCount(User user) {
		return albumService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	protected void onDeleteAll(User user) {
		List<Song> songs = songService.findByUser(user, CriteriaParams.<Song>of(Results.ALL));

		for(Song song : songs)
			deleteSong(song);

		List<Album> albums = albumService.findByUser(user, CriteriaParams.<Album>of(Results.ALL));

		for(Album album : albums)
			deleteAlbum(album);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new MusicTrackerDemo(this);
	}

	@Override
	public MusicTrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		Songs songs = findActiveSongs(user, Song.ARTIST.getName());
		Albums albums = findActiveAlbums(user, Album.ARTIST.getName());
		return new MusicTrackerReport(preferences.getTrackers().getMusicTrackerPreferences(), songs, albums);
	}

	@Override
	public List<CalendarEntry> getTrackerCalendarEntries(User user, Interval interval, Preferences preferences) {
		List<CalendarEntry> calendarEntries = new ArrayList<>();

		// active songs
		Songs songs = findActiveSongs(user, Song.ARTIST.getName());

		for(SongCategory category : songs.getSongCategories())
			for(Song song : category.getItems())
			{
				if(song.getTargetDate() != null)
				{
					CalendarEntry calendarEntry = new CalendarEntry(user, song.getTargetDate());
					calendarEntry.setTracker(Tracker.MUSIC);
					calendarEntry.setName(String.format("Song: %s", song.getTitle()));
					calendarEntry.setDescription(song.getArtist());
					calendarEntries.add(calendarEntry);
				}
			}

		// active albums
		Albums albums = findActiveAlbums(user, Album.ARTIST.getName());

		for(AlbumCategory category : albums.getAlbumCategories())
			for(Album album : category.getItems())
			{
				if(album.getTargetDate() != null)
				{
					CalendarEntry calendarEntry = new CalendarEntry(user, album.getTargetDate());
					calendarEntry.setTracker(Tracker.MUSIC);
					calendarEntry.setName(String.format("Album: %s", album.getTitle()));
					calendarEntry.setDescription(album.getArtist());
					calendarEntries.add(calendarEntry);
				}
			}

		return calendarEntries;
	}

	// getters and setters
	public SongService getSongService() {
		return songService;
	}

	public void setSongService(SongService songService) {
		this.songService = songService;
	}

	public AlbumService getAlbumService() {
		return albumService;
	}

	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}

}