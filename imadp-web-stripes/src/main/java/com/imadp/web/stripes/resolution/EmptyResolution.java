package com.imadp.web.stripes.resolution;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.Resolution;

import org.apache.http.HttpStatus;

/**
 * EmptyResolution
 *
 * An empty resolution to return when no response is expected.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class EmptyResolution implements Resolution {

	// instance
	private static final Resolution resolution = new EmptyResolution();
	
	// constructor
	private EmptyResolution() {
		
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setStatus(HttpStatus.SC_OK);
		response.setContentType("text/html");
	}
	
	/**
	 * Returns the singleton instance of the EmptyResolution.
	 * 
	 * @return Resolution
	 */
	public static Resolution get() {
		return resolution;
	}
	
}