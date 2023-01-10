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
		postBehaviour(getEngine().SimulationDate().add(LogicalDuration.ofMinutes((long) (2 + Math.random()*10))), this::landingStart);  // Ajouter random time
		//postBehaviour(getEngine().SimulationDate(), this::takeOff);
	}
	
	public void landingStart() {
		// Logger.Information(this, getName(), "wants to land");
		List<EntiteSimulee> myAeroports = recherche(e -> ((e instanceof Aeroport)));
		if(myAeroports.size()>0) {
			Aeroport a = (Aeroport) myAeroports.get(0) ;
			if (a.pisteLibre && a.twLanding) {
				a.pisteLibre = false;
				a.twLanding = false;
				Logger.Information(this, "landing start", getName() + " va atterrir a l'aeroport " + myAeroports.get(0).getName());
				//getEngine().SimulationDate().add(LogicalDuration.ofMinutes(2));
			//	a.pisteLibre = true;	
				postBehaviour(getEngine().SimulationDate().add(LogicalDuration.ofMinutes(2)), this::landingEnd);
			} else {
				a.waitingPlanes.add(this);
				Logger.Information(this, "wait landing", getName() + " is waiting to land ");
				//System.out.println("Test landing " + a.waitingPlanes.toString());
			}
		}
	}
	
	public void landingEnd() {
		//Logger.Information(this, getName(), "landing");
		List<EntiteSimulee> myAeroports = recherche(e -> ((e instanceof Aeroport)));
		if(myAeroports.size()>0) {
			Aeroport a = (Aeroport) myAeroports.get(0) ;
			a.pisteLibre = true;
			if(a.porteLibre > 0 && a.porteLibre <= 6) {
				a.twLanding = true;
				a.porteLibre -= 1;
				Logger.Information(this, "landing end", getName() + " a fini d'atterrir, il reste " + a.porteLibre + " places");
			} // Ajouter else condition
			
			//postBehaviour(getEngine().SimulationDate(), this::takeOffStart);
			if(!a.waitingPlanes.isEmpty() && a.porteLibre > 0 && a.twLanding) {
				postBehaviour(getEngine().SimulationDate().add(LogicalDuration.ofMinutes(5)), ((Airplane) a.waitingPlanes.get(0))::landingStart);
				a.waitingPlanes.remove(0);
			}
			if(!a.waitingPlanesTakeOff.isEmpty() && a.porteLibre < 6 && a.twTakeoff) {
				postBehaviour(getEngine().SimulationDate().add(LogicalDuration.ofMinutes(5)), ((Airplane) a.waitingPlanesTakeOff.get(0))::takeOffStart);
				a.waitingPlanesTakeOff.remove(0);
			}
				//getEngine().SimulationDate().add(LogicalDuration.ofMinutes(2));
			//	a.pisteLibre = true;	
			postBehaviour(getEngine().SimulationDate().add(LogicalDuration.ofMinutes(5)), this::takeOffStart);
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
	
	public void takeOffStart() {
		//Logger.Information(this, getName(), "takeOff");
		List<EntiteSimulee> myAeroports = recherche(e -> ((e instanceof Aeroport)));
		if(myAeroports.size()>0) {
			Aeroport a = (Aeroport) myAeroports.get(0) ;
			if (a.pisteLibre && a.twTakeoff && a.porteLibre < 6) {
				a.pisteLibre = false;
				a.twTakeoff = false;
				a.porteLibre += 1;
				Logger.Information(this, "takeOff start", getName() + " est en train de décoller de l'aeroport " + myAeroports.get(0).getName());	
				//getEngine().SimulationDate().add(LogicalDuration.ofMinutes(3));
				//a.pisteLibre = true;	
				postBehaviour(getEngine().SimulationDate().add(LogicalDuration.ofMinutes(3)), this::takeOffEnd);
			} else {
				a.waitingPlanesTakeOff.add(this);
				Logger.Information(this, "wait takeOff", getName() + " is waiting to takeOff ");
				//System.out.println("Test takeoff " + a.waitingPlanes.toString());
			}
		}
	}	
	
	public void takeOffEnd() {
		//Logger.Information(this, getName(), "takeOff");
		List<EntiteSimulee> myAeroports = recherche(e -> ((e instanceof Aeroport)));
		if(myAeroports.size()>0) {
			Aeroport a = (Aeroport) myAeroports.get(0) ;
			a.pisteLibre = true;
			a.twTakeoff = true;
			Logger.Information(this, "takeOff end", getName() + " a décollé de l'aeroport, il reste " + a.porteLibre + " places");	
			//getEngine().SimulationDate().add(LogicalDuration.ofMinutes(3));
			
			if(!a.waitingPlanesTakeOff.isEmpty() && a.porteLibre < 6) {
				postBehaviour(getEngine().SimulationDate(), ((Airplane) a.waitingPlanesTakeOff.get(0))::takeOffStart);
				a.waitingPlanesTakeOff.remove(0);
			}
			if(!a.waitingPlanes.isEmpty() && a.porteLibre > 0) {
				postBehaviour(getEngine().SimulationDate().add(LogicalDuration.ofMinutes(5)), ((Airplane) a.waitingPlanes.get(0))::landingStart);
				a.waitingPlanes.remove(0);
			}
			
			//System.out.println("Test takeoff " + a.waitingPlanes.toString());
			
		}
	}	

}
