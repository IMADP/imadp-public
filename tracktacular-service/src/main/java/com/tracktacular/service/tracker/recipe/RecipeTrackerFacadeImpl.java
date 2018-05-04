package com.tracktacular.service.tracker.recipe;

import java.util.List;

import org.apache.commons.lang.Validate;

import com.imadp.dao.PersistableState;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Order;
import com.imadp.dao.criteria.Results;
import com.imadp.service.tag.TagCloud;
import com.imadp.service.tag.TagFrequency;
import com.imadp.service.user.User;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.tracker.AbstractTrackerFacade;
import com.tracktacular.service.tracker.TrackerDemo;


/**
 * RecipeTrackerFacadeImpl
 *
 * The standard implementation of the RecipeTrackerFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class RecipeTrackerFacadeImpl extends AbstractTrackerFacade
	implements RecipeTrackerFacade {

	private RecipeService recipeService;
	private RecipeTagService recipeTagService;
	private RecipeChapterService recipeChapterService;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(recipeService);
		Validate.notNull(recipeTagService);
		Validate.notNull(recipeChapterService);
	}

	@Override
	public Recipe getRecipe(User user, String uid) {
		return recipeService.findFirstByUser(user, uid);
	}

	@Override
	public RecipeBook getRecipeBook(User user) {
		List<RecipeChapter> chapters = recipeChapterService.findByUser(user,
				CriteriaParams.of(Results.ALL, Order.asc(RecipeChapter.SORT)));

		return new RecipeBook(chapters);
	}

	@Override
	public void saveRecipe(Recipe recipe) {
		validateRecipe(recipe);
		recipeChapterService.saveRecipe(recipe);
	}

	@Override
	public void saveRecipes(List<Recipe> recipes) {
		for(Recipe recipe : recipes)
			validateRecipe(recipe);

		recipeChapterService.saveRecipes(recipes);
	}

	/**
	 * Validates a Recipe.
	 *
	 * @param recipe
	 */
	private void validateRecipe(Recipe recipe) {
		new RecipeValidator("recipe", recipe).validate();
	}

	@Override
	public void deleteRecipe(Recipe recipe) {
		recipeChapterService.deleteRecipe(recipe);
	}

	@Override
	public List<Recipe> findDeletedRecipes(User user, CriteriaParams<Recipe> criteriaParams) {
		return recipeService.findByUser(user, PersistableState.DELETED, criteriaParams);
	}

	@Override
	public long findDeletedRecipeCount(User user) {
		return recipeService.findCountByUser(user, PersistableState.DELETED);
	}

	@Override
	public RecipeChapter getRecipeChapter(User user, String uid) {
		return recipeChapterService.findFirstByUser(user, uid);
	}


	@Override
	public void saveRecipeChapter(RecipeChapter chapter) {
		validateRecipeChapter(chapter);
		recipeChapterService.save(chapter);
	}

	@Override
	public void saveRecipeChapters(List<RecipeChapter> chapters) {
		for(RecipeChapter chapter : chapters)
			validateRecipeChapter(chapter);

		recipeChapterService.save(chapters);
	}

	/**
	 * Validates a RecipeChapter.
	 *
	 * @param recipeChapter
	 */
	private void validateRecipeChapter(RecipeChapter recipeChapter) {
		new RecipeChapterValidator("recipeChapter", recipeChapter, recipeChapterService).validate();
	}

	@Override
	public List<RecipeTag> findRecipeTags(User user, String name, CriteriaParams<RecipeTag> criteriaParams) {
		return recipeTagService.findByUser(user, name, PersistableState.ACTIVE, criteriaParams);
	}

	@Override
	public long findRecipeTagCount(User user, String name) {
		return recipeTagService.findCountByUser(user, name, PersistableState.ACTIVE);
	}

	@Override
	public TagCloud findRecipeTagCloud(User user, int tagCount, double minWeight, double maxWeight) {
		List<TagFrequency> recipeTags = recipeTagService.findTagFrequencies(user, PersistableState.ACTIVE,
				CriteriaParams.of(new Results(0, tagCount), Order.desc(TagFrequency.FREQUENCY)));

		return new TagCloud(recipeTags, minWeight, maxWeight);
	}

	@Override
	public void deleteRecipeChapter(RecipeChapter chapter) {
		recipeChapterService.delete(chapter);
	}

	@Override
	protected void onDeleteAll(User user) {

		// deleted recipes
		for(Recipe recipe : findDeletedRecipes(user, CriteriaParams.<Recipe>of(Results.ALL)))
			recipeService.delete(recipe);

		// recipes and chapters
		for(RecipeChapter chapter : recipeChapterService.findByUser(user, CriteriaParams.<RecipeChapter>of(Results.ALL)))
			deleteRecipeChapter(chapter);
	}

	@Override
	public TrackerDemo getTrackerDemo() {
		return new RecipeTrackerDemo(this);
	}

	@Override
	public RecipeTrackerReport getTrackerReport(User user, Preferences preferences, boolean publicOnly) {
		return new RecipeTrackerReport(getRecipeBook(user));
	}

	// getters and setters
	public RecipeService getRecipeService() {
		return recipeService;
	}

	public void setRecipeService(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	public RecipeChapterService getRecipeChapterService() {
		return recipeChapterService;
	}

	public void setRecipeChapterService(RecipeChapterService recipeChapterService) {
		this.recipeChapterService = recipeChapterService;
	}

	public RecipeTagService getRecipeTagService() {
		return recipeTagService;
	}

	public void setRecipeTagService(RecipeTagService recipeTagService) {
		this.recipeTagService = recipeTagService;
	}

}