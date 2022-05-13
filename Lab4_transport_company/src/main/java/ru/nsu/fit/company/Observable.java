package ru.nsu.fit.company;

public interface Observable {
    void notifyAllObservers();
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
}

