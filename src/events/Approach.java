package events;

import airport.Airplane;
import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class Approach extends SimEvent {
    public Approach(SimEntity entity, LogicalDateTime dateOccurence) {
        super(entity, dateOccurence);
    }

    @Override
    public void process() {
        Airplane plane = (Airplane) this.getEntity();
        plane.approach();

        // Create event landing
        Landing land = new Landing(plane, this.getEntity().getEngine().getCurrentDate().add(LogicalDuration.ofMinutes(2)));
        // Add event to the queue
        plane.getEngine().postEvent(land);

    }
}
