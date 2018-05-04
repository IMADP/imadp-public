package com.imadp.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * AbstractFilter
 *
 * Base filter containing common logic.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractFilter implements Filter {

	@Override	
	public abstract void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException;

	@Override	
	public void init(FilterConfig config) throws ServletException {
		
	}
	
	@Override
	public void destroy() {
		
	}
	
}