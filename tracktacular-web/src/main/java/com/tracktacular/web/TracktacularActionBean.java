package com.tracktacular.web;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationErrors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.imadp.service.commerce.subscription.Subscription;
import com.imadp.service.user.User;
import com.imadp.web.stripes.account.AbstractAuthenticatedActionBean;
import com.imadp.web.stripes.link.Link;
import com.imadp.web.stripes.resolution.EmptyResolution;
import com.rits.cloning.Cloner;
import com.tracktacular.service.account.Preferences;
import com.tracktacular.service.account.TracktacularAccountFacade;
import com.tracktacular.service.tracker.Tracker;
import com.tracktacular.web.page.FaqsActionBean;
import com.tracktacular.web.page.FeedbackActionBean;
import com.tracktacular.web.page.GettingStartedActionBean;
import com.tracktacular.web.page.IndexActionBean;
import com.tracktacular.web.page.NewsActionBean;
import com.tracktacular.web.page.PricingActionBean;
import com.tracktacular.web.page.TracktacularReportActionBean;
import com.tracktacular.web.page.tracker.TrackerPage;


/**
 * TracktacularActionBean
 *
 * Parent action bean for all tracktacular pages.
 *
 * Tracktacular essentially operates in two modes, viewing public data and tracking private data.
 * Its important for subclasses to distinguish which methods to use when interacting with user data.
 *
 * User Properties
 *  getUser()            - returns the currently logged in user
 *  getUserUsername()    - returns the username of the currently logged in user
 *  getUserPreferences() - returns the preferences of the currently logged in user
 *
 * These properties are meant to reflect the user who is currently interacting with the site,
 * regardless of whether they are viewing their own data or someone else's public data.
 * Pages should use these properties for saving preferences, changing account settings, providing feedback etc.
 *
 * Tracker User Properties
 *  getTrackerUser()            - returns the user owning the current public tracker data
 *  getTrackerUserUsername()    - returns the username of the user owning the current public tracker data
 *  getTrackerUserPreferences() - returns the preferences of the user owning the current public tracker data
 *
 * These properties are meant to reflect the owner of whatever data is being viewed, which can refer to either
 * a logged in user tracking his data, or any another user's public data. Individual trackers should only
 * interact with these properties for tracker data.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
@HttpCache(allow=false)
public class TracktacularActionBean extends AbstractAuthenticatedActionBean<TracktacularActionBeanContext> {

	// views
	public static final String AJAX_VALIDATION_RESULT_VIEW = "/WEB-INF/jsp/page/ajaxValidationResult.jsp";

	// params
	public static final String MOBILE_PARAM = "mobile";
	public static final String TRACKER_USER_USERNAME_PARAM = "trackerUserUsername";

	// defaults
	public static final String DEFAULT_USERNAME = "demo";

	@SpringBean
	private TracktacularAccountFacade accountFacade;

	@SpringBean
	private TracktacularVersion tracktacularVersion;

	// properties
	private Preferences preferences;
	private Map<Tracker, Link> pageLinks;
	private Map<Tracker, TrackerPage[]> trackerPageMap;

	// tracker user properties
	private Preferences trackerUserPreferences;

	/**
	 * Returns true if this is an error response.
	 *
	 * @return boolean
	 */
	public boolean isError() {
		return false;
	}

	/**
	 * Returns the tracker for the current page.
	 * This is used for various display operations such as navigation and the content header.
	 *
	 * @return Tracker
	 */
	public Tracker getTracker() {
		return null;
	}

	/**
	 * Returns the page version number.
	 *
	 * @return String
	 */
	public final String getPageVersion() {
		return tracktacularVersion.getPageVersion();
	}

	/**
	 * Returns the page mobile version number.
	 *
	 * @return String
	 */
	public final String getPageMobileVersion() {
		return tracktacularVersion.getPageMobileVersion();
	}

	/**
	 * Returns the tracker version for the page tracker, or null if no tracker was found.
	 *
	 * @return String
	 */
	public final String getPageTrackerVersion() {
		return getTracktacularVersion().getPageTrackerVersion(getTracker());
	}

	/**
	 * Returns true if the tracker user notification is displayed on the page, false by default.
	 *
	 * @return boolean
	 */
	public boolean isDisplayTrackerUserNotification() {
		return false;
	}

	/**
	 * Returns true if the request is in public mode, false otherwise.
	 * Public mode is true when a user is logged out or viewing data that they do not own.
	 *
	 * This method will only be false for logged in users whose credentials match the trackerUserUsername in session.
	 *
	 * @return boolean
	 */
	public final boolean isPublicMode() {
		if(!getContext().isLoggedIn())
			return true;

		return !getUserUsername().equalsIgnoreCase(getTrackerUserUsernameFromSession());
	}

	/**
	 * Returns a link to the toggle the user between viewing public data and private data.
	 * If the link is called while in publicMode, the user is redirected to his private data.
	 * Otherwise the user is redirected to the default user's public data.
	 *
	 * @return Link
	 */
	public final Link getUserToggleLink() {
		String trackerUserUsername = isPublicMode() ? getUserUsername() : DEFAULT_USERNAME;
		return new Link(getUserToggleClass()).addParameter(TRACKER_USER_USERNAME_PARAM, trackerUserUsername);
	}

	/**
	 * Returns the class to redirect to when the toggle link is clicked.
	 *
	 * @return
	 */
	protected Class<? extends TracktacularActionBean> getUserToggleClass() {
		return TracktacularReportActionBean.class;
	}

	/**
	 * Returns a list of links to be displayed in the righthand side of the content navigation header.
	 *
	 * @return List<Link>
	 */
	public List<Link> getContentHeaderLinks() {
		return Lists.newArrayList(
				getContentHeaderLink(IndexActionBean.class),
				getContentHeaderLink(FaqsActionBean.class),
				getContentHeaderLink(PricingActionBean.class),
				getContentHeaderLink(GettingStartedActionBean.class),
				getContentHeaderLink(NewsActionBean.class),
				getContentHeaderLink(FeedbackActionBean.class));
	}

	/**
	 * Returns a content header link.
	 * Link labels should be defined as simple name keys in stripes properties.
	 *
	 * @param actionBean
	 * @return Link.
	 */
	private Link getContentHeaderLink(Class<? extends TracktacularActionBean> actionBean) {
		return new Link(actionBean).setLabel(getLocale(), actionBean.getSimpleName());
	}

	/*
	 * User Properties
	 *
	 */

	/**
	 * Returns the currently logged in user.
	 * The user is retrieved from session after a successful authentication.
	 *
	 * @return User
	 */
	public User getUser() {
		return getContext().getUser();
	}

	/**
	 * Returns the username of the currently logged in user.
	 * The user is retrieved from session after a successful authentication.
	 *
	 * @return String
	 */
	public String getUserUsername() {
		return getContext().getUsername();
	}

	/**
	 * Returns the preferences of the currently logged in user, or the default preferences in none was found.
	 * The user preferences is retrieved from session after the first request is received.
	 *
	 * The getPreferences method in the accountFacade allows us to pass a null user, in which case a new default
	 * preferences object is returned.
	 *
	 * @return Preferences
	 */
	public final Preferences getUserPreferences() {
		if(getContext().getSession().getUserPreferences() == null)
			getContext().getSession().setUserPreferences(accountFacade.getPreferences(getContext().getUserOrNull()));

		return getContext().getSession().getUserPreferences();
	}

	/**
	 * Returns the subscription associated with the current user.
	 *
	 * @return Subscription
	 */
	public final Subscription getUserSubscription() {
		if(getContext().getSession().getUserSubscription() == null)
			getContext().getSession().setUserSubscription(accountFacade.getSubscription(getUser()));

		return getContext().getSession().getUserSubscription();
	}

	/*
	 * Tracker User Properties
	 *
	 */

	/**
	 * Returns the user owning the current public tracker data.
	 * If we are not currently in public mode, we return the currently logged in user.
	 * Otherwise the trackerUser is retrieved from session, but may be null if the user was not found.
	 *
	 * @return User
	 */
	public User getTrackerUser() {
		if(!isPublicMode())
			return getUser();

		return getContext().getSession().getTrackerUser();
	}

	/**
	 * Gets the username of the user owning the current public tracker data or returns the default if none was found.
	 * If we are not currently in public mode, we return the username of the currently logged in user.
	 * Otherwise the trackerUserUsername is retrieved from session.
	 *
	 * @return String
	 */
	public final String getTrackerUserUsername() {
		if(!isPublicMode())
			return getUserUsername();

		return getTrackerUserUsernameFromSession();
	}

	/**
	 * Returns the trackerUserUsername from session.
	 * This method is necessary to prevent a cycle from isPublicMode to getTrackerUserUsername.
	 *
     * There is an additional check here if there is no trackerUserUsername stored in session. In this case,
     * no parameter was passed in the url, so we want to set either the username of the logged in user,
     * or if no user is logged in, we set the default user instead.
	 *
	 * @return String
	 */
	private String getTrackerUserUsernameFromSession() {
		TracktacularSession session = getContext().getSession();

		// if there is no tracker user username in session, we don't want the default to override the user
		if(!session.isTrackerUserUsernameInSession())
			setTrackerUserUsername(getContext().isLoggedIn() ? getUserUsername() : DEFAULT_USERNAME);

		return session.getTrackerUserUsername();
	}

	/**
	 * Sets the trackerUserUsername into session.
	 * If the username is null, as in pages where the username is not in the url, the method does nothing.
	 * If the username is not null and it different from the username in session, we store it as well as the
	 * associated user.
	 *
	 * @param username
	 */
	public final void setTrackerUserUsername(String username) {
		if(StringUtils.isBlank(username))
			return;

		TracktacularSession session = getContext().getSession();

		if(!session.isTrackerUserUsernameInSession(username))
		{
			session.setTrackerUserUsername(username);
			session.setTrackerUser(accountFacade.getUserByUsername(username));
		}
	}

	/**
	 * Returns the preferences of the user owning the current public tracker data,
	 * or the default preferences in none was found. If we are not currently in public mode,
	 * we return the preferences of the currently logged in user. Otherwise, we return the trackerUserPreferences.
	 * Note that this is not stored in session so that users can see real time updates to permission changes.
	 *
	 * @return Preferences
	 */
	public final Preferences getTrackerUserPreferences() {
		if(!isPublicMode())
			return getUserPreferences();

		if(trackerUserPreferences == null)
			trackerUserPreferences = accountFacade.getPreferences(getTrackerUser());

		return trackerUserPreferences;
	}

	/**
	 * Converts Preferences.
	 *
	 * @param input
	 * @return Preferences
	 */
	public Preferences convertPreferences(String input) {
		return new Cloner().deepClone(getUserPreferences());
	}

	/**
	 * Returns an array of all trackers.
	 *
	 * @return Tracker[]
	 */
	public Tracker[] getAllTrackers() {
		return Tracker.values();
	}

	/**
	 * Save or updates preferences.
	 *
	 * @return Resolution
	 */
	@IgnoreInPublicMode
	public Resolution savePreferences() {
		Preferences preferences = getPreferences();
		populatePersistableUser(preferences);
		accountFacade.savePreferences(preferences);
		getContext().addSuccessMessage("account.savePreferences.success");
		getContext().getSession().clearUserPreferences();
		return new RedirectResolution(getClass()).
				addParameter(TracktacularActionBean.TRACKER_USER_USERNAME_PARAM, getTrackerUserUsername());
	}

	@Override
	public final Resolution onHandleValidationErrors(ValidationErrors errors) {
		if(getContext().isAjax())
			return new ForwardResolution(AJAX_VALIDATION_RESULT_VIEW);

		getContext().addErrorMessage("tracktacular.validationErrors");
		return super.onHandleValidationErrors(errors);
	}

	/**
	 * Returns a map of trackers and corresponding page links.
	 *
	 * @return Map<Tracker, Link>
	 */
	public Map<Tracker, Link> getPageLinks() {
		if(pageLinks == null)
		{
			pageLinks = new HashMap<>();

			for(Tracker tracker : Tracker.values())
				pageLinks.put(tracker, getPageLink(tracker));
		}

		return pageLinks;
	}

	/**
	 * Returns a page link for the given tracker.
	 *
	 * @param tracker
	 * @return Link
	 */
	private Link getPageLink(Tracker tracker) {
		TrackerPage[] trackerPages = getTrackerPages(tracker);
		TrackerPage defaultPage = trackerPages[0].getDefault();
		boolean active = tracker == getTracker();
		boolean disabled = !getTrackerUserPreferences().getTrackers().getTrackerPreferences(tracker).isTrackerEnabled();
		boolean locked = isPublicMode() && getTrackerUser() != null &&
				!getTrackerUserPreferences().getTrackers().getTrackerPreferences(tracker).isTrackerPublic();

		return new Link(defaultPage.getActionBean()).
				addParameter(TRACKER_USER_USERNAME_PARAM, getTrackerUserUsername()).
				setDisabled(disabled).
				setLocked(locked).
				setActive(active);
	}

	/**
	 * Returns a map of Trackers and TrackerPages.
	 *
	 * @return Map<Tracker, TrackerPage[]>
	 */
	public Map<Tracker, TrackerPage[]> getTrackerPageMap() {
		if(trackerPageMap == null)
		{
			trackerPageMap = Maps.newHashMap();

			for(Tracker tracker : Tracker.values())
				trackerPageMap.put(tracker, getTrackerPages(tracker));
		}

		return trackerPageMap;
	}

	/**
	 * Returns the TrackerPages array for the given tracker. + tracker.getName() +
	 *
	 * @param tracker
	 * @return TrackerPage[]
	 */
	protected final TrackerPage[] getTrackerPages(Tracker tracker) {
		try
		{
			String simpleName = tracker.getCapitalizedName() + "TrackerPage";
			String packageName = "com.tracktacular.web.page.tracker." + tracker.getName();
			String className = packageName + "." + simpleName;
			Class<?> trackerPageClass = Class.forName(className);
			return (TrackerPage[]) trackerPageClass.getEnumConstants();
		}
		catch (Exception exception)
		{
			throw new RuntimeException(exception);
		}
	}

	// getters and setters
	public TracktacularAccountFacade getAccountFacade() {
		return accountFacade;
	}

	public TracktacularVersion getTracktacularVersion() {
		return tracktacularVersion;
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}

	public boolean isMobile() {
		return getContext().getSession().isMobile();
	}

	public void setMobile(boolean mobile) {
		getContext().getSession().setMobile(mobile);
	}

	/**
	 * This lifecycle hook is executed before the BindingAndValidation stage.
	 * This method is primarily used to stop processing on handler methods that should be ignored in public mode.
	 *
	 * @return Resolution
	 */
	@SuppressWarnings("unused")
	@Before(stages = {LifecycleStage.BindingAndValidation})
	private Resolution forIgnoreOnPublicMode() {

		// this method occurs before binding so we have to manually retrieve the username parameter
		setTrackerUserUsername(getContext().getRequest().getParameter(TRACKER_USER_USERNAME_PARAM));

		if(isPublicMode())
		{
			Method method = ReflectionUtils.findMethod(getClass(), getContext().getEventName());

			if(method != null && method.isAnnotationPresent(IgnoreInPublicMode.class))
				return EmptyResolution.get();
		}

		return null;
	}

}