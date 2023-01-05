package aeroport;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.engine.ScenarioInitData;

public class InitScenarioAERO extends ScenarioInitData{
	
	double freq;

	public InitScenarioAERO(String name, int graine, LogicalDateTime debut, LogicalDateTime fin, double freq) {
		super(name, graine, debut, fin);
		this.freq = freq;
	}
	
	public double getFreq() {
		return freq;
	}
	
	public void setName(String newname) {
		this.name = newname;
	}
}
