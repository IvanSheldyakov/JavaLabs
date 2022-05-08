package ru.nsu.fit.minesweeper.GameViewer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StartMenuFrame extends JFrame implements FrameObservable {

    private int width = 200;
    private int height = 200;
    private JTextField fieldForName;
    private final ArrayList<FrameObserver> frameObservers = new ArrayList<>();
    private FrameStates frameState;

    public StartMenuFrame() {
        setBasicParametersOfFrame();
        initComponents();
        setVisible(true);
        frameState = FrameStates.START_MENU_OPENED;
    }

    @Override
    public void dispose() {
        frameState = FrameStates.START_MENU_CLOSED;
        notifyAllObservers();
        super.dispose();

    }

    public String getTextFromPlayerNameField() {
        return fieldForName.getText();
    }

    private void setBasicParametersOfFrame() {
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents(){
        JPanel basicPanel = new JPanel();
        basicPanel.setLayout(new BoxLayout(basicPanel,BoxLayout.Y_AXIS));
        JLabel enterName = new JLabel("Enter your name:");
        enterName.setPreferredSize(new Dimension(100,50));
        fieldForName = new JTextField(40);

        JButton button = new JButton("start");

        button.addActionListener(new MenuButtonListener(this));

        JPanel bPanel = new JPanel();
        bPanel.setLayout(new BorderLayout());

        bPanel.add(button,BorderLayout.CENTER);
        basicPanel.add(enterName,BorderLayout.CENTER);
        basicPanel.add(fieldForName);
        basicPanel.add(bPanel);
        add(basicPanel);
    }





    @Override
    public void notifyAllObservers() {
        for (var observer : frameObservers) {
            observer.update(frameState);
        }
    }

    @Override
    public void removeObserver(FrameObserver observer) {frameObservers.remove(observer);
    }

    @Override
    public void registerObserver(FrameObserver observer) {
        frameObservers.add(observer);
    }
}
