package main;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

import java.time.DayOfWeek;

public class Utils {

    //String tomorrow = "";
    //DayOfWeek rainyDay = DayOfWeek.MONDAY;

    // Verifie si on est en heure d'ouverture de l'aeroport
    public boolean openHour(LogicalDateTime time, int phase){
        boolean isOpenHour = false;
        String myTime = time.toString();
        String[] separateDateTime = myTime.split(" ");
        String onlyTime = separateDateTime[1];
        //String[] onlyTime = separateDateTime[1].split(":");
        //String hour = onlyTime[0];

        if(phase == 1){  // Cas pour l'evenement Preparation
            if(onlyTime.compareTo("07:00:00") >= 0 && onlyTime.compareTo("21:10:00") <= 0) {
                isOpenHour = true;
            }
        } else {  // Cas pour l'evenement CreateAirplane
            if(onlyTime.compareTo("07:00:00") >= 0 && onlyTime.compareTo("21:30:00") <= 0) {
                isOpenHour = true;
            }
        }

        return isOpenHour;
    }

    // Verifie si on est en heure de pointe
    public boolean rushHour(LogicalDateTime time){
        boolean isRushHour = false;
        String myTime = time.toString();
        String[] separateDateTime = myTime.split(" ");
        String[] onlyTime = separateDateTime[1].split(":");
        String hour = onlyTime[0];

        if(hour.compareTo("07") >= 0 && hour.compareTo("10") < 0) {
            isRushHour = true;
        }
        if(hour.compareTo("17") >= 0 && hour.compareTo("19") < 0) {
            isRushHour = true;
        }
        return isRushHour;
    }


    // Reprogramme un evenement au lendemain matin
    public LogicalDateTime rescheduleToNextMorning(LogicalDateTime currentTime) {
        String formatedHour = " 07:00:00.00";

        currentTime = currentTime.add(LogicalDuration.ofDay(1));
        String[] separateDateTime = currentTime.toString().split(" ");
        String onlyDate = separateDateTime[0];

        String completeDate = onlyDate + formatedHour;
        LogicalDateTime nextMorning = new LogicalDateTime(completeDate);
        
        return nextMorning;
    }

   /* public boolean weather(LogicalDateTime currentTime){  // MARCHE PAS !!
        boolean isRaining = false;
        String today = currentTime.toString().split(" ")[0];
        boolean isEqual = today.compareTo(this.tomorrow) == 0;

        if(!isEqual && currentTime.getDayOfWeek() == this.rainyDay){
            this.tomorrow = currentTime.add(LogicalDuration.ofDay(1)).toString().split(" ")[0];
            isRaining = true;
            this.rainyDay = currentTime.add(LogicalDuration.ofDay(1)).getDayOfWeek();
            System.out.println("TEST WEATHER IF " + currentTime + this.tomorrow + this.rainyDay);
        }
        System.out.println("TEST WEATHER " + currentTime + this.tomorrow + this.rainyDay);
        return isRaining;
    }*/

}
