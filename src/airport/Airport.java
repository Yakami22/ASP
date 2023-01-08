package airport;

import enstabretagne.base.logger.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Airport {
    private String name;
    private boolean landingLaneAvailable;
    private boolean takeOffLaneAvailable;
    private ArrayList<EntryLane> entryLanes = new ArrayList<EntryLane>();
    private ArrayList<ExitLane> exitLanes = new ArrayList<ExitLane>();
    private ArrayList<Terminal> terminals = new ArrayList<Terminal>();
    private ControlTower tower;

    public Airport(String name, int terminalNumber, int entryLaneNumber, int exitLaneNumber) {
        // All lanes are available at the beginning
        this.setLandingLaneAvailable(true);
        this.setTakeOffLaneAvailable(true);

        // Set Airport name
        this.setName(name);
        Logger.Information(this, "Airport", this.name + " is now opened !");

        // Set Control Tower
        this.tower = new ControlTower(this, 1);

        // Create given number of terminals
        for (int i=0; i<terminalNumber; i++) {
            Terminal terminal = new Terminal(this);
        }

        // Create given number of entry lanes
        for (int i=0; i<entryLaneNumber; i++) {
            EntryLane entryLane = new EntryLane(this);
        }

        // Create given number of exit lanes
        for (int i=0; i<exitLaneNumber; i++) {
            ExitLane exitLane = new ExitLane(this);
        }
    }

    public Terminal findTerminal(Airplane airplane) {
        // Finds an available terminal and returns it
        // Returns null if all terminals are taken
        for (Terminal terminal : this.getTerminals()) {
            if (terminal.isAvailable()) {
                Logger.Information(this, "findTerminal", "Terminal " + terminal.getId() + " is available for plane " + airplane.getId());
                return terminal;
            }
        }
        return null;
    }

    public EntryLane findEntryLane(Airplane airplane) {
        // Fins an available entry lane and returns it
        // Returns null if all entry lanes are taken
        for (EntryLane entryLane : this.getEntryLanes()) {
            if (entryLane.isAvailable()) {
                Logger.Information(this, "findEntryLane", "Entry lane " + entryLane.getId() + " is available for plane " + airplane.getId());
                return entryLane;
            }
        }
        return null;
    }

    public ExitLane findExitLane(Airplane airplane) {
        // Fins an available exit lane and returns it
        // Returns null if all exit lanes are taken
        for (ExitLane exitLane : this.getExitLanes()) {
            if (exitLane.isAvailable()) {
                Logger.Information(this, "findExitLane", "Exit lane " + exitLane.getId() + " is available for plane " + airplane.getId());
                return exitLane;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLandingLaneAvailable() {
        return landingLaneAvailable;
    }

    public void setLandingLaneAvailable(boolean landingLaneAvailable) {
        this.landingLaneAvailable = landingLaneAvailable;
    }

    public boolean isTakeOffLaneAvailable() {
        return takeOffLaneAvailable;
    }

    public void setTakeOffLaneAvailable(boolean takeOffLaneAvailable) {
        this.takeOffLaneAvailable = takeOffLaneAvailable;
    }

    public ArrayList<EntryLane> getEntryLanes() {
        return entryLanes;
    }

    public void setEntryLanes(ArrayList<EntryLane> entryLanes) {
        this.entryLanes = entryLanes;
    }

    public ArrayList<ExitLane> getExitLanes() {
        return exitLanes;
    }

    public void setExitLanes(ArrayList<ExitLane> exitLanes) {
        this.exitLanes = exitLanes;
    }

    public ControlTower getTower() {
        return tower;
    }

    public void setTower(ControlTower tower) {
        this.tower = tower;
    }

    public ArrayList<Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(ArrayList<Terminal> terminals) {
        this.terminals = terminals;
    }
}
