package com.imadp.web.security;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;

/**
 * NullRememberMeServices
 *
 * A no-op remember-me services implementation.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class NullRememberMeServices implements RememberMeServices {

	@Override
	public Authentication autoLogin(HttpServletRequest arg0, HttpServletResponse arg1) {
		return null;
	}

	@Override
	public void loginFail(HttpServletRequest arg0, HttpServletResponse arg1) {
		
	}

	@Override
	public void loginSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication arg2) {
		
	}
			
}