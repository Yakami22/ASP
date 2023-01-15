package events;

import airport.Airplane;
import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.time.LogicalDateTime;
import main.Utils;

public class CreateAirplane extends SimEvent {

    public CreateAirplane(SimEntity entity, LogicalDateTime dateOccurence) {
        super(entity, dateOccurence);
    }

    @Override
    public void process() {
        Airplane airplane = (Airplane) this.getEntity();

        airplane.creation();
        NotifyArrival notifyArrival = new NotifyArrival(airplane, airplane.getEngine().getCurrentDate());
        airplane.getEngine().postEvent(notifyArrival);


    }
}
