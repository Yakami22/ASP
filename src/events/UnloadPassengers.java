package events;

import airport.Airplane;
import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class UnloadPassengers extends SimEvent {
    public UnloadPassengers(SimEntity entity, LogicalDateTime dateOccurence) {
        super(entity, dateOccurence);
    }

    @Override
    public void process() {
        Airplane plane = (Airplane) this.getEntity();
        plane.unloadPassengers();

        // Create preparation event
        Preparation prep = new Preparation(plane, this.getEntity().getEngine().getCurrentDate().add(LogicalDuration.ofMinutes(30)));
        // Add event to the queue
        plane.getEngine().postEvent(prep);
    }
}
