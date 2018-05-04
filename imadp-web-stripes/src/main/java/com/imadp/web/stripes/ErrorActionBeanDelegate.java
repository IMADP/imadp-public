package com.imadp.web.stripes;


import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;

import org.apache.http.HttpStatus;

/**
 * ErrorActionBean
 *
 * Page to direct error resolutions back to the context.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class ErrorActionBeanDelegate extends AbstractActionBean<AbstractActionBeanContext<?>> {

	@DefaultHandler
	public Resolution index() {
		getContext().getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		return getContext().getErrorResolution();
	}

}