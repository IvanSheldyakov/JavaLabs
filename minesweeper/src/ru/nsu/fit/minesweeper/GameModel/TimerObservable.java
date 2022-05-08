package ru.nsu.fit.minesweeper.GameModel;



public interface TimerObservable {
    void notifyAllObservers();
    void removeObserver(TimerObserver observer);
    void registerObserver(TimerObserver observer);
}
