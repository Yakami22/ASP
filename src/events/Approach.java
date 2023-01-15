package events;

import airport.Airplane;
import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import jdk.jshell.execution.Util;
import main.Utils;

import java.time.DayOfWeek;

public class Approach extends SimEvent {
    public Approach(SimEntity entity, LogicalDateTime dateOccurence) {
        super(entity, dateOccurence);
    }

    @Override
    public void process() {
        Airplane plane = (Airplane) this.getEntity();
        plane.approach();
        long duration;

        // Create event landing
        if((plane.getEngine().getCurrentDate().getDayOfWeek().ordinal() & 6) == 6) {
            duration = (long) plane.getEngine().getRandom().nextUniform(4, 10);
        } else {
            duration = (long) plane.getEngine().getRandom().nextUniform(2, 5);
        }
        Landing land = new Landing(plane, plane.getEngine().getCurrentDate().add(LogicalDuration.ofMinutes(duration)));
        // Add event to the queue
        plane.getEngine().postEvent(land);

    }
}
