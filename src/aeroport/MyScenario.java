package aeroport;

import java.util.List;

import engine.GenericSimEvent;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.engine.EntiteSimulee;
import enstabretagne.engine.Scenario;
import enstabretagne.engine.ScenarioInitData;
import enstabretagne.engine.SimEvent;
import enstabretagne.engine.SimuEngine;

public class MyScenario extends Scenario{

	
	public MyScenario(SimuEngine engine, ScenarioInitData init) {
		super(engine, init);
		// TODO Auto-generated constructor stub
		iniAirport = (InitScenarioAERO) init;
	}

	InitScenarioAERO iniAirport;
	


	//-------------------
	
	

	public void Post(LogicalDateTime d,Runnable r) {
		Post(new GenericSimEvent(d, r));
	}
	
	public void Post(LogicalDuration d,Runnable r) {
		Post(new GenericSimEvent(getEngine().SimulationDate().add(d), r));
	}

	@Override
	public void creerEntitesSimulees() {
		// TODO Auto-generated method stub
		new Aeroport(getEngine(), iniAirport);
		
	}
	
	
	

}
