package com.tracktacular.service.tracker.tv;

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
 * Shows
 *
 * A collection of a shows.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Shows extends AbstractSerializable {

	// show count
	private final int showCount;

	// list of all shows
	private final List<Show> shows;

	// list of shows by category
	private final Collection<ShowCategory> showCategories;

	// constructor
	public Shows(List<Show> shows, String sortProperty) {
		this.shows = shows;
		this.showCount = shows.size();
		this.showCategories = getShowCategories(shows, sortProperty);
	}

	/**
	 * Categorizes and returns a list of ShowCategories based on the sortProperty.
	 *
	 * @param shows
	 * @param sortProperty
	 * @return Collection<ShowCategory>
	 */
	private Collection<ShowCategory> getShowCategories(List<Show> shows, String sortProperty) {

		// tag
		if(Show.TAG.getName().equalsIgnoreCase(sortProperty))
			return getShowCategoriesByTag(shows);

		// rating
		if(Show.RATING.getName().equalsIgnoreCase(sortProperty))
			return getShowCategoriesByRating(shows);

		// author
		return getShowCategoriesByTitle(shows);
	}

	/**
	 * Returns a collection of show categories by title.
	 *
	 * @param shows
	 * @return Collection<ShowCategory>
	 */
	private Collection<ShowCategory> getShowCategoriesByTitle(List<Show> shows) {
		Collections.sort(shows, Show.TITLE_COMPARATOR);

		Map<String, ShowCategory> categoryMap = new LinkedHashMap<>();

		for(Show show : shows)
			getShowCategory(categoryMap, show.getTitle().substring(0, 1)).addItem(show);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of show categories by tag.
	 *
	 * @param shows
	 * @return Collection<ShowCategory>
	 */
	private Collection<ShowCategory> getShowCategoriesByTag(List<Show> shows) {
		Collections.sort(shows, Show.TITLE_COMPARATOR);

		Map<String, ShowCategory> categoryMap = new LinkedHashMap<>();

		for(Show show : shows)
			if(!StringUtils.isBlank(show.getTag()))
				for(String tag : show.getTag().split(","))
					if(!StringUtils.isBlank(tag))
						getShowCategory(categoryMap, tag).addItem(show);

		List<ShowCategory> showCategories = new ArrayList<>(categoryMap.values());
		Collections.sort(showCategories);
		return showCategories;
	}

	/**
	 * Returns a collection of show categories by rating.
	 *
	 * @param shows
	 * @return Collection<ShowCategory>
	 */
	private Collection<ShowCategory> getShowCategoriesByRating(List<Show> shows) {
		Collections.sort(shows, Show.RATING_COMPARATOR);

		Map<String, ShowCategory> categoryMap = new LinkedHashMap<>();
		getShowCategory(categoryMap, "Unrated");

		for(Show show : shows)
		{
			String categoryName = "Unrated";

			if(show.isCompleted())
				categoryName = show.getRating() + "/10";

			getShowCategory(categoryMap, categoryName).addItem(show);
		}

		return categoryMap.values();
	}

	/**
	 * Returns a ShowCategory matching the category name.
	 *
	 * @param categoryMap
	 * @param categoryName
	 * @return ShowCategory
	 */
	private ShowCategory getShowCategory(Map<String, ShowCategory> categoryMap, String categoryName) {
		ShowCategory showCategory = categoryMap.get(categoryName.toLowerCase().trim());

		if(showCategory == null)
		{
			showCategory = new ShowCategory(WordUtils.capitalize(categoryName.trim()));
			categoryMap.put(categoryName.toLowerCase().trim(), showCategory);
		}

		return showCategory;
	}

	/**
	 * Returns a list of unrated shows.
	 *
	 * @return List<Show>
	 */
	public List<Show> getUnratedShows() {
		List<Show> unratedShows = new ArrayList<>();

		for(Show show : shows)
			if(!show.isCompleted())
				unratedShows.add(show);

		Collections.sort(unratedShows, Show.TITLE_COMPARATOR);

		return unratedShows;
	}

	/**
	 * Returns the count of all shows.
	 *
	 * @return int
	 */
	public int getShowCount() {
		return showCount;
	}

	/**
	 * Returns a collection of ShowCategories.
	 *
	 * @return Collection<ShowCategory>
	 */
	public Collection<ShowCategory> getShowCategories() {
		return showCategories;
	}

	/**
	 * Returns true if the given show was found in the shows collection, according to title.
	 *
	 * @param otherShow
	 * @return boolean
	 */
	public boolean hasDuplicate(Show otherShow) {
		for(Show show : shows)
			if(StringUtils.equals(toSlug(show.getTitle()), toSlug(otherShow.getTitle())))
				return !show.equals(otherShow);

		return false;
	}

}