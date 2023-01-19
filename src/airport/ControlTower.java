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
            //Logger.Information(this, "identifyAirplane", "Unidentified airplane wants to " + action + "; Time = " + airplane.getEngine().getCurrentDate());
            airplane.setId(this.idCounter);
            //Logger.Information(this, "identifyAirplane", "Plane was assigned id " + this.idCounter + "; Time = " + airplane.getEngine().getCurrentDate());
            // Increment the tower id counter (for future planes)
           if(this.idCounter >= 99){
               setIdCounter(1);
           } else {
               setIdCounter(this.idCounter+1);
           }


        }
    }

    public void authorizeLanding(Airplane plane, TaxiwayIn taxiWayIn, Runway runway) {
        // Make runway & taxiway in not available
        runway.acceptPlane(plane);
        taxiWayIn.acceptPlane(plane);
        //Logger.Information(this, "authorizeLanding", "Plane " + plane.getId() + " was given authorization to land; Time = " + plane.getEngine().getCurrentDate());
    }

    public void denyLanding(Airplane plane) {
        //Logger.Information(this, "denyLanding", "Plane " + plane.getId() + " was denied landing; Time = " + plane.getEngine().getCurrentDate());
    }

    public void authorizeExit(Airplane plane, TaxiwayOut taxiWayOut) {
        // Make taxiway out not available
        taxiWayOut.acceptPlane(plane);
    }

    public void denyExit(Airplane plane) {
        //Logger.Information(this, "denyExit", "Plane " + plane.getId() + " was denied leaving gate " + plane.getGate().getId() + "; Time = " + plane.getEngine().getCurrentDate());
    }

    public void authorizeTakeOff(Airplane plane) {
        // Make runway unavailable and taxiway out available
        plane.getRunway().setAvailable(false);
        plane.getTaxiwayOut().setAvailable(true);
        //Logger.Information(this, "authorizeTakeOff", "Plane " + plane.getId() + " was given authorization to take off on runway " + plane.getRunway().getId() + "; Time = " + plane.getEngine().getCurrentDate());
    }

    public void notifyLanding(Airplane plane) {
        // Make runway & taxi way in available
        plane.getTaxiwayIn().setAvailable(true);
        //Logger.Information(this, "notifyLanding", "Plane " + plane.getId() + "'s flight is over; Time = " + plane.getEngine().getCurrentDate());
        //Logger.Information(this, "acceptPlane", "Runway " + plane.getRunway().getId() + " & taxiway in " + plane.getTaxiwayIn().getId() + " are now available; Time = " + plane.getEngine().getCurrentDate());
    }

    public void notifyTakeOff(Airplane plane) {
        // Make runway available
        plane.getRunway().setAvailable(true);
        //Logger.Information(this, "notifyTakeOff", "Plane " + plane.getId() + " just took off; Time = " + plane.getEngine().getCurrentDate());
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