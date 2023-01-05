//package aeroport;
//
//import enstabretagne.base.time.LogicalDuration;
//import enstabretagne.engine.ScenarioInitData;
//import enstabretagne.engine.SimuEngine;
//
//public class ScenarioAERO extends MyScenario {
//	
//	
//	public ScenarioAERO(SimuEngine engine, ScenarioInitData init) {
//		super(engine, init);
//		// TODO Auto-generated constructor stub
//		iniAiroport = (InitScenarioAERO) init;
//	}
//
//	InitScenarioAERO iniAiroport; 
//	
//	protected void init() {
//		super.init();
//
//		// Utilisation de l'extension de la m�thode Post
//		// on cr�e le premier avion � l 'init les autres seront cr��s en cascade
//		Post(LogicalDuration.ZERO, this::newPlane);
//	}
//	
//	LogicalDuration getNextDate4AvionCreation() {
//		double time = getRandomGenerator().nextExp(iniAirport.freq);
//		return LogicalDuration.ofMinutes((long) time);
//	}
//
//	public int i;
//
//	public void newPlane() {
//		Airplane a = new Airplane(getEngine(), new InitAirplane("A" + i++, "booeing"));
//		a.requestInit();
//
//		// cr�ation du prochain avion en cascade
//		Post(getNextDate4AvionCreation(), this::newPlane);
//	}
//
//
//}
