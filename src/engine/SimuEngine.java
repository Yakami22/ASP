package engine;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.basics.SortedList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SimuEngine implements Runnable {
    final private SortedList<SimEvent> sortedEventList;
    final private List<SimEntity> entityList;
    private LogicalDateTime currentDate;
    final private LogicalDateTime endDate;
    final private MoreRandom random = new MoreRandom();


    public SimuEngine(LogicalDateTime currentDate, LogicalDateTime endDate) {
        this.sortedEventList = new SortedList<>();
        this.currentDate = currentDate;
        this.endDate = endDate;
        this.entityList = new ArrayList<>();
    }

    public void postEvent(SimEvent simEvent) {
        sortedEventList.add(simEvent);
    }

    public SimEvent getCurrentEvent() {
        return sortedEventList.first();
    }

    public LogicalDateTime getCurrentDate() {
        return currentDate;
    }

    public MoreRandom getRandom() {
        return random;
    }

    public boolean hasNextEvent() { return this.sortedEventList.size() > 0;}
    /*
    public boolean simulationStep() {
        if (!hasNextEvent()) return false;
        SimEvent currentEvent = getCurrentEvent();
        currentDate = currentEvent.getDateOccurence();
        if (currentDate.compareTo(endDate) > 0) return false;
        sortedEventList.remove(currentEvent);
        currentEvent.process();
        System.gc();
        return true;
    }

    public void simulationLoop() {
        while (simulationStep());
    }*/

    public void addEntity(SimEntity entity) {
        entityList.add(entity);
    }

    public List<SimEntity> getEntityList() {
        return entityList;
    }

    public List<SimEntity> request(Predicate<SimEntity> filter) {
        List<SimEntity> result = new ArrayList<>();
        for (SimEntity entity : entityList) {
            if (filter.test(entity)) result.add(entity);
        }
        return result;
    }

    @Override
    public void run() {
        while (this.hasNextEvent()) {
            SimEvent currentEvent = getCurrentEvent();
            currentDate = currentEvent.getDateOccurence();
            sortedEventList.remove(currentEvent);
            if (currentDate.compareTo(endDate) > 0) {
                break;
            }
            currentEvent.process();
            System.gc();
        }
    }
}
