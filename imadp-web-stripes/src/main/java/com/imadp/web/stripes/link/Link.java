package com.imadp.web.stripes.link;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import net.sourceforge.stripes.action.LocalizableMessage;

import com.imadp.core.AbstractSerializable;
import com.imadp.web.stripes.AbstractActionBean;
import com.imadp.web.stripes.AbstractActionBeanContext;

/**
 * Link
 *
 * An object encapsulating a dynamic link.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class Link extends AbstractSerializable {
	private Class<? extends AbstractActionBean<? extends AbstractActionBeanContext<?>>> beanClass;
	private Map<String, String> parameters;
	private String label;
	private String event;
	private boolean disabled;
	private boolean locked;
	private boolean active;

	// constructor
	public Link(Class<? extends AbstractActionBean<? extends AbstractActionBeanContext<?>>> beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * Adds a parameter to the link.
	 *
	 * @param key
	 * @param value
	 * @return Link
	 */
	public Link addParameter(String key, int value) {
		return addParameter(key, String.valueOf(value));
	}

	/**
	 * Adds a parameter to the link.
	 *
	 * @param key
	 * @param value
	 * @return Link
	 */
	public Link addParameter(String key, long value) {
		return addParameter(key, String.valueOf(value));
	}

	/**
	 * Adds a parameter to the link.
	 *
	 * @param key
	 * @param value
	 * @return Link
	 */
	public Link addParameter(String key, boolean value) {
		return addParameter(key, String.valueOf(value));
	}

	/**
	 * Adds a parameter to the link.
	 *
	 * @param key
	 * @param value
	 * @return Link
	 */
	public Link addParameter(String key, String value) {
		if(parameters == null)
			parameters = new HashMap<>(4);

		parameters.put(key, value);
		return this;
	}

	/**
	 * Returns true if this link has page parameters.
	 *
	 * @return boolean
	 */
	public boolean hasParameters() {
		return parameters != null;
	}

	/**
	 * Returns true if a label is provided, false otherwise.
	 *
	 * @return boolean
	 */
	public boolean hasLabel() {
		return label != null;
	}

	/**
	 * Sets a localizable label message.
	 *
	 * @param locale
	 * @param labelKey
	 * @param parameters
	 * @return Link
	 */
	public Link setLabel(Locale locale, String labelKey, Object... parameters) {
		this.label = new LocalizableMessage(labelKey, parameters).getMessage(locale);
		return this;
	}

	// getters and setters
	public Class<? extends AbstractActionBean<? extends AbstractActionBeanContext<?>>> getBeanClass() {
		return beanClass;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public String getEvent() {
		return event;
	}

	public Link setEvent(String event) {
		this.event = event;
		return this;
	}

	public String getLabel() {
		return label;
	}

	public Link setLabel(boolean label) {
		this.label = String.valueOf(label);
		return this;
	}

	public Link setLabel(int label) {
		this.label = String.valueOf(label);
		return this;
	}

	public Link setLabel(long label) {
		this.label = String.valueOf(label);
		return this;
	}

	public Link setLabel(String label) {
		this.label = label;
		return this;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public Link setDisabled(boolean disabled) {
		this.disabled = disabled;
		return this;
	}

	public boolean isActive() {
		return active;
	}

	public Link setActive(boolean active) {
		this.active = active;
		return this;
	}

	public boolean isLocked() {
		return locked;
	}

	public Link setLocked(boolean locked) {
		this.locked = locked;
		return this;
	}

}