package com.imadp.dao.criteria;

import java.io.Serializable;


/**
 * Results
 * 
 * Encapsulates the parameters used to determine the specific set of results to return.
 *
 * @note This class is immutable and thread-safe.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public final class Results implements Serializable {
	
	// static reusable results
	public static final Results ONE = new Results(0, 1);
	public static final Results ALL = new Results(0, 0);
	
	// properties
	private final long firstResult;
	private final long maxResults;
	
	// constructor
	public Results(long firstResult, long maxResults) {
		this.firstResult = firstResult;
		this.maxResults = maxResults;
	}
	
	/**
	 * Returns true if a firstResult is present, false otherwise.
	 * 
	 * @return boolean
	 */
	public boolean hasFirstResult() {
		return firstResult != 0;
	}
	
	/**
	 * Returns true if maxResults are present, false otherwise.
	 * 
	 * @return boolean
	 */
	public boolean hasMaxResults() {
		return maxResults != 0;
	}
	
	// getters and setters
	public long getFirstResult() {
		return firstResult;
	}

	public long getMaxResults() {
		return maxResults;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (firstResult ^ (firstResult >>> 32));
		result = prime * result + (int) (maxResults ^ (maxResults >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Results other = (Results) obj;
		if (firstResult != other.firstResult)
			return false;
		if (maxResults != other.maxResults)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		if(!hasFirstResult() && !hasMaxResults())
			return "ALL";
		
		return new StringBuilder()
		.append(firstResult)
		.append('-')
		.append(maxResults)
		.toString();
	}
		
}
