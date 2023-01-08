package aeroport;

import java.util.List;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.engine.*;
import engine.GenericSimEntity;

public class Airplane extends GenericSimEntity {

	private int id ;
	public int i ; 
//	public int numLib = 6 ; 
	
	
	public Airplane(SimuEngine engine, InitAirplane ini) {
		super(engine, ini);
	}

	@Override
	protected void init() {
		super.init();
//		postBehaviour(getEngine().SimulationDate(), this::newPlane);
		//4654654
		postBehaviour(getEngine().SimulationDate().add(getNextDate4AvionCreation()), this::landing);
		postBehaviour(getEngine().SimulationDate().add(getNextDate4AvionCreation()), this::takeOff);
		
	}
	
	
	public void newPlane() {
		Airplane a = new Airplane(getEngine(), new InitAirplane("F" + i++, "booeing"));
		a.requestInit();

		// cr�ation du prochain avion en cascade
//		postBehaviour(getEngine().SimulationDate(), this::newPlane);
		//Aeroport a = (Aeroport) myAeroports.get(0) ;
	}
	
	public void landing() {
		Logger.Information(this, getName(), " The landing ");
		List<EntiteSimulee> myAeroports = recherche(e -> ((e instanceof Aeroport)));
		if(myAeroports.size()>0 )
///		
			{
			Aeroport a = (Aeroport) myAeroports.get(0);
//			numLib = numLib -1 ; 
			
			if(a.numLib>0)
			{
				
				a.numLib--; 
				
				Logger.Information(this, "landing", "the airplane num " + getName() + " is landing in " + myAeroports.get(0).getName() + " num de place libre est  " + a.numLib);
				
				
			}
			
			
			}
		//Aeroport a = (Aeroport) myAeroports.get(0) ;
	}
	
	public void takeOff() {
		
		Logger.Information(this, getName(), "The takeoff ");
		List<EntiteSimulee> myAeroports = recherche(e -> ((e instanceof Aeroport)));
		if(myAeroports.size()>0  )
			Logger.Information(this, "takeoff",  "the airplane num " + getName() + " is taking off from " + myAeroports.get(0).getName());
		
	}
	
	LogicalDuration getNextDate4AvionCreation() {
		double time = getRandomGenerator().nextExp(1/20d);
		return LogicalDuration.ofMinutes((long) time);
	}

}
