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
        // Get an id from control tower
        plane.getAirport().getTower().identifyAirplane(plane, "ride to exit track");
        // Check if an exit lane is available
        ExitLane exitLane = plane.getAirport().findExitLane(plane);
        // If there is one go on it and create take off event
        if (exitLane != null) {
            plane.getAirport().getTower().authorizeExit(plane, exitLane);
            // Take off event
            TakeOff takeOff = new TakeOff(plane, this.getEntity().getEngine().getCurrentDate());
            // Add event to the queue
            plane.getEngine().postEvent(takeOff);
        }
        // If not, reschedule event
        else {
            plane.getAirport().getTower().denyExit(plane);
            this.rescheduleAt(getDateOccurence().add(LogicalDuration.ofSeconds(20)));
        }
    }
}
