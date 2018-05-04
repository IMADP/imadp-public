package com.tracktacular.service.test;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.imadp.core.cache.CacheManager;
import com.imadp.service.account.authority.AuthorityService;
import com.imadp.service.account.credentials.CredentialsService;
import com.imadp.service.commerce.subscription.SubscriptionFacade;
import com.imadp.service.commerce.subscription.plan.SubscriptionPlanService;
import com.imadp.service.location.country.CountryService;
import com.imadp.service.user.UserService;
import com.tracktacular.service.account.PreferencesServiceImpl;
import com.tracktacular.service.account.TracktacularAccountFacadeImpl;
import com.tracktacular.service.admin.TracktacularAdminFacadeImpl;
import com.tracktacular.service.blog.TracktacularBlogFacadeImpl;
import com.tracktacular.service.support.TracktacularSupportFacadeImpl;
import com.tracktacular.service.tracker.TracktacularTrackersFacadeImpl;
import com.tracktacular.service.tracker.birthday.BirthdayServiceImpl;
import com.tracktacular.service.tracker.birthday.BirthdayTrackerFacadeImpl;
import com.tracktacular.service.tracker.blood.BloodPressureServiceImpl;
import com.tracktacular.service.tracker.blood.BloodTrackerFacadeImpl;
import com.tracktacular.service.tracker.body.BodyServiceImpl;
import com.tracktacular.service.tracker.body.BodyTrackerFacadeImpl;
import com.tracktacular.service.tracker.book.BookServiceImpl;
import com.tracktacular.service.tracker.book.BookTrackerFacadeImpl;
import com.tracktacular.service.tracker.book.ChapterServiceImpl;
import com.tracktacular.service.tracker.bucket.BucketTrackerFacadeImpl;
import com.tracktacular.service.tracker.budget.BudgetServiceImpl;
import com.tracktacular.service.tracker.budget.BudgetTrackerFacadeImpl;
import com.tracktacular.service.tracker.budget.ItemCategoryServiceImpl;
import com.tracktacular.service.tracker.calendar.CalendarEntryDaoImpl;
import com.tracktacular.service.tracker.calendar.CalendarEntryServiceImpl;
import com.tracktacular.service.tracker.calendar.CalendarTrackerFacadeImpl;
import com.tracktacular.service.tracker.cholesterol.CholesterolServiceImpl;
import com.tracktacular.service.tracker.cholesterol.CholesterolTrackerFacadeImpl;
import com.tracktacular.service.tracker.dream.DreamDaoImpl;
import com.tracktacular.service.tracker.dream.DreamServiceImpl;
import com.tracktacular.service.tracker.dream.DreamTrackerFacadeImpl;
import com.tracktacular.service.tracker.exercise.ExerciseServiceImpl;
import com.tracktacular.service.tracker.exercise.ExerciseTrackerFacadeImpl;
import com.tracktacular.service.tracker.exercise.RoutineServiceImpl;
import com.tracktacular.service.tracker.exercise.WorkoutServiceImpl;
import com.tracktacular.service.tracker.game.GameServiceImpl;
import com.tracktacular.service.tracker.game.GameTrackerFacadeImpl;
import com.tracktacular.service.tracker.gift.GiftServiceImpl;
import com.tracktacular.service.tracker.gift.GiftTrackerFacadeImpl;
import com.tracktacular.service.tracker.goal.GoalServiceImpl;
import com.tracktacular.service.tracker.goal.GoalTrackerFacadeImpl;
import com.tracktacular.service.tracker.journal.JournalServiceImpl;
import com.tracktacular.service.tracker.journal.JournalTrackerFacadeImpl;
import com.tracktacular.service.tracker.movie.MovieServiceImpl;
import com.tracktacular.service.tracker.movie.MovieTrackerFacadeImpl;
import com.tracktacular.service.tracker.music.AlbumServiceImpl;
import com.tracktacular.service.tracker.music.MusicTrackerFacadeImpl;
import com.tracktacular.service.tracker.music.SongServiceImpl;
import com.tracktacular.service.tracker.recipe.RecipeChapterServiceImpl;
import com.tracktacular.service.tracker.recipe.RecipeServiceImpl;
import com.tracktacular.service.tracker.recipe.RecipeTrackerFacadeImpl;
import com.tracktacular.service.tracker.restaurant.MealServiceImpl;
import com.tracktacular.service.tracker.restaurant.RestaurantServiceImpl;
import com.tracktacular.service.tracker.restaurant.RestaurantTrackerFacadeImpl;
import com.tracktacular.service.tracker.task.TaskCategoryServiceImpl;
import com.tracktacular.service.tracker.task.TaskDaoImpl;
import com.tracktacular.service.tracker.task.TaskServiceImpl;
import com.tracktacular.service.tracker.task.TaskTrackerFacadeImpl;
import com.tracktacular.service.tracker.tv.EpisodeServiceImpl;
import com.tracktacular.service.tracker.tv.ShowServiceImpl;
import com.tracktacular.service.tracker.tv.TvTrackerFacadeImpl;
import com.tracktacular.service.tracker.wish.WishTrackerFacadeImpl;

/**
 * TracktacularServiceTestCase
 *
 * Provides an abstract test case for all tests.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"/com/tracktacular/service/context/application.context.xml",
		"/com/tracktacular/service/test/context/test-application.context.xml"})
public abstract class TracktacularServiceTestCase {

	// logger
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	// imadpCore.context
	@Resource protected CacheManager cacheManager;

	// imadpService.context
	@Resource protected DataSource dataSource;
	@Resource(name = "&sessionFactory") protected LocalSessionFactoryBean sessionFactory;
	@Resource protected UserService userService;
	@Resource protected AuthorityService authorityService;
	@Resource protected CountryService countryService;
	@Resource protected CredentialsService credentialsService;
	@Resource protected SubscriptionPlanService subscriptionPlanService;
	@Resource protected SubscriptionFacade subscriptionFacadeImpl;

	// tracktacularService.context
	@Resource protected PreferencesServiceImpl preferencesService;
	@Resource protected TracktacularAccountFacadeImpl accountFacade;
	@Resource protected TracktacularAdminFacadeImpl adminFacade;
	@Resource protected TracktacularSupportFacadeImpl supportFacade;
	@Resource protected TracktacularBlogFacadeImpl blogFacade;
	@Resource protected TracktacularTrackersFacadeImpl trackersFacade;

	// tracktacularServiceBirthday.context
	@Resource protected BirthdayTrackerFacadeImpl birthdayTrackerFacade;
	@Resource protected BirthdayServiceImpl birthday_birthdayService;

	// tracktacularServiceBlood.context
	@Resource protected BloodTrackerFacadeImpl bloodTrackerFacade;
	@Resource protected BloodPressureServiceImpl blood_bloodPressureService;

	// tracktacularServiceBody.context
	@Resource protected BodyTrackerFacadeImpl bodyTrackerFacade;
	@Resource protected BodyServiceImpl body_bodyService;

	// tracktacularServiceBook.context
	@Resource protected BookTrackerFacadeImpl bookTrackerFacade;
	@Resource protected BookServiceImpl book_bookService;
	@Resource protected ChapterServiceImpl book_chapterService;

	// tracktacularServiceBucket.context
	@Resource protected BucketTrackerFacadeImpl bucketTrackerFacade;
	@Resource protected com.tracktacular.service.tracker.bucket.ItemServiceImpl bucket_itemService;

	// tracktacularServiceBudget.context
	@Resource protected BudgetTrackerFacadeImpl budgetTrackerFacade;
	@Resource protected BudgetServiceImpl budget_budgetService;
	@Resource protected ItemCategoryServiceImpl budget_itemCategoryService;
	@Resource protected com.tracktacular.service.tracker.budget.ItemServiceImpl budget_itemService;

	// tracktacularServiceCalendar.context
	@Resource protected CalendarTrackerFacadeImpl calendarTrackerFacade;
	@Resource protected CalendarEntryDaoImpl calendar_calendarEntryDao;
	@Resource protected CalendarEntryServiceImpl calendar_calendarEntryService;

	// tracktacularServiceCholesterol.context
	@Resource protected CholesterolTrackerFacadeImpl cholesterolTrackerFacade;
	@Resource protected CholesterolServiceImpl cholesterol_cholesterolService;

	// tracktacularServiceDream.context
	@Resource protected DreamTrackerFacadeImpl dreamTrackerFacade;
	@Resource protected DreamDaoImpl dream_dreamDao;
	@Resource protected DreamServiceImpl dream_dreamService;

	// tracktacularServiceGame.context
	@Resource protected GameTrackerFacadeImpl gameTrackerFacade;
	@Resource protected GameServiceImpl game_gameService;

	// tracktacularServiceGift.context
	@Resource protected GiftTrackerFacadeImpl giftTrackerFacade;
	@Resource protected GiftServiceImpl gift_giftService;

	// tracktacularServiceGoal.context
	@Resource protected GoalTrackerFacadeImpl goalTrackerFacade;
	@Resource protected GoalServiceImpl goal_goalService;
	@Resource protected com.tracktacular.service.tracker.goal.ObjectiveServiceImpl goal_objectiveService;

	// tracktacularServiceExercise.context
	@Resource protected ExerciseTrackerFacadeImpl exerciseTrackerFacade;
	@Resource protected RoutineServiceImpl exercise_routineService;
	@Resource protected WorkoutServiceImpl exercise_workoutService;
	@Resource protected ExerciseServiceImpl exercise_exerciseService;
	@Resource protected com.tracktacular.service.tracker.exercise.EntryServiceImpl exercise_entryService;

	// tracktacularServiceJournal.context
	@Resource protected JournalTrackerFacadeImpl journalTrackerFacade;
	@Resource protected JournalServiceImpl journal_journalService;
	@Resource protected com.tracktacular.service.tracker.journal.EntryServiceImpl journal_entryService;

	// tracktacularServiceMovie.context
	@Resource protected MovieTrackerFacadeImpl movieTrackerFacade;
	@Resource protected MovieServiceImpl movie_movieService;

	// tracktacularServiceTv.context
	@Resource protected MusicTrackerFacadeImpl musicTrackerFacade;
	@Resource protected AlbumServiceImpl music_albumService;
	@Resource protected SongServiceImpl music_songService;

	// tracktacularServiceRecipe.context
	@Resource protected RecipeTrackerFacadeImpl recipeTrackerFacade;
	@Resource protected RecipeServiceImpl recipe_recipeService;
	@Resource protected RecipeChapterServiceImpl recipe_recipeChapterService;

	// tracktacularServiceRestaurant.context
	@Resource protected RestaurantTrackerFacadeImpl restaurantTrackerFacade;
	@Resource protected RestaurantServiceImpl restaurant_restaurantService;
	@Resource protected MealServiceImpl restaurant_mealService;

	// tracktacularServiceTask.context
	@Resource protected TaskTrackerFacadeImpl taskTrackerFacade;
	@Resource protected TaskDaoImpl task_taskDao;
	@Resource protected TaskServiceImpl task_taskService;
	@Resource protected TaskCategoryServiceImpl task_taskCategoryService;

	// tracktacularServiceTv.context
	@Resource protected TvTrackerFacadeImpl tvTrackerFacade;
	@Resource protected ShowServiceImpl tv_showService;
	@Resource protected EpisodeServiceImpl tv_episodeService;

	// tracktacularServiceWish.context
	@Resource protected WishTrackerFacadeImpl wishTrackerFacade;
	@Resource protected com.tracktacular.service.tracker.wish.ItemServiceImpl wish_itemService;

	@Before
	public void before() throws Exception {
		cacheManager.clearAll();
		sessionFactory.dropDatabaseSchema();
		sessionFactory.createDatabaseSchema();
	}

	@After
	public void after() throws Exception {

	}

}
