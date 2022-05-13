package ru.nsu.fit.company;

import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        Configuration config;
        ArrayList<GoodsFactory> factories = new ArrayList<>();
        ArrayList<GoodsStorage> departureStationStorages = new ArrayList<>();
        ArrayList<GoodsStorage> destinationStationStorages = new ArrayList<>();
        ArrayList<Consumer> consumers = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/config.json"))) {
            Gson gson = new Gson();
            config = gson.fromJson(reader,Configuration.class);

            for (var goodConfig : config.getGoods()) {
                GoodsStorage storageForDepartureStation = new GoodsStorage(goodConfig,goodConfig.getCapacityForDepartureStationStorage());
                GoodsStorage storageForDestinationStation = new GoodsStorage(goodConfig,goodConfig.getCapacityForDepartureStationStorage());

                departureStationStorages.add(storageForDepartureStation);
                destinationStationStorages.add(storageForDestinationStation);

                GoodsFactory factory = new GoodsFactory(storageForDepartureStation,goodConfig.getCreateTime());
                factory.start();
                factories.add(factory);

                Consumer consumer = new Consumer(storageForDestinationStation,goodConfig.getConsumeTime());
                consumer.start();
                consumers.add(consumer);
            }

            DepartureStation departureStation = new DepartureStation(config.getStations(), departureStationStorages);
            DestinationStation destinationStation = new DestinationStation(config.getStations(),destinationStationStorages);
            Stations stations = new Stations(departureStation,destinationStation);

            Depot depot = new Depot(config.getTrains(),stations);
            depot.launchTrains();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
