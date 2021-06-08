package com.icss.base.logging;

import java.io.Serializable;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.icss.base.logging.Log;

public class LogImplLog4j implements Log, Serializable {

	private static final String FQCN = LogImplLog4j.class.getName();

	private static final boolean is12 = Priority.class
			.isAssignableFrom(Level.class);

	private transient Logger logger = null;

	private String name = null;

	public LogImplLog4j() {
	}

	public LogImplLog4j(String name) {
		this.name = name;
		this.logger = getLogger();
	}

	public LogImplLog4j(Logger logger) {
		this.name = logger.getName();
		this.logger = logger;
	}

	public void trace(Object message) {
		if (is12) {
			getLogger().log(FQCN, (Priority) Level.DEBUG, message,
					getThrowable(message));
		} else {
			getLogger().log(FQCN, Level.DEBUG, message, getThrowable(message));
		}
	}

	public void trace(Object message, Throwable t) {
		if (is12) {
			getLogger().log(FQCN, (Priority) Level.DEBUG, message, t);
		} else {
			getLogger().log(FQCN, Level.DEBUG, message, t);
		}
	}

	public void debug(Object message) {
		if (is12) {
			getLogger().log(FQCN, (Priority) Level.DEBUG, message,
					getThrowable(message));
		} else {
			getLogger().log(FQCN, Level.DEBUG, message, getThrowable(message));
		}
	}

	public void debug(Object message, Throwable t) {
		if (is12) {
			getLogger().log(FQCN, (Priority) Level.DEBUG, message, t);
		} else {
			getLogger().log(FQCN, Level.DEBUG, message, t);
		}
	}

	public void info(Object message) {
		if (is12) {
			getLogger().log(FQCN, (Priority) Level.INFO, message,
					getThrowable(message));
		} else {
			getLogger().log(FQCN, Level.INFO, message, getThrowable(message));
		}
	}

	public void info(Object message, Throwable t) {
		if (is12) {
			getLogger().log(FQCN, (Priority) Level.INFO, message, t);
		} else {
			getLogger().log(FQCN, Level.INFO, message, t);
		}
	}

	public void warn(Object message) {
		if (is12) {
			getLogger().log(FQCN, (Priority) Level.WARN, message,
					getThrowable(message));
		} else {
			getLogger().log(FQCN, Level.WARN, message, getThrowable(message));
		}
	}

	public void warn(Object message, Throwable t) {
		if (is12) {
			getLogger().log(FQCN, (Priority) Level.WARN, message, t);
		} else {
			getLogger().log(FQCN, Level.WARN, message, t);
		}
	}

	public void error(Object message) {
		if (is12) {
			getLogger().log(FQCN, (Priority) Level.ERROR, message,
					getThrowable(message));
		} else {
			getLogger().log(FQCN, Level.ERROR, message, getThrowable(message));
		}
	}

	public void error(Object message, Throwable t) {
		if (is12) {
			getLogger().log(FQCN, (Priority) Level.ERROR, message, t);
		} else {
			getLogger().log(FQCN, Level.ERROR, message, t);
		}
	}

	public void fatal(Object message) {
		if (is12) {
			getLogger().log(FQCN, (Priority) Level.FATAL, message,
					getThrowable(message));
		} else {
			getLogger().log(FQCN, Level.FATAL, message, getThrowable(message));
		}
	}

	public void fatal(Object message, Throwable t) {
		if (is12) {
			getLogger().log(FQCN, (Priority) Level.FATAL, message, t);
		} else {
			getLogger().log(FQCN, Level.FATAL, message, t);
		}
	}

	/**
	 * Check whether the Log4j Logger used is enabled for <code>DEBUG</code>
	 * priority.
	 */
	public boolean isDebugEnabled() {
		return getLogger().isDebugEnabled();
	}

	/**
	 * Check whether the Log4j Logger used is enabled for <code>ERROR</code>
	 * priority.
	 */
	public boolean isErrorEnabled() {
		if (is12) {
			return getLogger().isEnabledFor((Priority) Level.ERROR);
		}
		return getLogger().isEnabledFor(Level.ERROR);
	}

	/**
	 * Check whether the Log4j Logger used is enabled for <code>FATAL</code>
	 * priority.
	 */
	public boolean isFatalEnabled() {
		if (is12) {
			return getLogger().isEnabledFor((Priority) Level.FATAL);
		}
		return getLogger().isEnabledFor(Level.FATAL);
	}

	/**
	 * Check whether the Log4j Logger used is enabled for <code>INFO</code>
	 * priority.
	 */
	public boolean isInfoEnabled() {
		return getLogger().isInfoEnabled();
	}

	/**
	 * Check whether the Log4j Logger used is enabled for <code>TRACE</code>
	 * priority. For Log4J, this returns the value of
	 * <code>isDebugEnabled()</code>
	 */
	public boolean isTraceEnabled() {
		return getLogger().isDebugEnabled();
	}

	/**
	 * Check whether the Log4j Logger used is enabled for <code>WARN</code>
	 * priority.
	 */
	public boolean isWarnEnabled() {
		if (is12) {
			return getLogger().isEnabledFor((Priority) Level.WARN);
		}
		return getLogger().isEnabledFor(Level.WARN);
	}

	public Logger getLogger() {
		if (logger == null) {
			logger = Logger.getLogger(name);
		}
		return (this.logger);
	}

	private Throwable getThrowable(Object obj) {
		if (obj instanceof Throwable)
			return (Throwable) obj;
		return null;
	}

}
