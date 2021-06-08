package com.icss.base.logging;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class LogFactory {
	private static String PROPERTIESFILENAME = "log4j";

	private static LogFactory factoryInstance = null;

	private Hashtable instances = new Hashtable();

	private static Properties p = null;

	/**
	 * Protected constructor that is not available for public use.
	 */
	protected LogFactory() {
	}

	public void release() {
		synchronized (instances) {
			instances.clear();
		}
	}

	public static LogFactory getFactory() {
		if (factoryInstance == null) {
			factoryInstance = new LogFactory();
			try {
				if (p == null) {
					p = new Properties();
					ResourceBundle resources;
					try {
						resources = ResourceBundle.getBundle(
								PROPERTIESFILENAME, new Locale("zh", "CN"));
					} catch (RuntimeException e) {
						resources = ResourceBundle.getBundle("log4j",
								new Locale("zh", "CN"));
					}
					Enumeration keys = resources.getKeys();
					while (keys.hasMoreElements()) {
						String key = (String) keys.nextElement();
						String value = resources.getString(key);
						if (value != null && key != null) {
							if (key.endsWith(".File")) {
								File file = new File(value);
								if (file != null && !file.exists()) {
									createDir(file.getParent());
								}
							} else if (key.endsWith("Dir")) {
								createDir(value);
							}
							p.put(key, value);
						}
					}
				}
				PropertyConfigurator.configure(p);
			} catch (Exception ex) {
				System.err.println("Log4j配置文件异常");
				ex.printStackTrace();
				BasicConfigurator.configure();
			}
		}
		return factoryInstance;
	}

	public static Log getLog(Object obj) {
		return getFactory().getInstance(obj);
	}

	@SuppressWarnings("unchecked")
	protected Log getInstance(Object obj) {
		Log instance = (Log) instances.get(obj);
		if (instance != null)
			return instance;
		if (obj instanceof String) {
			instance = new LogImplLog4j(Logger.getLogger((String) obj));
		} else if (obj instanceof Class) {
			instance = new LogImplLog4j(Logger.getLogger(((Class) obj)
					.getName()));
		} else {
			instance = new LogImplLog4j(Logger.getLogger(obj.getClass()
					.getName()));
		}
		instances.put(obj, instance);
		return instance;
	}


	public static String getPROPERTIESFILENAME() {
		return PROPERTIESFILENAME;
	}

	public static void setPROPERTIESFILENAME(String propertiesfilename) {
		PROPERTIESFILENAME = propertiesfilename;
		if (propertiesfilename == null || propertiesfilename.equals(""))
			factoryInstance = new LogFactory();
		else
			factoryInstance = null;
	}

	private static void createDir(String dir) {
		try {
			File directory = new File(dir);
			if (directory != null && !directory.exists()) {
				directory.mkdirs();
			}
		} catch (Exception e) {
		}
	}

}
