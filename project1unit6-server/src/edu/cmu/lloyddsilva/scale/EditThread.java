package edu.cmu.lloyddsilva.scale;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public interface EditThread {
	public void editOptionSetName(String modelName, String optionSetName, String newOptionSetName);
	public void editOptionPrice(String modelName, String optionSetName, String optionName, double newPrice);
}
