package edu.cmu.lloyddsilva.server;

import java.util.ArrayList;
import java.util.Properties;

import edu.cmu.lloyddsilva.adapter.BuildAuto;
import edu.cmu.lloyddsilva.model.Automobile;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class BuildCarModelOptions implements AutoServer {
	AutoServer as;
	
	protected BuildCarModelOptions() {
		super();
		as = new BuildAuto();
	}

	@Override
	public void ingestProperties(Properties props) {
		as.ingestProperties(props);
	}
	
	@Override
	public void deleteAuto(String modelname) {
		as.deleteAuto(modelname);
	}

	@Override
	public ArrayList<String> listModels() {
		return as.listModels();
	}

	@Override
	public Automobile getModel(String modelname) {
		return as.getModel(modelname);
	}
}
