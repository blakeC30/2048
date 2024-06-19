package game;

public class Cell {
    private int value;

    public Cell(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getPrintValue() {
        return value == -1 ? "" : String.valueOf(value);

    }

    public void setValue(int value) {
        this.value = value;
    }
}
