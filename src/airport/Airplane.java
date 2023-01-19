package airport;

import engine.SimEntity;
import engine.SimuEngine;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import events.NotifyArrival;


public class Airplane extends SimEntity {
    public int retardLanding = 0;
    public int retardTakeOff = 0;
    public int durationLanding = 0;
    public int durationTakeOff = 0;
    private int id = 0;
    private Airport airport;

    private Runway runway;
    private TaxiwayIn taxiwayIn;
    private TaxiwayOut taxiwayOut;
    private Gate gate;

    public Airplane(SimuEngine eng, Airport airport) {
        super(eng);
        this.airport =  airport;
        // Random number added to the default time
       // long random = (long) this.getEngine().getRandom().nextUniform(0, 20);
       // LogicalDateTime date = this.getEngine().getCurrentDate().add(LogicalDuration.ofMinutes(random));
        //NotifyArrival notifyArrival = new NotifyArrival(this, this.getEngine().getCurrentDate());
        //eng.postEvent(notifyArrival);
    }

    @Override
    public void init() {
    }

    public void creation() {
        //Logger.Information(this, "creation", "A plane was created; Time = " + getEngine().getCurrentDate());
    }

    public void approach() {
        //Logger.Information(this, "approach", "Plane " + this.getId() + " has started its approach; Time = " + getEngine().getCurrentDate());
    }
    public void land() {
        //Logger.Information(this, "land", "Plane " + this.getId() + " is landing on runway " + this.getRunway().getId() + "; Time = " + getEngine().getCurrentDate());
    }

    public void flightOver() {
        this.getAirport().getTower().notifyLanding(this);
    }

    public void takeOff() {
        this.getAirport().getTower().notifyTakeOff(this);
    }

    public void retard() {
        Logger.Information(this, "retard", "Plane " + this.getId() + " is " + this.retardLanding + " min late for landing");
        Logger.Information(this, "retard", "Plane " + this.getId() + " is " + this.retardTakeOff + " min late for takeOff");
    }

    public void rideToTrack() {
        //Logger.Information(this, "rideToTrack", "Plane " + this.getId() + " is riding on taxiway out " + this.getTaxiwayOut().getId() + "; Time = " + getEngine().getCurrentDate());
    }

    public void rideToGate() {
        this.getRunway().setAvailable(true);
        //Logger.Information(this, "rideToGate", "Plane " + this.getId() + " is riding on taxiway in " + this.getTaxiwayIn().getId() + "; Time = " + getEngine().getCurrentDate());
    }

    public void unloadPassengers() {
        //Logger.Information(this, "unloadPassengers", "Plane " + this.getId() + " is unloading its passengers; Time = " + getEngine().getCurrentDate());
    }

    public void preparation() {
        //Logger.Information(this, "preparation", "Plane " + this.getId() + " is preparing for next flight; Time = " + getEngine().getCurrentDate());
        this.setId(0);
    }

    public void loadPassengers() {
        //Logger.Information(this, "loadPassengers", "Unidentified plane is loading its passengers; Time = " + getEngine().getCurrentDate());
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

    public TaxiwayIn getTaxiwayIn() {
        return taxiwayIn;
    }

    public void setTaxiwayIn(TaxiwayIn taxiwayIn) {
        this.taxiwayIn = taxiwayIn;
    }

    public TaxiwayOut getTaxiwayOut() {
        return taxiwayOut;
    }

    public Runway getRunway() {
        return runway;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
    }

    public void setTaxiwayOut(TaxiwayOut taxiwayOut) {
        this.taxiwayOut = taxiwayOut;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }
}
