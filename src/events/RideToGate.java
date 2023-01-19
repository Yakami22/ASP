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
            //Logger.Information(this, "process", "All gates are currently unavailable and cannot accept plane " + plane.getId() + "; Time = " + plane.getEngine().getCurrentDate());
            // Reschedule
            this.rescheduleAt(getDateOccurence().add(LogicalDuration.ofMinutes(2)));
            plane.durationLanding += 2;
        }
        // Else, accept plane and create unload passengers event
        else {
            gate.acceptPlane(plane);
            plane.getAirport().nbAirplane += 1;
            Logger.Information(this, "frequentation", "There are " + plane.getAirport().nbAirplane + " planes in the airport; Time= " + plane.getEngine().getCurrentDate());
            Logger.Information(this, "duration", "Duration for landing is " + plane.durationLanding + " min");
            //plane.flightOver();
            long random = (long) plane.getEngine().getRandom().nextUniform(2, 6);
            plane.durationLanding += random;
            UnloadPassengers unload = new UnloadPassengers(plane, this.getEntity().getEngine().getCurrentDate().add(LogicalDuration.ofMinutes(random)));
            // Add event to the queue
            plane.getEngine().postEvent(unload);
        }
    }

}
