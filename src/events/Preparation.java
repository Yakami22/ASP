package events;

import airport.Airplane;
import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import main.Utils;

public class Preparation extends SimEvent {
    Utils utils = new Utils();
    public Preparation(SimEntity entity, LogicalDateTime dateOccurence) {
        super(entity, dateOccurence);
    }

    @Override
    public void process() {
        Airplane plane = (Airplane) this.getEntity();
        plane.preparation();

        // Create load passengers event
        if(utils.openHour(plane.getEngine().getCurrentDate(), 1)) {
            LoadPassengers load = new LoadPassengers(plane, plane.getEngine().getCurrentDate().add(LogicalDuration.ofMinutes(30)));
            // Add event to the queue
            plane.getEngine().postEvent(load);
        } else {  // On attend le lendemain matin
            LoadPassengers load = new LoadPassengers(plane, utils.rescheduleToNextMorning(plane.getEngine().getCurrentDate()));
            // Add event to the queue
            plane.getEngine().postEvent(load);
        }

    }
}
