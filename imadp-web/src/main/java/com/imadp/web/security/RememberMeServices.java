package com.imadp.web.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * RememberMeServices
 *
 * A standard remember-me service implementation.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class RememberMeServices extends TokenBasedRememberMeServices {
	
	@Override
	protected boolean rememberMeRequested(HttpServletRequest request, String parameter) {
		return true;
	}
		
}
