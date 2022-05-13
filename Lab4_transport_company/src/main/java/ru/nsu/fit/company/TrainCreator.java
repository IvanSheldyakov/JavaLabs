package ru.nsu.fit.company;

public class TrainCreator extends Thread {

    private final Train train;
    private final long timeToCreate;
    private boolean ready;

    public TrainCreator(TrainConfiguration config, Stations stations) {
        train = new Train(config,stations);
        timeToCreate = config.getConstructTime();
    }

    @Override
    public void run() {
        try {
            sleep(timeToCreate);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ready = true;
    }

    public Train getTrain() {
        if (ready) {
            ready = false;
            return train;
        } else {
            throw new IllegalThreadStateException("not ready");
        }
    }
}
