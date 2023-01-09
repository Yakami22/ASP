package airport;

import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.time.LogicalDateTime;

public class LoadPassengers extends SimEvent {
    public LoadPassengers(SimEntity entity, LogicalDateTime dateOccurence) {
        super(entity, dateOccurence);
    }

    @Override
    public void process() {
        Airplane plane = (Airplane) this.getEntity();
        plane.loadPassengers();

        // Create ride to track event
        RideToTrack ride = new RideToTrack(plane, this.getEntity().getEngine().getCurrentDate());
        // Add event to the queue
        plane.getEngine().postEvent(ride);
    }
}
