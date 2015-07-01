package edu.cmu.lloyddsilva.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class OptionDAO extends BaseDAO {
	public int saveOption(int opSetId, String name, Double price) {
		int opId = 0;
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.OPT_CREATE_AN_OPTION,  Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, opSetId);
			stmt.setString(2, name);
			stmt.setDouble(3, price);

			System.out.println(stmt);
			int rowCount = stmt.executeUpdate();
			System.out.println("Statement executed, and " + rowCount + " rows inserted.");
			
			
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	opId = generatedKeys.getInt(1);
	            }
	            else {
	                throw new SQLException("Creating option failed, no ID obtained.");
	            }
	        }
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			
		}

		return opId;
	}
	
	public void updateOption(String make, String model, String optionSetName, String oldName, String newName, double price) {
		AutomobileDAO autoDao = new AutomobileDAO();
		int autoId = autoDao.getAutoId(make, model);
		
		OptionSetDAO opSetDao = new OptionSetDAO();
		int opSetId = opSetDao.getOptionSetId(autoId, optionSetName);
		
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.OPT_UPDATE_AN_OPTION,  Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, newName);
			stmt.setDouble(2, price);
			stmt.setInt(3, opSetId);
			stmt.setString(4, oldName);
			
			System.out.println(stmt);
			int rowCount = stmt.executeUpdate();
			System.out.println("Statement executed, and " + rowCount + " rows updated.");
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			
		}
	}
	
	public void deleteOption(int opSetId, String name) {
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.OPT_DELETE_AN_OPTION);) {
			stmt.setInt(1, opSetId);
			stmt.setString(2, name);
			
			System.out.println(stmt);
			int rowCount = stmt.executeUpdate();
			System.out.println("Statement executed, and " + rowCount + " rows deleted.");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
