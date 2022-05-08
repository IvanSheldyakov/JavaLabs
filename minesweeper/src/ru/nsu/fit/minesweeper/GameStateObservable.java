package ru.nsu.fit.minesweeper;

public interface GameStateObservable {
    void notifyAllObservers();
    void removeObserver(GameObserver gameObserver);
    void registerObserver(GameObserver gameObserver);
}
