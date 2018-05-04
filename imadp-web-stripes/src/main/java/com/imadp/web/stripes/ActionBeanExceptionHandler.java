package com.imadp.web.stripes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.StripesConstants;
import net.sourceforge.stripes.exception.DefaultExceptionHandler;
import net.sourceforge.stripes.exception.UrlBindingConflictException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imadp.core.validation.ValidationException;

/**
 * ActionBeanExceptionHandler
 *
 * Exception handler for common exception handling.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ActionBeanExceptionHandler extends DefaultExceptionHandler {

	// logger
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Handles validation exceptions thrown from service operations.
	 *
	 * @param validationException
	 * @param request
	 * @param response
	 * @return Resolution
	 */
	public Resolution handleValidationException(ValidationException validationException,
			HttpServletRequest request, HttpServletResponse response) {

		AbstractActionBean<?> bean = (AbstractActionBean<?>)request.getAttribute(StripesConstants.REQ_ATTR_ACTION_BEAN);
		AbstractActionBeanContext<?> context = bean.getContext();

		context.addValidationResult(validationException.getValidationResult());

		Resolution resolution = bean.handleValidationErrors(context.getValidationErrors());
		return resolution != null ? resolution : context.getSourcePageResolution();
	}

	/**
	 * Handles all url conflict binding exceptions and returns the page not found resolution.
	 *
	 * @param exception
	 * @param request
	 * @param response
	 * @return Resolution
	 */
	public Resolution handleUrlBindingConflictException(UrlBindingConflictException exception,
			HttpServletRequest request,	HttpServletResponse response) {
		return new ForwardResolution(PageNotFoundActionBeanDelegate.class);
	}

	/**
	 * Handles all generic exceptions and returns the error resolution.
	 *
	 * @param exception
	 * @param request
	 * @param response
	 * @return Resolution
	 */
	public Resolution handleGenericException(Exception exception, HttpServletRequest request,
			HttpServletResponse response) {

		AbstractActionBean<?> bean = (AbstractActionBean<?>) request.getAttribute(
				StripesConstants.REQ_ATTR_ACTION_BEAN);

		String actionBean = bean == null ? null : bean.getClass().getName();
		logger.error("An exception occurred processing actionBean ["+ actionBean +"]", exception);
		return new ForwardResolution(ErrorActionBeanDelegate.class);
	}

}