package edu.cmu.lloyddsilva.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class CarModelOptionsIO {
	
	public Properties readPropertiesFile(String filename) {
		Properties props= new Properties(); 
		FileInputStream in = null;
		
		try {
			in = new FileInputStream(filename);
			props.load(in);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return props;
	}
	
	
			
}
