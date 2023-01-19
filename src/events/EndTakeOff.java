package events;

import airport.Airplane;
import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;

public class EndTakeOff extends SimEvent {
    public EndTakeOff(SimEntity entity, LogicalDateTime dateOccurence) {
        super(entity, dateOccurence);
    }

    @Override
    public void process() {
        Airplane plane = (Airplane) this.getEntity();
        plane.takeOff();
        plane.getAirport().nbAirplane -= 1;
        if (plane.retardLanding > 0) {
            Logger.Information(this, "retard", "Plane is " + plane.retardLanding + " min late for landing");
        }
        if (plane.retardTakeOff > 0) {
            Logger.Information(this, "retard", "Plane is " + plane.retardTakeOff + " min late for takeOff");
        }
        Logger.Information(this, "frequentation", "There are " + plane.getAirport().nbAirplane + " planes in the airport; Time = " + plane.getEngine().getCurrentDate());
        Logger.Information(this, "duration", "Duration for takeOff is " + plane.durationTakeOff + " min");

    }
}
