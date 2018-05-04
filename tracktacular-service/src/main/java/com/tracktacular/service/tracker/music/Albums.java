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
 * Albums
 *
 * A collection of a albums.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Albums extends AbstractSerializable {

	// album count
	private final int albumCount;

	// list of all albums
	private final List<Album> albums;

	// list of albums by category
	private final Collection<AlbumCategory> albumCategories;

	// constructor
	public Albums(List<Album> albums, String sortProperty) {
		this.albums = albums;
		this.albumCount = albums.size();
		this.albumCategories = getAlbumCategories(albums, sortProperty);
	}

	/**
	 * Categorizes and returns a list of AlbumCategories based on the sortProperty.
	 *
	 * @param albums
	 * @param sortProperty
	 * @return Collection<AlbumCategory>
	 */
	private Collection<AlbumCategory> getAlbumCategories(List<Album> albums, String sortProperty) {

		// title
		if(Album.TITLE.getName().equalsIgnoreCase(sortProperty))
			return getAlbumCategoriesByTitle(albums);

		// rating
		if(Album.RATING.getName().equalsIgnoreCase(sortProperty))
			return getAlbumCategoriesByRating(albums);

		// tag
		if(Album.TAG.getName().equalsIgnoreCase(sortProperty))
			return getAlbumCategoriesByTag(albums);

		return getAlbumCategoriesByArtist(albums);
	}

	/**
	 * Returns a list of unrated albums.
	 *
	 * @return List<Album>
	 */
	public List<Album> getUnratedAlbums() {
		List<Album> unratedAlbums = new ArrayList<>();

		for(Album album : albums)
			if(!album.isCompleted())
				unratedAlbums.add(album);

		Collections.sort(unratedAlbums, Album.TITLE_COMPARATOR);

		return unratedAlbums;
	}

	/**
	 * Returns a collection of album categories by title.
	 *
	 * @param albums
	 * @return Collection<AlbumCategory>
	 */
	private Collection<AlbumCategory> getAlbumCategoriesByTitle(List<Album> albums) {
		Collections.sort(albums, Album.TITLE_COMPARATOR);

		Map<String, AlbumCategory> categoryMap = new LinkedHashMap<>();

		for(Album album : albums)
			getAlbumCategory(categoryMap, album.getTitle().substring(0, 1)).addItem(album);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of album categories by artist.
	 *
	 * @param albums
	 * @return Collection<AlbumCategory>
	 */
	private Collection<AlbumCategory> getAlbumCategoriesByArtist(List<Album> albums) {
		Collections.sort(albums, Album.ARTIST_COMPARATOR);

		Map<String, AlbumCategory> categoryMap = new LinkedHashMap<>();

		for(Album album : albums)
			getAlbumCategory(categoryMap, album.getArtist()).addItem(album);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of album categories by tag.
	 *
	 * @param albums
	 * @return Collection<AlbumCategory>
	 */
	private Collection<AlbumCategory> getAlbumCategoriesByTag(List<Album> albums) {
		Collections.sort(albums, Album.ARTIST_COMPARATOR);

		Map<String, AlbumCategory> categoryMap = new LinkedHashMap<>();

		for(Album album : albums)
			if(!StringUtils.isBlank(album.getTag()))
				for(String tag : album.getTag().split(","))
					if(!StringUtils.isBlank(tag))
						getAlbumCategory(categoryMap, tag).addItem(album);

		List<AlbumCategory> albumCategories = new ArrayList<>(categoryMap.values());
		Collections.sort(albumCategories);
		return albumCategories;
	}

	/**
	 * Returns a collection of album categories by rating.
	 *
	 * @param albums
	 * @return Collection<AlbumCategory>
	 */
	private Collection<AlbumCategory> getAlbumCategoriesByRating(List<Album> albums) {
		Collections.sort(albums, Album.RATING_COMPARATOR);

		Map<String, AlbumCategory> categoryMap = new LinkedHashMap<>();
		getAlbumCategory(categoryMap, "Unrated");

		for(Album album : albums)
		{
			String categoryName = "Unrated";

			if(album.isCompleted())
				categoryName = album.getRating() + "/10";

			getAlbumCategory(categoryMap, categoryName).addItem(album);
		}

		return categoryMap.values();
	}

	/**
	 * Returns a AlbumCategory matching the category name.
	 *
	 * @param categoryMap
	 * @param categoryName
	 * @return AlbumCategory
	 */
	private AlbumCategory getAlbumCategory(Map<String, AlbumCategory> categoryMap, String categoryName) {
		AlbumCategory albumCategory = categoryMap.get(categoryName.toLowerCase().trim());

		if(albumCategory == null)
		{
			albumCategory = new AlbumCategory(WordUtils.capitalize(categoryName.trim()));
			categoryMap.put(categoryName.toLowerCase().trim(), albumCategory);
		}

		return albumCategory;
	}

	/**
	 * Returns the count of all albums.
	 *
	 * @return int
	 */
	public int getAlbumCount() {
		return albumCount;
	}

	/**
	 * Returns a collection of AlbumCategories.
	 *
	 * @return Collection<AlbumCategory>
	 */
	public Collection<AlbumCategory> getAlbumCategories() {
		return albumCategories;
	}

	/**
	 * Returns true if the given album was found in the albums collection, according to title and artist.
	 *
	 * @param otherAlbum
	 * @return boolean
	 */
	public boolean hasDuplicate(Album otherAlbum) {
		for(Album album : albums)
			if(StringUtils.equals(toSlug(album.getTitle()), toSlug(otherAlbum.getTitle())))
				if(StringUtils.equals(toSlug(album.getArtist()), toSlug(otherAlbum.getArtist())))
					return !album.equals(otherAlbum);

		return false;
	}

}