package ru.nsu.fit.minesweeper.GameViewer;

import ru.nsu.fit.minesweeper.GameModel.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;

//interface controller TODO
// еще интерфейсы TODO

public class MainGameFrame extends JFrame implements GameModelObserver, FrameObserver {

    private GameModelForMinesweeper model;
    private int width = 500;
    private int height = 600;
    private ArrayList<JButton> cells = new ArrayList<>();
    private JPanel grid;
    private JPanel infoPanel;
    private JPanel basicPanel;
    private JTable scoreTable;
    private JScrollPane paneForTable;
    private JLabel flagsLabel;
    private JLabel timeLabel;
    private StartMenuFrame startMenuFrame;
    private FinishFrame finishFrame;
    private long secondsLeft;

    public MainGameFrame(GameModelForMinesweeper model) {
        super("Minesweeper");
        this.model = model;

        setBasicParametersOfFrame();

    }

    @Override
    public void update(GameStates state) {

        switch (state) {
            case IN_PROGRESS -> {draw();}
            case START -> {
                initStartMenu();
                model.setDifficultyLevel(DifficultyLevels.STANDARD); //will set up from start menu
                try {
                    SwingUtilities.invokeAndWait(this::initFrameElements);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            case VICTORY -> {
                draw();
                finishFrame = new FinishFrame("You win, your time " + Long.toString(secondsLeft));

            }
            case FAIL -> {
                draw();
                finishFrame = new FinishFrame("You stepped on mine");
            }

            default -> {}
        }
    }

    @Override
    public void update(long seconds) {
        secondsLeft = seconds;
        drawTimer(seconds);
    }

    @Override
    public void update(FrameStates state) {
        switch (state) {
            case START_MENU_CLOSED -> {
                model.setPlayerName(startMenuFrame.getTextFromPlayerNameField());
                setVisible(true);
            }
            default -> {}
        }
    }


    private void initStartMenu() {
        startMenuFrame = new StartMenuFrame();
        startMenuFrame.registerObserver(this);
    }


    private void draw() {
        drawGrid();
        drawFlagLabel();
    }

    private void drawFlagLabel() {
        int currentAmountOfFlags = model.getAmountOfFlags();
        int totalAmount = model.getDifficultyLevel().getMinesCount();
        flagsLabel.setText(String.format("%d :| %d flags",currentAmountOfFlags,totalAmount));
    }

    private void drawGrid() {
        int countOfRows = model.getDifficultyLevel().getSizeOfField().countOfRows;
        int countOfColumns = model.getDifficultyLevel().getSizeOfField().countOfColumns;
        for (int y = 0; y < countOfRows; y++) {
            for (int x = 0; x < countOfColumns; x++) {
                Cell cell = model.getCellByDot(new Dot(x,y));
                JButton button = cells.get(y * countOfColumns + x);
                if (cell.isHidden()) {
                    drawButton(button,cell.getFakeValue());
                } else {
                    drawButton(button,cell.getTrueValue());
                }
            }
        }
    }



    private void drawButton(JButton button, CellValue value) {
        switch (value) {
            case MINE ->
                    button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("pictures/mine.jpg"))));
            case ONE ->
                    button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("pictures/1.png"))));
            case TWO ->
                    button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("pictures/2.png"))));
            case THREE ->
                    button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("pictures/3.png"))));
            case FOUR ->
                    button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("pictures/4.png"))));
            case FIVE ->
                    button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("pictures/5.png"))));
            case SIX ->
                    button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("pictures/6.png"))));
            case SEVEN ->
                    button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("pictures/7.png"))));
            case EIGHT ->
                    button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("pictures/8.png"))));
            case FLAG ->
                    button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("pictures/flag.png"))));
            case EMPTY -> button.setIcon(null);
            case DEFAULT ->
                    button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("pictures/default.jpg"))));
        }
    }

    private void setBasicParametersOfFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);

    }

    private void drawTimer(long seconds) {
        timeLabel.setText("time - " + Long.toString(seconds));
    }


    private void initFrameElements() {
        initGrid();
        initTable();
        initInfoPanel();
        initBasicPanel();

    }

    private void initGrid() {
        int countOfRows = model.getDifficultyLevel().getSizeOfField().countOfRows;
        int countOfColumns = model.getDifficultyLevel().getSizeOfField().countOfColumns;
        grid = new JPanel();
        GridLayout layout = new GridLayout(countOfRows, countOfColumns, 1, 1);
        grid.setLayout(layout);
        for (int y = 0; y < countOfRows; y++) {
            for (int x = 0; x < countOfColumns; x++) {
                JButton cell = new JButton();
                cell.setName(Integer.toString(x) + " " + Integer.toString(y));
                cell.addMouseListener(new CellListener(model));
                cell.setPreferredSize(new Dimension(50,50));
                cell.setBackground(Color.WHITE);
                cell.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("pictures/default.jpg"))));
                cells.add(cell);
                grid.add(cell);
            }
        }
    }

    private void initTable() {
        String[][] data = model.getScoreTable();
        String[] columnsNames = {"№","Player","Result"};

        scoreTable = new JTable(data,columnsNames);
        paneForTable = new JScrollPane(scoreTable);
        paneForTable.setPreferredSize(new Dimension(500,100));
        paneForTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    private void initInfoPanel() {
        initFlagsLabel();
        initTimeLabel();

        infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(500,50));

        infoPanel.add(flagsLabel);
        infoPanel.add(timeLabel);
    }

    private void initFlagsLabel() {
        int totalAmount = model.getDifficultyLevel().getMinesCount();
        flagsLabel = new JLabel(String.format("0 | %d flags",totalAmount));
        flagsLabel.setPreferredSize(new Dimension(80,50));
    }

    private void initTimeLabel() {
        timeLabel = new JLabel("time - 0");
        timeLabel.setPreferredSize(new Dimension(80,50));
    }

    private void initBasicPanel() {
        basicPanel = new JPanel();
        basicPanel.setLayout(new BoxLayout(basicPanel,BoxLayout.Y_AXIS));
        basicPanel.add(paneForTable);
        basicPanel.add(grid);
        basicPanel.add(infoPanel);
        add(basicPanel);
    }



}
