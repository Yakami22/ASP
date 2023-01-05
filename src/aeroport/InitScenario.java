package aeroport;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.engine.ScenarioInitData;

public class InitScenario extends ScenarioInitData{
	
	private final double freq;
	private final InitAirplane initAirplane ;
	private final InitAirport initAirport ;

	public InitScenario(String name, int graine, LogicalDateTime debut, LogicalDateTime fin,
			double freq,InitAirplane initAirplane, InitAirport initAirport) {
		super(name, graine, debut, fin);
		this.freq = freq;
		this.initAirplane = initAirplane ;
		this.initAirport = initAirport ;
	}
	
	public double getFreq() {
		return freq;
	}

	public InitAirplane getInitAirplane() {
		return initAirplane;
	}

	public InitAirport getInitAirport() {
		return initAirport;
	}
	
	

}