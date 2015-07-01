package edu.cmu.lloyddsilva.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import edu.cmu.lloyddsilva.exceptions.AutoException;
import edu.cmu.lloyddsilva.exceptions.ErrorCodes;
import edu.cmu.lloyddsilva.model.Automobile;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
//Utilities to read and build an automobile
public class IOUtilities {
	public Automobile buildAutomobileFromTextFile(String filename) throws AutoException {
		BufferedReader br = null;
		Automobile automobile = new Automobile();
		String delims = "[{}:,]+";
		 
		try {
			String baseDetails;
			String optionDetails;
			try {
				br = new BufferedReader(new FileReader(filename));
			} catch (FileNotFoundException fnfe) {
					throw new AutoException(ErrorCodes.FILE_NOT_FOUND);
			}
			
			
			//Parse the header with the auto name and base price
			if ((baseDetails = br.readLine()) != null) {
				String base_details[] = baseDetails.split(delims);
				automobile = new Automobile(base_details[0], base_details[1], Double.parseDouble(base_details[2]));
			
				//Parse all the option sets and options
				while ((optionDetails = br.readLine()) != null) {
					String option_details[] = optionDetails.split(delims);
					String option_set_name = option_details[0];
					automobile.addOptionSet(option_set_name, Arrays.copyOfRange(option_details, 1, option_details.length));
				}
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
				
		return automobile;
	}
	
	public Automobile buildAutomobileFromPropertiesFile(String filename) throws AutoException {
		Properties props= new Properties(); 
		FileInputStream in = null;
		Automobile automobile = new Automobile();
		try {
			in = new FileInputStream(filename);
			props.load(in);
			
			String carMake = props.getProperty("CarMake");
			if(!carMake.equals(null)) {
				String carModel = props.getProperty("CarModel");
				String carBasePrice = props.getProperty("CarBasePrice");
				automobile = new Automobile(carMake, carModel, Double.parseDouble(carBasePrice));
				
				int opSetCounter = 1;
				
				while(true) {
					String currentOptionSet = props.getProperty("CarOptionSet"+opSetCounter);
					if (currentOptionSet != null) {
						automobile.addOptionSet(currentOptionSet);
						int opCounter = 1;
						while(true) {
							String currentOption = props.getProperty("CarOptionSet"+opSetCounter+"Option"+opCounter);
							if (currentOption != null) {
								String currentOptionPrice = props.getProperty("CarOptionSet"+opSetCounter+"Option"+opCounter+"Price");
								automobile.addOption(currentOptionSet, currentOption, Double.parseDouble(currentOptionPrice));	
								opCounter++;
							} else {
								break;
							}
						}
						opSetCounter++;
					} else {
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			throw new AutoException(ErrorCodes.FILE_NOT_FOUND);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return automobile;
		
	}
	
	public Automobile buildAutomobileFromPropertiesObject(Properties props) {
		Automobile automobile = new Automobile();
		try {
			
			String carMake = props.getProperty("CarMake");
			if(!carMake.equals(null)) {
				String carModel = props.getProperty("CarModel");
				String carBasePrice = props.getProperty("CarBasePrice");
				automobile = new Automobile(carMake, carModel, Double.parseDouble(carBasePrice));
				
				int opSetCounter = 1;
				
				while(true) {
					String currentOptionSet = props.getProperty("CarOptionSet"+opSetCounter);
					if (currentOptionSet != null) {
						automobile.addOptionSet(currentOptionSet);
						int opCounter = 1;
						while(true) {
							String currentOption = props.getProperty("CarOptionSet"+opSetCounter+"Option"+opCounter);
							if (currentOption != null) {
								String currentOptionPrice = props.getProperty("CarOptionSet"+opSetCounter+"Option"+opCounter+"Price");
								automobile.addOption(currentOptionSet, currentOption, Double.parseDouble(currentOptionPrice));	
								opCounter++;
							} else {
								break;
							}
						}
						opSetCounter++;
					} else {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return automobile;
		
	}
}
