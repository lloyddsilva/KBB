package edu.cmu.lloyddsilva.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class DBProperties {
	public static String DB_CONN_URL;
	public static String DB_USERNAME;
	public static String DB_PASSWORD;
	
	
	static {
		try (InputStream input = DBProperties.class.getClassLoader().getResourceAsStream("connection.properties");) {
			if (input != null) {
				// Load a properties file
				Properties prop = new Properties();
				prop.load(input);

				DB_CONN_URL = prop.getProperty("dbConnURL");
				DB_USERNAME = prop.getProperty("dbUsername");
				DB_PASSWORD = prop.getProperty("dbPassword");
			}
		} catch (IOException ex) {
			System.out.println("Error when loading properties file : " + ex);
		} finally {
			initializeWithDefaultValuesIfNeeded();
		}
	}
	
	private static void initializeWithDefaultValuesIfNeeded() {
		if (DB_CONN_URL == null) {
			DB_CONN_URL = "jdbc:mysql://localhost:3306/automobile";
		}

		if (DB_USERNAME == null) {
			DB_USERNAME = "auto_user";
		}

		if (DB_PASSWORD == null) {
			DB_PASSWORD = "auto_pass";
		}
	}
}
