package airport;

import enstabretagne.base.logger.Logger;

public class Runway {
    private static int totNumber = 0;
    private Airport airport;
    private boolean isAvailable;
    private Airplane currentAirplane;
    private int id;

    public Runway(Airport airport) {
        this.airport = airport;
        // Runway is free by default
        this.isAvailable = true;
        // Increase total number and assign id to runway
        this.id = totNumber + 1;
        totNumber = id;
        // Add runway out to airport's runways list
        this.airport.getRunways().add(this);
    }

    public void acceptPlane(Airplane airplane) {
        // Make runway unavailable
        setAvailable(false);
        setCurrentAirplane(airplane);
        airplane.setRunway(this);
        //Logger.Information(this, "acceptPlane", "Plane " + airplane.getId() + " is assigned runway " + this.id + "; Time = " + airplane.getEngine().getCurrentDate());
    }

    public static int getTotNumber() {
        return totNumber;
    }

    public static void setTotNumber(int totNumber) {
        Runway.totNumber = totNumber;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
