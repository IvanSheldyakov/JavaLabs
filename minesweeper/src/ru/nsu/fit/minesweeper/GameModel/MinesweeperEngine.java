package ru.nsu.fit.minesweeper.GameModel;

import ru.nsu.fit.minesweeper.GameStateObservable;
import ru.nsu.fit.minesweeper.GameObserver;

import java.util.ArrayList;
import java.util.Random;

//TODO level 1 - 3

public class MinesweeperEngine implements Minesweeper, GameStateObservable {

    private Cell[][] field;
    private SizeOfField size;
    private int minesCount;
    private int flagsCount = 0;
    private GameStates gameState = GameStates.START;
    private int minesFound = 0;
    private int countOfOpenedCells = 0;
    private int countOfSafeCells;
    private final ArrayList<GameObserver> gameObservers = new ArrayList<>();
    private DifficultyLevel level;



    @Override
    public void startGame() {
        notifyAllObservers();
    }

    @Override
    public Cell getCellByDot(Dot dot) throws IllegalArgumentException {
        if (!isDotInField(dot)) {
            throw new IllegalArgumentException("dot out of bounds");
        }
        return new Cell(field[dot.y][dot.x]);
    }

    @Override
    public GameStates getGameState() {
        return gameState;
    }

    @Override
    public int getAmountOfFlags() {
        return flagsCount;
    }

    @Override
    public void setDifficultyLevel(DifficultyLevels level) {
        switch (level) {
            default -> this.level = new StandardDifficultyLevel();
        }
    }

    @Override
    public DifficultyLevel getDifficultyLevel() {
        return level;
    }

    @Override
    public void openCell(Dot dot) {
        if (gameState == GameStates.START) {generateGameField(dot);}
        if (gameState == GameStates.VICTORY || gameState == GameStates.FAIL) {
            return;
        }
        Cell cell = field[dot.y][dot.x];
        if (!isMine(dot)) {
            showCellsAround(dot);
            if (countOfOpenedCells == countOfSafeCells) {
                gameState = GameStates.VICTORY;
            }
        } else {
            showAllMines();
            gameState = GameStates.FAIL;
        }
        notifyAllObservers();

    }

    @Override
    public void setFlagOnField(Dot dot) {
        if (gameState == GameStates.START) {generateGameField(dot);}
        if (gameState == GameStates.VICTORY || gameState == GameStates.FAIL) {
            return;
        }
        if (isFlag(dot)) {
            deleteFlag(dot);
        } else {
            addFlag(dot);
            victoryIfAllMinesFound();
        }
        notifyAllObservers();
    }

    @Override
    public void notifyAllObservers() {
        for (var observer : gameObservers) {
            observer.update(gameState);
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

    private void initField() {
        minesCount = level.getMinesCount();
        countOfSafeCells = level.getCountOfSafeCells();
        size = level.getSizeOfField();
        field = new Cell[size.countOfRows][size.countOfColumns];
        for (int y = 0; y < size.countOfRows; y++) {
            for (int x = 0; x < size.countOfColumns; x++) {
                field[y][x] = new Cell(CellValue.DEFAULT);
            }
        }
    }

    private void showAllMines() {
        for (int y = 0; y < size.countOfRows; y++) {
            for (int x = 0; x < size.countOfColumns; x++) {
                Dot dot = new Dot(x, y);
                if (isMine(dot)) {
                    field[dot.y][dot.x].setHidden(false);
                }
            }
        }
    }

    private void showCellsAround(Dot dot) {
        showCell(dot);
        if (isDigit(dot)) {return;}
        for (int i = 0; i < 8; i++) {
            Dot nextDot = getNeighborDot(dot,i);
            if (isDotInField(nextDot)) {
                if (isEmpty(nextDot) && isHidden(nextDot)){
                    showCellsAround(nextDot);
                } else {
                    if (!isMine(nextDot) && isHidden(nextDot)) {
                        showCell(nextDot);
                        if (isFlag(nextDot)) {
                            deleteFlag(nextDot);
                        }
                    }
                }
            }
        }
    }

    private void showCell(Dot dot) {
        field[dot.y][dot.x].setHidden(false);
        countOfOpenedCells++;
    }



    private void victoryIfAllMinesFound() {
        if (minesFound == minesCount) {
            gameState = GameStates.VICTORY;
        }
    }

    private void deleteFlag(Dot dot) {
        flagsCount--;
        field[dot.y][dot.x].deleteFlag();
        if (isMine(dot)) {
            minesFound--;
        }
    }

    private void addFlag(Dot dot) {
        if (flagsCount >= minesCount) {return;}
        field[dot.y][dot.x].setValue(CellValue.FLAG);
        flagsCount++;
        if (isMine(dot)) {
            minesFound++;
        }
    }


    private void generateGameField(Dot mineOff) {
        initField();
        fillByMines(mineOff);
        setAmountOfMinesAroundEachCellExceptMines();
        gameState = GameStates.IN_PROGRESS;
    }

    private void setAmountOfMinesAroundEachCellExceptMines() {
        for (int y = 0; y < size.countOfRows; y++) {
            for (int x = 0; x < size.countOfColumns; x++) {
                Dot dot = new Dot(x,y);
                if (!isMine(dot)) {
                    field[y][x].setValue(calculateAmountOfMinesAroundCell(dot));
                }
            }
        }
    }

    private CellValue calculateAmountOfMinesAroundCell(Dot dot) {
        int amountOfMines = 0;
        for (int i = 0; i < 8; i++) {
            Dot nextDot = getNeighborDot(dot,i);
            if (isDotInField(nextDot)) {
                if (isMine(nextDot)){
                    amountOfMines++;
                }
            }
        }
        return valueByDigit(amountOfMines);
    }

    private Dot getNeighborDot(Dot dot, int idx) {
        int dx = (int)Math.round(Math.cos((Math.PI / 4 * idx)));
        int dy = (int)Math.round(Math.sin((Math.PI / 4 * idx)));
        return new Dot(dot.x + dx,dot.y + dy);
    }

    private CellValue valueByDigit(int digit) {
       return switch (digit) {
            case 0 -> CellValue.EMPTY;
            case 1 -> CellValue.ONE;
            case 2 -> CellValue.TWO;
            case 3 -> CellValue.THREE;
            case 4-> CellValue.FOUR;
            case 5-> CellValue.FIVE;
            case 6 -> CellValue.SIX;
            case 7 -> CellValue.SEVEN;
            case 8-> CellValue.EIGHT;
            default -> CellValue.DEFAULT;
        };
    }


    private boolean isDotInField(Dot dot) {
        return dot.y >= 0 && dot.y < size.countOfRows && dot.x >= 0 && dot.x < size.countOfColumns;
    }


    private void fillByMines(Dot mineOff) {
        int minesLeft = minesCount;

        while (minesLeft != 0) {
            Dot mine = generateDotForMine(mineOff);
            field[mine.y][mine.x].setValue(CellValue.MINE);
            minesLeft--;
        }
    }

    private Dot generateDotForMine(Dot mineOff) {
        Random random = new Random();
        Dot forMine;
        do {
            int newY = random.nextInt(size.countOfRows);
            int newX = random.nextInt(size.countOfColumns);
            forMine  = new Dot(newX,newY);
        } while(!isPlaceForMine(mineOff,forMine));
        return forMine;
    }

    private boolean isPlaceForMine(Dot mineOff, Dot forMine) {
        if (isMine(forMine)) {return false;}
        return !mineOff.equals(forMine);

    }

    private boolean isMine(Dot dot) {
        return field[dot.y][dot.x].isMine();
    }
    private boolean isFlag(Dot dot) {return field[dot.y][dot.x].isFlag();}
    private boolean isEmpty(Dot dot) {return field[dot.y][dot.x].isEmpty();}
    private boolean isHidden(Dot dot) {return field[dot.y][dot.x].isHidden();}
    private boolean isDigit(Dot dot) {return field[dot.y][dot.x].isDigit();}


}