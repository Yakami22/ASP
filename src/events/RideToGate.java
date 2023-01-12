package events;

import airport.Airplane;
import airport.Gate;
import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class RideToGate extends SimEvent {
    public RideToGate(SimEntity entity, LogicalDateTime dateOccurence) {
        super(entity, dateOccurence);
    }

    @Override
    public void process() {
        Airplane plane = (Airplane) this.getEntity();
        plane.rideToGate();
        // Find an available gate
        Gate gate = plane.getAirport().findGate(plane);
        // If no gate available wait
        if (gate == null) {
            Logger.Information(this, "process", "All gates are currently unavailable and cannot accept plane " + plane.getId() + "; Time = " + plane.getEngine().getCurrentDate());
            // Reschedule
            this.rescheduleAt(getDateOccurence().add(LogicalDuration.ofMinutes(2)));
        }
        // Else, accept plane and create unload passengers event
        else {
            gate.acceptPlane(plane);
            //plane.flightOver();
            long random = (long) plane.getEngine().getRandom().nextUniform(2, 6);
            UnloadPassengers unload = new UnloadPassengers(plane, this.getEntity().getEngine().getCurrentDate().add(LogicalDuration.ofMinutes(random)));
            // Add event to the queue
            plane.getEngine().postEvent(unload);
        }
    }

}
