package com.tracktacular.service.tracker.recipe;

import com.imadp.service.user.User;
import com.tracktacular.service.tracker.AbstractTrackerDemo;
import com.tracktacular.service.tracker.TrackerPreferences;


/**
 * RecipeTrackerDemo
 *
 * Inserts demo data for a tracker.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RecipeTrackerDemo extends AbstractTrackerDemo {
	private RecipeTrackerFacade trackerFacade;

	// constructor
	public RecipeTrackerDemo(RecipeTrackerFacade trackerFacade) {
		this.trackerFacade = trackerFacade;
	}

	@Override
	public void insertDemoData(User user, TrackerPreferences preferences) {

		// preferences
		preferences.setTrackerEnabled(true);
		preferences.setTrackerPublic(true);

		insertBreakfast(user);
		insertLunch(user);
		insertDinner(user);
		insertSmoothies(user);
		insertAlcohol(user);
	}

	/**
	 * Inserts breakfast recipes.
	 *
	 * @param user
	 */
	private void insertBreakfast(User user) {
		RecipeChapter chapter = new RecipeChapter(user);
		chapter.setTitle("Breakfast");
		chapter.setDescription("A collection of morning meals");

		trackerFacade.saveRecipeChapter(chapter);

		// hard boiled eggs
		Recipe recipe = new Recipe(user, chapter);
		recipe.setName("Hard Boiled Eggs");
		recipe.setDescription("Healthy and filling breakfast meal");
		recipe.setRecipeTagsAsString("cheap, healthy, protein");

		recipe.setIngredients(
				"1 dozen Extra Large Eggs.\n" +
				"Old Bay Seasoning or JO Seasoning.");

		recipe.setDirections(
				"Boil 1/3 pot of water for about 13 minutes.\n" +
				"Optionally poke a pin sized hole in each egg to relieve pressure.\n" +
				"Lower eggs slowly into the water and let them sit for 13 minutes.\n" +
				"Drain the water and replace with cold water (x3).\n" +
				"Peel the egg shells and discard yolks.\n" +
				"Sprinkle seasoning on egg whites.");

		trackerFacade.saveRecipe(recipe);

		// graham cracker cereal
		recipe = new Recipe(user, chapter);
		recipe.setName("Graham Cracker Cereal");
		recipe.setDescription("The best homemade cereal possible");
		recipe.setRecipeTagsAsString("cereal, unhealthy");

		recipe.setIngredients(
				"6 Graham Crackers\n" +
				"1 teaspoon of sugar\n" +
				"1 bowl of milk");

		recipe.setDirections(
				"Crack the graham crackers into bite sized chunks and place in a bowl.\n" +
				"Pour milk until they are covered.\n" +
				"Add a spoonful of sugar to coat the top and enjoy.");

		trackerFacade.saveRecipe(recipe);
	}

	/**
	 * Inserts lunch recipes.
	 *
	 * @param user
	 */
	private void insertLunch(User user) {
		RecipeChapter chapter = new RecipeChapter(user);
		chapter.setTitle("Lunch");
		chapter.setDescription("A collection of lunch meals");

		trackerFacade.saveRecipeChapter(chapter);

		// peanut butter and jelly
		Recipe recipe = new Recipe(user, chapter);
		recipe.setName("Peanut Butter and Jelly");
		recipe.setDescription("The most basic of lunch snacks");
		recipe.setRecipeTagsAsString("cheap");

		recipe.setIngredients(
				"Skippy Creamy Peanut Butter\n" +
				"Smuckers Concord Jelly\n" +
				"2 slices Arnold Health Multi Grain bread\n" +
				"Optional: 1 Packet of Sugar in the Raw");

		recipe.setDirections(
				"Apply jelly to one slice of bread.\n" +
				"Apply peanut butter to the other slice of bread.\n" +
				"Optional: Sprinkle Sugar in the Raw over peanut butter");

		trackerFacade.saveRecipe(recipe);
	}

	/**
	 * Inserts dinner recipes.
	 *
	 * @param user
	 */
	private void insertDinner(User user) {
		RecipeChapter chapter = new RecipeChapter(user);
		chapter.setTitle("Dinner");
		chapter.setDescription("A collection of dinner meals");

		trackerFacade.saveRecipeChapter(chapter);

		// spaghetti
		Recipe recipe = new Recipe(user, chapter);
		recipe.setFavorite(true);
		recipe.setName("Spaghetti with Gravy");
		recipe.setDescription("Unbeatable dinner in terms of flavor and value");
		recipe.setRecipeTagsAsString("carbs, cheap, healthy");

		recipe.setIngredients(
				"Gravy:\n" +
				"1 Onion\n" +
				"1 Garlic Clove\n" +
				"2 cans of Tomato Paste\n" +
				"2 cans of Tomato Puree\n" +
				"Olive Oil\n" +
				"Salt\n" +
				"Pepper\n" +
				"Sweet sausage\n" +
				"Hot sausage\n" +
				"Basil\n\n" +
				"Spaghetti:\n" +
				"1/2 lb Healthy Harvest Whole Wheat Thin Spaghetti");

		recipe.setDirections(
				"Gravy:\n" +
				"Heat up a thin layer of olive oil in a pot on low heat.\n" +
				"Mix up and heat chopped garlic, salt, pepper, and onion.\n" +
				"Chop up both sausages, mix and heat until brown.\n" +
				"Mix in 4 cans of Tomato Paste/Puree.\n" +
				"Heat up slowly, stir frequently and mix in basil once boiling.\n\n" +
				"Spaghetti:\n" +
				"Boil half a pot of water for 15 minutes.\n" +
				"Cook spaghetti for 15 minutes.\n");

		trackerFacade.saveRecipe(recipe);
	}

	/**
	 * Inserts smoothies recipes.
	 *
	 * @param user
	 */
	private void insertSmoothies(User user) {
		RecipeChapter chapter = new RecipeChapter(user);
		chapter.setTitle("Smoothies");
		chapter.setDescription("A collection of blender drinks");

		trackerFacade.saveRecipeChapter(chapter);

		// protein shake
		Recipe recipe = new Recipe(user, chapter);
		recipe.setName("Protein Shake");
		recipe.setDescription("Healthy shake ideal for post-workouts");
		recipe.setRecipeTagsAsString("healthy, protein");

		recipe.setIngredients(
				"2 scoops Pro Complex Chocolate protein powder\n" +
				"1 banana\n" +
				"1 cup of milk\n" +
				"5 ice cubes");

		recipe.setDirections(
				"Blend all ingredients for about 30 seconds on high speed.");

		trackerFacade.saveRecipe(recipe);
	}

	/**
	 * Inserts alcohol recipes.
	 *
	 * @param user
	 */
	private void insertAlcohol(User user) {
		RecipeChapter chapter = new RecipeChapter(user);
		chapter.setTitle("Alcohol");
		chapter.setDescription("A collection of alcoholic beverages");

		trackerFacade.saveRecipeChapter(chapter);

		// royal sapphire
		Recipe recipe = new Recipe(user, chapter);
		recipe.setName("Royal Sapphire");
		recipe.setDescription("Class in a bottle");
		recipe.setRecipeTagsAsString("classy");

		recipe.setIngredients(
				"1/3 cup Bombay Sapphire Gin\n" +
				"1/3 cup Cranberry Juice\n" +
				"1/3 cup Lemonade");

		recipe.setDirections(
				"Mix all three and drink while wearing a monocle.");

		trackerFacade.saveRecipe(recipe);
	}

}
