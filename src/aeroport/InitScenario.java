package aeroport;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.engine.ScenarioInitData;

public class InitScenario extends ScenarioInitData{
	
	double freq;

	public InitScenario(String name, int graine, LogicalDateTime debut, LogicalDateTime fin, double freq) {
		super(name, graine, debut, fin);
		this.freq = freq;
	}
	
	public double getFreq() {
		return freq;
	}

}
