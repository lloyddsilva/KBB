package edu.cmu.lloyddsilva.server;

import java.net.Socket;
import java.util.Properties;

import edu.cmu.lloyddsilva.dto.Instruction;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class ServerSocketClient extends DefaultSocketClient {
    private Object clientInstObj;
    private boolean isRunning = true;
    
	public ServerSocketClient(String strHost, int iPort) {
		super(strHost, iPort);
	}
	
	public ServerSocketClient(Socket socket) {
		super(socket);
	}
	
	@Override
	public void run(){
		System.out.println("starting in ssc ..");
		isRunning = true;
		if (openConnection()){
			handleSession();
			closeSession();
		}
	}
	
	@Override
	public void handleSession() {
		if (DEBUG) System.out.println ("Handling session within ServerSocketClient");
		
		try {
			if(isRunning) {
				while ( (clientInstObj = readInput()) != null)
					handleInput (clientInstObj);
			} else {
				return;
			}
		}
		catch (Exception e){
			closeSession();
			isRunning = false;
			if (DEBUG) System.out.println ("Closing Session ..");
		}
	}
	
	@Override
	public void handleInput(Object clientInstObj){
		BuildCarModelOptions bcmo = new BuildCarModelOptions();
		if(clientInstObj instanceof Instruction) {
			Instruction clientInst = (Instruction) clientInstObj;
			switch(clientInst.getCommand()) {
			case ADD_A_MODEL:
				Properties prop = (Properties) clientInst.getRequest();
				bcmo.ingestProperties(prop);
				clientInst.setComment("Successfully imported properties");
				sendOutput(clientInst);
				break;
			case GET_ALL_MODELS:
				clientInst.setResponse(bcmo.listModels());
				sendOutput(clientInst);
				break;
			case GET_A_MODEL:
				String modelname = (String) clientInst.getRequest();
				clientInst.setResponse(bcmo.getModel(modelname));
				sendOutput(clientInst);
				break;
	
			case QUIT:
				System.out.println("Client Disconnected .. ");
				isRunning = false;
				break;
				
			case BLANK:
			default:
				break;
				
			}
		}
	}

}
