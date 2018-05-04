package com.imadp.web.sitemap;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/**
 * SiteMapUrl
 *
 * Provides a convenient object mapping to a sitemap url entry.
 *
 * @param <T>
 * @version 1.0
 * @author Anthony DePalma
 */
public class SiteMapUrl<T> implements Serializable  {

	// static properties
	private static final DateTimeFormatter LAST_MOD_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");

	// change frequency enums
	public enum ChangeFreq {
	    ALWAYS, HOURLY, DAILY, WEEKLY, MONTHLY, YEARLY, NEVER
	}

	private String loc;
	private String domain;
	private String priority;
	private DateTime lastMod;
	private ChangeFreq changeFreq;
	private Class<T> urlClass;

	// constructor
	public SiteMapUrl() {

	}

	/**
	 * Returns the full url by appending the domain and loc.
	 *
	 * @return String
	 */
	public String getFullURL() {
		return new StringBuilder().append(domain).append(loc).toString();
	}

	/**
	 * Returns the lastMod Date as a String.
	 *
	 * @return String
	 */
	public String getLastModAsString() {
		return LAST_MOD_FORMATTER.print(lastMod);
	}

	/**
	 * Returns the changeFreq as a String.
	 *
	 * @return String
	 */
	public String getChangeFreqAsString() {
		return changeFreq.toString().toLowerCase();
	}

	// getters and setters
	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public ChangeFreq getChangeFreq() {
		return changeFreq;
	}

	public void setChangeFreq(ChangeFreq changeFreq) {
		this.changeFreq = changeFreq;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public DateTime getLastMod() {
		return lastMod;
	}

	public void setLastMod(DateTime lastMod) {
		this.lastMod = lastMod;
	}

	public Class<T> getUrlClass() {
		return urlClass;
	}

	public void setUrlClass(Class<T> urlClass) {
		this.urlClass = urlClass;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((changeFreq == null) ? 0 : changeFreq.hashCode());
		result = prime * result + ((lastMod == null) ? 0 : lastMod.hashCode());
		result = prime * result + ((loc == null) ? 0 : loc.hashCode());
		result = prime * result
				+ ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result
				+ ((urlClass == null) ? 0 : urlClass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SiteMapUrl<?> other = (SiteMapUrl<?>) obj;
		if (changeFreq == null) {
			if (other.changeFreq != null)
				return false;
		} else if (!changeFreq.equals(other.changeFreq))
			return false;
		if (lastMod == null) {
			if (other.lastMod != null)
				return false;
		} else if (!lastMod.equals(other.lastMod))
			return false;
		if (loc == null) {
			if (other.loc != null)
				return false;
		} else if (!loc.equals(other.loc))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (domain == null) {
			if (other.domain != null)
				return false;
		} else if (!domain.equals(other.domain))
			return false;
		if (urlClass == null) {
			if (other.urlClass != null)
				return false;
		} else if (!urlClass.equals(other.urlClass))
			return false;
		return true;
	}

	@Override
	public String toString() {
	    return (new StringBuilder())
		.append("SiteMapURL [")
	    .append(super.toString())
	    .append("domain=").append(this.domain+", ")
	    .append("loc=").append(this.loc+", ")
	    .append("changeFreq=").append(this.changeFreq+", ")
	    .append("priority=").append(this.priority+", ")
	    .append("lastMod=").append(this.lastMod+", ")
	    .append("urlClass=").append(this.urlClass+", ")
	    .append("]").toString();
	}

}