package ru.nsu.fit.minesweeper.GameModel;

public interface Timer {
    void start();
    void end();
    long getTimeLeftInSeconds();
}
