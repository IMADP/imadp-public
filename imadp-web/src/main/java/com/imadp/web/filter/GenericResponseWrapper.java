package com.imadp.web.filter;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * GenericResponseWrapper
 *
 * Generic HttpServletResponseWrapper.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class GenericResponseWrapper extends HttpServletResponseWrapper { 
	private ByteArrayOutputStream output;
	private String contentType;
	private int contentLength;

	//constructor
	public GenericResponseWrapper(HttpServletResponse response) { 
		super(response);
		output = new ByteArrayOutputStream();
	} 

	/**
	 * Returns the data.
	 * 
	 * @return byte[]
	 */
	public byte[] getData() { 
		return output.toByteArray(); 
	} 

	/**
	 * Returns the content length.
	 * 
	 * @return int
	 */
	public int getContentLength() { 
		return contentLength; 
	} 

	/**
	 * Returns the HttpServletResponse.
	 * 
	 * @return HttpServletResponse
	 */
	public HttpServletResponse getHttpResponse() {
		return (HttpServletResponse) getResponse();
	}

	@Override
	public ServletOutputStream getOutputStream() { 
		return new FilterServletOutputStream(output); 
	} 

	@Override
	public PrintWriter getWriter() { 
		return new PrintWriter(getOutputStream(),true); 
	} 

	@Override
	public void setContentLength(int length) { 
		this.contentLength = length;
		super.setContentLength(length); 
	} 

	@Override
	public void setContentType(String type) { 
		this.contentType = type;
		super.setContentType(type); 
	} 

	@Override
	public String getContentType() { 
		return contentType; 
	} 

} 