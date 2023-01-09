package airport;

import enstabretagne.base.logger.Logger;

public class Terminal {
    private static int totNumber = 0;
    private Airport airport;
    private boolean isAvailable;
    private Airplane currentAirplane;
    private int id;

    public Terminal(Airport airport) {
        this.airport = airport;
        // Terminal is free by default
        this.isAvailable = true;
        // Increase total number and assign id to terminal
        this.id = totNumber + 1;
        totNumber = id;
        // Add terminal to airport's terminal list
        this.airport.getTerminals().add(this);
    }

    public void acceptPlane(Airplane airplane) {
        // Make terminal unavailable
        setAvailable(false);
        setCurrentAirplane(airplane);
        airplane.setTerminal(this);
        // Make runway & taxiway in available
        airplane.getTaxiwayIn().setAvailable(true);
        airplane.getRunway().setAvailable(true);
        Logger.Information(this, "acceptPlane", "Terminal " + this.id + " accepted plane " + airplane.getId());
        Logger.Information(this, "acceptPlane", "Runway " + airplane.getRunway().getId() + " & taxiway in " + airplane.getTaxiwayIn().getId() + " are now available");
        // Set plane id to 0
        airplane.setId(0);
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