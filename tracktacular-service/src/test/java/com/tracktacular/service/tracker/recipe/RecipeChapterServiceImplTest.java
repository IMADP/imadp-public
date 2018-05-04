package com.tracktacular.service.tracker.recipe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * RecipeChapterServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RecipeChapterServiceImplTest extends TracktacularServiceTestCase {
	RecipeChapter recipeChapter;
	User user;

	@Override
	public void before() throws Exception {
		super.before();

		user = new User();

		recipeChapter = new RecipeChapter();
		recipeChapter.setTitle("name");
		recipeChapter.setSort(1);
		recipeChapter.setUser(user);

		userService.save(user);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(recipeChapter, recipe_recipeChapterService);
	}

	@Test
	public void recipeOperations() {
		recipe_recipeChapterService.save(recipeChapter);

		Recipe recipe1 = new Recipe(user, recipeChapter);
		recipe1.setDescription("description");
		recipe1.setDirections("directions");
		recipe1.setIngredients("ingredients");
		recipe1.setNotes("notes");
		recipe1.setName("recipe1");

		Recipe recipe2 = new Recipe(user, recipeChapter);
		recipe2.setDescription("description");
		recipe2.setDirections("directions");
		recipe2.setIngredients("ingredients");
		recipe2.setNotes("notes");
		recipe2.setName("recipe2");

		Recipe recipe3 = new Recipe(user, recipeChapter);
		recipe3.setDescription("description");
		recipe3.setDirections("directions");
		recipe3.setIngredients("ingredients");
		recipe3.setNotes("notes");
		recipe3.setName("recipe3");

		recipeChapter = recipe_recipeChapterService.get(recipeChapter.getId());

		assertNotNull(recipeChapter);
		assertEquals(0, recipeChapter.getRecipes().size());

		recipe_recipeChapterService.saveRecipe(recipe1);
		recipe_recipeChapterService.saveRecipe(recipe2);
		recipe_recipeChapterService.saveRecipe(recipe3);

		recipeChapter = recipe_recipeChapterService.get(recipeChapter.getId());

		assertNotNull(recipeChapter);
		assertEquals(3, recipeChapter.getRecipes().size());
	}

	@Test
	public void multipleRecipeAssociations() throws Exception {
		RecipeChapter one = new RecipeChapter(user);
		one.setTitle("Chapter 1");
		one.setSort(3);

		RecipeChapter two = new RecipeChapter(user);
		one.setTitle("Chapter 2");
		one.setSort(2);

		RecipeChapter three = new RecipeChapter(user);
		one.setTitle("Chapter 3");
		one.setSort(1);

		recipe_recipeChapterService.save(one);
		recipe_recipeChapterService.save(two);
		recipe_recipeChapterService.save(three);

		List<RecipeChapter> categories = new ArrayList<>(3);
		categories.add(one);
		categories.add(two);
		categories.add(three);

		for(int i=0; i<15; i++)
			recipe_recipeService.save(createRecipe(user, categories));

		assertEquals(3, recipe_recipeChapterService.findCount());

		categories = recipe_recipeChapterService.findByUser(user, CriteriaParams.<RecipeChapter>of(
				Results.ALL, Order.asc(RecipeChapter.SORT)));

		assertEquals(3, categories.size());
	}

	/**
	 * Returns a mock object.
	 *
	 * @param user
	 * @return Recipe
	 */
	private Recipe createRecipe(User user, List<RecipeChapter> categories) {
		Recipe recipe = new Recipe();
		recipe.setUser(user);
		recipe.setName("name");
		recipe.setDescription("description");
		recipe.setDirections("directions");
		recipe.setIngredients("ingredients");
		recipe.setNotes("notes");
		recipe.setChapter(categories.get(new Random().nextInt(categories.size())));
		return recipe;
	}

}