package com.imadp.service.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;

/**
 * ILogEntryAppender
 * 
 * A logback appender which writes log messages to the database.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public interface LogEntryAppender extends Appender<ILoggingEvent> {	
	
}
