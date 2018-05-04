package com.imadp.dao.hibernate.criteria.search;

import java.util.Collections;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imadp.core.Property;
import com.imadp.dao.DaoException;
import com.imadp.dao.Persistable;
import com.imadp.dao.criteria.Results;

/**
 * SearchCriteriaExecutor
 *
 * The object designed to execute a search query after all criterion have been applied.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class SearchCriteriaExecutor<T extends Persistable> {

	// logger
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	// properties
	private Session session;
	private Results results;
	private String query;
	private Class<T> entityClass;
	private List<Property<T, String>> properties;

	// constructor
	public SearchCriteriaExecutor(Class<T> entityClass, Session session) {
		this.entityClass = entityClass;
		this.session = session;
	}

	/**
	 * Executes the search query.
	 *
	 * @throws DaoException if unable to parse the search criteria
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> search() {

		// if no query string was passed, return the empty list
		if(query == null)
			return Collections.emptyList();

		try
		{
			FullTextSession fullTextSession = Search.getFullTextSession(session);
			FullTextQuery fullTextQuery = createFullTextQuery(query, fullTextSession);

			fullTextQuery.setFirstResult((int) results.getFirstResult());

			if(results.hasMaxResults())
				fullTextQuery.setMaxResults((int) results.getMaxResults());

			return fullTextQuery.list();
		}
		catch (ParseException exception)
		{
			throw new DaoException("Error executing search with ["+this+"]", exception);
		}
	}

	/**
	 * Executes the search count query.
	 *
	 * @throws DaoException if unable to parse the search criteria
	 * @return long
	 */
	public long searchCount() {

		// if no query string was passed, return 0
		if(query == null)
			return 0;

		try
		{
			return createFullTextQuery(query, Search.getFullTextSession(session)).getResultSize();
		}
		catch (ParseException exception)
		{
			throw new DaoException("Error executing search count with ["+this+"]", exception);
		}
	}

	/**
	 * Creates a FullTextQuery from the SearchCriteria parameters.
	 *
	 * @param query
	 * @param fullTextSession
	 * @return FullTextQuery
	 * @throws ParseException
	 */
	private FullTextQuery createFullTextQuery(String query, FullTextSession fullTextSession)
			throws ParseException {

		// create fieldNames
		String[] fieldNames = new String[properties.size()];

		for(int i = 0; i < properties.size(); i++)
			fieldNames[i] = properties.get(i).getName();

		// create parser
		MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_31,
				fieldNames, new StandardAnalyzer(Version.LUCENE_31));

		// create query
		return fullTextSession.createFullTextQuery(parser.parse(query), entityClass);
	}

	// getters and setters
	public void setResults(Results results) {
		this.results = results;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setProperties(List<Property<T, String>> properties) {
		this.properties = properties;
	}

}
