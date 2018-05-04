package com.imadp.core.template;

import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DateTool;

import com.imadp.core.CoreComponent;

/**
 * TemplateEngineVelocityImpl
 *
 * The velocity implementation of the TemplateEngine.
 *
 * @note This component is declared final as it was not designed with inheritance in mind.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class TemplateEngineVelocityImpl extends CoreComponent implements TemplateEngine {

	// underscore
	private static final char UNDERSCORE = '_';

	// properties
	private VelocityEngine velocityEngine;

	@Override
	protected void onInit() {
		super.onInit();

		Validate.notNull(velocityEngine);
	}

	@Override
	public TemplateDocument createDocument(String templateLocation, Locale locale) {
		return createDocument(templateLocation, locale, null);
	}

	@Override
	public TemplateDocument createDocument(String templateLocation, Locale locale, Map<String, ?> model) {
		return merge(getLocalizedTemplateLocation(templateLocation, locale), model);
	}

	/**
	 * Returns the localized template location.
	 *
	 * @param templateLocation
	 * @param locale
	 * @throws TemplateException
	 * @return String
	 */
	private String getLocalizedTemplateLocation(String templateLocation, Locale locale) {

		// if no locale is present, try the base template name
		if(locale == null)
		{
			if(!velocityEngine.resourceExists(templateLocation))
				throw new TemplateException("No template was found by templateLocation " +
					"["+templateLocation+"]");

			return templateLocation;
		}

		// append the locale language to the template location
		String localizedTemplateName = appendLanguage(templateLocation, locale);

		// if no localized template was found, try the base template name
		if(!velocityEngine.resourceExists(localizedTemplateName))
		{
			logger.warn("No template was found by localizedTemplateName: {}, attempting to use" +
					" base templateLocation: {}", localizedTemplateName, templateLocation);

			return getLocalizedTemplateLocation(templateLocation, null);
		}

		return localizedTemplateName;
	}

	/**
	 * Appends an underscore and the locale language to a templateName.
	 *
	 * @param templateName
	 * @param locale
	 * @return String
	 */
	private String appendLanguage(String templateName, Locale locale) {
		int extentionIndex = templateName.lastIndexOf(".");

		if(extentionIndex < 0)
			return templateName + UNDERSCORE + locale.getLanguage();

		return templateName.substring(0, extentionIndex) + UNDERSCORE + locale.getLanguage()
			+ templateName.substring(extentionIndex);
	}

	/**
	 * Merges the values into the template, using the default encoding.
	 *
	 * @param templateName
	 * @param model
	 * @return TemplateDocument
	 * @throws TemplateException
	 */
	private TemplateDocument merge(String templateName, Map<String, ?> model){
		try
		{
			TemplateDocument templateDocument = new TemplateDocument();

			StringWriter stringWriter = new StringWriter();
			VelocityContext velocityContext = new VelocityContext(model);
			velocityContext.put("dateTool", new DateTool());

			velocityEngine.mergeTemplate(templateName, VelocityEngine.ENCODING_DEFAULT,
					velocityContext, stringWriter);

			templateDocument.setContent(stringWriter.toString());

			for(Object o : velocityContext.getKeys())
				if(o != null)
					templateDocument.add(o.toString(), String.valueOf(velocityContext.get(o.toString())));

			return templateDocument;
		}
		catch (Exception exception)
		{
			throw new TemplateException(exception);
		}
	}

	// getters and setters
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

}
