package com.tracktacular.service.admin;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.imadp.core.cache.CacheManager;
import com.imadp.core.email.Email;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.dao.criteria.Results;
import com.imadp.service.FacadeComponent;
import com.imadp.service.account.credentials.Credentials;
import com.imadp.service.account.credentials.CredentialsService;
import com.imadp.service.blog.BlogCategory;
import com.imadp.service.blog.BlogCategoryService;
import com.imadp.service.blog.BlogEntry;
import com.imadp.service.commerce.subscription.Subscription;
import com.imadp.service.email.PersistableEmailService;
import com.imadp.service.metrics.MetricsService;
import com.imadp.service.metrics.MetricsSummaries;
import com.imadp.service.user.User;
import com.tracktacular.service.TracktacularEmail;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.account.PreferencesService;
import com.tracktacular.service.account.TracktacularAccountFacade;
import com.tracktacular.service.admin.report.StatusReport;
import com.tracktacular.service.admin.report.StatusReportService;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TrackerFacade;
import com.tracktacular.service.tracker.TrackerPreferences;
import com.tracktacular.service.tracker.TracktacularReport;
import com.tracktacular.service.tracker.TracktacularTrackersFacade;
import com.tracktacular.service.tracker.game.GameTrackerFacade;


/**
 * TracktacularAdminFacadeImpl
 *
 * The standard implementation of the TracktacularAdminFacade.
 *
 * @note This service is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class TracktacularAdminFacadeImpl extends FacadeComponent implements TracktacularAdminFacade {
	private BlogCategoryService blogCategoryService;
	private CredentialsService credentialsService;
	private PreferencesService preferencesService;
	private TracktacularAccountFacade accountFacade;
	private TracktacularTrackersFacade trackersFacade;
	private GameTrackerFacade gameTrackerFacade;
	private StatusReportService statusReportService;
	private MetricsService metricsService;
	private PersistableEmailService persistableEmailService;
	private CacheManager cacheManager;
	private String statusReportEmailAddress;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(blogCategoryService);
		Validate.notNull(credentialsService);
		Validate.notNull(preferencesService);
		Validate.notNull(cacheManager);
		Validate.notNull(statusReportService);
		Validate.notNull(accountFacade);
		Validate.notNull(trackersFacade);
		Validate.notNull(gameTrackerFacade);
		Validate.notNull(persistableEmailService);
		Validate.notNull(metricsService);
		Validate.notNull(statusReportEmailAddress);
	}

	@Override
	public void clearAllCaches() {
		cacheManager.clearAll();
	}

	@Override
	public void saveBlogCategory(BlogCategory blogCategory) {
		blogCategoryService.save(blogCategory);
	}

	@Override
	public void deleteBlogCategory(BlogCategory blogCategory) {
		blogCategoryService.delete(blogCategory);
	}

	@Override
	public void saveBlogEntry(BlogEntry blogEntry) {
		blogCategoryService.saveBlogEntry(blogEntry);
	}

	@Override
	public void deleteBlogEntry(BlogEntry blogEntry) {
		blogCategoryService.deleteBlogEntry(blogEntry);
	}

	@Override
	public int sendBlogEntryNotification(BlogEntry blogEntry) {
		List<Preferences> preferenceList = preferencesService.findByBlogNotification(true, CriteriaParams.<Preferences>of(Results.ALL));
		int blogNotifications = 0;

		logger.info("Generating and sending blog notifications");

		for(Preferences preferences : preferenceList)
		{
			Credentials credentials = credentialsService.findFirstByUser(preferences.getUser());

			// dont send to unverified email addresses
			if(!credentials.isEmailVerified())
				continue;

			try
			{
				blogNotifications++;

				// build and send the email
				Map<String, Object> model = Maps.newHashMap();
				model.put("blogEntry", blogEntry);
				model.put("preferences", preferences);
				Email email = persistableEmailService.buildEmail(TracktacularEmail.BLOG_ENTRY_NOTIFICATION.getTemplate(), Locale.ENGLISH, model);
				email.setTo(credentials.getEmail());

				persistableEmailService.send(email);
			}
			catch(Exception exception)
			{
				logger.error("Could not send Tracktacular Report for username ["+credentials.getUsername()+"] " +
						"and email ["+credentials.getEmail()+"]", exception);
			}
		}

		logger.info("Generated and sent [{}] Blog Notifications", blogNotifications);
		return blogNotifications;
	}

	@Override
	public MetricsSummaries getRecentMetricsSummary() {
		return new MetricsSummaries(metricsService.findRecentlyCreated(Integer.MAX_VALUE));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insertTrackerDemoData() {
		logger.info("Inserting tracker demo data");

		for(Tracker tracker : Tracker.values())
			insertTrackerDemoData(tracker);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insertTrackerDemoData(Tracker tracker) {
		logger.info("Inserting demo data for tracker [{}]", tracker);

		User user = accountFacade.getDemoUser();
		Preferences preferences = accountFacade.getPreferences(user);

		// add facade demo data
		TrackerFacade trackerFacade = trackersFacade.getTrackerFacade(tracker);
		TrackerPreferences trackerPreferences = preferences.getTrackers().getTrackerPreferences(tracker);
		trackerFacade.deleteAll(user);
		trackerFacade.getTrackerDemo().insertDemoData(user, trackerPreferences);
		accountFacade.savePreferences(preferences);
	}

	@Override
	public StatusReport generateStatusReport() {
		return statusReportService.generateStatusReport();
	}

	@Override
	public void generateAndSendStatusReport() {
		StatusReport statusReport = generateStatusReport();

		// build and send the email
		Map<String, StatusReport> model = Maps.newHashMap();
		model.put("statusReport", statusReport);
		Email email = persistableEmailService.buildEmail(TracktacularEmail.STATUS_REPORT.getTemplate(), Locale.ENGLISH, model);
		email.setTo(statusReportEmailAddress);

		persistableEmailService.send(email);
	}

	@Override
	public void generateAndSendTracktacularReports() {
		List<Preferences> preferenceList = preferencesService.findByEmailAlerts(true, CriteriaParams.<Preferences>of(Results.ALL));
		int tracktacularReports = 0;

		logger.info("Generating and sending Tracktacular Reports");

		for(Preferences preferences : preferenceList)
		{
			Credentials credentials = credentialsService.findFirstByUser(preferences.getUser());

			// dont send to unverified email addresses
			if(!credentials.isEmailVerified())
				continue;

			Subscription subscription = accountFacade.getSubscription(preferences.getUser());

			// dont send to expired accounts
			if(subscription.isExpired())
				continue;

			try
			{
				TracktacularReport tracktacularReport = trackersFacade.generateTracktacularReport(credentials.getUsername(), preferences, false);

				// don't send the report if it has no alerts
				if(!tracktacularReport.isAlerts())
					continue;

				tracktacularReports++;

				// build and send the email
				Map<String, Object> model = Maps.newHashMap();
				model.put("tracktacularReport", tracktacularReport);
				model.put("preferences", preferences);
				Email email = persistableEmailService.buildEmail(TracktacularEmail.TRACKTACULAR_REPORT.getTemplate(), Locale.ENGLISH, model);
				email.setTo(credentials.getEmail());

				persistableEmailService.send(email);
			}
			catch(Exception exception)
			{
				logger.error("Could not send Tracktacular Report for username ["+credentials.getUsername()+"] " +
						"and email ["+credentials.getEmail()+"]", exception);
			}
		}

		logger.info("Generated and sent [{}] Tracktacular Reports", tracktacularReports);
	}

	@Override
	public void synchronizeSteamGames() {
		logger.info("Synchronizing steam games for all users");

		for(Preferences preferences : preferencesService.findBy(CriteriaParams.<Preferences>of(Results.ALL)))
			gameTrackerFacade.synchronizeSteamGames(preferences);
	}

	// getters and setters
	public TracktacularAccountFacade getAccountFacade() {
		return accountFacade;
	}

	public void setAccountFacade(TracktacularAccountFacade accountFacade) {
		this.accountFacade = accountFacade;
	}

	public TracktacularTrackersFacade getTrackersFacade() {
		return trackersFacade;
	}

	public void setTrackersFacade(TracktacularTrackersFacade trackersFacade) {
		this.trackersFacade = trackersFacade;
	}

	public StatusReportService getStatusReportService() {
		return statusReportService;
	}

	public void setStatusReportService(StatusReportService statusReportService) {
		this.statusReportService = statusReportService;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public CredentialsService getCredentialsService() {
		return credentialsService;
	}

	public void setCredentialsService(CredentialsService credentialsService) {
		this.credentialsService = credentialsService;
	}

	public PreferencesService getPreferencesService() {
		return preferencesService;
	}

	public void setPreferencesService(PreferencesService preferencesService) {
		this.preferencesService = preferencesService;
	}

	public PersistableEmailService getPersistableEmailService() {
		return persistableEmailService;
	}

	public void setPersistableEmailService(PersistableEmailService persistableEmailService) {
		this.persistableEmailService = persistableEmailService;
	}

	public String getStatusReportEmailAddress() {
		return statusReportEmailAddress;
	}

	public void setStatusReportEmailAddress(String statusReportEmailAddress) {
		this.statusReportEmailAddress = statusReportEmailAddress;
	}

	public BlogCategoryService getBlogCategoryService() {
		return blogCategoryService;
	}

	public void setBlogCategoryService(BlogCategoryService blogCategoryService) {
		this.blogCategoryService = blogCategoryService;
	}

	public GameTrackerFacade getGameTrackerFacade() {
		return gameTrackerFacade;
	}

	public void setGameTrackerFacade(GameTrackerFacade gameTrackerFacade) {
		this.gameTrackerFacade = gameTrackerFacade;
	}

	public MetricsService getMetricsService() {
		return metricsService;
	}

	public void setMetricsService(MetricsService metricsService) {
		this.metricsService = metricsService;
	}

}