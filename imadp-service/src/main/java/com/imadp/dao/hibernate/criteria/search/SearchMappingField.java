package com.imadp.dao.hibernate.criteria.search;

import java.lang.annotation.ElementType;

import org.apache.commons.lang.Validate;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.cfg.DocumentIdMapping;

/**
 * SearchMappingField
 * 
 * A configuration for a single SearchMapping field.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class SearchMappingField {
	private String field;
	
	/**
	 * Applies the mapping for this field to the DocumentIdMapping object.
	 * 
	 * @param documentIdMapping
	 */
	public void applyConfiguration(DocumentIdMapping documentIdMapping) {
		Validate.notNull(field);
		
		documentIdMapping.property(field, ElementType.FIELD).field().index(Index.TOKENIZED).store(Store.YES);
	}

	// getters and setters
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}