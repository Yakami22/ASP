package events;

import airport.Airplane;
import airport.Runway;
import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class TakeOff extends SimEvent {
    public TakeOff(SimEntity entity, LogicalDateTime dateOccurence) {
        super(entity, dateOccurence);
    }

    @Override
    public void process() {
        Airplane plane = (Airplane) this.getEntity();
        // Check if a runway is available
        Runway runway = plane.getAirport().findRunway(plane);
        if (runway != null) {
            plane.getAirport().getTower().authorizeTakeOff(plane);
            // Create endTakeOff event
            EndTakeOff endTakeOff = new EndTakeOff(plane, plane.getEngine().getCurrentDate().add(LogicalDuration.ofMinutes(3)));
            // Add event to the queue
            plane.getEngine().postEvent(endTakeOff);
        }
        // Else, reschedule event
        else {
            Logger.Information(this, "process", "Take off lane is not available for plane " + plane.getId() + "; Time = " + plane.getEngine().getCurrentDate());
            // Reschedule
            this.rescheduleAt(getDateOccurence().add(LogicalDuration.ofMinutes(1)));
        }

    }
}
