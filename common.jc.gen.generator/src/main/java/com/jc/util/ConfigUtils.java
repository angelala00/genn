package com.jc.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


public class ConfigUtils {

	private static Configuration conf;
	static {
		try {
			conf = new PropertiesConfiguration("config.properties");
//			logger.info("sphinx server address is "+conf.getString("conf.sphinxserver")+":"+conf.getInt("conf.sphinxserverport"));
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	public static Configuration getConf() {
		return conf;
	}
	public static void setConf(Configuration conf) {
		ConfigUtils.conf = conf;
	}
	
}
