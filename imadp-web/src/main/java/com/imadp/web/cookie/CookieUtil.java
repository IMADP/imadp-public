package com.imadp.web.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CookieUtil
 *
 * Utility methods for cookies.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class CookieUtil {
	
	/**
	 * Adds a cookie with the specified name, value, and expiration.
	 * If a cookie already exists, its values are updated.
	 * 
	 * @param cookieName
	 * @param cookieValue
	 * @param expiration
	 * @param request
	 * @param response
	 */
	public static void addCookie(String cookieName, String cookieValue, int expiration,
			HttpServletRequest request, HttpServletResponse response) {
		
		Cookie cookie = CookieUtil.getCookie(cookieName, request);
		
		if(cookie == null)
			cookie = new Cookie(cookieName, cookieValue);
		
		cookie.setMaxAge(expiration);
		cookie.setValue(cookieValue);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	/**
	 * Returns the cookie with the specified name.
	 * 
	 * @param cookieName
	 * @param request
	 * @return Cookie
	 */
	public static Cookie getCookie(String cookieName, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		if(cookies == null)
			return null;

		for(Cookie cookie : cookies)
			if(cookie.getName().equals(cookieName))
				return cookie;
		
		return null;
	}
	
	/**
	 * Returns the value from the cookie by the given cookieName, or null if no cookie was found.
	 * 
	 * @param cookieName
	 * @param request
	 * @return String
	 */
	public static String getCookieValue(String cookieName, HttpServletRequest request) {
		Cookie cookie = CookieUtil.getCookie(cookieName, request);
		
		if(cookie == null)
			return null;
		
		return cookie.getValue();
	}

	/**
	 * Deletes a cookie with the specified name.
	 * 
	 * @param cookieName
	 * @param request
	 * @param response
	 */
	public static void deleteCookie(String cookieName, HttpServletRequest request, HttpServletResponse response) {
		CookieUtil.addCookie(cookieName, null, 0, request, response);		
	}
	
}