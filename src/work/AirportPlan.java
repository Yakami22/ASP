//package work;
//
//import java.time.LocalDateTime;
//import java.util.LinkedList;
//
//import aeroport.InitScenarioAERO;
//import aeroport.ScenarioAERO;
//import enstabretagne.base.logger.Logger;
//import enstabretagne.engine.Plan;
//import enstabretagne.engine.Scenario;
//
//public class AirportPlan extends Plan {
//	InitScenarioAERO ini ; 
//	
//	LinkedList<ScenarioAERO> airportsScenarii;
//	
//	public AirportPlan(int nbReplique, InitScenarioAERO ini) {
//		super(nbReplique);
//		this.ini= ini;
//		airportsScenarii = new LinkedList<>();
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public void initScenarii() {
//		// TODO Auto-generated method stub
//
//		for(int i = 0 ; i<getNbReplique() ; i++) {
//			InitScenarioAERO scIni = new InitScenarioAERO(
//					ini.getName() + "i", i, ini.getDebut(), ini.getFin(),
//					ini.getFreq());
//					
//			ScenarioAERO sc = new ScenarioAERO(engine, scIni);
//			airportsScenarii.add(sc);
//		}
//		
//	}
//
//	@Override
//	public boolean hasNextScenario() {
//		// TODO Auto-generated method stub
//		return airportsScenarii.size()>0;
//	}
//
//	@Override
//	public Scenario nextScenario() {
//		if(hasNextScenario()) {
//			 var sc = airportsScenarii.pop();
//			 engine.setCurrentScenario(sc);
//			 return sc;
//		}
//		return null;
//	}
//
//
//
//}
