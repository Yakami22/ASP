package aeroport;

import java.util.List;

import enstabretagne.base.logger.Logger;
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
	}
	
	public void landing() {
		Logger.Information(this, getName(), "init");
		List<EntiteSimulee> myAeroports = recherche(e -> ((e instanceof Aeroport)));
		if(myAeroports.size()>0)
			Logger.Information(this, "init", getName() + " a trouvé l'aeroport " + myAeroports.get(0).getName());
		//Aeroport a = (Aeroport) myAeroports.get(0) ;
	}
	
	public void takeOff() {
		
	}

}
