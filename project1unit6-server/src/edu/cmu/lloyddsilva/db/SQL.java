package edu.cmu.lloyddsilva.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class SQL {
	public static String AUTO_CREATE_AN_AUTO;
	public static String AUTO_GET_AUTO_ID;
	public static String AUTO_GET_AN_AUTO;
	public static String AUTO_GET_ALL_AUTOS;
	public static String AUTO_UPDATE_AN_AUTO;
	public static String AUTO_DELETE_AN_AUTO;
	public static String OPSET_CREATE_AN_OPTION_SET;
	public static String OPSET_GET_OPSET_ID;
	public static String OPSET_UPDATE_AN_OPTION_SET;
	public static String OPSET_DELETE_AN_OPTION_SET;
	public static String OPT_CREATE_AN_OPTION;
	public static String OPT_UPDATE_AN_OPTION;
	public static String OPT_DELETE_AN_OPTION;
	
	static {
		try (InputStream input = DBProperties.class.getClassLoader().getResourceAsStream("sql.properties");) {
			if (input != null) {
				// Load a properties file
				Properties prop = new Properties();
				prop.load(input);

				AUTO_CREATE_AN_AUTO = prop.getProperty("AUTO_CREATE_AN_AUTO");
				AUTO_GET_AUTO_ID = prop.getProperty("AUTO_GET_AUTO_ID");
				AUTO_GET_AN_AUTO = prop.getProperty("AUTO_GET_AN_AUTO");
				AUTO_GET_ALL_AUTOS = prop.getProperty("AUTO_GET_ALL_AUTOS");
				AUTO_UPDATE_AN_AUTO = prop.getProperty("AUTO_UPDATE_AN_AUTO");
				AUTO_DELETE_AN_AUTO = prop.getProperty("AUTO_DELETE_AN_AUTO");
				OPSET_CREATE_AN_OPTION_SET = prop.getProperty("OPSET_CREATE_AN_OPTION_SET");
				OPSET_GET_OPSET_ID = prop.getProperty("OPSET_GET_OPSET_ID");
				OPSET_UPDATE_AN_OPTION_SET = prop.getProperty("OPSET_UPDATE_AN_OPTION_SET");
				OPSET_DELETE_AN_OPTION_SET = prop.getProperty("OPSET_DELETE_AN_OPTION_SET");
				OPT_CREATE_AN_OPTION = prop.getProperty("OPT_CREATE_AN_OPTION");
				OPT_UPDATE_AN_OPTION = prop.getProperty("OPT_UPDATE_AN_OPTION");
				OPT_DELETE_AN_OPTION = prop.getProperty("OPT_DELETE_AN_OPTION");
			}
		} catch (IOException ex) {
			System.out.println("Error when loading properties file : " + ex);
		} 
	}
	
}
