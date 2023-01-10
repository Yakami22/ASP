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
        plane.rideToTerminal();
        // Find an available terminal
        Terminal terminal = plane.getAirport().findTerminal(plane);
        // If no terminal available wait
        if (terminal == null) {
            Logger.Information(this, "process", "All terminals are currently unavailable and cannot accept plane " + plane.getId());
            // Reschedule
            this.rescheduleAt(getDateOccurence().add(LogicalDuration.ofMinutes(2)));
        }
        // Else, accept plane and create unload passengers event
        else {
            terminal.acceptPlane(plane);
            plane.flightOver();
            long random = (long) plane.getEngine().getRandom().nextUniform(2, 6);
            UnloadPassengers unload = new UnloadPassengers(plane, this.getEntity().getEngine().getCurrentDate().add(LogicalDuration.ofMinutes(random)));
            // Add event to the queue
            plane.getEngine().postEvent(unload);
        }
    }

}
