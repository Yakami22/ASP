package airport;

import enstabretagne.base.logger.Logger;

public class TaxiwayIn {
    private static int totNumber = 0;
    private Airport airport;
    private boolean isAvailable;
    private Airplane currentAirplane;
    private int id;

    public TaxiwayIn(Airport airport) {
        this.airport = airport;
        // Taxiway in is free by default
        this.isAvailable = true;
        // Increase total number and assign id to terminal
        this.id = totNumber + 1;
        totNumber = id;
        // Add taxi way in to airport's taxiways in list
        this.airport.getTaxiwaysIn().add(this);
    }

    public void acceptPlane(Airplane airplane) {
        // Make taxiway in unavailable
        setAvailable(false);
        setCurrentAirplane(airplane);
        airplane.setTaxiwayIn(this);
        Logger.Information(this, "acceptPlane", "Plane " + airplane.getId() + " is assigned taxiway in " + this.id);
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
        TaxiwayIn.totNumber = totNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
