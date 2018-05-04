package com.imadp.web.stripes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.controller.DefaultActionBeanContextFactory;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.integration.spring.SpringHelper;

/**
 * SpringActionBeanContextFactory
 * 
 * ContextFactory providing Spring injection to the action bean context.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class SpringActionBeanContextFactory extends DefaultActionBeanContextFactory {

	@Override
	public ActionBeanContext getContextInstance(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ActionBeanContext context = super.getContextInstance(request, response);
		SpringHelper.injectBeans(context, StripesFilter.getConfiguration().getServletContext());
		return context;
	}
	
}