package com.imadp.web.stripes;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;

import org.apache.http.HttpStatus;

/**
 * PageNotFoundActionBean
 *
 * Page to direct pageNotFound resolutions back to the context.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class PageNotFoundActionBeanDelegate extends AbstractActionBean<AbstractActionBeanContext<?>> {

	@DefaultHandler
	public Resolution index() {
		getContext().getResponse().setStatus(HttpStatus.SC_NOT_FOUND);
		return getContext().getPageNotFoundResolution();
	}

}