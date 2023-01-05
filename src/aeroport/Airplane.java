package aeroport;

import java.util.List;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.engine.*;
import engine.GenericSimEntity;

public class Airplane extends GenericSimEntity {

	private int id ;
	
	
	public Airplane(SimuEngine engine, InitAirplane ini) {
		super(engine, ini);
	}

	@Override
	protected void init() {
		super.init();
		postBehaviour(getEngine().SimulationDate(), this::landing);
		postBehaviour(getEngine().SimulationDate(), this::takeOff);
	}
	
	public void landing() {
		//Logger.Information(this, getName(), "landing");
		List<EntiteSimulee> myAeroports = recherche(e -> ((e instanceof Aeroport)));
		if(myAeroports.size()>0) {
			Aeroport a = (Aeroport) myAeroports.get(0) ;
			if (a.pisteLibre && a.porteLibre > 0) {
				a.pisteLibre = false;
				a.porteLibre -= 1;
				Logger.Information(this, "landing", getName() + " a trouvé l'aeroport " + myAeroports.get(0).getName());
				getEngine().SimulationDate().add(LogicalDuration.ofMinutes(2));
				a.pisteLibre = true;	
			} else {
				a.waitingPlanes.add(this);
				System.out.println("Test landing " + a.waitingPlanes.toString());
			}
		}
	}
	
//	public void takeOff() {
//		Logger.Information(this, getName(), "takeOff");
//		List<EntiteSimulee> myAirplanes = recherche(e-> ((e instanceof Airplane)));
//		if(myAirplanes.size()>0)
//			for( int i = 0 ; i<myAirplanes.size() ; i++)
//			Logger.Information(this, "takeoff", getName() + " a décollé " + myAirplanes.get(i).getName());
//		
//	}
	
	public void takeOff() {
		//Logger.Information(this, getName(), "takeOff");
		List<EntiteSimulee> myAeroports = recherche(e -> ((e instanceof Aeroport)));
		if(myAeroports.size()>0) {
			Aeroport a = (Aeroport) myAeroports.get(0) ;
			if (a.pisteLibre) {
				a.pisteLibre = false;
				a.porteLibre += 1;
				Logger.Information(this, "takeOff", getName() + " a décollé de l'aeroport " + myAeroports.get(0).getName());	
				getEngine().SimulationDate().add(LogicalDuration.ofMinutes(3));
				a.pisteLibre = true;	
			} else {
				a.waitingPlanes.add(this);
				System.out.println("Test takeoff " + a.waitingPlanes.toString());
			}
		}
	}	
	

}
