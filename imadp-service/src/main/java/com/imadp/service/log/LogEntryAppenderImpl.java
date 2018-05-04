package com.imadp.service.log;

import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.filter.ThresholdFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.AppenderBase;

import com.imadp.service.PersistableService;

/**
 * LogEntryAppenderImpl
 *
 * A LogEntry appender which persists all log events through the ILogEntryService.
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public class LogEntryAppenderImpl extends AppenderBase<ILoggingEvent>
	implements LogEntryAppender, ApplicationListener<ContextRefreshedEvent> {

	// logger
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	// properties
	private Level level;
	private PersistableService<LogEntry> logEntryService;

	/**
	 * Initialization hook.
	 *
	 */
	public void init() {
		Validate.notNull(logEntryService);
		Validate.notNull(level);

		// add threshold filter
		ThresholdFilter thresholdFilter = new ThresholdFilter();
		thresholdFilter.setLevel(level.toString());
		thresholdFilter.start();
		addFilter(thresholdFilter);

		// start the appender
		start();
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("Adding LogEntryAppender to root logger");

		// add the appender
		((ch.qos.logback.classic.Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)).addAppender(this);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	protected void append(ILoggingEvent eventObject) {
        LogEntry logEntry = new LogEntry();

        logEntry.setMessage(eventObject.getFormattedMessage());
        logEntry.setLogger(eventObject.getLoggerName());
        logEntry.setThread(eventObject.getThreadName());
        logEntry.setLevel(eventObject.getLevel().toString());
        logEntry.setTimestamp(new DateTime(eventObject.getTimeStamp()));

        appendThrowableCause(logEntry, eventObject.getThrowableProxy());

        // save the log entry
        logEntryService.save(logEntry);
	}

	/**
	 * Recursively append the throwable cause, or return if there is no cause.
	 *
	 * @param logEntry
	 * @param throwableProxy
	 */
	private void appendThrowableCause(LogEntry logEntry, IThrowableProxy throwableProxy) {

		// if there is no cause, simply return
		if(throwableProxy == null)
			return;

		// if there is a nested cause, recursively retrieve it
		if(throwableProxy.getCause() != null)
		{
			appendThrowableCause(logEntry, throwableProxy.getCause());
			return;
		}

		// append the details
		logEntry.setThrowableCauseClass(throwableProxy.getClassName());
		logEntry.setThrowableCauseMessage(throwableProxy.getMessage());

		// append the source if available and substring the "at " away
		if(throwableProxy.getStackTraceElementProxyArray().length > 0)
			logEntry.setThrowableCauseSource(
					throwableProxy.getStackTraceElementProxyArray()[0].toString().substring(3));
	}

	// getters and setters
	public PersistableService<LogEntry> getLogEntryService() {
		return logEntryService;
	}

	public void setLogEntryService(PersistableService<LogEntry> logEntryService) {
		this.logEntryService = logEntryService;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}