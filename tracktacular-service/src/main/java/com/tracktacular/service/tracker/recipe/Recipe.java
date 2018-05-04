package com.tracktacular.service.tracker.recipe;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.imadp.core.Property;
import com.imadp.dao.PersistableState;
import com.imadp.service.Sortable;
import com.imadp.service.user.AbstractPersistableUser;
import com.imadp.service.user.User;


/**
 * Recipe
 *
 * A user supplied recipe.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Recipe extends AbstractPersistableUser implements Sortable {

	// static Properties
	public static final Property<Recipe, RecipeChapter> RECIPE_CHAPTER = Property.of("chapter");
	public static final Property<Recipe, String> CHAPTER_TITLE = Property.of("chapterTitle");
	public static final Property<Recipe, String> NAME = Property.of("name");
	public static final Property<Recipe, String> DESCRIPTION = Property.of("description");
	public static final Property<Recipe, String> INGREDIENTS = Property.of("ingredients");
	public static final Property<Recipe, String> DIRECTIONS = Property.of("directions");
	public static final Property<Recipe, String> NOTES = Property.of("notes");
	public static final Property<Recipe, Integer> SORT = Property.of("sort");
	public static final Property<Recipe, Boolean> FAVORITE = Property.of("favorite");

	// properties
	private RecipeChapter chapter;
	private String chapterTitle;
	private String name;
	private String description;
	private String ingredients;
	private String directions;
	private String notes;
	private Set<RecipeTag> tags;
	private boolean favorite;
	private int sort;

	// constructor
	public Recipe() {
		this(null, null);
	}

	// constructor
	public Recipe(User user, RecipeChapter chapter) {
		super(user);
		this.chapter = chapter;
		setPersistableState(PersistableState.ACTIVE);
	}

	/**
	 * Adds a tag to the recipe.
	 *
	 * @param tag
	 */
	public void addRecipeTag(String tag) {
		if(tags == null)
			tags = new HashSet<>();

		tags.add(new RecipeTag(user, this, tag, getPersistableState()));
	}

	/**
	 * Returns tags as a comma separated list of values.
	 *
	 * @return String
	 */
	public String getRecipeTagsAsString() {
		return RecipeTag.getTagNamesAsString(tags);
	}

	/**
	 * Sets the dreamsigns from a comma separated list of names.
	 *
	 * @param recipeTagsAsString
	 */
	public void setRecipeTagsAsString(String recipeTagsAsString) {
		setTags(null);

		for(String name : RecipeTag.getTagsNamesAsSet(recipeTagsAsString))
			addRecipeTag(name);
	}

	@Override
	protected void onActiveStateChange() {
		this.chapterTitle = null;
	}

	@Override
	protected void onArchivedStateChange() {
		if(chapter != null)
		{
			this.chapterTitle = chapter.getTitle();
			this.chapter = null;
		}
	}

	@Override
	protected void onDeletedStateChange() {
		if(chapter != null)
		{
			this.chapterTitle = chapter.getTitle();
			this.chapter = null;
		}
	}

	/**
	 * Returns a list of recipeItems out of ingredients.
	 *
	 * @return List<RecipeItem>
	 */
	public List<RecipeItems> getIngredientItems() {
		return RecipeItems.parseRecipeItems(ingredients);
	}

	/**
	 * Returns a list of recipeItems out of directions.
	 *
	 * @return List<RecipeItem>
	 */
	public List<RecipeItems> getDirectionItems() {
		return RecipeItems.parseRecipeItems(directions);
	}

	// getters and setters
	public RecipeChapter getChapter() {
		return chapter;
	}

	public void setChapter(RecipeChapter chapter) {
		this.chapter = chapter;
	}

	public String getName() {
		return name;
	}

	public String getNameSlug() {
		return toSlug(getName());
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getChapterTitle() {
		return chapterTitle;
	}

	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public Set<RecipeTag> getTags() {
		return tags;
	}

	public void setTags(Set<RecipeTag> tags) {
		this.tags = tags;
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
