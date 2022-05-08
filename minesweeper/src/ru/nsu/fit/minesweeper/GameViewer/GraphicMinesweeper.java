package ru.nsu.fit.minesweeper.GameViewer;

import ru.nsu.fit.minesweeper.GameModel.GameModelForMinesweeper;

public class GraphicMinesweeper {

    private MainGameFrame mainFrame;

    public GraphicMinesweeper(GameModelForMinesweeper model) {
        mainFrame = new MainGameFrame(model);
        model.registerObserver(mainFrame);
        model.registerObserverForTimer(mainFrame);
        model.startGame();
    }
}
