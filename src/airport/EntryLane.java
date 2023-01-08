package airport;

import enstabretagne.base.logger.Logger;

public class EntryLane {
    private static int totNumber = 0;
    private Airport airport;
    private boolean isAvailable;
    private Airplane currentAirplane;
    private int id;

    public EntryLane(Airport airport) {
        this.airport = airport;
        // Entry lane is free by default
        this.isAvailable = true;
        // Increase total number and assign id to terminal
        this.id = totNumber + 1;
        totNumber = id;
        // Add entry lane to airport's entry lanes list
        this.airport.getEntryLanes().add(this);
    }

    public void acceptPlane(Airplane airplane) {
        setAvailable(false);
        setCurrentAirplane(airplane);
        airplane.setEntryLane(this);
        Logger.Information(this, "acceptPlane", "Plane " + airplane.getId() + " is assigned entry lane " + this.id);
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
        EntryLane.totNumber = totNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
