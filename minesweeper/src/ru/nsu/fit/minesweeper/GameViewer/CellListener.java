package ru.nsu.fit.minesweeper.GameViewer;

import ru.nsu.fit.minesweeper.GameModel.Dot;
import ru.nsu.fit.minesweeper.GameModel.GameModelForMinesweeper;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellListener extends MouseAdapter {

    private GameModelForMinesweeper model;

    public CellListener(GameModelForMinesweeper model) {
        this.model = model;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        if (e.getButton() == MouseEvent.BUTTON1) {
            model.openCell(new Dot(button.getName()));
        }
        else if (e.getButton() == MouseEvent.BUTTON3) {
            model.setFlagOnField(new Dot(button.getName()));
        }
    }
}
