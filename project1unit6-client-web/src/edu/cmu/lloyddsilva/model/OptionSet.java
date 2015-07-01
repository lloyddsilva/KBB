package edu.cmu.lloyddsilva.model;

import java.io.Serializable;
import java.util.ArrayList;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
//OptionSet class with protected Option inner class
public class OptionSet implements Serializable {

	private static final long serialVersionUID = -2817620390965777058L;
	
	private String name;
	private ArrayList<Option> options;
	private Option choice;
	
	protected OptionSet(String name) {
		super();
		this.name = name;
		options = new ArrayList<Option>();
	}

	protected OptionSet(String name, ArrayList<Option> options) {
		super();
		this.name = name;
		this.options = options;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected ArrayList<Option> getOptions() {
		return options;
	}

	protected void setOptions(ArrayList<Option> options) {
		this.options = options;
	}
	
	protected Option getChoice() {
		return choice;
	}

	protected void setChoice(Option choice) {
		this.choice = choice;
	}

	protected void addOption(String name, double price) {
		options.add(new Option(name, price));
	}
	
	protected Option getOption(String name) {
		for(int i=0 ; i<options.size(); i++)
		{
			Option optionAtIndex = options.get(i);
			if(optionAtIndex != null && optionAtIndex.getName() != null &&  optionAtIndex.getName().equals(name)) {
				return optionAtIndex;
			}
		}
		return null;
	}
	
	protected Option getOption(int index) {
		if(index < options.size())
			return options.get(index);
		else return null;
	}
	
	protected int getOptionIndex(String name) {
		for(int i=0 ; i<options.size(); i++)
		{
			Option optionAtIndex = options.get(i);
			if(optionAtIndex != null && optionAtIndex.getName() != null &&  optionAtIndex.getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	protected void updateOption(String name, double price) {
		Option selectedOption = getOption(name);
		selectedOption.setPrice(price);
	}
	
	protected boolean deleteOption(String name) {
		int index = getOptionIndex(name);
		if(index >= 0)
		{
			options.remove(index);
			return true;
		}
		return false;
	}	

	@Override
	public synchronized String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OptionSet [name=").append(name).append(", options=")
				.append(options).append(", choice=").append(choice).append("]");
		return builder.toString();
	}
	
	//Protected Option inner class
	protected class Option implements Serializable {
		
		private static final long serialVersionUID = -1432140875525900134L;
		
		private String name;
		private double price;

		protected Option(String name, double price) {
			super();
			this.name = name;
			this.price = price;
		}

		protected String getName() {
			return this.name;
		}
		
		protected void setName(String name) {
			this.name = name;
		}
		
		protected double getPrice() {
			return this.price;
		}
		
		protected void setPrice(double price) {
			this.price = price;
		}

		@Override
		public synchronized String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Option [name=").append(this.name).append(", price=")
					.append(this.price).append("]");
			return builder.toString();
		}
	}
}
