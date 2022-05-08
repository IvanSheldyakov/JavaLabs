package ru.nsu.fit.minesweeper.GameModel;

public interface ScoreTable {
    String[][] getAsStringsArray();
    void updateScoreTable(ScoreTableRow row);

}
