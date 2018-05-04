package com.imadp.core.template;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.annotation.ExpectedException;

import com.imadp.core.template.TemplateDocument;
import com.imadp.core.template.TemplateException;
import com.imadp.core.test.IMADPCoreTestCase;

/**
 * TemplateEngineVelocityImplTest
 *
 * Tests and asserts class methods.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class TemplateEngineVelocityImplTest extends IMADPCoreTestCase {

	@Test
	@ExpectedException(TemplateException.class)
	public void createDocumentWithTestTemplateInvalidTemplateLocation() {
		String templateLocation = "templateLocation";

		templateEngineVelocity.createDocument(templateLocation, Locale.ENGLISH);
	}

	@Test
	public void createDocumentWithTestTemplateEnglishLocale() {
		String templateLocation = "com/imadp/core/context/template/TestTemplate.vm";

		TemplateDocument templateDocument = templateEngineVelocity.
			createDocument(templateLocation, Locale.ENGLISH);

		assertEquals("Subject_en", templateDocument.getProperty("subject"));
		assertEquals("From_en", templateDocument.getProperty("from"));
		assertEquals("SampleContent_en", templateDocument.getContent());
	}

	@Test
	public void createDocumentWithTestTemplateGermanLocale() {
		String templateLocation = "com/imadp/core/context/template/TestTemplate.vm";

		TemplateDocument templateDocument = templateEngineVelocity.
			createDocument(templateLocation, Locale.GERMAN);

		assertEquals("Subject_de", templateDocument.getProperty("subject"));
		assertEquals("From_de", templateDocument.getProperty("from"));
		assertEquals("SampleContent_de", templateDocument.getContent());
	}

	@Test
	public void createDocumentWithTestTemplateInvalidLocale() {
		String templateLocation = "com/imadp/core/context/template/TestTemplate.vm";

		TemplateDocument templateDocument = templateEngineVelocity.
			createDocument(templateLocation, Locale.CHINESE);

		assertEquals("Subject_en", templateDocument.getProperty("subject"));
		assertEquals("From_en", templateDocument.getProperty("from"));
		assertEquals("SampleContent_en", templateDocument.getContent());
	}

	@Test
	public void createDocumentWithTestTemplateDynamicEnglishLocale() {
		String templateLocation = "com/imadp/core/context/template/TestTemplateDynamic.vm";
		Map<String, Object> model = new HashMap<>();

		model.put("subject", "Dynamic Subject!");
		model.put("from", "Dynamic From!");
		model.put("content", "Dynamic Content!");

		TemplateDocument templateDocument = templateEngineVelocity.
			createDocument(templateLocation, Locale.ENGLISH, model);

		assertEquals("Subject_en: Dynamic Subject!", templateDocument.getProperty("subject"));
		assertEquals("From_en: Dynamic From!", templateDocument.getProperty("from"));
		assertEquals("SampleContent_en: Dynamic Content!", templateDocument.getContent());
	}

	@Test
	public void createDocumentWithTestTemplateDynamicGermanLocale() {
		String templateLocation = "com/imadp/core/context/template/TestTemplateDynamic.vm";
		Map<String, Object> model = new HashMap<>();

		model.put("subject", "Dynamic Subject!");
		model.put("from", "Dynamic From!");
		model.put("content", "Dynamic Content!");

		TemplateDocument templateDocument = templateEngineVelocity.
			createDocument(templateLocation, Locale.GERMAN, model);

		assertEquals("Subject_de: Dynamic Subject!", templateDocument.getProperty("subject"));
		assertEquals("From_de: Dynamic From!", templateDocument.getProperty("from"));
		assertEquals("SampleContent_de: Dynamic Content!", templateDocument.getContent());
	}

	@Test
	public void createDocumentWithTestTemplateDynamicInvalidLocale() {
		String templateLocation = "com/imadp/core/context/template/TestTemplateDynamic.vm";
		Map<String, Object> model = new HashMap<>();

		model.put("subject", "Dynamic Subject!");
		model.put("from", "Dynamic From!");
		model.put("content", "Dynamic Content!");

		TemplateDocument templateDocument = templateEngineVelocity.
			createDocument(templateLocation, Locale.CHINESE, model);

		assertEquals("Subject_en: Dynamic Subject!", templateDocument.getProperty("subject"));
		assertEquals("From_en: Dynamic From!", templateDocument.getProperty("from"));
		assertEquals("SampleContent_en: Dynamic Content!", templateDocument.getContent());
	}

}