package events;

import airport.Airplane;
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
        plane.rideToTrack();

        long random = (long) plane.getEngine().getRandom().nextUniform(2, 6);
        // Create take off event
        TakeOff takeOff = new TakeOff(plane, plane.getEngine().getCurrentDate().add(LogicalDuration.ofMinutes(random)));
        // Add event to the queue
        plane.getEngine().postEvent(takeOff);
    }
}
