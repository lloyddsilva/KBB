package edu.cmu.lloyddsilva.driver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import edu.cmu.lloyddsilva.server.ServerSocketClient;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class CarConfigServer {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private ServerSocketClient ccsClient;
	
	public CarConfigServer() {
		serverSocket = null;
		clientSocket = null;
		@SuppressWarnings("unused")
		ServerSocketClient ccsClient = null;
		
		try {
			serverSocket = new ServerSocket(4444);
			System.out.println("CarConfigServer is listening on port: 4444");
		} catch (IOException e) {
			System.err.println("Could not listen on port: 4444.");
			System.exit(1);
		}
	}
	
	public void listen() {
		clientSocket = null;
		ccsClient = null;
		
		while(true) {
			try {
				clientSocket = serverSocket.accept();
	            System.out.println("Accept passed.");
	            
				ccsClient = new ServerSocketClient(clientSocket);
	            System.out.println("ccsclient created.");
	            
	            ccsClient.start();
	            System.out.println("socket started.");
			} catch (IOException e) {
				System.err.println("Accept failed.");
				System.exit(1);
			}
		}
	}
	
	
	public static void main(String[] args) {
		CarConfigServer server = new CarConfigServer();
		server.listen();
	}
}
