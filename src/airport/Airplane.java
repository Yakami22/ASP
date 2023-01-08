package airport;

import engine.SimEntity;
import engine.SimuEngine;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

import java.util.concurrent.ThreadLocalRandom;

public class Airplane extends SimEntity {
    private int id = 0;
    private Airport airport;
    private EntryLane entryLane;
    private ExitLane exitLane;
    private Terminal terminal;

    public Airplane(SimuEngine eng, Airport airport) {
        super(eng);
        this.airport =  airport;
        // Random number added to the default time
        long randomNum = ThreadLocalRandom.current().nextInt(0, 20+1);
        LogicalDateTime date = this.getEngine().getCurrentDate().add(LogicalDuration.ofSeconds(randomNum));
        Landing land = new Landing(this, date);
        eng.postEvent(land);
    }

    @Override
    public void init() {
    }

    public void takeOff() {
        this.getAirport().getTower().notifyTakeOff(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public EntryLane getEntryLane() {
        return entryLane;
    }

    public void setEntryLane(EntryLane entryLane) {
        this.entryLane = entryLane;
    }

    public ExitLane getExitLane() {
        return exitLane;
    }

    public void setExitLane(ExitLane exitLane) {
        this.exitLane = exitLane;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }
}
