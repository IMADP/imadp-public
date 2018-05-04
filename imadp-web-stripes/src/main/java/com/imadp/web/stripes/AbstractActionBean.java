package com.imadp.web.stripes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.After;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.util.UrlBuilder;
import net.sourceforge.stripes.validation.ScopedLocalizableError;
import net.sourceforge.stripes.validation.ValidationError;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.google.common.base.CaseFormat;
import com.imadp.core.AbstractSerializable;
import com.imadp.core.money.Money;
import com.imadp.core.validation.ValidationResult;
import com.imadp.core.validation.Validator;
import com.imadp.service.Sortable;
import com.imadp.web.WebUtil;
import com.imadp.web.stripes.link.Link;
import com.imadp.web.stripes.resolution.EmptyResolution;

/**
 * AbstractActionBean
 *
 * An abstract class providing base functionality for ActionBean implementations.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractActionBean<T extends AbstractActionBeanContext<?>>
	implements ActionBean, ValidationErrorHandler {

	// logger
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	// label key suffix
	protected static final String TITLE_KEY_PREFIX = "title.";
	protected static final String HEADING_KEY_PREFIX = "heading.";
	protected static final String BREADCRUMB_KEY_PREFIX = "breadcrumb.";

	// context
	private T context;

	// breadcrumbs
	private List<Link> breadcrumbs;

	// the default url binding for the action bean
	private String defaultUrl;

	/**
	 * Converts Money.
	 *
	 * @param amount
	 * @param returnClass
	 * @param errors
	 * @return Money
	 */
	public Money convertMoney(String amount, Class<?> returnClass, List<ValidationError> errors) {
		try
		{
			return new Money(amount);
		}
		catch(Exception exception)
		{
			errors.add(new ScopedLocalizableError("converter.number", "invalidNumber"));
			return null;
		}
	}

	/**
	 * A no-operation event provided for simple requests that do not require response state.
	 *
	 * @return Resolution
	 */
	public Resolution none() {
		return EmptyResolution.get();
	}

	/**
	 * An event that simply accesses session to prevent the session from expiring.
	 * Often used in conjunction with a background ajax polling request.
	 *
	 * @return Resolution
	 */
	public Resolution heartbeat() {
		getContext().getSession();
		return EmptyResolution.get();
	}

	/**
	 * Returns the context object.
	 *
	 * @return T
	 */
	@Override
	public T getContext() {
		return context;
	}

	/**
	 * Sets the context object.
	 *
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void setContext(ActionBeanContext context) {
		this.context = (T) context;
	}

	@Override
	public final Resolution handleValidationErrors(ValidationErrors errors) {
		WebUtil.setValidationError(context.getResponse());
		return onHandleValidationErrors(errors);
	}

	/**
	 * Hook into validation error handling.
	 *
	 * @param errors
	 * @return Resolution
	 */
	protected Resolution onHandleValidationErrors(ValidationErrors errors) {
		return null;
	}

	/**
	 * Returns a new date string matching the current date.
	 *
	 * @return String
	 */
	public String getNewDateString() {
		return AbstractSerializable.DATE_FORMATTER.print(new DateTime());
	}

	/**
	 * Returns a new dateTime string matching the current date.
	 *
	 * @return String
	 */
	public String getNewDateTimeString() {
		return AbstractSerializable.DATE_TIME_FORMATTER.print(new DateTime());
	}

	/**
	 * Returns the current locale;
	 *
	 * @return Locale
	 */
	public Locale getLocale() {
		return Locale.ENGLISH;
	}

	/**
	 * Returns the title of the page using a localized message with a key following the pattern: title.{className}
	 *
	 * @return String
	 */
	public String getTitle() {
		return new LocalizableMessage(getTitleKey()).getMessage(getLocale());
	}

	/**
	 * Returns the key used to look up the title resource.
	 *
	 * @return String
	 */
	protected final String getTitleKey() {
		return TITLE_KEY_PREFIX + getClass().getName();
	}

	/**
	 * Returns the heading of the page using a localized message with a key following the pattern: heading.{className}
	 *
	 * @return String
	 */
	public String getHeading() {
		return new LocalizableMessage(getHeadingKey()).getMessage(getLocale());
	}

	/**
	 * Returns the key used to look up the heading resource.
	 *
	 * @return String
	 */
	protected final String getHeadingKey() {
		return HEADING_KEY_PREFIX + getClass().getName();
	}

	/**
	 * Return the List of breadcrumb links.
	 *
	 * @return List<Link>
	 */
	public final List<Link> getBreadcrumbs() {
		if(breadcrumbs == null)
			onGetBreadcrumbs();

		return breadcrumbs;
	}

	/**
	 * Adds a link to the list of breadcrumbs.
	 * If a label is not provided, a default is provided using a localized message with a key following the pattern:
	 * breadcrumb.{className}
	 *
	 * @param link
	 * @param labelParameters
	 */
	protected final void addBreadcrumb(Link link, Object...labelParameters) {
		if(breadcrumbs == null)
			breadcrumbs = new ArrayList<>(4);

			if(!link.hasLabel())
				link.setLabel(getLocale(), getBreadcrumbKey(link), labelParameters);

			breadcrumbs.add(link);
	}

	/**
	 * Returns the key used to look up the breadcrumb resource.
	 *
	 * @return String
	 */
	protected final String getBreadcrumbKey() {
		return BREADCRUMB_KEY_PREFIX + getClass().getName();
	}

	/**
	 * Returns the key used to look up the breadcrumb resource.
	 *
	 * @param link
	 * @return String
	 */
	protected final String getBreadcrumbKey(Link link) {
		return BREADCRUMB_KEY_PREFIX + link.getBeanClass().getName();
	}

	/**
	 * Removes the last breadcrumb, if one was found.
	 *
	 */
	protected final void removeBreadcrumb() {
		if(!CollectionUtils.isEmpty(breadcrumbs))
			breadcrumbs.remove(breadcrumbs.size() - 1);
	}

	/**
	 * Hook called when breadcrumbs are requested.
	 *
	 */
	protected void onGetBreadcrumbs() {
		addBreadcrumb(new Link(getContext().getHomePage()));
	}

	/**
	 * Adds a validation result by executing the given validator and adding any errors into the ValidationErrors.
	 *
	 * @param validator
	 * @return boolean
	 */
	protected final boolean addValidationResult(Validator validator) {
		return addValidationResult(validator.getValidationResult());
	}

	/**
	 * Adds a validation result by adding any errors found in the ValidationResult into the ValidationErrors.
	 *
	 * @param validationResult
	 * @return boolean
	 */
	protected final boolean addValidationResult(ValidationResult validationResult) {
		return getContext().addValidationResult(validationResult);
	}

	/**
	 * Returns the ValidationErrors object.
	 *
	 * @return ValidationErrors
	 */
	protected final ValidationErrors getValidationErrors() {
		return getContext().getValidationErrors();
	}

	/**
	 * Returns an ajax resolution in the form of a forwardResolution of the last source page if the request is ajax,
	 * with a fallback redirect resolution for non ajax requests.
	 *
	 * @param view
	 * @param actionBean
	 * @return Resolution
	 */
	protected final Resolution getAjaxSourceResolution() {
		return getContext().isAjax() ? new ForwardResolution(getContext().getSourcePage()) :
			new RedirectResolution(getClass());
	}

	/**
	 * Returns the class of this bean, which may otherwise be difficult to retrieve through jsps.
	 *
	 * @return Class<?>
	 */
	public final Class<?> getBeanClass() {
		return this.getClass();
	}

	/**
	 * Returns the class name of the action bean.
	 *
	 * @return String
	 */
	public final String getClassName() {
		return this.getClass().getName();
	}

	/**
	 * Returns the view name of a page, which should be followed for consistencies sake.
	 * Example: AboutActionBean > aboutPage
	 * Example: TermsAndConditionsActionBean > termsAndConditionsPage
	 *
	 * @return String
	 */
	public final String getViewName() {
		String pageName = getClass().getSimpleName().replace("ActionBean", "Page");
		return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, pageName);
	}

	/**
	 * Returns the view path of a page, which should be followed for consistencies sake.
	 * Example: com.service.web.page.IndexActionBean > /WEB-INF/jsp/page/indexPage.jsp
	 *
	 * @return String
	 */
	public final String getViewPath() {
		String packageName = getClass().getPackage().getName();
		String jspPath = packageName.substring(packageName.indexOf("page")).replace('.', '/');
		return "/WEB-INF/jsp/" + jspPath + "/" + getViewName() + ".jsp";
	}

	/**
	 * Returns the default resolution, which forwards to the view path.
	 *
	 * @return Resolution
	 */
	public final Resolution getDefaultResolution() {
		return new ForwardResolution(getViewPath());
	}

	/**
	 * Returns the default url binding for the actionBean.
	 *
	 * @return String
	 */
	public final String getDefaultUrl() {
		if(defaultUrl == null)
		{
			String urlBinding =  StripesFilter.getConfiguration().getActionResolver().getUrlBinding(this.getClass());
			UrlBuilder urlBuilder = new UrlBuilder(getContext().getRequest().getLocale(), urlBinding, false).setEvent(null);
			String action = urlBuilder.toString();

			if(action.startsWith("/"))
			{
				String contextPath = getContext().getRequest().getContextPath();

				if(contextPath.length() > 1 && !action.startsWith(contextPath + '/'))
					action = contextPath + action;
			}

			defaultUrl = getContext().getResponse().encodeURL(action);
		}

		return defaultUrl;
	}

	/**
	 * Takes a list of sorted objects and returns a new list containing the objects
	 * whose sort orders were changed. The sortable object's sortOrders are set according to
	 * the index they were found in the list. The returned list may be empty or contain fewer
	 * objects than the original if none or only some of the sort values were changed.
	 *
	 * @param <V>
	 * @param sortedObjects
	 * @return List<V>
	 */
	protected <V extends Sortable> List<V> getResortedList(List<V> sortedObjects) {
		List<V> updatedObjects = new ArrayList<>(sortedObjects.size());

		for(int i = 0; i < sortedObjects.size(); i++)
		{
			V sortable = sortedObjects.get(i);

			if(sortable != null && sortable.getSort() != i)
			{
				sortable.setSort(i);
				updatedObjects.add(sortable);
			}
		}

		return updatedObjects;
	}

	/**
	 * This lifecycle hook is executed after the ActionBeanResolution stage.
	 * Its purpose is to initialize a new custom session and set an indicator in the header of the response.
	 * A post request on a new session needs to be handled specifically, as it usually signals a session expiration.
	 *
	 * @return Resolution
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@After(stages = {LifecycleStage.ActionBeanResolution})
	Resolution forSessionLifecycle() throws InstantiationException, IllegalAccessException {
		if(getContext().getSession() == null)
		{
			getContext().initializeSession();

			// if the action bean should handle session expiration, we inspect the request and handle
			if(isHandleSessionExpirationOnPost())
			{
				logger.debug("Handling expired session on post in lifecycle hook forSessionLifecycle");

				WebUtil.setNewSession(getContext().getResponse());

				// return an empty resolution for an ajax request to interrupt processing
				if(getContext().isAjax() && getContext().isPost())
					return EmptyResolution.get();

				// return a resolution provided by a subclass for a non ajax session expiration
				if(!getContext().isAjax() && getContext().isPost())
					return getContext().getSessionExpiredResolution(getClass());
			}
		}

		return null;
	}

	/**
	 * Override to determine if session expiration handling should be applied to post requests.
	 * By default session expiration handling is enabled, meaning an ajax or non ajax post with a
	 * new session will be intercepted and redirected to a session expired resolution.
	 *
	 * @return boolean
	 */
	protected boolean isHandleSessionExpirationOnPost() {
		return true;
	}

	/**
	 * This lifecycle hook is executed after the ActionBeanResolution stage.
	 * Its purpose is to traverse all fields of the ActionBean for fields that are instances of IActionBeanAware.
	 * These fields are then instantiated if they have not been already, and initialized with the current action bean.
	 *
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@After(stages = {LifecycleStage.ActionBeanResolution})
	void forActionBeanAwareInitialization() throws IllegalArgumentException, IllegalAccessException,
	InstantiationException {

		// look for any fields that are instances of IActionBeanAware and initialize them
		for(Field field : getClass().getDeclaredFields())
		{
			// if the fields are assignable, first check to see if they are instantiated
			if(ActionBeanAware.class.isAssignableFrom(field.getType()))
			{
				field.setAccessible(true);
				Object fieldValue = field.get(this);

				// if they are not instantiated, instantiate them now
				if(fieldValue == null)
				{
					fieldValue = field.getType().newInstance();
					field.set(this, fieldValue);
				}

				// initialize the field
				((ActionBeanAware)fieldValue).initialize(this);
			}

		}
	}

}