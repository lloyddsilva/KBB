package edu.cmu.lloyddsilva.driver;

import edu.cmu.lloyddsilva.client.ClientSocketClient;


//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class CarConfigClient {
	ClientSocketClient cccSocket;
	
	public CarConfigClient() {
		cccSocket = new ClientSocketClient("127.0.0.1", 4444);
		cccSocket.start();
		System.out.println("CarConfigClient running and communicating to port: 4444");
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		CarConfigClient client = new CarConfigClient();
	}
}
