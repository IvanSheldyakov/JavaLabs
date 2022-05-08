package ru.nsu.fit.minesweeper.GameViewer;

public interface FrameObservable {
    void notifyAllObservers();
    void removeObserver(FrameObserver observer);
    void registerObserver(FrameObserver observer);
}
