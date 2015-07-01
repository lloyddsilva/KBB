package edu.cmu.lloyddsilva.adapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

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
		} else {
			System.out.println("The model name does not exist");
		}
	}

	public void updateOptionPrice(String modelName, String optionSetName,
			String optionName, double newPrice) {
		Automobile auto = autos.get(modelName);
		if(auto != null) {
			auto.setOption(optionSetName, optionName, newPrice);
		} else {
			System.out.println("The model name does not exist");
		}
	}
	
	public void editOptionSetName(String modelName, String optionSetName,
			String newOptionSetName) {
		Automobile auto = autos.get(modelName);
		if(auto != null) {
			auto.updateOptionSetName(optionSetName, newOptionSetName);
		} else {
			System.out.println("The model name does not exist");
		}
	}

	public void editOptionPrice(String modelName, String optionSetName,
			String optionName, double newPrice) {
		Automobile auto = autos.get(modelName);
		if(auto != null) {
			auto.setOption(optionSetName, optionName, newPrice);
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
