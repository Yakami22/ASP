package events;

import airport.Airplane;
import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class LoadPassengers extends SimEvent {
    public LoadPassengers(SimEntity entity, LogicalDateTime dateOccurence) {
        super(entity, dateOccurence);
    }

    @Override
    public void process() {
        Airplane plane = (Airplane) this.getEntity();
        plane.loadPassengers();

        // Create notify departure event
        NotifyDeparture notifyDeparture = new NotifyDeparture(plane, this.getEntity().getEngine().getCurrentDate().add(LogicalDuration.ofMinutes(20)));
        // Add event to the queue
        plane.getEngine().postEvent(notifyDeparture);
    }
}
