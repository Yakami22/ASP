package aeroport;

import java.util.List;
import java.util.function.Predicate;

import enstabretagne.engine.*;
import engine.GenericSimEntity;
import engine.GenericSimEvent;
import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;

public class Airplane extends GenericSimEntity {

	private int id ;
	
	
	public Airplane(SimuEngine engine, InitAirplane ini) {
		super(engine, ini);
	}

	@Override
	protected void init() {
		super.init();
		postBehaviour(getEngine().SimulationDate(), this::landing);
	}
	
	public void landing() {
		List<EntiteSimulee> myAeroports = recherche(e -> ((e instanceof Aeroport)));
		Aeroport a = (Aeroport) myAeroports.get(0) ;
	}
	
	public void takeOff() {
		
	}

}
