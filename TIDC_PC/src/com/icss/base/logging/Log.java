package com.icss.base.logging;


public interface Log extends org.apache.commons.logging.Log {

	public boolean isDebugEnabled();

	public boolean isErrorEnabled();

	public boolean isFatalEnabled();

	public boolean isInfoEnabled();

	public boolean isTraceEnabled();

	public boolean isWarnEnabled();

	public void trace(Object message);

	public void trace(Object message, Throwable t);

	public void debug(Object message);

	public void debug(Object message, Throwable t);

	public void info(Object message);

	public void info(Object message, Throwable t);

	public void warn(Object message);

	public void warn(Object message, Throwable t);

	public void error(Object message);

	public void error(Object message, Throwable t);

	public void fatal(Object message);

	public void fatal(Object message, Throwable t);

}
