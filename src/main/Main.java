package main;

import airport.Airplane;
import airport.Airport;
import engine.SimuEngine;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import events.CreateAirplane;

import java.time.DayOfWeek;

public class Main {
    public static void main(String[] args) {
        Utils utils = new Utils();

        // Time creation
        LogicalDateTime currentTime = new LogicalDateTime("01/01/2023 00:00:00.00");
        LogicalDateTime end = new LogicalDateTime("01/04/2023 00:00:00.00");

        // Engine creation
        SimuEngine engine = new SimuEngine(currentTime, end);

        // Airport creation
        Airport airport = new Airport("Goetghebeur International Airport", 1, 6, 1, 1);

        // Airplanes creation
        LogicalDateTime time = engine.getCurrentDate();
        DayOfWeek dayOfWeek = time.getDayOfWeek();
        long random;

        //while(time.compareTo(end) < 0) {
        //for(int i = 0; i<200; i++) {
        for(;time.compareTo(end) < 0;) {
            if(utils.openHour(time, 0)) {  // Si on est entre 07H et 21H30 (pour garder une marge)
                if ((dayOfWeek.ordinal() & 6) == 6 || (dayOfWeek.ordinal() & 7) == 7) {
                    random = (long) engine.getRandom().nextUniform(30, 50);
                } else {
                    if (utils.rushHour(time)) {
                        random = (long) engine.getRandom().nextUniform(5, 15);
                    } else {
                        random = (long) engine.getRandom().nextUniform(10, 30);
                    }
                }

                time = time.add(LogicalDuration.ofMinutes(random));

                Airplane airplane = new Airplane(engine, airport);
                CreateAirplane createAirplane = new CreateAirplane(airplane, time);

                engine.postEvent(createAirplane);
                engine.getEntityList().add(airplane);
            } else {
                String hour = " 07:00:00.00";
                String date = time.add(LogicalDuration.ofDay(1)).toString().split(" ")[0];
                LogicalDateTime nextMorning = new LogicalDateTime(date + hour);
                time = nextMorning;
            }
        }

       /* Airplane plane1 = new Airplane(engine, airport);
        engine.getCurrentDate().add(LogicalDuration.ofMinutes(random));
        Airplane plane2 = new Airplane(engine, airport);
        Airplane plane3 = new Airplane(engine, airport);
        Airplane plane4 = new Airplane(engine, airport);
        Airplane plane5 = new Airplane(engine, airport);
        Airplane plane6 = new Airplane(engine, airport);
        Airplane plane7 = new Airplane(engine, airport);*/

        // Add airplanes to the engine
       /* engine.getEntityList().add(plane1);
        engine.getEntityList().add(plane2);
        engine.getEntityList().add(plane3);
        engine.getEntityList().add(plane4);
        engine.getEntityList().add(plane5);
        engine.getEntityList().add(plane6);
        engine.getEntityList().add(plane7);*/

        // Start of simulation
        engine.run();

        Logger.Terminate();
    }
}
