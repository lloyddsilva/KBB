package edu.cmu.lloyddsilva.scale;

import edu.cmu.lloyddsilva.adapter.BuildAuto;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class EditOptions extends Thread {
	private String threadName;
	private String modelName;
	private String optionSetName;
	private String newOptionSetName;
	private String optionName;
	private double newOptionPrice;
	private int editOp = 0;
	
	//Constructor for editing the OptionSetName
	public EditOptions(String threadName, String modelName, String optionSetName,
			String newOptionSetName) {
		super();
		this.threadName = threadName;
		this.modelName = modelName;
		this.optionSetName = optionSetName;
		this.newOptionSetName = newOptionSetName;
		this.editOp = 1;
	}
	
	//Constructor for editing the OptionPrice
	public EditOptions(String threadName, String modelName, String optionSetName,
			String optionName, double newOptionPrice) {
		super();
		this.threadName = threadName;
		this.modelName = modelName;
		this.optionSetName = optionSetName;
		this.optionName = optionName;
		this.newOptionPrice = newOptionPrice;
		this.editOp = 2;
	}
	
	void randomWait() {
		try {
			Thread.currentThread();
			Thread.sleep((long) (3000 * Math.random()));
		} catch (InterruptedException e) {
			System.out.println("Interrupted!");
		}
	}
	
	@Override
	public void run() {
		BuildAuto buildAuto = new BuildAuto();
		
		switch(editOp) {
		case 1: randomWait(); //Randomly wait for a bit
				System.out.println("Thread::" + this.threadName + "::Setting old OptionSetName::" + this.optionSetName + "::to new OptionSetName::" +this.newOptionSetName);
				buildAuto.editOptionSetName(modelName, optionSetName, newOptionSetName);
				break;
		case 2: randomWait(); //Randomly wait for a bit
				System.out.println("Thread::"+this.threadName+ "::Setting OptionName::" + this.optionSetName + "::" + this.optionName + "::to Price::" +this.newOptionPrice);
				buildAuto.editOptionPrice(modelName, optionSetName, optionName, newOptionPrice);
				break;
		default: break;
		}
		
	}
	

}
