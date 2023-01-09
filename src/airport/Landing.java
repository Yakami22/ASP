package airport;

import engine.SimEntity;
import engine.SimEvent;
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
        // Check if a runway & taxiway in are available
        // If it is land & create ride to terminal event
        TaxiwayIn taxiwayIn = plane.getAirport().findTaxiwayIn(plane);
        Runway runway = plane.getAirport().findRunway(plane);
        if (runway!=null && taxiwayIn!=null) {
            plane.getAirport().getTower().authorizeLanding(plane, taxiwayIn, runway);
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
