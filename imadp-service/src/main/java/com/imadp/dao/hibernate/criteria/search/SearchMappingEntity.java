package com.imadp.dao.hibernate.criteria.search;

import java.lang.annotation.ElementType;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.search.cfg.DocumentIdMapping;
import org.hibernate.search.cfg.SearchMapping;

import com.imadp.dao.AbstractPersistable;
import com.imadp.dao.Persistable;

/**
 * SearchMappingEntity
 * 
 * A configuration for an individual entity in a SearchMapping.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class SearchMappingEntity {
	private Class<? extends Persistable> entity;
	private List<SearchMappingField> searchMappingFields;

	/**
	 * Applies the mapping for this entity to the SearchMapping object.
	 * 
	 * @param searchMapping
	 */
	public void applyConfiguration(SearchMapping searchMapping) {
		Validate.notNull(entity);
		Validate.notEmpty(searchMappingFields);
		
		DocumentIdMapping mapping = searchMapping.entity(entity).indexed().
			property(AbstractPersistable.ID.getName(), ElementType.METHOD).documentId();
		
		for(SearchMappingField searchMappingField : searchMappingFields)
			searchMappingField.applyConfiguration(mapping);
	}

	// getters and setters
	public Class<? extends Persistable> getEntity() {
		return entity;
	}

	public void setEntity(Class<? extends Persistable> entity) {
		this.entity = entity;
	}

	public List<SearchMappingField> getSearchMappingFields() {
		return searchMappingFields;
	}

	public void setSearchMappingFields(List<SearchMappingField> searchMappingFields) {
		this.searchMappingFields = searchMappingFields;
	}

}
