package edu.cmu.lloyddsilva.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import edu.cmu.lloyddsilva.model.Automobile;
import edu.cmu.lloyddsilva.model.OptionSet;
import edu.cmu.lloyddsilva.model.OptionSet.Option;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class AutomobileDAO extends BaseDAO {

	public int saveAuto(String make, String model, double price) {
		int autoId = 0;
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.AUTO_CREATE_AN_AUTO,  Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, make);
			stmt.setString(2, model);
			stmt.setDouble(3, price);

			System.out.println(stmt);
			int rowCount = stmt.executeUpdate();
			System.out.println("Statement executed, and " + rowCount + " rows inserted.");
			
			
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	autoId = generatedKeys.getInt(1);
	            }
	            else {
	                throw new SQLException("Creating automobile failed, no ID obtained.");
	            }
	        }
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			
		}

		return autoId;
	}
	
	public int saveAutoObject(Automobile auto) {
		int autoId = 0;
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.AUTO_CREATE_AN_AUTO,  Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, auto.getMake());
			stmt.setString(2, auto.getModel());
			stmt.setDouble(3, auto.getBasePrice());

			System.out.println(stmt);
			int rowCount = stmt.executeUpdate();
			System.out.println("Statement executed, and " + rowCount + " rows inserted.");
			
			
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	autoId = generatedKeys.getInt(1);
	            	
	            	for(OptionSet opset : auto.getOptionSets()) {
	            		OptionSetDAO opsetDao = new OptionSetDAO();
	            		int opsetId = opsetDao.saveOptionSet(autoId, opset.getName());  
	            		
	            		for (Option op : opset.getOptions()) {
	            			OptionDAO opDao = new OptionDAO();
	            			opDao.saveOption(opsetId, op.getName(), op.getPrice());
	            		}
	            	} 	
	            }
	            else {
	                throw new SQLException("Creating automobile failed, no ID obtained.");
	            }
	        }
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			
		}

		return autoId;
	}
	
	public int getAutoId(String make, String model) {
		int autoId = 0;
		
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.AUTO_GET_AUTO_ID);) {
			stmt.setString(1, make);
			stmt.setString(2, model);
			
			System.out.println(stmt);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					ResultSetMetaData rsmd = rs.getMetaData();
					int colCount = rsmd.getColumnCount();
					if(colCount >=1) autoId = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return autoId;
	}
	
	public int updateAuto(String oldMake, String oldModel, String newMake, String newModel, double price) {
		int autoId = 0;
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.AUTO_UPDATE_AN_AUTO,  Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, newMake);
			stmt.setString(2, newModel);
			stmt.setDouble(3, price);
			stmt.setString(4, oldMake);
			stmt.setString(5, oldModel);
			
			System.out.println(stmt);
			int rowCount = stmt.executeUpdate();
			System.out.println("Statement executed, and " + rowCount + " rows updated.");
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			
		}

		return autoId;
	}
	
	public void deleteAuto(String make, String model) {
		try (Connection conn = this.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL.AUTO_DELETE_AN_AUTO);) {
			stmt.setString(1, make);
			stmt.setString(2, model);
			
			System.out.println(stmt);
			int rowCount = stmt.executeUpdate();
			System.out.println("Statement executed, and " + rowCount + " rows deleted.");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
