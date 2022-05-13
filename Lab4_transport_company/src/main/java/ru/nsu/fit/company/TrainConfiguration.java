package ru.nsu.fit.company;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class TrainConfiguration {

    @SerializedName("places for goods")
    private List<Map<String,Integer>> placesForGoods;

    private long speed;

    @SerializedName("construct time")
    private long constructTime;

    @SerializedName("amortize time")
    private long amortizeTime;

    @Override
    public String toString() {
        return "TrainConfiguration{" +
                "placesForGoods=" + placesForGoods +
                ", speed=" + speed +
                ", constructTime=" + constructTime +
                ", amortizeTime=" + amortizeTime +
                '}';
    }

    public List<Map<String, Integer>> getPlacesForGoods() {
        return placesForGoods;
    }

    public void setPlacesForGoods(List<Map<String, Integer>> placesForGoods) {
        this.placesForGoods = placesForGoods;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public long getConstructTime() {
        return constructTime;
    }

    public void setConstructTime(long constructTime) {
        this.constructTime = constructTime;
    }

    public long getAmortizeTime() {
        return amortizeTime;
    }

    public void setAmortizeTime(long amortizeTime) {
        this.amortizeTime = amortizeTime;
    }
}
