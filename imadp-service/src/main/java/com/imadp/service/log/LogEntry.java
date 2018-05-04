package com.imadp.service.log;

import org.joda.time.DateTime;

import com.imadp.core.Property;
import com.imadp.dao.AbstractPersistable;


/**
 * LogEntry
 * 
 * Represents an entry in a log file, as well as any exception data associated with the entry.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class LogEntry extends AbstractPersistable {
	
	// static properties 
	public static final Property<LogEntry, String> MESSAGE = Property.of("message");		
	public static final Property<LogEntry, String> LOGGER = Property.of("logger");		
	public static final Property<LogEntry, String> THREAD = Property.of("thread");		
	public static final Property<LogEntry, String> LEVEL = Property.of("level");		
	public static final Property<LogEntry, String> THROWABLE_CAUSE_CLASS = Property.of("throwableCauseClass");		
	public static final Property<LogEntry, String> THROWABLE_CAUSE_MESSAGE = Property.of("throwableCauseMessage");		
	public static final Property<LogEntry, String> THROWABLE_CAUSE_SOURCE = Property.of("throwableCauseSource");		
	public static final Property<LogEntry, DateTime> TIMESTAMP = Property.of("timestamp");		
	
	// properties
    private String message;
    private String logger;
    private String thread;
    private String level;
    private String throwableCauseClass; 
    private String throwableCauseMessage;
    private String throwableCauseSource;
    private DateTime timestamp;
    
    // constructor
    public LogEntry() {
    	
    }
    
    // getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }
    
    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    
    public String getThrowableCauseClass() {
		return throwableCauseClass;
	}

	public void setThrowableCauseClass(String throwableCauseClass) {
		this.throwableCauseClass = throwableCauseClass;
	}

	public String getThrowableCauseMessage() {
		return throwableCauseMessage;
	}

	public void setThrowableCauseMessage(String throwableCauseMessage) {
		this.throwableCauseMessage = throwableCauseMessage;
	}

	public String getThrowableCauseSource() {
		return throwableCauseSource;
	}

	public void setThrowableCauseSource(String throwableCauseSource) {
		this.throwableCauseSource = throwableCauseSource;
	}

	public DateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

}