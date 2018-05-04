package com.imadp.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * WebUtil
 *
 * A collection of general utilities for servlet programming.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class WebUtil {

	// headers
	private static final String HEADER_NEW_SESSION = "IMADP-NewSession";
	private static final String HEADER_VALIDATION_ERROR = "IMADP-ValidationError";
	private static final String HEADER_REQUESTED_WITH = "X-Requested-With";

	 // header values
    private static final String AJAX_REQUEST = "XMLHttpRequest";

	/**
     * Returns true if this is an ajax request that the filter should handle.
     *
     * @param request
     * @return boolean
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return AJAX_REQUEST.equals(request.getHeader(HEADER_REQUESTED_WITH));
    }

    /**
     * Returns true if the response contains validation error, false otherwise.
     *
     * @param httpResponse
     * @return boolean
     */
    public static boolean isNewSession(HttpServletResponse httpResponse) {
        return httpResponse.containsHeader(HEADER_NEW_SESSION);
    }

    /**
     * Sets a new session header.
     *
     * @param httpResponse
     */
    public static void setNewSession(HttpServletResponse httpResponse) {
    	httpResponse.setHeader(HEADER_NEW_SESSION, String.valueOf(Boolean.TRUE));
    }

    /**
     * Returns true if the response contains validation error, false otherwise.
     *
     * @param httpResponse
     * @return boolean
     */
    public static boolean isValidationError(HttpServletResponse httpResponse) {
        return httpResponse.containsHeader(HEADER_VALIDATION_ERROR);
    }

    /**
     * Sets an invalid response header.
     *
     * @param httpResponse
     */
    public static void setValidationError(HttpServletResponse httpResponse) {
    	httpResponse.setHeader(HEADER_VALIDATION_ERROR, String.valueOf(Boolean.TRUE));
    }

    /**
	 * Encodes a string using UTF-8.
	 *
	 * @param string
	 * @return String
	 */
	public static String encodeUrl(String string) {
		if(string == null)
			return null;

		try
		{
			return URLEncoder.encode(string, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			// this should never happen
			throw new RuntimeException(e);
		}
	}

	/**
	 * Decodes a string using UTF-8.
	 *
	 * @param string
	 * @return String
	 */
	public static String decodeUrl(String string) {
		if(string == null)
			return null;

		try
		{
			return URLDecoder.decode(string, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			// this should never happen
			throw new RuntimeException(e);
		}
	}

}