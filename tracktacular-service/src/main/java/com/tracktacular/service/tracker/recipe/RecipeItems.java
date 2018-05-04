package com.tracktacular.service.tracker.recipe;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.imadp.core.AbstractSerializable;


/**
 * RecipeItem
 *
 * A line item in a recipe.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RecipeItems extends AbstractSerializable {

	/**
	 * Parses a list of recipeItems out of text.
	 *
	 * @param text
	 * @return List<RecipeItems>
	 */
	public static List<RecipeItems> parseRecipeItems(String text) {
		if(text == null)
			return null;

		List<RecipeItems> recipeItemsList = new ArrayList<>();
		RecipeItems recipeItems = null;

		for(String item : text.split("\\r?\\n"))
		{
			if(StringUtils.isBlank(item))
				continue;

			// if we have a new recipeItem in the text, add it and continue o the next element
			if(item.endsWith(":"))
			{
				recipeItems = new RecipeItems(item);
				recipeItemsList.add(recipeItems);
				continue;
			}

			// if we have a no items created, add one now
			if(recipeItems == null)
			{
				recipeItems = new RecipeItems(null);
				recipeItemsList.add(recipeItems);
			}

			recipeItems.getItems().add(item);
		}

		return recipeItemsList;
	}

	// properties
	private final String name;
	private final List<String> items;

	// constructor
	private RecipeItems(String name) {
		this.name = name;
		this.items = new ArrayList<>();
	}

	// getters
	public String getName() {
		return name;
	}

	public List<String> getItems() {
		return items;
	}

}