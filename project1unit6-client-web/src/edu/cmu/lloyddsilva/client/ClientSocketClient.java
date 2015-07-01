package edu.cmu.lloyddsilva.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

import edu.cmu.lloyddsilva.dto.Instruction;
import edu.cmu.lloyddsilva.dto.InstructionType;
import edu.cmu.lloyddsilva.model.Automobile;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class ClientSocketClient extends DefaultSocketClient {
	private static final int UPLOADING = 0;
    private static final int SELECTING = 1;
    private static final int DONE = 2;
    private static final int EXIT = 3;

    private int state = UPLOADING;
    
	private CarModelOptionsIO cmoi;
	private SelectCarOption sco;
	private ArrayList<String> models = new ArrayList<String>();
	private Object serverInput;
	private BufferedReader userIn;
	private String userInput;
    
	public ClientSocketClient(String strHost, int iPort) {
		super(strHost, iPort);
		cmoi = new CarModelOptionsIO();
		sco = new SelectCarOption();
	}
	
	public ClientSocketClient(Socket socket) {
		super(socket);
		cmoi = new CarModelOptionsIO();
		sco = new SelectCarOption();
	}
	
	@Override
	public void run(){
		System.out.println("starting in csc ..");
		if (openConnection()){
			handleSession();
			closeSession();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void handleSession(){
		userIn = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			switch(state) {
				case EXIT:
					return;
					
				case UPLOADING:
					System.out.println("Please input a properties file name");
					try {
						userInput = userIn.readLine(); //read file name
						Properties props = cmoi.readPropertiesFile(userInput); //ingest properties
						Instruction inst_add = new Instruction(InstructionType.ADD_A_MODEL);
						inst_add.setRequest(props);
						sendOutput(inst_add); //send add command to server
						serverInput = readInput(); //Receive ack
						System.out.println("Successfully imported properties. Enter [upload] to send another or [get] to list all models.");
						try {
							userInput = userIn.readLine();
							if(userInput.equalsIgnoreCase("upload")) {
								state = UPLOADING;
							} else if (userInput.equalsIgnoreCase("get")){
								state = SELECTING;
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					break;
					
				case SELECTING:
					Instruction inst_get_all = new Instruction(InstructionType.GET_ALL_MODELS);
					sendOutput(inst_get_all);
					serverInput = readInput(); //Receive response
					models = ((ArrayList<String>)((Instruction) serverInput).getResponse());
					System.out.println("Please choose a model: " + models);
					try {
						userInput = userIn.readLine();
						Instruction inst_get = new Instruction(InstructionType.GET_A_MODEL);
						inst_get.setRequest(userInput);
						sendOutput(inst_get);
						serverInput = readInput(); //Receive response
						
						Automobile auto = (Automobile)((Instruction) serverInput).getResponse();
						if(auto != null) {
							System.out.println(auto);
							sco.setInMemoryAuto(auto);
							sco.configureAuto();
							sco.displayChoices();
						} else {
							System.out.println("Invalid choice. Please try again.");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					state = DONE;
					break;
					
				case DONE:
					System.out.println("Enter [upload] to send another or [get] to list all models or [quit] to exit.");
					try {
						userInput = userIn.readLine();
						if(userInput.equalsIgnoreCase("upload")) {
							state = UPLOADING;
						} else if (userInput.equalsIgnoreCase("get")){
							state = SELECTING;
						} else {
							Instruction inst_quit = new Instruction(InstructionType.QUIT);
							sendOutput(inst_quit);
							state = EXIT;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
					
				default:
					return;
				
			}
		}
	}
	
}
