package ru.nsu.fit.minesweeper;

import ru.nsu.fit.minesweeper.GameModel.GameModelForMinesweeper;
import ru.nsu.fit.minesweeper.GameViewer.GraphicMinesweeper;


public class Main {

    public static void main(String[] args) {

        GameModelForMinesweeper gameModel = new GameModelForMinesweeper();
        GraphicMinesweeper game = new GraphicMinesweeper(gameModel);
    }
}
