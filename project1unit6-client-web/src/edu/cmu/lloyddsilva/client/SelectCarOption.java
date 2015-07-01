package edu.cmu.lloyddsilva.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.cmu.lloyddsilva.model.Automobile;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class SelectCarOption {
	Automobile inMemoryAuto;
	
	protected SelectCarOption() {
		super();
		inMemoryAuto = new Automobile();
	}

	protected SelectCarOption(Automobile inMemoryAuto) {
		super();
		this.inMemoryAuto = inMemoryAuto;
	}

	public Automobile getInMemoryAuto() {
		return inMemoryAuto;
	}

	public void setInMemoryAuto(Automobile inMemoryAuto) {
		this.inMemoryAuto = inMemoryAuto;
	}

	public void configureAuto() {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> opSets = inMemoryAuto.getOptionSetsAsString();
		for(String opSet : opSets) {
			System.out.println("Select " + opSet);
			String choice;
			try {
				choice = stdin.readLine();
				inMemoryAuto.setOptionChoice(opSet, choice);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void displayChoices() {
		System.out.println("Here is your configured automobile: " + inMemoryAuto.toStringChoicesOnly());
	}
}
