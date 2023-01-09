package airport;

import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class RideToTrack extends SimEvent {
    public RideToTrack(SimEntity entity, LogicalDateTime dateOccurence) {
        super(entity, dateOccurence);
    }

    @Override
    public void process() {
        Airplane plane = (Airplane) this.getEntity();

        // Create take off event
        TakeOff takeOff = new TakeOff(plane, this.getEntity().getEngine().getCurrentDate());
        // Add event to the queue
        plane.getEngine().postEvent(takeOff);
    }
}
