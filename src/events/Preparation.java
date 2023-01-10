package events;

import airport.Airplane;
import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class Preparation extends SimEvent {
    public Preparation(SimEntity entity, LogicalDateTime dateOccurence) {
        super(entity, dateOccurence);
    }

    @Override
    public void process() {
        Airplane plane = (Airplane) this.getEntity();
        plane.preparation();

        // Create load passengers event
        LoadPassengers load = new LoadPassengers(plane, this.getEntity().getEngine().getCurrentDate().add(LogicalDuration.ofMinutes(20)));
        // Add event to the queue
        plane.getEngine().postEvent(load);
    }
}
