package com.imadp.dao.hibernate.criteria.search;

import java.util.List;

import org.hibernate.search.cfg.SearchMapping;

import com.imadp.dao.Persistable;

/**
 * SearchMappingConfiguration
 *
 * A configuration providing dynamic SearchMapping construction.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class SearchMappingConfiguration {
	List<SearchMappingEntity> searchMappingEntities;

	/**
	 * Constructs a SearchMapping object from SearchEntityMappings.
	 *
	 * @return SearchMapping
	 */
	public SearchMapping toSearchMapping() {
		SearchMapping searchMapping = new SearchMapping();

		for(SearchMappingEntity searchMappingEntity : searchMappingEntities)
			searchMappingEntity.applyConfiguration(searchMapping);

		return searchMapping;
	}

	/**
	 * Returns true if the searchMappingEntities contains the given entityClass.
	 *
	 * @param entityClass
	 * @return boolean
	 */
	public boolean hasSearchClass(Class<? extends Persistable> entityClass) {
		for(SearchMappingEntity searchMappingEntity : searchMappingEntities)
			if(searchMappingEntity.getEntity().equals(entityClass))
				return true;

		return false;
	}

	// getters and setters
	public List<SearchMappingEntity> getSearchMappingEntities() {
		return searchMappingEntities;
	}

	public void setSearchMappingEntities(List<SearchMappingEntity> searchMappingEntities) {
		this.searchMappingEntities = searchMappingEntities;
	}

}
