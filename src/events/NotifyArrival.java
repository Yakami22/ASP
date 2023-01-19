package events;

import airport.Airplane;
import airport.Runway;
import airport.TaxiwayIn;
import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import main.Utils;

public class NotifyArrival extends SimEvent {
    public NotifyArrival(SimEntity entity, LogicalDateTime dateOccurence) {
        super(entity, dateOccurence);
    }

    @Override
    public void process() {
        Airplane plane = (Airplane) this.getEntity();
        // Get an id from control tower
        plane.getAirport().getTower().identifyAirplane(plane, "notify its arrival");
        // Check if a runway & taxiway in are available
        // If it is land & create ride to gate event
        TaxiwayIn taxiwayIn = plane.getAirport().findTaxiwayIn(plane);
        Runway runway = plane.getAirport().findRunway(plane);
        if (runway!=null && taxiwayIn!=null) {
            plane.getAirport().getTower().authorizeLanding(plane, taxiwayIn, runway);
            // Create approach event
            Approach approach = new Approach(plane, this.getEntity().getEngine().getCurrentDate());
            // Add event to the queue
            plane.getEngine().postEvent(approach);
        }
        // If not, reschedule landing event
        else {
            plane.getAirport().getTower().denyLanding(plane);
            this.rescheduleAt(getDateOccurence().add(LogicalDuration.ofMinutes(2)));
            plane.retardLanding += 2;
            plane.durationLanding += 2;
        }
}}
