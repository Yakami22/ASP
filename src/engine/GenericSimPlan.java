package engine;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.engine.Scenario;
import enstabretagne.engine.ScenarioInitData;
import enstabretagne.engine.SimuEngine;

public abstract class GenericSimPlan extends Scenario {

	public GenericSimPlan(SimuEngine engine, ScenarioInitData init) {
		super(engine, init);
	}

	
	public void Post(LogicalDateTime date,Runnable runnable) {
		Post(new GenericSimEvent(date, runnable));
	}
	
	public void Post(LogicalDuration date,Runnable runnable) {
		Post(new GenericSimEvent(getEngine().SimulationDate().add(date), runnable));
	}

}
