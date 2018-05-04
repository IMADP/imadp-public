package com.imadp.core.template;

import java.util.Locale;
import java.util.Map;

/**
 * ITemplateEngine
 *
 * Provides dynamic document creation by merging dynamic model values with specified templates.
 * Includes support for Locale based template selection.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface TemplateEngine {

	/**
	 * Creates a TemplateDocument from the templateLocation for a given locale.
	 *
	 * @param templateLocation
	 * @param locale
	 * @throws TemplateException if unable to build the TemplateDocument
	 * @return TemplateDocument
	 */
	public TemplateDocument createDocument(String templateLocation, Locale locale);

	/**
	 * Merges the model values into the template found by templateLocation and locale,
	 * and returns the merged template as a TemplateDocument.
	 *
	 * @param templateLocation
	 * @param locale
	 * @param model
	 * @throws TemplateException if unable to merge the template
	 * @return TemplateDocument
	 */
	public TemplateDocument createDocument(String templateLocation, Locale locale, Map<String, ?> model);

}
