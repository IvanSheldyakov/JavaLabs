package ru.nsu.fit.company;

import org.apache.log4j.Logger;

import java.util.ArrayList;

public class Depot implements Observer {

    private static final Logger logger = Logger.getLogger(Depot.class);

    private final ArrayList<Train> trains = new ArrayList<>();
    private final TrainConfiguration[] configs;
    private final Stations stations;

    public Depot(TrainConfiguration[] configs, Stations stations) {
        this.configs = configs;
        this.stations = stations;

        ArrayList<TrainCreator> creators = initCreatorsOfTrainsAndStart();
        getTrainsFromCreators(creators);
    }

    @Override
    public void update() {
        createAndStartNewTrain();
    }

    public void launchTrains() {
        for (var train : trains) {
            train.start();
            logger.info("new train " + train.getId() + " launched");
        }
    }

    private ArrayList<TrainCreator> initCreatorsOfTrainsAndStart() {
        ArrayList<TrainCreator> creators = new ArrayList<>();
        for (TrainConfiguration config : configs) {
            TrainCreator creator = new TrainCreator(config, stations);
            creators.add(creator);
            creator.start();
        }
        return creators;
    }

    private void getTrainsFromCreators(ArrayList<TrainCreator> creators) {
        for (int i = 0; i < configs.length; i++) {
            TrainCreator creator = creators.get(i);
            if (creator.isAlive()) {
                try {
                    creator.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }

            }
            addTrain(creator);
        }
    }



    private void createAndStartNewTrain() {
        for (int i = 0; i < trains.size(); i++) {
            Train train = trains.get(i);
            if (!train.isAlive()) {
                trains.remove(i);
                TrainCreator creator = new TrainCreator(configs[i], stations);
                creator.start();
                try {
                    creator.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }

                addTrain(creator,i);
                trains.get(i).start();
                logger.info("new train " + train.getId() + " launched");
            }
        }
    }

    private void addTrain(TrainCreator creator, int idx) {
        Train newTrain = creator.getTrain();
        logger.info("new train " + newTrain.getId() + " created");
        newTrain.registerObserver(this);
        trains.add(idx, newTrain);
    }

    private void addTrain(TrainCreator creator) {
        Train newTrain = creator.getTrain();
        logger.info("new train " + newTrain.getId() + " created");
        newTrain.registerObserver(this);
        trains.add(newTrain);
    }


}
