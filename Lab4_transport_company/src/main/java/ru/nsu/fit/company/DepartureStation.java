package ru.nsu.fit.company;

import java.util.ArrayList;

public class DepartureStation extends Station {


    public DepartureStation(StationsConfiguration config, ArrayList<GoodsStorage> storages) {
        this.amountOfServicePaths = config.getLoadPaths();
        this.distanceBetweenStations = config.getDistance();
        this.amountOfTransferPaths = config.getPathsFromDepartureStationToDestinationStation();
        this.storages = storages;
        this.pathsForService = new PathsManager(amountOfServicePaths);
        this.pathsForTransfer = new PathsManager(amountOfTransferPaths);
    }

    public long getDistanceBetweenStations() {
        return distanceBetweenStations;
    }

    public void serviceTrain(Train train) {
        Path path = pathsForService.takePath();
        logger.info(train.getId() + " got load path");
        for (GoodsStorage goodsStorage : storages) {
            int currentSize = 0;
            GoodsStorage storage = goodsStorage;
            long capacity = train.getCapacityByGood(storage.getGoodName());
            while (currentSize != capacity) {
                train.addGood(storage.takeGood());
                try {
                    Thread.sleep(storage.getGoodLoadTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                currentSize++;
            }
        }
        pathsForService.returnPath(path);

    }

    public Path getPathForTransfer() {
        return pathsForTransfer.takePath();
    }

    public void returnPathForTransfer(Path path) {
        pathsForTransfer.returnPath(path);
    }


}
