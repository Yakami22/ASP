package airport;

import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class RideToTerminal extends SimEvent {
    public RideToTerminal(SimEntity entity, LogicalDateTime dateOccurence) {
        super(entity, dateOccurence);
    }

    @Override
    public void process() {
        Airplane plane = (Airplane) this.getEntity();
        // Find an available terminal
        Terminal terminal = plane.getAirport().findTerminal(plane);
        // If no terminal available wait
        if (terminal == null) {
            Logger.Information(this, "process", "All terminals are currently unavailable and cannot accept plane " + plane.getId());
            // Reschedule
            this.rescheduleAt(getDateOccurence().add(LogicalDuration.ofSeconds(20)));
        }
        // Else, accept plane and create ride to track event
        else {
            terminal.acceptPlane(plane);
            RideToTrack ride = new RideToTrack(plane, this.getEntity().getEngine().getCurrentDate());
            // Add event to the queue
            plane.getEngine().postEvent(ride);
        }
    }

}
