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
//		postBehaviour(getEngine().SimulationDate().add(LogicalDuration.ofMinutes(1)), this::aiplane_approach);
		
		
		//-	Atterrissage : durée supposée fixe de 2 minutes et avec 100% de réussite.
		
		
		LogicalDateTime myldt = getEngine().SimulationDate().add(getNextDate4AvionCreation(1/20d)) ; 
		postBehaviour(myldt, this::aiplane_arrived);

		LogicalDateTime  myldt_approach = myldt.add(LogicalDuration.ofMinutes(1)) ; 
		postBehaviour(myldt_approach, this::aiplane_approach);
		
		LogicalDateTime myldt_takeOff  = myldt_approach.add(getNextDate4AvionCreation(1/20d)) ; 
		postBehaviour(myldt_takeOff,  this::takeOff);
		
		
		LogicalDateTime myldt_airplaneLeft  = myldt_takeOff.add(LogicalDuration.ofMinutes(3)) ; 
		postBehaviour(myldt_airplaneLeft,  this::airplaneLeft);
		
	}
	
	

	
	public void aiplane_arrived() {
		Logger.Information(this, getName(), " The aiplane_arrived ");
		List<EntiteSimulee> myAeroports = recherche(e -> ((e instanceof Aeroport)));
//		List<Scenario> myScen = recherche(s -> ((s instanceof MyScenario)));
		if(myAeroports.size()>0 )
///		
			{
			Aeroport a = (Aeroport) myAeroports.get(0);
			
//			MyScenario s = (MyScenario) MyScenario.get(0);
//			numLib = numLib -1 ; 
			
			if(a.pisteLibre && a.TW_entry)
			{
				
//				a.numLib--; 
				a.TW_entry = false ; 
				a.pisteLibre = false ; 
				
				Logger.Information(this, "aiplane_arrived", "the airplane num " + getName() + " is aiplane_arrived in " + myAeroports.get(0).getName() + " TW entry et piste sont occupés et num de place libre est  " + a.numLib );
				
				
			}
			
			else 
			{
				Logger.Information(this , "waiting for piste or TW ...." ,getName() + "waiting for piste or TW") ; 
			}
			
			
			}
		//Aeroport a = (Aeroport) myAeroports.get(0) ;
	}
	
	
	
	
	public void aiplane_approach() {
		Logger.Information(this, getName(), " The aiplane_approach ");
		List<EntiteSimulee> myAeroports = recherche(e -> ((e instanceof Aeroport)));
//		List<Scenario> myScen = recherche(s -> ((s instanceof MyScenario)));
		if(myAeroports.size()>0 )
///		
			{
			Aeroport a = (Aeroport) myAeroports.get(0);
			
//			MyScenario s = (MyScenario) MyScenario.get(0);
//			numLib = numLib -1 ; 
			
			if(a.numLib>0 )
			{
				
				a.numLib--; 
				a.TW_entry = true ; 
				a.pisteLibre = true ; 
				
				Logger.Information(this, "aiplane_approach", "the airplane num " + getName() + " is aiplane_approach in " + myAeroports.get(0).getName() + " TW entry et piste sont libres et num de place libre est  " + a.numLib );
				
				
			}
			
			else 
			{
				a.pisteLibre = true ; 
				Logger.Information(this , "waiting for gate .... piste libre .... tw entry occupé " ,getName() ) ; 
			}
			
			
			}
		//Aeroport a = (Aeroport) myAeroports.get(0) ;
	}
	
	public void takeOff() {
		
		Logger.Information(this, getName(), "The takeoff ");
		List<EntiteSimulee> myAeroports = recherche(e -> ((e instanceof Aeroport)));
		if(myAeroports.size()>0  ) {
			
			Aeroport a = (Aeroport) myAeroports.get(0);

			
			if(a.TW_exit )
			{
			
			a.TW_exit = false ; 
			a.numLib++; 
			
			
			Logger.Information(this, "in TW2",  "the airplane num " + getName() + " is in TW2 of  " + myAeroports.get(0).getName() + "places libres : " + a.numLib);

			
			
			if(a.pisteLibre)
			{
				a.pisteLibre= false ; 
				a.TW_exit = true ; 
				Logger.Information(this, "takeoff",  "the airplane num " + getName() + " is taking off from " + myAeroports.get(0).getName() + " piste occupé , places libres : " + a.numLib);
				
			}
			
			
			}

		else 
		{
			Logger.Information(this , "waiting for take off ...." ,getName() + " airplanes waiting for take off") ; 
		}
		
		
		}
	}
	
	
	
	public void airplaneLeft() {
		
		Logger.Information(this, getName(), "The takeoff ");
		List<EntiteSimulee> myAeroports = recherche(e -> ((e instanceof Aeroport)));
		if(myAeroports.size()>0  ) {
			
			Aeroport a = (Aeroport) myAeroports.get(0);

			
			a.pisteLibre= true ; 
			Logger.Information(this, "airplane left",  "the airplane num " + getName() + " left " + myAeroports.get(0).getName() + " TW2 et piste libres , places libres : " + a.numLib);

			
//			if(a.pisteLibre)
//			{
//				a.pisteLibre= false ; 
//				Logger.Information(this, "takeoff",  "the airplane num " + getName() + " is taking off from " + myAeroports.get(0).getName() + " TW2 et piste occupé , places libres : " + a.numLib);
//				
//			}

//		else 
//		{
//			Logger.Information(this , "waiting to leave  ...." ,getName() ) ; 
//		}
		
		
		}
	}
	
	// -	Attente verifie tw2  -	Roulement  si tw2 et libre , piste occupee , place libre++ - 
	// if tw2 libre {tw2==false numlibre++ if piste libre{Logger takeoff  }} else wait
	
	LogicalDuration getNextDate4AvionCreation(double freqq) {
		double time = getRandomGenerator().nextExp( freqq);
		return LogicalDuration.ofMinutes((long) time);
	}
	
	
	public void newPlane() {
		Airplane a = new Airplane(getEngine(), new InitAirplane("F" + i++, "booeing"));
		a.requestInit();

		// cr�ation du prochain avion en cascade
//		postBehaviour(getEngine().SimulationDate(), this::newPlane);
		//Aeroport a = (Aeroport) myAeroports.get(0) ;
	}

}