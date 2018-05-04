package com.imadp.dao.hibernate.criteria.search;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.Environment;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

/**
 * SearchSessionFactoryBean
 * 
 * An extention of the LocalSessionFactoryBean to provide search mapping configuration.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class SearchSessionFactoryBean extends LocalSessionFactoryBean {
	private SearchMappingConfiguration searchMappingConfiguration;
	
	@Override
	protected void postProcessConfiguration(Configuration configuration) throws HibernateException {
		Validate.notNull(searchMappingConfiguration);
		
		configuration.getProperties().put(Environment.MODEL_MAPPING, searchMappingConfiguration.toSearchMapping());
	}
	
	// getters and setters
	public SearchMappingConfiguration getSearchMappingConfiguration() {
		return searchMappingConfiguration;
	}

	public void setSearchMappingConfiguration( SearchMappingConfiguration searchMappingConfiguration) {
		this.searchMappingConfiguration = searchMappingConfiguration;
	}
		
}
