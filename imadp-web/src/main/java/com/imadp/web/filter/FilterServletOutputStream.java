package com.imadp.web.filter;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

/**
 * FilterServletOutputStream
 *
 * Servlet output stream wrapper.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class FilterServletOutputStream extends ServletOutputStream {
	private DataOutputStream dataOutputStream; 

	// constructor
	public FilterServletOutputStream(OutputStream output) { 
		dataOutputStream = new DataOutputStream(output); 
	}

	@Override
	public void write(int b) throws IOException  { 
		dataOutputStream.write(b); 
	}

	@Override
	public void write(byte[] b) throws IOException  { 
		dataOutputStream.write(b); 
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException  { 
		dataOutputStream.write(b,off,len); 
	} 

}