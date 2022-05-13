package ru.nsu.fit.company;

import org.apache.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Queue;

public class GoodsStorage {

    private static final Logger logger = Logger.getLogger(GoodsStorage.class);

    private final String goodName;
    private final long capacity;
    private final long goodLoadTime;
    private final long goodUnloadTime;
    private final Queue<Good> goods = new ArrayDeque<>();


    public GoodsStorage(GoodConfiguration config, long capacity) {
        this.goodName = config.getName();
        this.goodLoadTime = config.getLoadTime();
        this.goodUnloadTime = config.getUnloadTime();
        this.capacity = capacity;
    }

    public long getGoodLoadTime() {
        return goodLoadTime;
    }

    public long getGoodUnloadTime() {
        return goodUnloadTime;
    }

    public String getGoodName() {
        return goodName;
    }

    public synchronized Good takeGood() {
        while(goods.size() < 1) {
            try {
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        Good good = goods.poll();
        notifyAll();
        logger.info("took good " + good.getId() + " from storage");
        return good;
    }

    public synchronized void addGood(Good good) {
        while(goods.size() >= capacity) {
            try {
                wait();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        goods.add(good);
        logger.info("add good " + good.getId() + " to storage");
        notifyAll();

    }


}
