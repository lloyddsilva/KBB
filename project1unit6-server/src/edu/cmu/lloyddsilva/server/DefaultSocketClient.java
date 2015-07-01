package edu.cmu.lloyddsilva.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class DefaultSocketClient extends Thread implements SocketClientInterface, SocketClientConstants {

	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	private Socket sock;
	private String strHost;
	private int iPort;

	public DefaultSocketClient(String strHost, int iPort) {       
		setPort (iPort);
		setHost (strHost);
	}
	
	public DefaultSocketClient(Socket socket) {       
		setSock (socket);
	}

	public void run(){
		System.out.println("starting in dsc ..");
		if (openConnection()){
			handleSession();
			closeSession();
		}
	}
	
	public boolean openConnection() {

		try {
			if(sock == null) sock = new Socket(strHost, iPort);
		}
		catch(IOException socketError){
			if (DEBUG) System.err.println
			("Unable to connect to " + strHost);
			return false;
		}
		try {
			writer = new ObjectOutputStream(sock.getOutputStream());
			writer.flush();
			reader = new ObjectInputStream(sock.getInputStream());
		}
		catch (Exception e){
			if (DEBUG) System.err.println
			("Unable to obtain stream to/from " + strHost);
			return false;
		}
		return true;
	}
	
	public void handleSession(){
		String strInput = "";
		if (DEBUG) System.out.println ("Handling session with "
				+ strHost + ":" + iPort);
		try {
			while ( (strInput = (String) reader.readObject()) != null)
				handleInput (strInput);
		}
		catch (IOException e){
			if (DEBUG) System.out.println ("Error handling session with "
					+ strHost + ":" + iPort);
		} catch (ClassNotFoundException e) {
			if (DEBUG) System.out.println ("Error handling session with "
					+ strHost + ":" + iPort);
		}
	}       

	public void sendOutput(Object objOutput){
		try {
			writer.writeObject(objOutput);
			writer.flush();
		}
		catch (IOException e){
			if (DEBUG) System.out.println 
			("Error writing to " + strHost);
		}
	}
	
	public Object readInput() { 
		try {
			return reader.readObject();
		}
		catch (EOFException e) {
			closeSession();
		}
		catch (IOException e){
			if (DEBUG) System.out.println ("Error reading from " + strHost);
		} catch (ClassNotFoundException e) {
			if (DEBUG) System.out.println ("Error reading from " + strHost);
		}
		return new Object();
	}
	
	public void handleInput(Object objInput){
		System.out.println(objInput);
	}       

	public void closeSession(){
		try {
			writer = null;
			reader = null;
			sock.close();
		}
		catch (IOException e){
			if (DEBUG) System.err.println
			("Error closing socket to " + strHost);
		}       
	}

	public void setHost(String strHost){
		this.strHost = strHost;
	}

	public void setPort(int iPort){
		this.iPort = iPort;
	}
	
	public void setSock(Socket sock){
		this.sock = sock;
	}
}
