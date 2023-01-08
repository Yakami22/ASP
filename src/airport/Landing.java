package airport;

import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class Landing extends SimEvent {
    public Landing(SimEntity entity, LogicalDateTime dateOccurence) {
        super(entity, dateOccurence);
    }

    @Override
    public void process() {
        Airplane plane = (Airplane) this.getEntity();
        // Get an id from control tower
        plane.getAirport().getTower().identifyAirplane(plane, "land");
        // Check if landing lane & entry lane are available
        // If it is land & create ride to terminal event
        EntryLane entryLane = plane.getAirport().findEntryLane(plane);
        if (plane.getAirport().isLandingLaneAvailable() && entryLane!=null) {
            plane.getAirport().getTower().authorizeLanding(plane, entryLane);
            // Ride to terminal
            RideToTerminal ride = new RideToTerminal(plane, this.getEntity().getEngine().getCurrentDate());
            // Add event to the queue
            plane.getEngine().postEvent(ride);
        }
        // If not, reschedule landing event
        else {
            plane.getAirport().getTower().denyLanding(plane);
            this.rescheduleAt(getDateOccurence().add(LogicalDuration.ofSeconds(20)));
        }
}}
