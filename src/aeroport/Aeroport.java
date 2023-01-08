package aeroport;

import java.util.List;

import enstabretagne.engine.EntiteSimulee;
import enstabretagne.engine.InitData;
import enstabretagne.engine.SimuEngine;

public class Aeroport extends EntiteSimulee{
	
	public int numLib = 6 ;
	
	public boolean TW_entry = true ; 
	
	public boolean TW_exit = true ; 
	
	boolean pisteLibre = true;
	
	List<EntiteSimulee> waitingPlanes = recherche(e -> ((e instanceof Airplane)));
	

	public Aeroport(SimuEngine engine, InitData ini) {
		super(engine, ini);
		// TODO Auto-generated constructor stub
	}

}