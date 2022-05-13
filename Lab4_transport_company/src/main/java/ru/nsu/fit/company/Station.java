package ru.nsu.fit.company;

import org.apache.log4j.Logger;

import java.util.ArrayList;

public abstract class Station {
    protected static final Logger logger = Logger.getLogger(Station.class);

    protected ArrayList<GoodsStorage> storages;
    protected int amountOfServicePaths;
    protected int amountOfTransferPaths;
    protected long distanceBetweenStations;
    protected PathsManager pathsForService;
    protected PathsManager pathsForTransfer;

    public abstract long getDistanceBetweenStations();
    public abstract void serviceTrain(Train train);
    public abstract Path getPathForTransfer();
    public abstract void returnPathForTransfer(Path path);

}
