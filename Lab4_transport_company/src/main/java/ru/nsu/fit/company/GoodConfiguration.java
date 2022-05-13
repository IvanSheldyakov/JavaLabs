package ru.nsu.fit.company;

import com.google.gson.annotations.SerializedName;

public class GoodConfiguration {

    private String name;

    @SerializedName("capacity for departure station storage")
    private int capacityForDepartureStationStorage;

    @SerializedName("capacity for destination station storage")
    private int capacityForDestinationStorage;

    @SerializedName("create time")
    private long createTime;

    @SerializedName("consume time")
    private long consumeTime;

    @SerializedName("load time")
    private long loadTime;

    @SerializedName("unload time")
    private long unloadTime;

    @Override
    public String toString() {
        return "GoodConfiguration{" +
                "name='" + name + '\'' +
                ", capacityForDepartureStationStorage=" + capacityForDepartureStationStorage +
                ", capacityForDestinationStorage=" + capacityForDestinationStorage +
                ", createTime=" + createTime +
                ", consumeTime=" + consumeTime +
                ", loadTime=" + loadTime +
                ", unloadTime=" + unloadTime +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacityForDepartureStationStorage() {
        return capacityForDepartureStationStorage;
    }

    public void setCapacityForDepartureStationStorage(int capacityForDepartureStationStorage) {
        this.capacityForDepartureStationStorage = capacityForDepartureStationStorage;
    }

    public int getCapacityForDestinationStorage() {
        return capacityForDestinationStorage;
    }

    public void setCapacityForDestinationStorage(int capacityForDestinationStorage) {
        this.capacityForDestinationStorage = capacityForDestinationStorage;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public long getUnloadTime() {
        return unloadTime;
    }

    public void setUnloadTime(long unloadTime) {
        this.unloadTime = unloadTime;
    }
}
