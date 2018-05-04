package com.tracktacular.service.tracker.recipe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.imadp.dao.PersistableState;
import com.imadp.service.ServiceTestUtil;
import com.imadp.service.user.User;
import com.tracktacular.service.test.TracktacularServiceTestCase;


/**
 * RecipeServiceImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class RecipeServiceImplTest extends TracktacularServiceTestCase {
	Recipe recipe;

	@Override
	public void before() throws Exception {
		super.before();

		User user = new User();

		RecipeChapter recipeChapter = new RecipeChapter();
		recipeChapter.setUser(user);

		recipe = new Recipe();
		recipe.setName("name");
		recipe.setDescription("description");
		recipe.setDirections("directions");
		recipe.setIngredients("ingredients");
		recipe.setNotes("notes");
		recipe.setFavorite(true);
		recipe.setChapter(recipeChapter);
		recipe.setUser(user);

		userService.save(user);
		recipe_recipeChapterService.save(recipeChapter);
	}

	@Test
	public void commonPersistableOperations() {
		ServiceTestUtil.assertPersistable(recipe, recipe_recipeService);
	}

	@Test
	public void recipeTags() {
		recipe.addRecipeTag("1");
		recipe.addRecipeTag("2");
		recipe.addRecipeTag("3");

		assertEquals(3, recipe.getTags().size());

		recipe_recipeService.save(recipe);

		assertEquals(3, recipe.getTags().size());

		recipe = recipe_recipeService.get(recipe.getId());

		assertEquals(3, recipe.getTags().size());

		recipe.setPersistableState(PersistableState.DELETED);

		recipe_recipeService.save(recipe);

		recipe = recipe_recipeService.get(recipe.getId());

		assertEquals(3, recipe.getTags().size());
		assertEquals(PersistableState.DELETED, recipe.getTags().iterator().next().getPersistableState());

		recipe = recipe_recipeService.get(recipe.getId());

		recipe_recipeService.delete(recipe);

		recipe = recipe_recipeService.get(recipe.getId());

		assertNull(recipe);
	}

}