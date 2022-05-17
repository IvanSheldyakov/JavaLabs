package ru.nsu.fit.company;

import org.apache.log4j.Logger;

public class GoodsFactory extends Thread {

    private static final Logger logger = Logger.getLogger(GoodsFactory.class);

    private final String goodName;
    private final long createTime;
    private final GoodsStorage storage;

    public GoodsFactory(GoodsStorage storage, long createTime) {
        this.createTime = createTime;
        this.storage = storage;
        this.goodName = storage.getGoodName();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                logger.info("Factory " + getId() + " starts creating good");
                sleep(createTime);
                Good good = new Good(goodName);
                logger.info("Factory "  + getId() + " created good " + good.getId());
                storage.addGood(good);

            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}
