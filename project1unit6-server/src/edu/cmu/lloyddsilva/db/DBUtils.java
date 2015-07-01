package edu.cmu.lloyddsilva.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class DBUtils {
	private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException {
		if(connection == null || (connection != null && connection.isClosed())) {
		    try {
				// This will load the MySQL driver, each DB has its own driver
				Class.forName("com.mysql.jdbc.Driver");
				
				// Setup the connection with the DB
			    connection = DriverManager.getConnection(DBProperties.DB_CONN_URL + "?user=" + DBProperties.DB_USERNAME + "&password=" + DBProperties.DB_PASSWORD);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
}
