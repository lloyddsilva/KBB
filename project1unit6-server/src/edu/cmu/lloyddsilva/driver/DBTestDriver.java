package edu.cmu.lloyddsilva.driver;

import edu.cmu.lloyddsilva.adapter.BuildAuto;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class DBTestDriver {
	
	//Test command line database insert, update, deletes
	public static void main(String[] args) {
		BuildAuto buildAuto = new BuildAuto();
		
		//Test creating two autos via LinkedHashMap with optionsets and options, propagates to database
		buildAuto.buildAuto("ford_properties.txt", "properties");
		buildAuto.buildAuto("toyota_properties.txt", "properties");
		
		//Test updating optionset name
		buildAuto.updateOptionSetName("Ford Focus Wagon ZTW", "Color", "Shade");
		
		//Test updating option name
		buildAuto.updateOptionName("Ford Focus Wagon ZTW", "Transmission", "Manual", "Stick", -815);
		
		//Test updating option price
		buildAuto.updateOptionPrice("Ford Focus Wagon ZTW", "Transmission", "Automatic", 300.00);
		
		//Test deleting auto from LinkedHashMap and onwards to database
		buildAuto.deleteAuto("Ford Focus Wagon ZTW");
		buildAuto.deleteAuto("Toyota Corolla");
	}

}
