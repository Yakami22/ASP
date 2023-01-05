package work;

import aeroport.InitAirplane;
import aeroport.InitAirport;
import aeroport.InitScenario;
import aeroport.MyScenario;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.engine.SimplePlanMonitor;
import enstabretagne.engine.SimuEngine;
import plan.AirportPlan;

public class Test {

	
	public static void main(String[] args) {
			
				LogicalDateTime start = new LogicalDateTime("04/12/2019 14:00");
		        LogicalDateTime end = new LogicalDateTime("04/12/2019 15:00");
						
		        AirportPlan plan = new AirportPlan(1, 
		        		new InitScenario("Brest Guipavas", 0,
						start, end, 1/20d,
						new InitAirplane("Avion","F"),
						new InitAirport("AirportOne")));
				
				SimplePlanMonitor spm = new SimplePlanMonitor(plan);
				spm.run();
				
				Logger.Terminate();
	}
}