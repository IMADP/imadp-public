package com.tracktacular.web;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.springframework.security.web.authentication.RememberMeServices;

import com.imadp.service.account.AccountFacade;
import com.imadp.service.account.credentials.Credentials;
import com.imadp.web.stripes.AbstractActionBean;
import com.imadp.web.stripes.AbstractActionBeanContext;
import com.imadp.web.stripes.account.AbstractAuthenticatedActionBeanContext;
import com.tracktacular.service.account.TracktacularAccountFacade;
import com.tracktacular.web.page.ErrorActionBean;
import com.tracktacular.web.page.IndexActionBean;
import com.tracktacular.web.page.IndexMobileActionBean;
import com.tracktacular.web.page.PageNotFoundActionBean;
import com.tracktacular.web.page.TracktacularReportActionBean;
import com.tracktacular.web.page.account.LoginActionBean;

/**
 * TracktacularActionBeanContext
 *
 * Custom action bean context.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TracktacularActionBeanContext extends AbstractAuthenticatedActionBeanContext<TracktacularSession> {

	@SpringBean
	TracktacularAccountFacade accountFacade;

	@SpringBean
	RememberMeServices rememberMeServices;

	@Override
	public Class<IndexActionBean> getHomePage() {
		return IndexActionBean.class;
	}

	@Override
	public Class<TracktacularSession> getSessionClass() {
		return TracktacularSession.class;
	}

	@Override
	public Class<? extends AbstractActionBean<? extends AbstractActionBeanContext<TracktacularSession>>> getLoginPage() {
		return LoginActionBean.class;
	}

	@Override
	public Resolution getAccessDeniedResolution() {
		addErrorMessage("tracktacular.accessDenied");
		return new RedirectResolution(IndexActionBean.class);
	}

	@Override
	public Resolution getSuccessfulLoginResolution() {
		getSession().clearUserPreferences();

		if(getSession().isMobile())
			return new RedirectResolution(IndexMobileActionBean.class).addParameter(
					TracktacularActionBean.TRACKER_USER_USERNAME_PARAM, getUsername());

		return new RedirectResolution(TracktacularReportActionBean.class).addParameter(
				TracktacularActionBean.TRACKER_USER_USERNAME_PARAM, getUsername());
	}

	@Override
	public void onSuccessfulAuthentication(Credentials credentials) {

	}

	@Override
	public Resolution getErrorResolution() {
		return new ForwardResolution(ErrorActionBean.class);
	}

	@Override
	public Resolution getPageNotFoundResolution() {
		return new ForwardResolution(PageNotFoundActionBean.class);
	}

	@Override
	protected AccountFacade<?> getAccountFacade() {
		return accountFacade;
	}

	@Override
	protected RememberMeServices getRememberMeServices() {
		return rememberMeServices;
	}

}