package com.imadp.service.email;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.imadp.core.email.Email;
import com.imadp.dao.criteria.CriteriaParams;
import com.imadp.service.PersistableService;

/**
 * PersistableEmailService
 *
 * Provides common retrieval operations for PersistableEmail objects.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public interface PersistableEmailService extends PersistableService<PersistableEmail> {

	/**
     * Builds an html email using the standard template options. A template location and locale are used to locate
     * the template itself.
     *
     * The returned email will contain the text and any additional fields supplied by the template.
     * Callers are responsible for populating any remaining required fields, the destination in particular.
     *
     * @param template
     * @param locale
     * @return String
     */
    public Email buildEmail(String template, Locale locale);

    /**
     * Builds an html email using the standard template options. A template location and locale are used to locate
     * the template itself, and values from the model are bound into it the template.
     *
     * The returned email will contain the text and any additional fields supplied by the template.
     * Callers are responsible for populating any remaining required fields, the destination in particular.
     *
     * @param template
     * @param locale
     * @param model
     * @return String
     */
    public Email buildEmail(String template, Locale locale, Map<String, ?> model);

    /**
     * Persists and sends an email and returns the id of the PersistableEmail object that was created.
     * The implementation is responsible for sending the email, which may be a synchronous or asynchronous process.
     * As it is not possible to reliably determine the status of the email immediately after this method call,
     * the status of the email can be determined for a given point in time by retrieving the object by its id.
     *
     * @param email
     * @return Long
     */
    public Long send(Email email);

    /**
     * Returns a list of unsent emails by criteriaParams.
     *
     * @param criteriaParams
     * @return PersistableEmail
     */
	public List<PersistableEmail> findUnsent(CriteriaParams<PersistableEmail> criteriaParams);

	/**
	 * Finds the count of unsent emails.
	 *
	 * @return long
	 */
	public long findUnsentCount();

	/**
	 * Returns true if an email was found with the given to address and template.
	 *
	 * @param to
	 * @param template
	 * @return boolean
	 */
	public boolean isFoundBy(String to, String template);

}