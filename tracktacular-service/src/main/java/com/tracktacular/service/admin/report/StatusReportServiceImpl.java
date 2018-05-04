package com.tracktacular.service.admin.report;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.imadp.core.CoreComponent;
import com.imadp.service.account.credentials.Credentials;
import com.imadp.service.account.credentials.CredentialsService;
import com.imadp.service.commerce.subscription.Subscription;
import com.imadp.service.commerce.subscription.SubscriptionService;
import com.imadp.service.feedback.FeedbackService;
import com.imadp.service.log.LogEntryService;
import com.imadp.service.user.User;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.service.tracker.TracktacularTrackersFacade;

/**
 * StatusReportServiceImpl
 *
 * The standard implementation of the StatusReportService.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class StatusReportServiceImpl extends CoreComponent implements StatusReportService {
	private CredentialsService credentialsService;
	private FeedbackService feedbackService;
	private LogEntryService logEntryService;
	private SubscriptionService subscriptionService;
	private TracktacularTrackersFacade trackersFacade;
	private Integer maxErrorLogEntries;
	private Integer maxFeedback;
	private Integer maxCredentials;
	private Integer maxLogin;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(credentialsService);
		Validate.notNull(feedbackService);
		Validate.notNull(logEntryService);
		Validate.notNull(subscriptionService);
		Validate.notNull(trackersFacade);
		Validate.notNull(maxErrorLogEntries);
		Validate.notNull(maxFeedback);
		Validate.notNull(maxCredentials);
		Validate.notNull(maxLogin);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public StatusReport generateStatusReport() {
		DateTime date = new DateTime();

		logger.info("Generating status report for date [{}]", date);

		StatusReport statusReport = new StatusReport();
		statusReport.setDate(date);
		statusReport.setTitle("Status Report");
		statusReport.setErrorLogEntryCount(logEntryService.findCount());
		statusReport.setCredentialsCount(credentialsService.findCount());
		statusReport.setFeedbackCount(feedbackService.findCount());
		statusReport.setRecentLoginCount(credentialsService.findRecentlyModifiedCount());
		statusReport.setRecentCredentialsCount(credentialsService.findRecentlyCreatedCount());
		statusReport.setRecentFeedbackCount(feedbackService.findRecentlyCreatedCount());
		statusReport.setRecentErrorLogEntryCount(logEntryService.findRecentlyCreatedCount());
		statusReport.setRecentLogin(credentialsService.findRecentlyModified(maxLogin));
		statusReport.setRecentCredentials(credentialsService.findRecentlyCreated(maxCredentials));
		statusReport.setRecentFeedback(feedbackService.findRecentlyCreated(maxFeedback));
		statusReport.setRecentErrorLogEntries(logEntryService.findRecentlyCreated(maxErrorLogEntries));
		statusReport.setSubscriptionMap(Maps.<Credentials, Subscription>newHashMap());

		for(Credentials credentials : statusReport.getRecentLogin())
			statusReport.getSubscriptionMap().put(credentials, subscriptionService.findFirstByUser(credentials.getUser()));

		Multimap<Credentials, Tracker> trackerUserMap = ArrayListMultimap.create();
		Map<Tracker, Integer> distinctUserCountMap = new LinkedHashMap<>();
		Map<Tracker, Integer> distinctRecentUserCountMap = new LinkedHashMap<>();

		for(Tracker tracker : Tracker.values())
		{
			Set<User> distinctUsers = trackersFacade.findDistinctUsers(tracker);
			Set<User> recentDistinctUsers = trackersFacade.findRecentDistinctUsers(tracker);

			// remove the demo user from both counts
			distinctUserCountMap.put(tracker, distinctUsers.size() - 1);
			distinctRecentUserCountMap.put(tracker, recentDistinctUsers.size() - 1);

			for(User user : recentDistinctUsers)
				for(Credentials credentials : statusReport.getRecentLogin())
					if(credentials.getUser().equals(user))
						trackerUserMap.put(credentials, tracker);
		}

		statusReport.setTrackerUserMap(trackerUserMap.asMap());
		statusReport.setDistinctUserCountMap(distinctUserCountMap);
		statusReport.setDistinctRecentUserCountMap(distinctRecentUserCountMap);
		return statusReport;
	}

	// getters and setters
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}

	public void setCredentialsService(CredentialsService credentialsService) {
		this.credentialsService = credentialsService;
	}

	public Integer getMaxCredentials() {
		return maxCredentials;
	}

	public void setMaxCredentials(Integer maxCredentials) {
		this.maxCredentials = maxCredentials;
	}

	public LogEntryService getLogEntryService() {
		return logEntryService;
	}

	public void setLogEntryService(LogEntryService logEntryService) {
		this.logEntryService = logEntryService;
	}

	public Integer getMaxErrorLogEntries() {
		return maxErrorLogEntries;
	}

	public void setMaxErrorLogEntries(Integer maxErrorLogEntries) {
		this.maxErrorLogEntries = maxErrorLogEntries;
	}

	public FeedbackService getFeedbackService() {
		return feedbackService;
	}

	public void setFeedbackService(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}

	public Integer getMaxFeedback() {
		return maxFeedback;
	}

	public void setMaxFeedback(Integer maxFeedback) {
		this.maxFeedback = maxFeedback;
	}

	public TracktacularTrackersFacade getTrackersFacade() {
		return trackersFacade;
	}

	public void setTrackersFacade(TracktacularTrackersFacade trackersFacade) {
		this.trackersFacade = trackersFacade;
	}

	public Integer getMaxLogin() {
		return maxLogin;
	}

	public void setMaxLogin(Integer maxLogin) {
		this.maxLogin = maxLogin;
	}

	public SubscriptionService getSubscriptionService() {
		return subscriptionService;
	}

	public void setSubscriptionService(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

}