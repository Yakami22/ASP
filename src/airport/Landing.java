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
        plane.land();

        // Create event ride to terminal
        RideToTerminal ride = new RideToTerminal(plane, this.getEntity().getEngine().getCurrentDate().add(LogicalDuration.ofMinutes(2)));
        // Add event to the queue
        plane.getEngine().postEvent(ride);
    }
}
