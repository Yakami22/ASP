package aeroport;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.engine.SimuEngine;

public class Main {
	
	static public void main(String[] args) {
		
		// Logger.Load();
		
		Logger.DataSimple("nom de la feuille excel", "nom", 10, 30, 2e3);
		
		LogicalDateTime start = new LogicalDateTime("04/12/2019 14:00");
        LogicalDateTime end = new LogicalDateTime("04/12/2019 15:00");
				
		SimuEngine engine = new SimuEngine();
		MyScenario scenario = new MyScenario(engine, new InitScenarioAERO("scenario", 2, start, end, 1.d/20.d));

		engine.setCurrentScenario(scenario); // Scenario a instancier
		engine.initSimulation();
		engine.simulate();
		
		
		Logger.Terminate();
	}

}
