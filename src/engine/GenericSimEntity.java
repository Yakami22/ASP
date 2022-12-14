package engine;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.engine.EntiteSimulee;
import enstabretagne.engine.InitData;
import enstabretagne.engine.SimuEngine;

public class GenericSimEntity extends EntiteSimulee {

	public GenericSimEntity(SimuEngine engine, InitData ini) {
		super(engine, ini);
		// TODO Auto-generated constructor stub
	}
	
	public void postBehaviour(LogicalDateTime time, Runnable behaviour) {
		Post(new GenericSimEvent(time, behaviour));
	}

}
