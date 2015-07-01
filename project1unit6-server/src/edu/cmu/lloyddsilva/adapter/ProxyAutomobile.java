package edu.cmu.lloyddsilva.adapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

import edu.cmu.lloyddsilva.db.AutomobileDAO;
import edu.cmu.lloyddsilva.db.OptionDAO;
import edu.cmu.lloyddsilva.db.OptionSetDAO;
import edu.cmu.lloyddsilva.exceptions.AutoException;
import edu.cmu.lloyddsilva.model.Automobile;
import edu.cmu.lloyddsilva.util.IOUtilities;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public abstract class ProxyAutomobile {
	private static LinkedHashMap<String, Automobile> autos = new LinkedHashMap<String, Automobile>();
	
	public void buildAuto(String filename, String fileType) {
		IOUtilities io = new IOUtilities();
		Automobile auto = new Automobile();
		try {
			if(fileType.equalsIgnoreCase("text")) {
				auto = io.buildAutomobileFromTextFile(filename);
			} else if(fileType.equalsIgnoreCase("properties")) {
				auto = io.buildAutomobileFromPropertiesFile(filename);
			} 	
		} catch (AutoException ex) {
			try {
				String newFilename = fix(ex.getErrorCode());
				auto = io.buildAutomobileFromTextFile(newFilename);
			} catch (AutoException e) {
				e.printStackTrace();
			}
		}
		autos.put(auto.getName(), auto);
		
		//Save Automobile to database
		AutomobileDAO autoDao = new AutomobileDAO();
		autoDao.saveAutoObject(auto);
	}
	
	public void ingestProperties(Properties props) {
		IOUtilities io = new IOUtilities();
		Automobile auto = io.buildAutomobileFromPropertiesObject(props);
		autos.put(auto.getName(), auto);
		
		//Save Automobile to database
		AutomobileDAO autoDao = new AutomobileDAO();
		autoDao.saveAutoObject(auto);
	}
	

	public void deleteAuto(String modelname) {
		if(autos != null) {
			if(autos.containsKey(modelname)) {
				Automobile auto = autos.get(modelname);
				autos.remove(modelname);
				
				//Delete Automobile from database
				AutomobileDAO autoDao = new AutomobileDAO();
				autoDao.deleteAuto(auto.getMake(), auto.getModel());
			}
		}
	}
	
	public ArrayList<String> listModels() {
		if(autos!= null) {
			ArrayList<String> keys = new ArrayList<String>(autos.keySet());
			return keys;
		} else {
			return new ArrayList<String>();
		}
	}
	
	public String listModelsAsString() {
		if(autos!= null) {
			ArrayList<String> keys = new ArrayList<String>(autos.keySet());
			StringBuilder sb = new StringBuilder();
			for(String value:keys) {
				if(sb.length() < 1) {
					sb.append(value);
				} else {
					sb.append(", " + value);
				}
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	public Automobile getModel(String modelname) {
		if(autos != null) {
			if(autos.containsKey(modelname))
				return autos.get(modelname);
		}
		return null;
	}

	public void printAuto(String modelName) {
		Automobile auto = autos.get(modelName);
		if(auto != null) {
			System.out.println(auto);
		} else {
			System.out.println("The model name does not exist");
		}	
	}
	
	public void updateOptionSetName(String modelName, String optionSetName,
			String newOptionSetName) {
		Automobile auto = autos.get(modelName);
		if(auto != null) {
			auto.updateOptionSetName(optionSetName, newOptionSetName);
			
			OptionSetDAO opSetDao = new OptionSetDAO();
			opSetDao.updateOptionSet(auto.getMake(), auto.getModel(), optionSetName, newOptionSetName);
		} else {
			System.out.println("The model name does not exist");
		}
	}
	
	public void updateOptionName(String modelName, String optionSetName,
			String oldOptionName, String newOptionName, double price) {
		Automobile auto = autos.get(modelName);
		if(auto != null) {
			auto.setOptionName(optionSetName, oldOptionName, newOptionName);
			
			OptionDAO opDao = new OptionDAO();
			opDao.updateOption(auto.getMake(), auto.getModel(), optionSetName, oldOptionName, newOptionName, price);
		} else {
			System.out.println("The model name does not exist");
		}
	}

	public void updateOptionPrice(String modelName, String optionSetName,
			String optionName, double newPrice) {
		Automobile auto = autos.get(modelName);
		if(auto != null) {
			auto.setOptionPrice(optionSetName, optionName, newPrice);
			
			OptionDAO opDao = new OptionDAO();
			opDao.updateOption(auto.getMake(), auto.getModel(), optionSetName, optionName, optionName, newPrice);
		} else {
			System.out.println("The model name does not exist");
		}
	}
	
	public void editOptionSetName(String modelName, String optionSetName,
			String newOptionSetName) {
		Automobile auto = autos.get(modelName);
		if(auto != null) {
			auto.updateOptionSetName(optionSetName, newOptionSetName);
			
			OptionSetDAO opSetDao = new OptionSetDAO();
			opSetDao.updateOptionSet(auto.getMake(), auto.getModel(), optionSetName, newOptionSetName);
		} else {
			System.out.println("The model name does not exist");
		}
	}

	public void editOptionPrice(String modelName, String optionSetName,
			String optionName, double newPrice) {
		Automobile auto = autos.get(modelName);
		if(auto != null) {
			auto.setOptionPrice(optionSetName, optionName, newPrice);
			
			OptionDAO opDao = new OptionDAO();
			opDao.updateOption(auto.getMake(), auto.getModel(), optionSetName, optionName, optionName, newPrice);
		} else {
			System.out.println("The model name does not exist");
		}
	}

	public String fix(int errorCode) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		switch(errorCode) {
			case 101: 
				System.out.print("Please input the filename:");
				try {
					return br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			case 201: 
				System.out.print("Please input a base price:");
				try {
					return br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	
		return null;	
	}
	

}
