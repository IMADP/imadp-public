package com.tracktacular.service.account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.imadp.core.AbstractSerializable;
import com.imadp.core.reflection.Methods;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerPreferences;
import com.tracktacular.service.tracker.birthday.BirthdayTrackerPreferences;
import com.tracktacular.service.tracker.blood.BloodTrackerPreferences;
import com.tracktacular.service.tracker.body.BodyTrackerPreferences;
import com.tracktacular.service.tracker.book.BookTrackerPreferences;
import com.tracktacular.service.tracker.bucket.BucketTrackerPreferences;
import com.tracktacular.service.tracker.budget.BudgetTrackerPreferences;
import com.tracktacular.service.tracker.calendar.CalendarTrackerPreferences;
import com.tracktacular.service.tracker.cholesterol.CholesterolTrackerPreferences;
import com.tracktacular.service.tracker.dream.DreamTrackerPreferences;
import com.tracktacular.service.tracker.exercise.ExerciseTrackerPreferences;
import com.tracktacular.service.tracker.game.GameTrackerPreferences;
import com.tracktacular.service.tracker.gift.GiftTrackerPreferences;
import com.tracktacular.service.tracker.goal.GoalTrackerPreferences;
import com.tracktacular.service.tracker.journal.JournalTrackerPreferences;
import com.tracktacular.service.tracker.movie.MovieTrackerPreferences;
import com.tracktacular.service.tracker.music.MusicTrackerPreferences;
import com.tracktacular.service.tracker.recipe.RecipeTrackerPreferences;
import com.tracktacular.service.tracker.restaurant.RestaurantTrackerPreferences;
import com.tracktacular.service.tracker.task.TaskTrackerPreferences;
import com.tracktacular.service.tracker.tv.TvTrackerPreferences;
import com.tracktacular.service.tracker.wish.WishTrackerPreferences;


/**
 * PreferencesTrackers
 *
 * Tracker preferences are stored as json objects to maximize their flexibility.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class PreferencesTrackers extends AbstractSerializable {

	// tracker preferences
	private BirthdayTrackerPreferences birthdayTrackerPreferences;
	private BloodTrackerPreferences bloodTrackerPreferences;
	private BodyTrackerPreferences bodyTrackerPreferences;
	private BookTrackerPreferences bookTrackerPreferences;
	private BucketTrackerPreferences bucketTrackerPreferences;
	private BudgetTrackerPreferences budgetTrackerPreferences;
    private CalendarTrackerPreferences calendarTrackerPreferences;
    private CholesterolTrackerPreferences cholesterolTrackerPreferences;
    private DreamTrackerPreferences dreamTrackerPreferences;
    private ExerciseTrackerPreferences exerciseTrackerPreferences;
    private GameTrackerPreferences gameTrackerPreferences;
    private GoalTrackerPreferences goalTrackerPreferences;
    private GiftTrackerPreferences giftTrackerPreferences;
    private JournalTrackerPreferences journalTrackerPreferences;
    private MovieTrackerPreferences movieTrackerPreferences;
    private MusicTrackerPreferences musicTrackerPreferences;
    private RecipeTrackerPreferences recipeTrackerPreferences;
    private RestaurantTrackerPreferences restaurantTrackerPreferences;
    private TaskTrackerPreferences taskTrackerPreferences;
    private TvTrackerPreferences tvTrackerPreferences;
    private WishTrackerPreferences wishTrackerPreferences;

	/**
	 * Returns a list of trackers that are enabled in the report according to the preferences.
	 * If the publicOnly boolean is true, there is an additional condition that they must be public.
	 *
	 * @param publicOnly
	 * @return List<Tracker>
	 */
    public List<Tracker> getTrackersWithReports(boolean publicOnly) {
		List<Tracker> trackers = new ArrayList<>();

		for(Tracker tracker : Tracker.values())
		{
			TrackerPreferences trackerPreferences = getTrackerPreferences(tracker);

			if(!trackerPreferences.isTrackerEnabled())
				continue;

			if(!publicOnly || trackerPreferences.isTrackerPublic())
				trackers.add(tracker);
		}

		Collections.sort(trackers);
		return trackers;
    }

	/**
	 * Returns a preferences object for a given tracker.
	 *
	 * @param <V>
	 * @param tracker
	 * @return V
	 */
	public <V extends TrackerPreferences> V getTrackerPreferences(Tracker tracker) {
		return Methods.getProperty(this, tracker.getName() + "TrackerPreferences");
	}

	// birthday
	public BirthdayTrackerPreferences getBirthdayTrackerPreferences() {
		if(birthdayTrackerPreferences == null)
			birthdayTrackerPreferences = new BirthdayTrackerPreferences();

		return birthdayTrackerPreferences;
	}

	public void setBirthdayTrackerPreferences(BirthdayTrackerPreferences birthdayTrackerPreferences) {
		this.birthdayTrackerPreferences = birthdayTrackerPreferences;
	}

	public String getBirthdayJson() {
		return toJson(getBirthdayTrackerPreferences());
	}

	public void setBirthdayJson(String birthdayJson) {
		this.birthdayTrackerPreferences = fromJson(birthdayJson, BirthdayTrackerPreferences.class);
	}

	// blood
	public BloodTrackerPreferences getBloodTrackerPreferences() {
		if(bloodTrackerPreferences == null)
			bloodTrackerPreferences = new BloodTrackerPreferences();

		return bloodTrackerPreferences;
	}

	public void setBloodTrackerPreferences(BloodTrackerPreferences bloodTrackerPreferences) {
		this.bloodTrackerPreferences = bloodTrackerPreferences;
	}

	public String getBloodJson() {
		return toJson(getBloodTrackerPreferences());
	}

	public void setBloodJson(String bloodJson) {
		this.bloodTrackerPreferences = fromJson(bloodJson, BloodTrackerPreferences.class);
	}

	// body
	public BodyTrackerPreferences getBodyTrackerPreferences() {
		if(bodyTrackerPreferences == null)
			bodyTrackerPreferences = new BodyTrackerPreferences();

		return bodyTrackerPreferences;
	}

	public void setBodyTrackerPreferences(BodyTrackerPreferences bodyTrackerPreferences) {
		this.bodyTrackerPreferences = bodyTrackerPreferences;
	}

	public String getBodyJson() {
		return toJson(getBodyTrackerPreferences());
	}

	public void setBodyJson(String bodyJson) {
		this.bodyTrackerPreferences = fromJson(bodyJson, BodyTrackerPreferences.class);
	}

	// book
	public BookTrackerPreferences getBookTrackerPreferences() {
		if(bookTrackerPreferences == null)
			bookTrackerPreferences = new BookTrackerPreferences();

		return bookTrackerPreferences;
	}

	public void setBookTrackerPreferences(BookTrackerPreferences bookTrackerPreferences) {
		this.bookTrackerPreferences = bookTrackerPreferences;
	}

	public String getBookJson() {
		return toJson(getBookTrackerPreferences());
	}

	public void setBookJson(String bookJson) {
		this.bookTrackerPreferences = fromJson(bookJson, BookTrackerPreferences.class);
	}

	// bucket
	public BucketTrackerPreferences getBucketTrackerPreferences() {
		if(bucketTrackerPreferences == null)
			bucketTrackerPreferences = new BucketTrackerPreferences();

		return bucketTrackerPreferences;
	}

	public void setBucketTrackerPreferences(BucketTrackerPreferences bucketTrackerPreferences) {
		this.bucketTrackerPreferences = bucketTrackerPreferences;
	}

	public String getBucketJson() {
		return toJson(getBucketTrackerPreferences());
	}

	public void setBucketJson(String bucketJson) {
		this.bucketTrackerPreferences = fromJson(bucketJson, BucketTrackerPreferences.class);
	}

	// budget
	public BudgetTrackerPreferences getBudgetTrackerPreferences() {
		if(budgetTrackerPreferences == null)
			budgetTrackerPreferences = new BudgetTrackerPreferences();

		return budgetTrackerPreferences;
	}

	public void setBudgetTrackerPreferences(BudgetTrackerPreferences budgetTrackerPreferences) {
		this.budgetTrackerPreferences = budgetTrackerPreferences;
	}

	public String getBudgetJson() {
		return toJson(getBudgetTrackerPreferences());
	}

	public void setBudgetJson(String budgetJson) {
		this.budgetTrackerPreferences = fromJson(budgetJson, BudgetTrackerPreferences.class);
	}

	// calendar
	public CalendarTrackerPreferences getCalendarTrackerPreferences() {
		if(calendarTrackerPreferences == null)
			calendarTrackerPreferences = new CalendarTrackerPreferences();

		return calendarTrackerPreferences;
	}

	public void setCalendarTrackerPreferences(CalendarTrackerPreferences calendarTrackerPreferences) {
		this.calendarTrackerPreferences = calendarTrackerPreferences;
	}

	public String getCalendarJson() {
		return toJson(getCalendarTrackerPreferences());
	}

	public void setCalendarJson(String calendarJson) {
		this.calendarTrackerPreferences = fromJson(calendarJson, CalendarTrackerPreferences.class);
	}

	// cholesterol
	public CholesterolTrackerPreferences getCholesterolTrackerPreferences() {
		if(cholesterolTrackerPreferences == null)
			cholesterolTrackerPreferences = new CholesterolTrackerPreferences();

		return cholesterolTrackerPreferences;
	}

	public void setCholesterolTrackerPreferences(CholesterolTrackerPreferences cholesterolTrackerPreferences) {
		this.cholesterolTrackerPreferences = cholesterolTrackerPreferences;
	}

	public String getCholesterolJson() {
		return toJson(getCholesterolTrackerPreferences());
	}

	public void setCholesterolJson(String cholesterolJson) {
		this.cholesterolTrackerPreferences = fromJson(cholesterolJson, CholesterolTrackerPreferences.class);
	}

	// dream
	public DreamTrackerPreferences getDreamTrackerPreferences() {
		if(dreamTrackerPreferences == null)
			dreamTrackerPreferences = new DreamTrackerPreferences();

		return dreamTrackerPreferences;
	}

	public void setDreamTrackerPreferences(DreamTrackerPreferences dreamTrackerPreferences) {
		this.dreamTrackerPreferences = dreamTrackerPreferences;
	}

	public String getDreamJson() {
		return toJson(getDreamTrackerPreferences());
	}

	public void setDreamJson(String dreamJson) {
		this.dreamTrackerPreferences = fromJson(dreamJson, DreamTrackerPreferences.class);
	}

	// exercise
	public ExerciseTrackerPreferences getExerciseTrackerPreferences() {
		if(exerciseTrackerPreferences == null)
			exerciseTrackerPreferences = new ExerciseTrackerPreferences();

		return exerciseTrackerPreferences;
	}

	public void setExerciseTrackerPreferences(ExerciseTrackerPreferences exerciseTrackerPreferences) {
		this.exerciseTrackerPreferences = exerciseTrackerPreferences;
	}

	public String getExerciseJson() {
		return toJson(getExerciseTrackerPreferences());
	}

	public void setExerciseJson(String exerciseJson) {
		this.exerciseTrackerPreferences = fromJson(exerciseJson, ExerciseTrackerPreferences.class);
	}

	// game
	public GameTrackerPreferences getGameTrackerPreferences() {
		if(gameTrackerPreferences == null)
			gameTrackerPreferences = new GameTrackerPreferences();

		return gameTrackerPreferences;
	}

	public void setGameTrackerPreferences(GameTrackerPreferences gameTrackerPreferences) {
		this.gameTrackerPreferences = gameTrackerPreferences;
	}

	public String getGameJson() {
		return toJson(getGameTrackerPreferences());
	}

	public void setGameJson(String gameJson) {
		this.gameTrackerPreferences = fromJson(gameJson, GameTrackerPreferences.class);
	}

	// gift
	public GiftTrackerPreferences getGiftTrackerPreferences() {
		if(giftTrackerPreferences == null)
			giftTrackerPreferences = new GiftTrackerPreferences();

		return giftTrackerPreferences;
	}

	public void setGiftTrackerPreferences(GiftTrackerPreferences giftTrackerPreferences) {
		this.giftTrackerPreferences = giftTrackerPreferences;
	}

	public String getGiftJson() {
		return toJson(getGiftTrackerPreferences());
	}

	public void setGiftJson(String giftJson) {
		this.giftTrackerPreferences = fromJson(giftJson, GiftTrackerPreferences.class);
	}

	// goal
	public GoalTrackerPreferences getGoalTrackerPreferences() {
		if(goalTrackerPreferences == null)
			goalTrackerPreferences = new GoalTrackerPreferences();

		return goalTrackerPreferences;
	}

	public void setGoalTrackerPreferences(GoalTrackerPreferences goalTrackerPreferences) {
		this.goalTrackerPreferences = goalTrackerPreferences;
	}

	public String getGoalJson() {
		return toJson(getGoalTrackerPreferences());
	}

	public void setGoalJson(String goalJson) {
		this.goalTrackerPreferences = fromJson(goalJson, GoalTrackerPreferences.class);
	}

	// journal
	public JournalTrackerPreferences getJournalTrackerPreferences() {
		if(journalTrackerPreferences == null)
			journalTrackerPreferences = new JournalTrackerPreferences();

		return journalTrackerPreferences;
	}

	public void setJournalTrackerPreferences(JournalTrackerPreferences journalTrackerPreferences) {
		this.journalTrackerPreferences = journalTrackerPreferences;
	}

	public String getJournalJson() {
		return toJson(getJournalTrackerPreferences());
	}

	public void setJournalJson(String journalJson) {
		this.journalTrackerPreferences = fromJson(journalJson, JournalTrackerPreferences.class);
	}

	// movie
	public MovieTrackerPreferences getMovieTrackerPreferences() {
		if(movieTrackerPreferences == null)
			movieTrackerPreferences = new MovieTrackerPreferences();

		return movieTrackerPreferences;
	}

	public void setMovieTrackerPreferences(MovieTrackerPreferences movieTrackerPreferences) {
		this.movieTrackerPreferences = movieTrackerPreferences;
	}

	public String getMovieJson() {
		return toJson(getMovieTrackerPreferences());
	}

	public void setMovieJson(String movieJson) {
		this.movieTrackerPreferences = fromJson(movieJson, MovieTrackerPreferences.class);
	}

	// music
	public MusicTrackerPreferences getMusicTrackerPreferences() {
		if(musicTrackerPreferences == null)
			musicTrackerPreferences = new MusicTrackerPreferences();

		return musicTrackerPreferences;
	}

	public void setMusicTrackerPreferences(MusicTrackerPreferences musicTrackerPreferences) {
		this.musicTrackerPreferences = musicTrackerPreferences;
	}

	public String getMusicJson() {
		return toJson(getMusicTrackerPreferences());
	}

	public void setMusicJson(String musicJson) {
		this.musicTrackerPreferences = fromJson(musicJson, MusicTrackerPreferences.class);
	}

	// recipe
	public RecipeTrackerPreferences getRecipeTrackerPreferences() {
		if(recipeTrackerPreferences == null)
			recipeTrackerPreferences = new RecipeTrackerPreferences();

		return recipeTrackerPreferences;
	}

	public void setRecipeTrackerPreferences(RecipeTrackerPreferences recipeTrackerPreferences) {
		this.recipeTrackerPreferences = recipeTrackerPreferences;
	}

	public String getRecipeJson() {
		return toJson(getRecipeTrackerPreferences());
	}

	public void setRecipeJson(String recipeJson) {
		this.recipeTrackerPreferences = fromJson(recipeJson, RecipeTrackerPreferences.class);
	}

	// restaurant
	public RestaurantTrackerPreferences getRestaurantTrackerPreferences() {
		if(restaurantTrackerPreferences == null)
			restaurantTrackerPreferences = new RestaurantTrackerPreferences();

		return restaurantTrackerPreferences;
	}

	public void setRestaurantTrackerPreferences(RestaurantTrackerPreferences restaurantTrackerPreferences) {
		this.restaurantTrackerPreferences = restaurantTrackerPreferences;
	}

	public String getRestaurantJson() {
		return toJson(getRestaurantTrackerPreferences());
	}

	public void setRestaurantJson(String restaurantJson) {
		this.restaurantTrackerPreferences = fromJson(restaurantJson, RestaurantTrackerPreferences.class);
	}

	// task
	public TaskTrackerPreferences getTaskTrackerPreferences() {
		if(taskTrackerPreferences == null)
			taskTrackerPreferences = new TaskTrackerPreferences();

		return taskTrackerPreferences;
	}

	public void setTaskTrackerPreferences(TaskTrackerPreferences taskTrackerPreferences) {
		this.taskTrackerPreferences = taskTrackerPreferences;
	}

	public String getTaskJson() {
		return toJson(getTaskTrackerPreferences());
	}

	public void setTaskJson(String taskJson) {
		this.taskTrackerPreferences = fromJson(taskJson, TaskTrackerPreferences.class);
	}

	// tv
	public TvTrackerPreferences getTvTrackerPreferences() {
		if(tvTrackerPreferences == null)
			tvTrackerPreferences = new TvTrackerPreferences();

		return tvTrackerPreferences;
	}

	public void setTvTrackerPreferences(TvTrackerPreferences tvTrackerPreferences) {
		this.tvTrackerPreferences = tvTrackerPreferences;
	}

	public String getTvJson() {
		return toJson(getTvTrackerPreferences());
	}

	public void setTvJson(String tvJson) {
		this.tvTrackerPreferences = fromJson(tvJson, TvTrackerPreferences.class);
	}

	// wish
	public WishTrackerPreferences getWishTrackerPreferences() {
		if(wishTrackerPreferences == null)
			wishTrackerPreferences = new WishTrackerPreferences();

		return wishTrackerPreferences;
	}

	public void setWishTrackerPreferences(WishTrackerPreferences wishTrackerPreferences) {
		this.wishTrackerPreferences = wishTrackerPreferences;
	}

	public String getWishJson() {
		return toJson(getWishTrackerPreferences());
	}

	public void setWishJson(String wishJson) {
		this.wishTrackerPreferences = fromJson(wishJson, WishTrackerPreferences.class);
	}

	/**
	 * Returns a string json representation of a preferences object.
	 *
	 * @param preferences
	 * @return String
	 */
	private String toJson(TrackerPreferences preferences) {
		return GSON.toJson(preferences);
	}

	/**
	 * Returns a preference object from a string json representation.
	 *
	 * @param json
	 * @param preferenceClass
	 */
	private <V extends TrackerPreferences> V fromJson(String json, Class<V> preferenceClass) {
		return GSON.fromJson(json, preferenceClass);
	}

}