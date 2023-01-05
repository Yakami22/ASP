//package work;
//
//import aeroport.InitScenarioAERO;
//import aeroport.MyScenario;
//import enstabretagne.base.logger.Logger;
//import enstabretagne.base.time.LogicalDateTime;
//import enstabretagne.base.time.LogicalDuration;
//import enstabretagne.engine.SimplePlanMonitor;
//import enstabretagne.engine.SimuEngine;
//
//public class mainSim {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
////		Logger.load();
//		
//		
//		LogicalDateTime start = new LogicalDateTime("04/12/2019 14:00");
//		LogicalDateTime end = start.add(LogicalDuration.ofHours(1));
//
//		AirportPlan plan = new AirportPlan(1, 
//				new InitScenarioAERO("Brest Guipavaaas", 0,
//						start, end, 1/20d));
//		
//		//String name, int graine, LogicalDateTime debut,
//		//LogicalDateTime fin, double freq
//		
//		//		SimuEngine engine = new SimuEngine();
//		// MyScenario scenario = new MyScenario(engine, new InitScenarioAERO("scenario", 2, start, end, 1.d/20.d));
//
//
//		SimplePlanMonitor spm = new SimplePlanMonitor(plan);
//		spm.run();
//		
//		Logger.Terminate();
//	}
//
//}
