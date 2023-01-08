package airport;

import enstabretagne.base.logger.Logger;

public class ExitLane {
    private static int totNumber = 0;
    private Airport airport;
    private boolean isAvailable;
    private Airplane currentAirplane;
    private int id;

    public ExitLane(Airport airport) {
        this.airport = airport;
        // Exit is free by default
        this.isAvailable = true;
        // Increase total number and assign id to terminal
        this.id = totNumber + 1;
        totNumber = id;
        // Add exit lane to airport's exit lanes list
        this.airport.getExitLanes().add(this);
    }

    public void acceptPlane(Airplane airplane) {
        // Make exit lane unavailable
        setAvailable(false);
        setCurrentAirplane(airplane);
        airplane.setExitLane(this);
        // Make terminal available
        airplane.getTerminal().setAvailable(true);
        Logger.Information(this, "acceptPlane", "Plane " + airplane.getId() + " is assigned exit lane " + this.id);
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
        ExitLane.totNumber = totNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
