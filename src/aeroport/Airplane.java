package aeroport;

import java.util.List;
import java.util.function.Predicate;

import enstabretagne.engine.*;
import engine.GenericSimEntity;
import engine.GenericSimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;

public class Airplane extends GenericSimEntity {

	private int id ;
	
	
	public Airplane(SimuEngine engine, InitAirplane ini) {
		super(engine, ini);
	}

	@Override
//	protected void init() {
//		super.init();
//		postBehaviour(getEngine().SimulationDate(), this::landing);
//	}
	
	
	protected void init() {
		super.init();
		Logger.Information(this, getName(), "l'initialisation ");
		List<EntiteSimulee> airportList = recherche(e-> (e instanceof Aeroport));
		if(airportList.size()>0)
			Logger.Information(this, "initialisation   ", getName() + " a trouve l'aeroport " + airportList.get(0).getName());
	}
	
	public void landing() {
		Logger.Information(this, getName(), "landing ");
		List<EntiteSimulee> myAeroports = recherche(e -> ((e instanceof Aeroport)));
		if(myAeroports.size()>0)
			Logger.Information(this, "landing", getName() + " is landing " + myAeroports.get(0).getName());
		//Aeroport a = (Aeroport) myAeroports.get(0) ;
	}
	
	public void takeOff() {
		
	}

}
