package ru.nsu.fit.company;

import org.apache.log4j.Logger;

public class Consumer extends Thread {

    private static final Logger logger = Logger.getLogger(Consumer.class);

    private final GoodsStorage storage;
    private final long consumeTime;

    public Consumer(GoodsStorage storage, long consumeTime) {
        this.storage = storage;
        this.consumeTime = consumeTime;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Good good = storage.takeGood();
                logger.info("Consumer " + getId() + " took good " + good.getId());
                Thread.sleep(consumeTime);
                logger.info("Consumer " + getId() + " consumed " + good.getId());

            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
