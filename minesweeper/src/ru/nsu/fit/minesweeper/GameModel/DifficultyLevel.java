package ru.nsu.fit.minesweeper.GameModel;

public abstract class DifficultyLevel {
    protected SizeOfField sizeOfField;
    protected int minesCount;
    protected int countOfSafeCells;

    public DifficultyLevel(SizeOfField sizeOfField, int minesCount){
        this.sizeOfField = sizeOfField;
        this.minesCount = minesCount;
        countOfSafeCells = sizeOfField.countOfRows*sizeOfField.countOfColumns - minesCount;
    }

    public SizeOfField getSizeOfField() {
        return sizeOfField;
    }

    public int getMinesCount() {
        return minesCount;
    }

    public int getCountOfSafeCells() {
        return countOfSafeCells;
    }
}


