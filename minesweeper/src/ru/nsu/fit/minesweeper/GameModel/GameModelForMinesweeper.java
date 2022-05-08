package ru.nsu.fit.minesweeper.GameModel;

import ru.nsu.fit.minesweeper.GameStateObservable;
import ru.nsu.fit.minesweeper.GameObserver;


import java.util.ArrayList;

//TODO interface or abs class

public class GameModelForMinesweeper implements Minesweeper, GameStateObservable, GameObserver {


    private final ArrayList<GameObserver> gameObservers = new ArrayList<>();
    private final ScoreTable scoreTable = new ScoreTableForMinesweeper();
    private MinesweeperEngine game;
    private final GameTimer timer = new TimerForMinesweeper();
    private String playerName;

    public GameModelForMinesweeper() {
        game = new MinesweeperEngine();
        game.registerObserver(timer);
        game.registerObserver(this);
    }

    @Override
    public GameStates getGameState() {
        return game.getGameState();
    }


    @Override
    public void startGame() {
        game.startGame();
    }

    @Override
    public void openCell(Dot dot) {
        game.openCell(dot);
    }

    @Override
    public int getAmountOfFlags() {
        return game.getAmountOfFlags();
    }

    @Override
    public void setDifficultyLevel(DifficultyLevels level) {
        game.setDifficultyLevel(level);
    }

    @Override
    public DifficultyLevel getDifficultyLevel() {
        return game.getDifficultyLevel();
    }

    @Override
    public void setFlagOnField(Dot dot) {
        game.setFlagOnField(dot);
    }

    @Override
    public Cell getCellByDot(Dot dot) throws IllegalArgumentException{
        return game.getCellByDot(dot);
    }

    public String[][] getScoreTable() {
        return scoreTable.getAsStringsArray();
    }

    @Override
    public void notifyAllObservers() {
        for (var observer : gameObservers) {
            observer.update(game.getGameState());
        }
    }

    @Override
    public void removeObserver(GameObserver gameObserver) {
        gameObservers.remove(gameObserver);
    }

    @Override
    public void registerObserver(GameObserver gameObserver) {
        gameObservers.add(gameObserver);
    }

    @Override
    public void update(GameStates state) {
        notifyAllObservers();
        switch (state) {
            case VICTORY -> {
                scoreTable.updateScoreTable(new ScoreTableRow(playerName,Long.toString(timer.getTimeLeftInSeconds())));
            }
            default -> {}
        }
    }


    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public void registerObserverForTimer(TimerObserver timerObserver) {
        timer.registerObserver(timerObserver);
    }

}
