package edu.cmu.lloyddsilva.servlet;

import java.util.ArrayList;

import edu.cmu.lloyddsilva.client.ClientSocketClient;
import edu.cmu.lloyddsilva.dto.Instruction;
import edu.cmu.lloyddsilva.dto.InstructionType;
import edu.cmu.lloyddsilva.model.Automobile;

public class ServletClient extends ClientSocketClient {
	private static ServletClient instance;

	private ServletClient(String strHost, int iPort) {
		super(strHost, iPort);
	}
	
	public static ServletClient getInstance() {
		if(instance == null) {
			instance = new ServletClient("127.0.0.1", 4444);
			instance.start();
			try {
				instance.join(); //Wait for the socket connection to server to complete
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("CarConfigClient running and communicating to port: 4444");
		}
		return instance;
	}
	
	@Override
	public void run(){
		System.out.println("starting in servlet client ..");
		openConnection();
	}
	
	public ArrayList<String> getAvailableModels() {
		Instruction inst = new Instruction(InstructionType.GET_ALL_MODELS);
		instance.sendOutput(inst);
		Instruction output = (Instruction) instance.readInput();
		@SuppressWarnings("unchecked")
		ArrayList<String> models = (ArrayList<String>) output.getResponse();
		return models; 
	}
	
	public Automobile getModel(String modelname) {
		Instruction inst = new Instruction(InstructionType.GET_A_MODEL);
		inst.setRequest(modelname);
		instance.sendOutput(inst);
		Instruction output = (Instruction) instance.readInput();
		Automobile auto = (Automobile) output.getResponse();
		return auto;
	}
}
