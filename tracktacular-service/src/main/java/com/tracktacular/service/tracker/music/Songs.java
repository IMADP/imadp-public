package com.tracktacular.service.tracker.music;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import com.imadp.core.AbstractSerializable;


/**
 * Songs
 *
 * A collection of a songs.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Songs extends AbstractSerializable {

	// song count
	private final int songCount;

	// list of all songs
	private final List<Song> songs;

	// list of songs by category
	private final Collection<SongCategory> songCategories;

	// constructor
	public Songs(List<Song> songs, String sortProperty) {
		this.songs = songs;
		this.songCount = songs.size();
		this.songCategories = getSongCategories(songs, sortProperty);
	}

	/**
	 * Categorizes and returns a list of SongCategories based on the sortProperty.
	 *
	 * @param songs
	 * @param sortProperty
	 * @return Collection<SongCategory>
	 */
	private Collection<SongCategory> getSongCategories(List<Song> songs, String sortProperty) {

		// title
		if(Song.TITLE.getName().equalsIgnoreCase(sortProperty))
			return getSongCategoriesByTitle(songs);

		// instrument
		if(Song.INSTRUMENT.getName().equalsIgnoreCase(sortProperty))
			return getSongCategoriesByInstrument(songs);

		// rating
		if(Song.RATING.getName().equalsIgnoreCase(sortProperty))
			return getSongCategoriesByRating(songs);

		// tag
		if(Song.TAG.getName().equalsIgnoreCase(sortProperty))
			return getSongCategoriesByTag(songs);

		// album
		if(Song.ALBUM.getName().equalsIgnoreCase(sortProperty))
			return getSongCategoriesByAlbum(songs);

		return getSongCategoriesByArtist(songs);
	}

	/**
	 * Returns a list of unrated songs.
	 *
	 * @return List<Song>
	 */
	public List<Song> getUnratedSongs() {
		List<Song> unratedSongs = new ArrayList<>();

		for(Song song : songs)
			if(!song.isCompleted())
				unratedSongs.add(song);

		Collections.sort(unratedSongs, Song.TITLE_COMPARATOR);

		return unratedSongs;
	}

	/**
	 * Returns a collection of song categories by title.
	 *
	 * @param songs
	 * @return Collection<SongCategory>
	 */
	private Collection<SongCategory> getSongCategoriesByTitle(List<Song> songs) {
		Collections.sort(songs, Song.TITLE_COMPARATOR);

		Map<String, SongCategory> categoryMap = new LinkedHashMap<>();

		for(Song song : songs)
			getSongCategory(categoryMap, song.getTitle().substring(0, 1)).addItem(song);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of song categories by instrument.
	 *
	 * @param songs
	 * @return Collection<SongCategory>
	 */
	private Collection<SongCategory> getSongCategoriesByInstrument(List<Song> songs) {
		Collections.sort(songs, Song.INSTRUMENT_COMPARATOR);

		Map<String, SongCategory> categoryMap = new LinkedHashMap<>();

		for(Song song : songs)
			if(song.getInstrument() != null)
				getSongCategory(categoryMap, song.getInstrument()).addItem(song);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of song categories by album.
	 *
	 * @param albums
	 * @return Collection<SongCategory>
	 */
	private Collection<SongCategory> getSongCategoriesByAlbum(List<Song> songs) {
		Collections.sort(songs, Song.ALBUM_COMPARATOR);

		Map<String, SongCategory> categoryMap = new LinkedHashMap<>();

		for(Song song : songs)
			if(song.getAlbum() != null)
				getSongCategory(categoryMap, song.getAlbum()).addItem(song);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of song categories by artist.
	 *
	 * @param songs
	 * @return Collection<SongCategory>
	 */
	private Collection<SongCategory> getSongCategoriesByArtist(List<Song> songs) {
		Collections.sort(songs, Song.ARTIST_COMPARATOR);

		Map<String, SongCategory> categoryMap = new LinkedHashMap<>();

		for(Song song : songs)
			getSongCategory(categoryMap, song.getArtist()).addItem(song);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of album categories by tag.
	 *
	 * @param albums
	 * @return Collection<SongCategory>
	 */
	private Collection<SongCategory> getSongCategoriesByTag(List<Song> albums) {
		Collections.sort(albums, Song.ARTIST_COMPARATOR);

		Map<String, SongCategory> categoryMap = new LinkedHashMap<>();

		for(Song album : albums)
			if(!StringUtils.isBlank(album.getTag()))
				for(String tag : album.getTag().split(","))
					if(!StringUtils.isBlank(tag))
						getSongCategory(categoryMap, tag).addItem(album);

		List<SongCategory> albumCategories = new ArrayList<>(categoryMap.values());
		Collections.sort(albumCategories);
		return albumCategories;
	}

	/**
	 * Returns a collection of song categories by rating.
	 *
	 * @param songs
	 * @return Collection<SongCategory>
	 */
	private Collection<SongCategory> getSongCategoriesByRating(List<Song> songs) {
		Collections.sort(songs, Song.RATING_COMPARATOR);

		Map<String, SongCategory> categoryMap = new LinkedHashMap<>();
		getSongCategory(categoryMap, "Unrated");

		for(Song song : songs)
		{
			String categoryName = "Unrated";

			if(song.isCompleted())
				categoryName = song.getRating() + "/10";

			getSongCategory(categoryMap, categoryName).addItem(song);
		}

		return categoryMap.values();
	}

	/**
	 * Returns a SongCategory matching the category name.
	 *
	 * @param categoryMap
	 * @param categoryName
	 * @return SongCategory
	 */
	private SongCategory getSongCategory(Map<String, SongCategory> categoryMap, String categoryName) {
		SongCategory songCategory = categoryMap.get(categoryName.toLowerCase().trim());

		if(songCategory == null)
		{
			songCategory = new SongCategory(WordUtils.capitalize(categoryName.trim()));
			categoryMap.put(categoryName.toLowerCase().trim(), songCategory);
		}

		return songCategory;
	}

	/**
	 * Returns the count of all songs.
	 *
	 * @return int
	 */
	public int getSongCount() {
		return songCount;
	}

	/**
	 * Returns a collection of SongCategories.
	 *
	 * @return Collection<SongCategory>
	 */
	public Collection<SongCategory> getSongCategories() {
		return songCategories;
	}

}