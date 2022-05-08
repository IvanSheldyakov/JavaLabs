package ru.nsu.fit.minesweeper.GameModel;

public class Cell {
    private CellValue trueValue;
    private CellValue fakeValue;
    private boolean hidden;
    private boolean digit;

    public Cell(CellValue value) {
        setValue(value);
        this.fakeValue = value;
    }

    public Cell(Cell other) {
        this.trueValue = other.trueValue;
        this.fakeValue = other.fakeValue;
        this.hidden = other.hidden;

    }


    public void setValue(CellValue value) {
        switch (value) {
            case MINE -> setMine(value);
            case ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT -> setDigit(value);
            case FLAG -> setFlag(value);
            case EMPTY -> setEmpty(value);
            case DEFAULT -> setDefault(value);
        }
    }

    public CellValue getTrueValue() {
        return trueValue;
    }

    public void setTrueValue(CellValue trueValue) {
        this.trueValue = trueValue;
    }

    public CellValue getFakeValue() {
        return fakeValue;
    }

    public void setFakeValue(CellValue value) {
        this.fakeValue = value;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void deleteFlag() {
        if (isFlag()) {
            fakeValue = CellValue.DEFAULT;
        }
    }

    public boolean isMine() {
        return trueValue == CellValue.MINE;
    }


    public boolean isEmpty() {
        return trueValue == CellValue.EMPTY;
    }

    public boolean isDigit() {
        return digit;
    }

    public boolean isFlag() {
        return fakeValue == CellValue.FLAG;
    }

    private void setDigit(CellValue value) {
        trueValue = value;
        hidden = true;
        digit = true;
    }

    private void setMine(CellValue value) {
        trueValue = value;
        hidden = true;
    }

    private void setFlag(CellValue value) {
        fakeValue = value;
        hidden = true;
    }

    private void setEmpty(CellValue value) {
        trueValue = value;
        hidden = true;
    }

    private void setDefault(CellValue value) {
        trueValue = value;
        hidden = false;
    }


}
