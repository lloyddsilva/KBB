package edu.cmu.lloyddsilva.server;

import java.util.ArrayList;
import java.util.Properties;

import edu.cmu.lloyddsilva.model.Automobile;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public interface AutoServer {
	public void ingestProperties(Properties props);
	public void deleteAuto(String modelname);
	public ArrayList<String> listModels();
	public Automobile getModel(String modelname);
}
