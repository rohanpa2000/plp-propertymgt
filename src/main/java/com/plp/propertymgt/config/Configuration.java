package com.plp.propertymgt.config;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;


public class Configuration {
	
	public static String DB_URL;
	public static String DB_USER;
	public static String DB_PWD;
	
 	static {
		try (InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream("config.properties")){
			Properties prop = new Properties();
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file config.properties not found in the classpath");
			}
 
			//define all the config properties to be loaded here
			DB_URL = prop.getProperty("dburl");
			DB_USER = prop.getProperty("dbuser");
			DB_PWD = prop.getProperty("dbpwd");
 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
}
