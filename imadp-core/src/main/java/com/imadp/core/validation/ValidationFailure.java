package com.imadp.core.validation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.imadp.core.AbstractSerializable;


/**
 * ValidationFailure
 * 
 * This object encapsulates the details of a validation failure.
 * 
 * @note This class is immutable and thread-safe.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class ValidationFailure extends AbstractSerializable {
	private final String key;	
	private final List<String> objectNames;	
	private final List<Object> parameters;	
	
	// constructor
	public ValidationFailure(String key, String... objectNames) {
		this(key, null, objectNames);
	}
	
	// constructor
	@SuppressWarnings("unchecked")
	public ValidationFailure(String key, Object[] parameters, String... objectNames) {
		this.key = key;
		
		this.parameters = parameters == null ? Collections.EMPTY_LIST : 
			Collections.unmodifiableList(Arrays.asList(parameters));
		
		this.objectNames = objectNames == null ? Collections.EMPTY_LIST : 
			Collections.unmodifiableList(Arrays.asList(objectNames));
	}
			
	// getters
	public String getKey() {
		return key;
	}

	public List<Object> getParameters() {
		return parameters;
	}

	public List<String> getObjectNames() {
		return objectNames;
	}

}