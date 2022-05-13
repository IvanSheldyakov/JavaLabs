package ru.nsu.fit.company;

import java.util.ArrayList;

public class DestinationStation extends Station {


    public DestinationStation(StationsConfiguration config, ArrayList<GoodsStorage> storages) {
        this.amountOfServicePaths = config.getUnloadPaths();
        this.amountOfTransferPaths = config.getPathsFromDestinationStationToDepartureStation();
        this.distanceBetweenStations = config.getDistance();
        this.storages = storages;
        this.pathsForService = new PathsManager(amountOfServicePaths);
        this.pathsForTransfer = new PathsManager(amountOfTransferPaths);
    }

    public long getDistanceBetweenStations() {
        return distanceBetweenStations;
    }


    public void serviceTrain(Train train) {
        Path path = pathsForService.takePath();
        logger.info("get unload path");
        for (GoodsStorage goodsStorage : storages) {
            int currentSize = 0;
            long capacity = train.getCapacityByGood(goodsStorage.getGoodName());
            while (currentSize != capacity) {
                goodsStorage.addGood(train.takeGood(goodsStorage.getGoodName()));
                try {
                    Thread.sleep(goodsStorage.getGoodUnloadTime());
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
