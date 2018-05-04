package com.tracktacular.service.tracker.recipe;

import java.util.Set;

import com.imadp.core.Property;
import com.imadp.service.Sortable;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * RecipeChapter
 *
 * A chapter in a recipe book containing a group of recipes.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RecipeChapter extends AbstractPersistableUser implements Sortable {

	// static properties
	public static final Property<RecipeChapter, String> TITLE = Property.of("title");
	public static final Property<RecipeChapter, String> DESCRIPTION = Property.of("description");
	public static final Property<RecipeChapter, Boolean> COLLAPSED = Property.of("collapsed");
	public static final Property<RecipeChapter, Integer> SORT = Property.of("sort");

	// properties
	private Set<Recipe> recipes;
	private String title;
	private String description;
	private boolean collapsed;
	private int sort;

	// constructor
	public RecipeChapter() {

	}

	// constructor
	public RecipeChapter(User user) {
		super(user);
	}

	/**
	 * Returns a new recipe for this chapter.
	 *
	 * @return Recipe
	 */
	public Recipe getNewRecipe() {
		return new Recipe(user, this);
	}

	/**
	 * Returns true if recipes are present, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean hasRecipes() {
		return recipes != null && !recipes.isEmpty();
	}

	/**
	 * Returns the count of recipes in this chapter.
	 *
	 * @return int
	 */
	public int getRecipeCount() {
		return recipes == null ? 0 : recipes.size();
	}

	// getters and setters
	public Set<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}

	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int getSort() {
		return sort;
	}

	@Override
	public void setSort(int sort) {
		this.sort = sort;
	}

}