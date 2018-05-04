package com.imadp.service.log;

import java.util.Collection;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.helpers.MessageFormatter;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;
import com.imadp.core.AbstractSerializable;


/**
 * PersistableLog
 *
 * A log that stores its entries in memory and can be represented in json form for persistence.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class PersistableLog extends AbstractSerializable {

	/**
	 * Returns a PersistableLog from a json string.
	 *
	 * @param json
	 * @return PersistableLog
	 */
	public static PersistableLog fromJson(String json) {
		if(json == null)
			return new PersistableLog();

		List<String> persistableLogs = GSON.fromJson(json, new TypeToken<Collection<String>>(){}.getType());
		return new PersistableLog(persistableLogs);
	}

	// properties
	private List<String> persistableLogs;

	// constructor
	public PersistableLog() {
		this(Lists.<String>newArrayList());
	}

	// constructor
	public PersistableLog(List<String> persistableLogs) {
		this.persistableLogs = persistableLogs;
	}

	/**
	 * Logs a message with optional object parameters.
	 *
	 * @param message
	 * @param objects
	 */
	public synchronized void log(String message, Object... objects) {
		persistableLogs.add(new DateTime()+" - " + MessageFormatter.arrayFormat(message, objects).getMessage());
	}

	/**
	 * Returns a json representation of the log.
	 *
	 * @return String
	 */
	public synchronized String toJson() {
		return GSON.toJson(persistableLogs);
	}

	// getters and setters
	public synchronized List<String> getPersistableLogs() {
		return ImmutableList.copyOf(persistableLogs);
	}

}
