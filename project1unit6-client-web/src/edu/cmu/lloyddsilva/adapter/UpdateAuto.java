package edu.cmu.lloyddsilva.adapter;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public interface UpdateAuto {
	public void updateOptionSetName(String modelName, String optionSetName, String newOptionSetName);
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, double newPrice);
}
