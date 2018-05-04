package com.imadp.web.stripes;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.integration.spring.SpringHelper;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;


/**
 * AbstractAction
 *
 * A base class providing functionality for actions, which are responses to request events.
 * The action framework provides a centralized place to put form processing code, while
 * guaranteeing protection and consistent resolution returning for double submissions.
 *
 * Subclasses should override and complete the abstract methods to allow action beans to use these features.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractAction<T extends AbstractActionBean<? extends AbstractActionBeanContext<?>>>
	implements Serializable, ActionBeanAware<T> {

	// logger
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	// the attribute name for storing actions in session
	private static final String ACTION_SESSION = AbstractAction.class.getName() + "#ActionSession";

	// the tokens identifying a session and form
	private String formToken;
	private String sessionToken;

	@Override
	public void initialize(T actionBean) {
		SpringHelper.injectBeans(this, StripesFilter.getConfiguration().getServletContext());
	}

	/**
	 * Performs an action in response to an event.
	 * The action is guaranteed to execute one time per formToken,
	 * effectively protecting against double submissions.
	 *
	 * @param actionBean
	 * @return Resolution
	 */
	public Resolution act(final T actionBean) {
		HttpSession session = actionBean.getContext().getHttpSession();

		// synchronize on the session to prevent concurrent processing
		synchronized(session)
		{
			// validate the tokens
			if(StringUtils.isEmpty(formToken) || (isSessionTokenRequired() && !actionBean.getContext().getSessionToken().equals(sessionToken)))
			{
				logger.warn("Invalid action tokens were provided for [{}].[{}], ipAddress=[{}], formToken=[{}], sessionToken=[{}]",
						new Object[] {actionBean.getClass().getName(), actionBean.getContext().getEventName(),
						actionBean.getContext().getRequest().getRemoteAddr(), formToken, sessionToken});

				throw new IllegalArgumentException("Invalid action tokens were provided");
			}

			// look for an existing action in session
			Cache<String, AbstractAction<T>> actionCache = getActionCache(session);
			AbstractAction<T> action = actionCache.getIfPresent(formToken);

			// if this is the first submission, the form is processed by the subclass
			if(action == null)
			{
				doAction(actionBean);
				actionCache.put(formToken, this);
				action = this;
			}

			// the resolution is returned
			return action.getResolution(actionBean);
		}
	}

	/**
	 * Gets the action map from session, or creates and stores it in session if it was not found.
	 *
	 * @param session
	 * @return Map<String, AbstractAction<T>>
	 */
	@SuppressWarnings("unchecked")
	private Cache<String, AbstractAction<T>> getActionCache(HttpSession session) {
		Cache<String, AbstractAction<T>> actionCache = (Cache<String, AbstractAction<T>>)session.getAttribute(ACTION_SESSION);

		// if it was not found, create a new map and store it in session
		if(actionCache == null)
		{
			actionCache = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES).build();
			session.setAttribute(ACTION_SESSION, actionCache);
		}

		return actionCache;
	}

	/**
	 * Allows subclasses to process the action. This will only be called once per duplicate submission.
	 * Subclasses may need to store state that the getResolution() method can use to return the appropriate resolution.
	 * This state will be stored in session, so it is best to limit the state to the minimum amount necessary.
	 *
	 * @param actionBean
	 */
	protected abstract void doAction(T actionBean);

	/**
	 * Returns the resolution as determined by the action.
	 *
	 * @note Subclasses that return a forward instead of a redirect may need to clear their
	 * 		 actionBean data or the forms may not be returned in the original empty state.
	 *
	 * @param actionBean
	 * @return Resolution
	 */
	protected abstract Resolution getResolution(T actionBean);

	/**
	 * Returns a boolean indicating whether a session token is required. This allows certain actions to protect against
	 * Cross Site Request Forgery attacks. However, any form submissions sent on expired sessions will fail with this setting.
	 *
	 * @return boolean
	 */
	protected boolean isSessionTokenRequired() {
		return false;
	}

	// getters and setters
	public String getFormToken() {
		return formToken;
	}

	public void setFormToken(String formToken) {
		this.formToken = formToken;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

}