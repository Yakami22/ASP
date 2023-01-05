package aeroport;

import java.util.List;

import enstabretagne.engine.EntiteSimulee;
import enstabretagne.engine.InitData;
import enstabretagne.engine.SimuEngine;

public class Aeroport extends EntiteSimulee{

	boolean pisteLibre = true;
	int porteLibre = 6;
	List<EntiteSimulee> waitingPlanes = recherche(e -> ((e instanceof Airplane)));
	
	public Aeroport(SimuEngine engine, InitData ini) {
		super(engine, ini);
		// TODO Auto-generated constructor stub
	}

}
