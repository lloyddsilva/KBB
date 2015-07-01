package edu.cmu.lloyddsilva.server;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public interface SocketClientInterface {
	boolean openConnection();
    void handleSession();
    void closeSession();
}
