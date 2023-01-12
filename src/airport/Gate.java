package airport;

import enstabretagne.base.logger.Logger;

public class Gate {
    private static int totNumber = 0;
    private Airport airport;
    private boolean isAvailable;
    private Airplane currentAirplane;
    private int id;

    public Gate(Airport airport) {
        this.airport = airport;
        // Gate is free by default
        this.isAvailable = true;
        // Increase total number and assign id to gate
        this.id = totNumber + 1;
        totNumber = id;
        // Add gate to airport's gate list
        this.airport.getGates().add(this);
    }

    public void acceptPlane(Airplane airplane) {
        // Make gate unavailable
        setAvailable(false);
        setCurrentAirplane(airplane);
        airplane.setGate(this);
        Logger.Information(this, "acceptPlane", "Gate " + this.id + " accepted plane " + airplane.getId() + "; Time = " + airplane.getEngine().getCurrentDate());
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
