package edu.cmu.lloyddsilva.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class OptionSetDAO extends BaseDAO {
	public int saveOptionSet(int autoId, String name) {
		int opSetId = 0;
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.OPSET_CREATE_AN_OPTION_SET,  Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, autoId);
			stmt.setString(2, name);

			System.out.println(stmt);
			int rowCount = stmt.executeUpdate();
			System.out.println("Statement executed, and " + rowCount + " rows inserted.");
			
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	opSetId = generatedKeys.getInt(1);
	            }
	            else {
	                throw new SQLException("Creating option set failed, no ID obtained.");
	            }
	        }
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			
		}

		return opSetId;
	}
	
	public int getOptionSetId(int autoId, String name) {
		int opSetId = 0;
		
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.OPSET_GET_OPSET_ID);) {
			stmt.setInt(1, autoId);
			stmt.setString(2, name);
			
			System.out.println(stmt);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					ResultSetMetaData rsmd = rs.getMetaData();
					int colCount = rsmd.getColumnCount();
					if(colCount >=1) opSetId = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return opSetId;
	}
	
	public void updateOptionSet(String make, String model, String oldName, String newName) {
		AutomobileDAO autoDao = new AutomobileDAO();
		int autoId = autoDao.getAutoId(make, model);
		
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.OPSET_UPDATE_AN_OPTION_SET,  Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, newName);
			stmt.setInt(2, autoId);
			stmt.setString(3, oldName);
			
			System.out.println(stmt);
			int rowCount = stmt.executeUpdate();
			System.out.println("Statement executed, and " + rowCount + " rows updated.");
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			
		}
	}
	
	public void deleteOptionSet(int autoId, String name) {
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.OPSET_DELETE_AN_OPTION_SET);) {
			stmt.setInt(1, autoId);
			stmt.setString(2, name);
			
			System.out.println(stmt);
			int rowCount = stmt.executeUpdate();
			System.out.println("Statement executed, and " + rowCount + " rows deleted.");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
