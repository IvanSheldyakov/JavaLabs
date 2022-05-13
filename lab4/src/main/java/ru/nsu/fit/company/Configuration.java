package ru.nsu.fit.company;

import java.util.Arrays;

public class Configuration {
    private GoodConfiguration[] goods;
    private StationsConfiguration stations;
    private TrainConfiguration[] trains;

    @Override
    public String toString() {
        return "Configuration{" +
                "goods=" + Arrays.toString(goods) +
                ", stations=" + stations +
                ", trains=" + Arrays.toString(trains) +
                '}';
    }

    public GoodConfiguration[] getGoods() {
        return goods;
    }

    public void setGoods(GoodConfiguration[] goods) {
        this.goods = goods;
    }

    public StationsConfiguration getStations() {
        return stations;
    }

    public void setStations(StationsConfiguration stations) {
        this.stations = stations;
    }

    public TrainConfiguration[] getTrains() {
        return trains;
    }

    public void setTrains(TrainConfiguration[] trains) {
        this.trains = trains;
    }
}
