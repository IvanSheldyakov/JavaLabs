package ru.nsu.fit.company;

import java.util.ArrayDeque;
import java.util.Queue;

public class PathsManager {

    private final long capacity;

    private final Queue<Path> paths = new ArrayDeque<>();

    public PathsManager(long capacity) {
        this.capacity = capacity;
        for (int i = 0; i < capacity; i++) {
            paths.add(new Path());
        }
    }

    public synchronized void returnPath(Path path) {
        while(capacity <= paths.size()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        paths.add(path);
        notifyAll();
    }

    public synchronized Path takePath() {
        while(paths.size() < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Path path = paths.poll();
        notifyAll();
        return path;
    }

}
