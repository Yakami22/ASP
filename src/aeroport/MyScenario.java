package aeroport;

import java.util.List;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.engine.EntiteSimulee;
import enstabretagne.engine.Scenario;
import enstabretagne.engine.ScenarioInitData;
import enstabretagne.engine.SimEvent;
import enstabretagne.engine.SimuEngine;

public class MyScenario extends Scenario{

	double freq; 
	int totalEntities;

	
	public MyScenario(SimuEngine engine, InitScenario init) {
		super(engine, init);
		this.freq = init.getFreq();
	}

	@Override
	public void creerEntitesSimulees() {
		//creer aeroprot
		Aeroport aeroport = new Aeroport(this.getEngine(), this.getInit());
		for(int i=0;i<10;i++) {
			createRandomAirplane(this);
			totalEntities++;

		}
	}
	
	protected static Airplane createRandomAirplane(Scenario s) {
		return new Airplane(s.getEngine(), new InitAirplane("Plane", "F"));
	}
	
	public void init() {
		super.init();
		List<EntiteSimulee> l = recherche(e->{return e instanceof Airplane ;});
		for(EntiteSimulee e: l) {
			e.requestInit();
		}
		Post(new CreateAirplane(getEngine().SimulationDate().add(LogicalDuration.ofMinutes(8)), "avion"));
	}
	
	public class CreateAirplane extends SimEvent {
		
		String nom;

		public CreateAirplane(LogicalDateTime d,String nom) {
			super(d);
			this.nom = nom;
		}

		//Exemple typique: 
		// en tant qu'entit�, le sc�nario cr�e des entit�s filles
		//c'est au sc�nario de faire le requestInit()
		@Override
		public void process() {
			Airplane es = createRandomAirplane(MyScenario.this);
			es.requestInit();
		}
		
	}

}
