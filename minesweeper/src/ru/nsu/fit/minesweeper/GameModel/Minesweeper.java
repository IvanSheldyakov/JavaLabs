package ru.nsu.fit.minesweeper.GameModel;

public interface Minesweeper {
    void startGame();
    void openCell(Dot dot);
    void setFlagOnField(Dot dot);
    Cell getCellByDot(Dot dot);
    GameStates getGameState();
    int getAmountOfFlags();
    void setDifficultyLevel(DifficultyLevels level);
    DifficultyLevel getDifficultyLevel();
}
