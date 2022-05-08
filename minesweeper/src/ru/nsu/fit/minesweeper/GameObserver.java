package ru.nsu.fit.minesweeper;


import ru.nsu.fit.minesweeper.GameModel.GameStates;

public interface GameObserver {
    void update(GameStates state);
}
