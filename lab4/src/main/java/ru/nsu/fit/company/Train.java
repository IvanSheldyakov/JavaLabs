package ru.nsu.fit.company;

import org.apache.log4j.Logger;

import java.util.*;

public class Train extends Thread implements Observable {

    private static final Logger logger = Logger.getLogger(Train.class);

    private final Map<String,Queue<Good>> goods = new HashMap<>();
    private final long speed;
    private long timeOfAmortization;
    private final DepartureStation departureStation;
    private final DestinationStation destinationStation;
    private final List<Map<String,Integer>> placesForGood;
    private final ArrayList<Observer> observers = new ArrayList<>();

    public Train(TrainConfiguration config, Stations stations) {
        this.placesForGood = config.getPlacesForGoods();
        this.speed = config.getSpeed();
        this.timeOfAmortization = config.getAmortizeTime();
        this.departureStation = stations.departureStation;
        this.destinationStation = stations.destinationStation;
    }


    @Override
    public void run() {
        while (!needToUtilize()) {
            try {
                departureStation.serviceTrain(this);
                logger.info("filled train " + getId());

                transferToDestinationStation();

                destinationStation.serviceTrain(this);
                logger.info("unloaded train " + getId());

                transferToDepartureStation();
            } catch (InterruptedException e) {
                interrupt();
                return;
            }
        }
        logger.info("train " + getId() + " need to replace");
        notifyAllObservers();
    }

    private void transferToDestinationStation() throws InterruptedException {
        Path pathForTransfer = departureStation.getPathForTransfer();
        logger.info(getId() + " got path for transfer");
        long transferTime = departureStation.getDistanceBetweenStations()/speed;
        Thread.sleep(transferTime);
        logger.info("train " + getId() + " arrived to destination station");
        timeOfAmortization -= transferTime;
        departureStation.returnPathForTransfer(pathForTransfer);
    }

    private void transferToDepartureStation() throws InterruptedException {
        Path pathForTransfer = destinationStation.getPathForTransfer();
        logger.info(getId() + " get path for back transfer");

        long transferTime = destinationStation.getDistanceBetweenStations()/speed;
        Thread.sleep(transferTime);
        logger.info(getId() + " train arrived to departure station");
        timeOfAmortization -= transferTime;

        destinationStation.returnPathForTransfer(pathForTransfer);
    }

    public int getCapacityByGood(String goodName) {
        for (var place : placesForGood) {
            int capacity = place.getOrDefault(goodName,-1);
            if (capacity != -1) {
                return capacity;
            }
        }
        throw new IllegalArgumentException("no such good");
    }

    public void addGood(Good good) {
        goods.putIfAbsent(good.getName(),new ArrayDeque<>());
        goods.get(good.getName()).add(good);
        logger.info("train " + getId() + " add good " + good.getId());
    }

    public Good takeGood(String goodName) {
        var queue = goods.getOrDefault(goodName,null);
        if (queue == null) {
            throw new IllegalArgumentException("No such good");
        }
        Good good = queue.poll();
        if (good == null) {
            throw new IllegalArgumentException("no more goods");
        }
        logger.info("train " + getId() + " unload good " + good.getId());
        return good;

    }


    private boolean needToUtilize() {
        boolean empty = true;
        for (var storage : goods.values()) {
            if(storage.size() != 0) {
                empty = false;
                break;
            }
        }
        return (empty && timeOfAmortization <= 0);
    }

    @Override
    public void notifyAllObservers() {
        for (var observer : observers) {
            observer.update();
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}
