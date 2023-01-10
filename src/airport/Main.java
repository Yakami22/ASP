package airport;

import engine.SimuEngine;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;

public class Main {
    public static void main(String[] args) {
        // Time creation
        LogicalDateTime currentTime = new LogicalDateTime("01/01/2023 00:00:00.00");
        LogicalDateTime end = new LogicalDateTime("31/12/2023 16:35:03.10");

        // Engine creation
        SimuEngine engine = new SimuEngine(currentTime, end);

        // Airport creation
        Airport airport = new Airport("Goetghebeur International Airport", 1, 6, 1, 1);

        // Airplanes creation
        Airplane plane1 = new Airplane(engine, airport);
        Airplane plane2 = new Airplane(engine, airport);
        Airplane plane3 = new Airplane(engine, airport);
        Airplane plane4 = new Airplane(engine, airport);
        Airplane plane5 = new Airplane(engine, airport);
        Airplane plane6 = new Airplane(engine, airport);
        Airplane plane7 = new Airplane(engine, airport);

        // Add airplanes to the engine
        engine.getEntityList().add(plane1);
        engine.getEntityList().add(plane2);
        engine.getEntityList().add(plane3);
        engine.getEntityList().add(plane4);
        engine.getEntityList().add(plane5);
        engine.getEntityList().add(plane6);
        engine.getEntityList().add(plane7);

        // Start of simulation
        engine.run();

        Logger.Terminate();
    }
}
