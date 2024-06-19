package game;

import static constant.Constants.EMPTY_CELL_VALUE;

public class Cell {
    private int value;

    public Cell(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getPrintValue() {
        return value == EMPTY_CELL_VALUE ? "" : String.valueOf(value);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == EMPTY_CELL_VALUE;
    }

    public void doubleValue() {
        value = value * 2;
    }

    public void emptyCell() {
        value = EMPTY_CELL_VALUE;
    }
}
