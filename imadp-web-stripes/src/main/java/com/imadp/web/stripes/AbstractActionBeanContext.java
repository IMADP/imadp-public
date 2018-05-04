package com.imadp.web.stripes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.Message;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.util.CryptoUtil;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imadp.core.encryption.Encryptor;
import com.imadp.core.validation.ValidationFailure;
import com.imadp.core.validation.ValidationResult;

/**
 * AbstractActionBeanContext
 *
 * Custom action bean context for session data.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public abstract class AbstractActionBeanContext<T extends StripesSession> extends ActionBeanContext {

	/*
	 * The encryptor is used to encrypt and decrypt cookied values.
	 *
	 */
	@SpringBean
	protected Encryptor encryptor;

	// logger
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	// attribute names
	private static final String SESSION = "SESSION";

	// message key names
	private static final String MESSAGE_INFO = "MESSAGE_INFO";
	private static final String MESSAGE_SUCCESS = "MESSAGE_SUCCESS";
	private static final String MESSAGE_WARN = "MESSAGE_WARN";
	private static final String MESSAGE_ERROR = "MESSAGE_ERROR";

	// the encrypted source page
	private String encryptedSourcePage;

	/**
	 * Returns the HttpSession instance.
	 *
	 * @return HttpSession.
	 */
	public HttpSession getHttpSession() {
		return getRequest().getSession();
	}

	/**
	 * Returns the class instance of the custom session object.
	 *
	 * @return Class<T>
	 */
	public abstract Class<T> getSessionClass();

	/**
	 * Returns the class instance of the home page.
	 *
	 * @return Class<? extends AbstractActionBean<? extends AbstractActionBeanContext<T>>>
	 */
	public abstract Class<? extends AbstractActionBean<? extends AbstractActionBeanContext<T>>> getHomePage();

	/**
	 * Hook called when a session has expired and a POST was performed.
	 * Generally subclasses should redirect and inform the user that their submission could not be completed.
	 *
	 * @param actionBean
	 * @return Resolution
	 */
	@SuppressWarnings("rawtypes")
	public Resolution getSessionExpiredResolution(Class<? extends AbstractActionBean> actionBean) {
		return new RedirectResolution(actionBean);
	}

	/**
	 * Returns the error resolution.
	 *
	 * @return Resolution
	 */
	public abstract Resolution getErrorResolution();

	/**
	 * Returns the page not found resolution.
	 *
	 * @return Resolution
	 */
	public abstract Resolution getPageNotFoundResolution();

	/**
	 * Initializes a new custom session instance and stores the instance in session.
	 * Subclasses can hook into the onSessionCreated() method for additional processing.
	 *
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public final void initializeSession() throws InstantiationException, IllegalAccessException {
		HttpSession httpSession = getHttpSession();

		synchronized(httpSession)
		{
			T session = getSessionClass().newInstance();
			session.setSessionToken(getRandomToken());
			httpSession.setAttribute(SESSION, session);
			onSessionCreated(session);
		}
	}

	/**
	 * Hook into after the session has been created and initialized.
	 *
	 * @param session
	 */
	protected void onSessionCreated(T session) {

	}

	/**
	 * Retrieves the custom session object, or creates a new one if one was not found.
	 *
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public final T getSession() {
		return (T) getHttpSession().getAttribute(SESSION);
	}

	/**
	 * Returns a random token.
	 *
	 * @return String
	 */
	public String getRandomToken() {
		return encryptor.encrypt(UUID.randomUUID().toString());
	}

	/**
	 * Returns the session token unique to this session.
	 *
	 * @return String
	 */
	public String getSessionToken() {
		 return encryptor.encrypt(getHttpSession().getId());
	}

	/**
     * Returns true if this is an ajax request, false otherwise.
     *
     * @return boolean
     */
    public boolean isAjax() {
    	return "XMLHttpRequest".equals(getRequest().getHeader("X-Requested-With"));
    }

    /**
     * Returns true if this request is a GET request, false otherwise.
     *
     * @return boolean
     */
    public boolean isGet() {
    	return "GET".equalsIgnoreCase(getRequest().getMethod());
    }

    /**
     * Returns true if this request is a POST request, false otherwise.
     *
     * @return boolean
     */
    public boolean isPost() {
    	return "POST".equalsIgnoreCase(getRequest().getMethod());
    }

    /**
     * Retrieves the last url from the request.
     *
     * @return String
     */
    public String getLastUrl() {
        HttpServletRequest request = getRequest();
        StringBuilder sb = new StringBuilder();

        // start with the URI and the path
        String uri = (String) request.getAttribute("javax.servlet.forward.request_uri");
        String path = (String) request.getAttribute("javax.servlet.forward.path_info");

        if (uri == null)
        {
            uri = request.getRequestURI();
            path = request.getPathInfo();
        }

        sb.append(uri);

        if (path != null)
        	sb.append(path);

        return sb.toString();
    }

    /**
     * Returns true if validation errors were found, false otherwise.
     *
     * @return boolean
     */
    public boolean isValidationErrorsFound() {
    	return !getValidationErrors().isEmpty();
    }

    /**
     * Returns the encrypted source page which is a required parameter for customized stripes forms.
     *
     * @return String
     */
	public String getEncryptedSourcePage() {
		if(encryptedSourcePage != null)
			return encryptedSourcePage;

		encryptedSourcePage = CryptoUtil.encrypt(getRequest().getServletPath());
		return encryptedSourcePage;
	}

    /**
     * Adds the result of a validation to the context by adding any error messages if the result contains failures.
     * Returns true if the validation was successful, false otherwise.
     *
     * @param validationResult
     * @return boolean
     */
    public boolean addValidationResult(ValidationResult validationResult) {
    	for(ValidationFailure failure : validationResult.getFailures())
    	{
    		ValidationError error = new LocalizableError(failure.getKey(), failure.getParameters().toArray());

    		// add a global error for the failure
    		getValidationErrors().addGlobalError(error);

    		// add individual errors for the objectNames associated with the failure
    		for(String objectName : failure.getObjectNames())
    			getValidationErrors().add(objectName, error);
    	}

    	return validationResult.isValid();
    }

    /**
     * Returns a list of ajax validation error messages.
     *
     * @return List<String>
     */
    public List<AjaxValidationError> getAjaxValidationErrors() {
    	List<AjaxValidationError> errors = new ArrayList<>();
    	Locale locale = getLocale();

    	// convert stripes validation errors to ajax validation errors
    	for(Map.Entry<String, List<ValidationError>> entry : getValidationErrors().entrySet())
    	{
    		for(ValidationError validationError : entry.getValue())
    		{
    			LocalizableError localizableError = ((LocalizableError) validationError);
    			Object[] parameters = localizableError.getReplacementParameters();
    			parameters = Arrays.copyOfRange(parameters, 2, parameters.length);
	    		ValidationFailure failure = new ValidationFailure(localizableError.getMessageKey(), parameters, entry.getKey());
    			errors.add(new AjaxValidationError(failure, locale));
    		}
    	}

    	return errors;
    }

    /**
     * Returns a List of localized messages at the given level.
     *
     * @param key
     * @return List<String>
     */
    private List<String> getMessagesLocalized(String key) {
    	List<Message> messages = getMessages(key);

    	if(messages.isEmpty())
    		return Collections.emptyList();

    	List<String> messagesLocalized = new ArrayList<>(messages.size());

    	for(Message message : messages)
    		messagesLocalized.add(message.getMessage(getLocale()));

    	return messagesLocalized;
    }

    /**
     * Returns true if there are info messages present, false otherwise.
     *
     * @return boolean
     */
    public boolean isInfoMessagesFound() {
    	return !getInfoMessages().isEmpty();
    }

    /**
     * Returns a List of messages at the info level.
     *
     * @return List<Message>
     */
    public List<Message> getInfoMessages() {
    	return getMessages(MESSAGE_INFO);
    }

    /**
     * Returns a List of localized messages at the info level.
     *
     * @return List<Message>
     */
    public List<String> getInfoMessagesLocalized() {
    	return getMessagesLocalized(MESSAGE_INFO);
    }

    /**
     * Adds a message to the info messages, checking for duplicates.
     *
     * @param messageKey
     * @param messageParameters
     */
    public void addInfoMessage(String messageKey, Object... messageParameters) {
    	addMessage(getInfoMessages(), messageKey, messageParameters);
    }

    /**
     * Returns true if there are success messages present, false otherwise.
     *
     * @return boolean
     */
    public boolean isSuccessMessagesFound() {
    	return !getSuccessMessages().isEmpty();
    }

    /**
     * Returns a List of messages at the success level.
     *
     * @return List<Message>
     */
    public List<Message> getSuccessMessages() {
    	return getMessages(MESSAGE_SUCCESS);
    }

    /**
     * Returns a List of localized messages at the success level.
     *
     * @return List<Message>
     */
    public List<String> getSuccessMessagesLocalized() {
    	return getMessagesLocalized(MESSAGE_SUCCESS);
    }

    /**
     * Adds a message to the success messages, checking for duplicates.
     *
     * @param messageKey
     * @param messageParameters
     */
    public void addSuccessMessage(String messageKey, Object... messageParameters) {
    	addMessage(getSuccessMessages(), messageKey, messageParameters);
    }

    /**
     * Returns true if there are warn messages present, false otherwise.
     *
     * @return boolean
     */
    public boolean isWarnMessagesFound() {
    	return !getWarnMessages().isEmpty();
    }

    /**
     * Returns a List of messages at the warn level.
     *
     * @return List<Message>
     */
    public List<Message> getWarnMessages() {
    	return getMessages(MESSAGE_WARN);
    }

    /**
     * Returns a List of localized messages at the warn level.
     *
     * @return List<Message>
     */
    public List<String> getWarnMessagesLocalized() {
    	return getMessagesLocalized(MESSAGE_WARN);
    }

    /**
     * Adds a message to the warn messages, checking for duplicates.
     *
     * @param messageKey
     * @param messageParameters
     */
    public void addWarnMessage(String messageKey, Object... messageParameters) {
    	addMessage(getWarnMessages(), messageKey, messageParameters);
    }

    /**
     * Returns true if there are error messages present, false otherwise.
     *
     * @return boolean
     */
    public boolean isErrorMessagesFound() {
    	return !getErrorMessages().isEmpty();
    }

    /**
     * Returns a List of messages at the error level.
     *
     * @return List<Message>
     */
    public List<Message> getErrorMessages() {
    	return getMessages(MESSAGE_ERROR);
    }

    /**
     * Returns a List of localized messages at the error level.
     *
     * @return List<Message>
     */
    public List<String> getErrorMessagesLocalized() {
    	return getMessagesLocalized(MESSAGE_ERROR);
    }

    /**
     * Adds a message to the error messages, checking for duplicates.
     *
     * @param messageKey
     * @param messageParameters
     */
    public void addErrorMessage(String messageKey, Object... messageParameters) {
    	addMessage(getErrorMessages(), messageKey, messageParameters);
    }

    /**
     * Adds a LocalizableMessage to the list of messages, if the message was not already found.
     *
     * @param messages
     * @param messageKey
     * @param messageParameters
     */
    private void addMessage(List<Message> messages, String messageKey, Object... messageParameters) {
    	Message message = new LocalizableMessage(messageKey, messageParameters);

    	if(!messages.contains(message))
    		messages.add(message);
    }

}