package com.tracktacular.service.admin.report;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.imadp.service.account.credentials.Credentials;
import com.imadp.service.commerce.subscription.Subscription;
import com.imadp.service.feedback.Feedback;
import com.imadp.service.log.LogEntry;
import com.imadp.service.report.Report;
import com.tracktacular.service.tracker.Tracker;


/**
 * StatusReport
 *
 * A report indicating the general status of the application.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class StatusReport extends Report {
	private long errorLogEntryCount;
	private long feedbackCount;
	private long credentialsCount;
	private long recentLoginCount;
	private long recentCredentialsCount;
	private long recentFeedbackCount;
	private long recentErrorLogEntryCount;
	private List<Credentials> recentLogin;
	private List<Credentials> recentCredentials;
	private List<LogEntry> recentErrorLogEntries;
	private List<Feedback> recentFeedback;
	private Map<Tracker, Integer> distinctUserCountMap;
	private Map<Tracker, Integer> distinctRecentUserCountMap;
	private Map<Credentials, Collection<Tracker>> trackerUserMap;
	private Map<Credentials, Subscription> subscriptionMap;

	// constructor
	public StatusReport() {
		setDate(new DateTime());
	}

	// getters and setters
	public long getErrorLogEntryCount() {
		return errorLogEntryCount;
	}

	public void setErrorLogEntryCount(long errorLogEntryCount) {
		this.errorLogEntryCount = errorLogEntryCount;
	}

	public long getFeedbackCount() {
		return feedbackCount;
	}

	public void setFeedbackCount(long feedbackCount) {
		this.feedbackCount = feedbackCount;
	}

	public long getCredentialsCount() {
		return credentialsCount;
	}

	public void setCredentialsCount(long credentialsCount) {
		this.credentialsCount = credentialsCount;
	}

	public long getRecentCredentialsCount() {
		return recentCredentialsCount;
	}

	public void setRecentCredentialsCount(long recentCredentialsCount) {
		this.recentCredentialsCount = recentCredentialsCount;
	}

	public long getRecentFeedbackCount() {
		return recentFeedbackCount;
	}

	public void setRecentFeedbackCount(long recentFeedbackCount) {
		this.recentFeedbackCount = recentFeedbackCount;
	}

	public long getRecentErrorLogEntryCount() {
		return recentErrorLogEntryCount;
	}

	public void setRecentErrorLogEntryCount(long recentErrorLogEntryCount) {
		this.recentErrorLogEntryCount = recentErrorLogEntryCount;
	}

	public List<Credentials> getRecentCredentials() {
		return recentCredentials;
	}

	public void setRecentCredentials(List<Credentials> recentCredentials) {
		this.recentCredentials = recentCredentials;
	}

	public List<LogEntry> getRecentErrorLogEntries() {
		return recentErrorLogEntries;
	}

	public void setRecentErrorLogEntries(List<LogEntry> recentErrorLogEntries) {
		this.recentErrorLogEntries = recentErrorLogEntries;
	}

	public List<Feedback> getRecentFeedback() {
		return recentFeedback;
	}

	public void setRecentFeedback(List<Feedback> recentFeedback) {
		this.recentFeedback = recentFeedback;
	}

	public Map<Tracker, Integer> getDistinctUserCountMap() {
		return distinctUserCountMap;
	}

	public void setDistinctUserCountMap(Map<Tracker, Integer> distinctUserCountMap) {
		this.distinctUserCountMap = distinctUserCountMap;
	}

	public Map<Tracker, Integer> getDistinctRecentUserCountMap() {
		return distinctRecentUserCountMap;
	}

	public void setDistinctRecentUserCountMap(Map<Tracker, Integer> distinctRecentUserCountMap) {
		this.distinctRecentUserCountMap = distinctRecentUserCountMap;
	}

	public long getRecentLoginCount() {
		return recentLoginCount;
	}

	public void setRecentLoginCount(long recentLoginCount) {
		this.recentLoginCount = recentLoginCount;
	}

	public List<Credentials> getRecentLogin() {
		return recentLogin;
	}

	public void setRecentLogin(List<Credentials> recentLogin) {
		this.recentLogin = recentLogin;
	}

	public Map<Credentials, Collection<Tracker>> getTrackerUserMap() {
		return trackerUserMap;
	}

	public void setTrackerUserMap(Map<Credentials, Collection<Tracker>> trackerUserMap) {
		this.trackerUserMap = trackerUserMap;
	}

	public Map<Credentials, Subscription> getSubscriptionMap() {
		return subscriptionMap;
	}

	public void setSubscriptionMap(Map<Credentials, Subscription> subscriptionMap) {
		this.subscriptionMap = subscriptionMap;
	}

}