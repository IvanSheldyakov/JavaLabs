package ru.nsu.fit.company;

import com.google.gson.annotations.SerializedName;

public class StationsConfiguration {

    private long distance;

    @SerializedName("load paths")
    private int loadPaths;

    @SerializedName("unload paths")
    private int unloadPaths;

    @SerializedName("paths from departure station to destination station")
    private int pathsFromDepartureStationToDestinationStation;

    @SerializedName("paths from destination station to departure station")
    private int pathsFromDestinationStationToDepartureStation;

    @Override
    public String toString() {
        return "StationsConfiguration{" +
                "distance=" + distance +
                ", loadPaths=" + loadPaths +
                ", unloadPaths=" + unloadPaths +
                ", pathsFromDepartureStationToDestinationStation=" + pathsFromDepartureStationToDestinationStation +
                ", pathsFromDestinationStationToDepartureStation=" + pathsFromDestinationStationToDepartureStation +
                '}';
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public int getLoadPaths() {
        return loadPaths;
    }

    public void setLoadPaths(int loadPaths) {
        this.loadPaths = loadPaths;
    }

    public int getUnloadPaths() {
        return unloadPaths;
    }

    public void setUnloadPaths(int unloadPaths) {
        this.unloadPaths = unloadPaths;
    }

    public int getPathsFromDepartureStationToDestinationStation() {
        return pathsFromDepartureStationToDestinationStation;
    }

    public void setPathsFromDepartureStationToDestinationStation(int pathsFromDepartureStationToDestinationStation) {
        this.pathsFromDepartureStationToDestinationStation = pathsFromDepartureStationToDestinationStation;
    }

    public int getPathsFromDestinationStationToDepartureStation() {
        return pathsFromDestinationStationToDepartureStation;
    }

    public void setPathsFromDestinationStationToDepartureStation(int pathsFromDestinationStationToDepartureStation) {
        this.pathsFromDestinationStationToDepartureStation = pathsFromDestinationStationToDepartureStation;
    }
}
