package ru.nsu.fit.minesweeper.GameViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinishFrame extends JFrame {

    private int width = 200;
    private int height = 200;

    public FinishFrame(String textForPlayer) {
        setBasicParametersOfFrame();
        initComponents(textForPlayer);
        setVisible(true);
    }

    private void setBasicParametersOfFrame() {
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);

    }

    private void initComponents(String textForPlayer) {
        JPanel basicPanel = new JPanel();
        basicPanel.setLayout(new BorderLayout());
        JLabel label = new JLabel(textForPlayer,SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(100,100));


        JButton button = new JButton("finish");
        button.setPreferredSize(new Dimension(50,50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        basicPanel.add(label,BorderLayout.PAGE_START);
        basicPanel.add(button,BorderLayout.CENTER);
        add(basicPanel);
    }
}
