package com.imadp.core.template;

import java.io.Serializable;
import java.util.Properties;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * TemplateDocument
 * 
 * The object created after merging a model to a template.
 * A TemplateDocument consists of the merged content of the template,
 * as well as any properties that were defined in the template, which
 * may also have been merged with dynamic content. 
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class TemplateDocument implements Serializable {
	private String content;	
	private Properties properties;
		
	// constructor
	public TemplateDocument() { 
		
	}
			
	// constructor
	public TemplateDocument(String content) { 
		this.content = content;
	}
	
	// constructor
	public TemplateDocument(String content, Properties properties) { 
		this.content = content;
		this.properties = properties;
	}
			
	/**
	 * Adds a new property name and value to the map of properties.
	 * 
	 * @param name
	 * @param value
	 */
	public void add(String name, String value) {
		if(properties == null)
			properties = new Properties();
		
		properties.put(name, value);
	}	

	/**
	 * Returns a property value by name
	 * 
	 * @param name
	 * @return String
	 */
	public String getProperty(String name) {
		return properties.getProperty(name);
	}
	
	// getters and setters	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
