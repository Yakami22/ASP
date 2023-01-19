package airport;

import enstabretagne.base.logger.Logger;

import java.util.ArrayList;

public class Airport {
    private String name;
    private ArrayList<Runway> runways = new ArrayList<Runway>();
    private ArrayList<TaxiwayIn> taxiwaysIn = new ArrayList<TaxiwayIn>();
    private ArrayList<TaxiwayOut> taxiwayOuts = new ArrayList<TaxiwayOut>();
    private ArrayList<Gate> gates = new ArrayList<Gate>();
    private ControlTower tower;
    public int nbAirplane = 0;

    public Airport(String name, int runwayNumber, int gateNumber, int taxiwayInNumber, int taxiwayOutNumber) {
        // Set Airport name
        this.setName(name);
        Logger.Information(this, "Airport", this.name + " is now opened !");

        // Set Control Tower
        this.tower = new ControlTower(this, 1);

        // Create given number of runways
        for (int i=0; i<runwayNumber; i++) {
            Runway runway = new Runway(this);
        }

        // Create given number of gates
        for (int i=0; i<gateNumber; i++) {
            Gate gate = new Gate(this);
        }

        // Create given number of taxiways in
        for (int i=0; i<taxiwayInNumber; i++) {
            TaxiwayIn taxiwayIn = new TaxiwayIn(this);
        }

        // Create given number of taxiways out
        for (int i=0; i<taxiwayOutNumber; i++) {
            TaxiwayOut taxiwayOut = new TaxiwayOut(this);
        }
    }

    public Gate findGate(Airplane airplane) {
        // Finds an available gate and returns it
        // Returns null if all gates are taken
        for (Gate gate : this.getGates()) {
            if (gate.isAvailable()) {
                //Logger.Information(this, "findGate", "Gate " + gate.getId() + " is available for plane " + airplane.getId() + "; Time = " + airplane.getEngine().getCurrentDate());
                return gate;
            }
        }
        return null;
    }

    public TaxiwayIn findTaxiwayIn(Airplane airplane) {
        // Finds an available taxiway in and returns it
        // Returns null if all taxiway in are taken
        for (TaxiwayIn taxiwayIn : this.getTaxiwaysIn()) {
            if (taxiwayIn.isAvailable()) {
                //Logger.Information(this, "findTaxiwayIn", "Taxiway in " + taxiwayIn.getId() + " is available for plane " + airplane.getId() + "; Time = " + airplane.getEngine().getCurrentDate());
                return taxiwayIn;
            }
        }
        return null;
    }

    public TaxiwayOut findTaxiwayOut(Airplane airplane) {
        // Finds an available taxiway out and returns it
        // Returns null if all taxiways out are taken
        for (TaxiwayOut taxiwayOut : this.getTaxiwaysOut()) {
            if (taxiwayOut.isAvailable()) {
                //Logger.Information(this, "findTaxiWayOut", "Taxiway out " + taxiwayOut.getId() + " is available for plane " + airplane.getId() + "; Time = " + airplane.getEngine().getCurrentDate());
                return taxiwayOut;
            }
        }
        return null;
    }

    public Runway findRunway(Airplane airplane) {
        // Finds an available runway and returns it
        // Returns null if all runways are taken
        for (Runway runway : this.getRunways()) {
            if (runway.isAvailable()) {
                //Logger.Information(this, "findRunway", "Runway " + runway.getId() + " is available for plane " + airplane.getId() + "; Time = " + airplane.getEngine().getCurrentDate());
                return runway;
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

    public ArrayList<Runway> getRunways() {
        return runways;
    }

    public void setRunways(ArrayList<Runway> runways) {
        this.runways = runways;
    }

    public ArrayList<TaxiwayOut> getTaxiwayOuts() {
        return taxiwayOuts;
    }

    public void setTaxiwayOuts(ArrayList<TaxiwayOut> taxiwayOuts) {
        this.taxiwayOuts = taxiwayOuts;
    }

    public ArrayList<TaxiwayIn> getTaxiwaysIn() {
        return taxiwaysIn;
    }

    public void setTaxiwaysIn(ArrayList<TaxiwayIn> taxiwaysIn) {
        this.taxiwaysIn = taxiwaysIn;
    }

    public ArrayList<TaxiwayOut> getTaxiwaysOut() {
        return taxiwayOuts;
    }

    public void setTaxiwaysOut(ArrayList<TaxiwayOut> taxiwayOuts) {
        this.taxiwayOuts = taxiwayOuts;
    }

    public ControlTower getTower() {
        return tower;
    }

    public void setTower(ControlTower tower) {
        this.tower = tower;
    }

    public ArrayList<Gate> getGates() {
        return gates;
    }

    public void setGates(ArrayList<Gate> gates) {
        this.gates = gates;
    }
}
