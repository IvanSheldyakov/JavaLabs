package ru.nsu.fit.minesweeper.GameViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuButtonListener implements ActionListener {

    private StartMenuFrame menu;

    public MenuButtonListener(StartMenuFrame menu) {
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = menu.getTextFromPlayerNameField();
        if (!text.isEmpty()) {
            menu.notifyAllObservers();
            menu.setVisible(false);
            menu.dispose();
        }
    }
}
