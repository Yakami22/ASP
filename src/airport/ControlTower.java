package airport;

import enstabretagne.base.logger.Logger;

public class ControlTower {
    private Airport airport;
    private int idCounter;

    public ControlTower(Airport airport, int idCounter) {
        this.airport = airport;
        this.idCounter = idCounter;
    }

    public void identifyAirplane(Airplane airplane, String action) {
        // Assign an id to the plane if it does not already have one
        if (airplane.getId() == 0 ) {
            Logger.Information(this, "identifyAirplane", "Unidentified airplane wants to " + action);
            airplane.setId(this.idCounter);
            Logger.Information(this, "identifyAirplane", "Plane was assigned id " + this.idCounter);
            // Increment the tower id counter (for future planes)
            setIdCounter(this.idCounter+1);
        }
    }

    public void authorizeLanding(Airplane plane, EntryLane entryLane) {
        // Make landing & entry lane not available
        this.getAirport().setLandingLaneAvailable(false);
        entryLane.acceptPlane(plane);
        Logger.Information(this, "authorizeLanding", "Plane " + plane.getId() + " was given authorization to land");
    }

    public void denyLanding(Airplane plane) {
        Logger.Information(this, "denyLanding", "Plane " + plane.getId() + " was denied landing");
    }

    public void authorizeExit(Airplane plane, ExitLane exitLane) {
        // Make exit lane not available
        exitLane.acceptPlane(plane);
    }

    public void denyExit(Airplane plane) {
        Logger.Information(this, "denyExit", "Plane " + plane.getId() + " was denied leaving terminal " + plane.getTerminal().getId());
    }

    public void authorizeTakeOff(Airplane plane) {
        // Make take off lane unavailable and exit lane available
        this.getAirport().setTakeOffLaneAvailable(false);
        plane.getExitLane().setAvailable(true);
        Logger.Information(this, "authorizeTakeOff", "Plane " + plane.getId() + " was given authorization to take off");
    }

    public void notifyTakeOff(Airplane plane) {
        // Make take off lane available
        this.getAirport().setTakeOffLaneAvailable(true);
        Logger.Information(this, "notifyTakeOff", "Plane " + plane.getId() + " just took off");
        plane.setId(0);
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public int getIdCounter() {
        return idCounter;
    }
    public void setIdCounter(int idCounter) {
        this.idCounter = idCounter;
    }
}
