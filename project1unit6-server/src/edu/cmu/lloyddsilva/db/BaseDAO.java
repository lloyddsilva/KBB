package edu.cmu.lloyddsilva.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class BaseDAO {
	protected Connection getConnection() throws SQLException {
		return DBUtils.getConnection();
	}
	
	protected void executeStatement(PreparedStatement stmt) throws SQLException {
		int rowUpdated = stmt.executeUpdate();
		System.out.println(rowUpdated + " row(s) updated");
	}
	
	protected void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Error when closing ResultSet" + e);
			}
		}
	}

	
}
