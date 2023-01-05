package plan;


import java.util.LinkedList;

import aeroport.InitScenario;
import aeroport.MyScenario;
import enstabretagne.engine.Plan;
import enstabretagne.engine.Scenario;

public class AirportPlan extends Plan{
	
	InitScenario ini ;
	LinkedList<MyScenario> myAirportsScenarios ;
	
	public AirportPlan(int nbReplique, InitScenario ini) {
		super(nbReplique);
		this.ini= ini;
		myAirportsScenarios = new LinkedList<>() ;
	}

	@Override
	public void initScenarii() {
		for(int i = 0 ; i<getNbReplique() ; i++) {
			InitScenario initScen = new InitScenario(ini.getName() + "i" ,
					i, ini.getDebut(), ini.getFin(),
					ini.getFreq(), ini.getInitAirplane(), ini.getInitAirport()) ;
			MyScenario scenario = new MyScenario(engine, initScen);
			myAirportsScenarios.add(scenario);
		}
		
	}

	@Override
	public boolean hasNextScenario() {
		return myAirportsScenarios.size()>0;
	}

	@Override
	public Scenario nextScenario() {
		if(hasNextScenario()) {
			var scenario = myAirportsScenarios.pop();
			engine.setCurrentScenario(scenario);
			return scenario ;
		}
		return null ;
	}
	
	

}
