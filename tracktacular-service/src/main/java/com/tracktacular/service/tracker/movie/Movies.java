package com.tracktacular.service.tracker.movie;

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
 * Movies
 *
 * A collection of a movies.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Movies extends AbstractSerializable {

	// movie count
	private final int movieCount;

	// list of all movies
	private final List<Movie> movies;

	// list of movies by category
	private final Collection<MovieCategory> movieCategories;

	// constructor
	public Movies(List<Movie> movies, String sortProperty) {
		this.movies = movies;
		this.movieCount = movies.size();
		this.movieCategories = getMovieCategories(movies, sortProperty);
	}

	/**
	 * Categorizes and returns a list of MovieCategories based on the sortProperty.
	 *
	 * @param movies
	 * @param sortProperty
	 * @return Collection<MovieCategory>
	 */
	private Collection<MovieCategory> getMovieCategories(List<Movie> movies, String sortProperty) {

		// tag
		if(Movie.TAG.getName().equalsIgnoreCase(sortProperty))
			return getMovieCategoriesByTag(movies);

		// rating
		if(Movie.RATING.getName().equalsIgnoreCase(sortProperty))
			return getMovieCategoriesByRating(movies);

		// author
		return getMovieCategoriesByTitle(movies);
	}

	/**
	 * Returns a collection of movie categories by title.
	 *
	 * @param movies
	 * @return Collection<MovieCategory>
	 */
	private Collection<MovieCategory> getMovieCategoriesByTitle(List<Movie> movies) {
		Collections.sort(movies, Movie.TITLE_COMPARATOR);

		Map<String, MovieCategory> categoryMap = new LinkedHashMap<>();

		for(Movie movie : movies)
			getMovieCategory(categoryMap, movie.getTitle().substring(0, 1)).addItem(movie);

		return categoryMap.values();
	}

	/**
	 * Returns a collection of movie categories by tag.
	 *
	 * @param movies
	 * @return Collection<MovieCategory>
	 */
	private Collection<MovieCategory> getMovieCategoriesByTag(List<Movie> movies) {
		Collections.sort(movies, Movie.TITLE_COMPARATOR);

		Map<String, MovieCategory> categoryMap = new LinkedHashMap<>();

		for(Movie movie : movies)
			if(!StringUtils.isBlank(movie.getTag()))
				for(String tag : movie.getTag().split(","))
					if(!StringUtils.isBlank(tag))
						getMovieCategory(categoryMap, tag).addItem(movie);

		List<MovieCategory> movieCategories = new ArrayList<>(categoryMap.values());
		Collections.sort(movieCategories);
		return movieCategories;
	}

	/**
	 * Returns a collection of movie categories by rating.
	 *
	 * @param movies
	 * @return Collection<MovieCategory>
	 */
	private Collection<MovieCategory> getMovieCategoriesByRating(List<Movie> movies) {
		Collections.sort(movies, Movie.RATING_COMPARATOR);

		Map<String, MovieCategory> categoryMap = new LinkedHashMap<>();
		getMovieCategory(categoryMap, "Unrated");

		for(Movie movie : movies)
		{
			String categoryName = "Unrated";

			if(movie.isCompleted())
				categoryName = movie.getRating() + "/10";

			getMovieCategory(categoryMap, categoryName).addItem(movie);
		}

		return categoryMap.values();
	}

	/**
	 * Returns a MovieCategory matching the category name.
	 *
	 * @param categoryMap
	 * @param categoryName
	 * @return MovieCategory
	 */
	private MovieCategory getMovieCategory(Map<String, MovieCategory> categoryMap, String categoryName) {
		MovieCategory movieCategory = categoryMap.get(categoryName.toLowerCase().trim());

		if(movieCategory == null)
		{
			movieCategory = new MovieCategory(WordUtils.capitalize(categoryName.trim()));
			categoryMap.put(categoryName.toLowerCase().trim(), movieCategory);
		}

		return movieCategory;
	}

	/**
	 * Returns a list of unrated movies.
	 *
	 * @return List<Movie>
	 */
	public List<Movie> getUnratedMovies() {
		List<Movie> unratedMovies = new ArrayList<>();

		for(Movie movie : movies)
			if(!movie.isCompleted())
				unratedMovies.add(movie);

		Collections.sort(unratedMovies, Movie.TITLE_COMPARATOR);

		return unratedMovies;
	}

	/**
	 * Returns the count of all movies.
	 *
	 * @return int
	 */
	public int getMovieCount() {
		return movieCount;
	}

	/**
	 * Returns a collection of MovieCategories.
	 *
	 * @return Collection<MovieCategory>
	 */
	public Collection<MovieCategory> getMovieCategories() {
		return movieCategories;
	}

	/**
	 * Returns true if the given movie was found in the movies collection, according to title.
	 *
	 * @param otherMovie
	 * @return boolean
	 */
	public boolean hasDuplicate(Movie otherMovie) {
		for(Movie movie : movies)
			if(StringUtils.equals(toSlug(movie.getTitle()), toSlug(otherMovie.getTitle())))
				return !movie.equals(otherMovie);

		return false;
	}

}