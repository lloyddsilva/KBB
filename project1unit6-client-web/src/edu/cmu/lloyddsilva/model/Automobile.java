package edu.cmu.lloyddsilva.model;

import java.io.Serializable;
import java.util.ArrayList;

import edu.cmu.lloyddsilva.model.OptionSet.Option;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
//Automotive class with public functions to manipulate automotive, optionset and option
public class Automobile implements Serializable {

	private static final long serialVersionUID = -4428105997099843026L;

	private String make;
	private String model;
	private double basePrice;
	private ArrayList<OptionSet> optionSets;
	
	public Automobile() {
		super();
	}
	
	public Automobile(String make, String model, double basePrice) {
		super();
		this.make = make;
		this.model = model;
		this.basePrice = basePrice;
		optionSets = new ArrayList<OptionSet>();
	}

	public Automobile(String name, String model, double basePrice, ArrayList<OptionSet> optionSets) {
		super();
		this.make = name;
		this.model = model;
		this.basePrice = basePrice;
		this.optionSets = optionSets;
	}

	//Automobile methods
	public synchronized String getMake() {
		return make;
	}
	
	public synchronized void setMake(String make) {
		this.make = make;
	}
	
	public synchronized String getModel() {
		return model;
	}
	
	public synchronized void setModel(String model) {
		this.model = model;
	}
	
	public synchronized String getName() {
		return this.make + " " + this.model;
	}
	
	public synchronized double getBasePrice() {
		return basePrice;
	}
	
	public synchronized void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	
	//OptionSet methods
	public synchronized ArrayList<OptionSet> getOptionSets() {
		return optionSets;
	}
	
	public synchronized ArrayList<String> getOptionSetsAsString() {
		ArrayList<String> opSetStrings = new ArrayList<String>();
		for(OptionSet opSet : optionSets)
			opSetStrings.add(opSet.getName());
		return opSetStrings;
	}
	
	public synchronized void setOptionSets(ArrayList<OptionSet> optionSets) {
		this.optionSets = optionSets;
	}
	
	public synchronized void addOptionSet(String name, String[] options) {
		OptionSet newOptionSet = new OptionSet(name);
		for(int i=0; i<options.length; i=i+2) {
			newOptionSet.addOption(options[i], Integer.parseInt(options[i+1]));
		}
		optionSets.add(newOptionSet);
	}	
	
	public synchronized void addOptionSet(String name) {
		OptionSet newOptionSet = new OptionSet(name);
		optionSets.add(newOptionSet);
	}
	
	public synchronized OptionSet getOptionSet(String name) {
		for(int i=0 ; i<optionSets.size(); i++)
		{
			if(optionSets.get(i).getName().equals(name)) {
				return optionSets.get(i);
			}
		}
		return null;
	}
	
	public synchronized OptionSet getAndRemoveOptionSet(String name) {
		for(int i=0 ; i<optionSets.size(); i++)
		{
			if(optionSets.get(i).getName().equals(name)) {
				return optionSets.remove(i);
			}
		}
		return null;
	}
	
	public synchronized OptionSet getOptionSet(int index) {
		if(index < optionSets.size())
			return optionSets.get(index);
		else return null;
	}
	
	public synchronized OptionSet getAndRemoveOptionSet(int index) {
		if(index < optionSets.size())
			return optionSets.remove(index);
		else return null;
	}
	
	public synchronized int getOptionSetIndex(String name) {
		for(int i=0 ; i<optionSets.size(); i++)
		{
			if(optionSets.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	public synchronized void updateOptionSet(String name, int size, String[] options) {
		OptionSet selectedOptionSet = getAndRemoveOptionSet(name);
		selectedOptionSet = new OptionSet(name);
		for(int i=0; i<options.length; i=i+2) {
			selectedOptionSet.addOption(options[i], Double.parseDouble(options[i+1]));
		}
		optionSets.add(selectedOptionSet);
	}	
	
	public synchronized boolean updateOptionSetName(String optionSetName, String newOptionSetName) {
		for(int i=0 ; i<optionSets.size(); i++)
		{
			if(optionSets.get(i).getName().equals(optionSetName)) {
				optionSets.get(i).setName(newOptionSetName);
				
				return true;
			}
		}
		return false;
	}
	
	public synchronized boolean deleteOptionSet(String name) {
		OptionSet deletedOptionSet = getAndRemoveOptionSet(name);
		if(deletedOptionSet != null)
		{
			return true;
		}
		return false;
	}
	
	public synchronized String getOptionChoice(String name) {
		return getOptionSet(name).getChoice().getName();
	}
	
	public synchronized double getOptionChoicePrice(String name) {
		return getOptionSet(name).getChoice().getPrice();
	}
	
	public synchronized void setOptionChoice(String optionSetName, String choosenOption) {
		OptionSet selectedOptionSet = getOptionSet(optionSetName);
		selectedOptionSet.setChoice(selectedOptionSet.getOption(choosenOption));
	}
	
	public synchronized double getTotalPrice() {
		double totalPrice = getBasePrice();
		for(OptionSet optionSet:optionSets) {
			totalPrice += optionSet.getChoice().getPrice();
		}
		return totalPrice;
	}
	
	//Option methods
	public synchronized Option getOption(String optionSetName, String optionName) {
		OptionSet selectedOptionSet = getOptionSet(optionSetName);
		return selectedOptionSet.getOption(optionName);
	}
	
	public synchronized ArrayList<String> getOptionsAsString(String optionSetName) {
		OptionSet selectedOptionSet = getOptionSet(optionSetName);
		ArrayList<String> options = new ArrayList<String>();
		for(Option option : selectedOptionSet.getOptions())
			options.add(option.getName());
		return options;
	}
	
	public synchronized void addOption(String optionSetName, String optionName, double optionPrice) {
		OptionSet selectedOptionSet = getOptionSet(optionSetName);
		selectedOptionSet.addOption(optionName, optionPrice);		
	}
	
	public synchronized void setOption(String optionSetName, String optionName, double optionPrice) {
		OptionSet selectedOptionSet = getOptionSet(optionSetName);
		Option selectedOption = selectedOptionSet.getOption(optionName);
		selectedOption.setPrice(optionPrice);	
	}
	
	public synchronized void deleteOption(String optionSetName, String optionName) {
		OptionSet selectedOptionSet = getOptionSet(optionSetName);
		selectedOptionSet.deleteOption(optionName);
	}
	
	public synchronized String toStringChoicesOnly() {
		StringBuilder builder = new StringBuilder();
		builder.append("Automobile [make=").append(make).append(", model=")
				.append(model).append(", basePrice=")
				.append(basePrice);
		
		for(OptionSet opSet: optionSets) {
			builder.append(", " + opSet.getName() + "=");
			if(opSet.getChoice()!= null) {
				builder.append(opSet.getChoice().getName());
			}
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public synchronized String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Automobile [make=").append(make).append(", model=")
				.append(model).append(", basePrice=")
				.append(basePrice).append(", optionSets=")
				.append(optionSets).append("]");
		return builder.toString();
	}
	
	


}
