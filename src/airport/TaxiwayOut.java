package airport;

import enstabretagne.base.logger.Logger;

public class TaxiwayOut {
    private static int totNumber = 0;
    private Airport airport;
    private boolean isAvailable;
    private Airplane currentAirplane;
    private int id;

    public TaxiwayOut(Airport airport) {
        this.airport = airport;
        // Exit is free by default
        this.isAvailable = true;
        // Increase total number and assign id to gate
        this.id = totNumber + 1;
        totNumber = id;
        // Add taxiway out to airport's taxiways out list
        this.airport.getTaxiwaysOut().add(this);
    }

    public void acceptPlane(Airplane airplane) {
        // Make taxiway out unavailable
        setAvailable(false);
        setCurrentAirplane(airplane);
        airplane.setTaxiwayOut(this);
        // Make gate available
        airplane.getGate().setAvailable(true);
        Logger.Information(this, "acceptPlane", "Plane " + airplane.getId() + " is assigned taxiway out " + this.id);
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Airplane getCurrentAirplane() {
        return currentAirplane;
    }

    public void setCurrentAirplane(Airplane currentAirplane) {
        this.currentAirplane = currentAirplane;
    }

    public static int getTotNumber() {
        return totNumber;
    }

    public static void setTotNumber(int totNumber) {
        TaxiwayOut.totNumber = totNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
