package game;

import util.RowColumnPair;

import java.util.ArrayList;
import java.util.List;

import static constant.Constants.*;

public class Board {
    private final int num_rows;
    private final int num_cols;
    private Cell[][] board;

    public Board(int num_rows, int num_cols) {
        this.num_rows = num_rows;
        this.num_cols = num_cols;
        populateBoard();
    }

    private void populateBoard() {
        board = new Cell[num_rows][num_cols];
        for(int r = 0; r < num_rows; r++) {
            for(int c = 0; c < num_cols; c++) {
                board[r][c] = new Cell(EMPTY_CELL_VALUE);
            }
        }
    }

    public void addRandomValue() {
        List<RowColumnPair> emptyCells = getEmptyCells();
        RowColumnPair selected = emptyCells.get((int) (Math.random() * emptyCells.size()));
        int value = Math.random() < CHANCE_OF_FOUR ? FOUR : TWO;
        board[selected.getRow()][selected.getCol()].setValue(value);
    }

    private List<RowColumnPair> getEmptyCells() {
        List<RowColumnPair> emptyCells = new ArrayList<>();
        for(int r = 0; r < num_rows; r++) {
            for(int c = 0; c < num_cols; c++) {
                if(board[r][c].getValue() == EMPTY_CELL_VALUE) {
                    emptyCells.add(new RowColumnPair(r, c));
                }
            }
        }
        return emptyCells;
    }

    public boolean isGameOver() {
        if(!getEmptyCells().isEmpty())
            return false;

        for(int r = 0; r < num_rows; r++) {
            for(int c = 0; c < num_cols; c++) {
                int val = board[r][c].getValue();
                if((r != 0 && board[r - 1][c].getValue() == val)
                    || (r != num_rows - 1 && board[r + 1][c].getValue() == val)
                    || (c != 0 && board[r][c - 1].getValue() == val)
                    || (c != num_cols - 1 && board[r][c + 1].getValue() == val)) {
                    return false;
                }
            }
        }
        System.out.println("GAME OVER!");
        return true;
    }

    public int move(String direction) {
        switch (direction) {
            case UP_CHARACTER -> {
                return moveUp();
            }
            case DOWN_CHARACTER -> {
                return moveDown();
            }
            case LEFT_CHARACTER -> {
                return moveLeft();
            }
            case RIGHT_CHARACTER -> {
                return moveRight();
            }
        }
        return 0;
    }


    public boolean isValidMove(String direction) {
        for(int r = 0; r < num_rows; r++) {
            for(int c = 0; c < num_cols; c++) {
                if(!board[r][c].isEmpty() && ((direction.equals(UP_CHARACTER) && canCellMoveUp(new RowColumnPair(r, c)))
                    || (direction.equals(DOWN_CHARACTER) && canCellMoveDown(new RowColumnPair(r, c)))
                    || (direction.equals(LEFT_CHARACTER) && canCellMoveLeft(new RowColumnPair(r, c)))
                    || (direction.equals(RIGHT_CHARACTER) && canCellMoveRight(new RowColumnPair(r, c)))))
                        return true;
            }
        }
        return false;
    }

    private boolean canCellMoveUp(RowColumnPair cell) {
        int col = cell.getCol();
        int row = cell.getRow();
        if(row != 0 && board[row][col].getValue() == board[row - 1][col].getValue()) {
            return true;
        }
        for(int r = row - 1; r >= 0; r--) {
            if(board[r][col].isEmpty())
                return true;
        }
        return false;
    }

    private boolean canCellMoveDown(RowColumnPair cell) {
        int col = cell.getCol();
        int row = cell.getRow();
        if(row != num_rows - 1 && board[row][col].getValue() == board[row + 1][col].getValue()) {
            return true;
        }
        for(int r = row + 1; r < num_rows; r++) {
            if(board[r][col].isEmpty())
                return true;
        }
        return false;
    }

    private boolean canCellMoveLeft(RowColumnPair cell) {
        int col = cell.getCol();
        int row = cell.getRow();
        if(col != 0 && board[row][col].getValue() == board[row][col - 1].getValue()) {
            return true;
        }
        for(int c = col - 1; c >= 0; c--) {
            if(board[row][c].isEmpty())
                return true;
        }
        return false;
    }

    private boolean canCellMoveRight(RowColumnPair cell) {
        int col = cell.getCol();
        int row = cell.getRow();
        if(col != num_cols - 1 && board[row][col].getValue() == board[row][col + 1].getValue()) {
            return true;
        }
        for(int c = col + 1; c < num_cols; c++) {
            if(board[row][c].isEmpty())
                return true;
        }
        return false;
    }

    private int moveUp() {
        int score = 0;
        shiftCellsUp();
        for(int c = 0; c < num_cols; c++) {
            for(int r = 0; r < num_rows - 1; r++) {
                if(!board[r][c].isEmpty() && board[r][c].getValue() == board[r + 1][c].getValue()) {
                    board[r][c].doubleValue();
                    board[r + 1][c].emptyCell();
                    score += board[r][c].getValue();
                }
            }
        }
        shiftCellsUp();
        return score;
    }

    private void shiftCellsUp() {
        for(int c = 0; c < num_cols; c++) {
            for (int r = 0; r < num_rows; r++) {
                if(!board[r][c].isEmpty()) {
                    int value = board[r][c].getValue();
                    int row = r - 1;
                    while(row >= 0 && board[row][c].isEmpty()) {
                        board[row][c].setValue(value);
                        board[row + 1][c].emptyCell();
                        row--;
                    }
                }
            }
        }
    }

    private int moveDown() {
        int score = 0;
        shiftCellsDown();
        for(int c = 0; c < num_cols; c++) {
            for(int r = num_rows - 1; r > 0; r--) {
                if(!board[r][c].isEmpty() && board[r][c].getValue() == board[r - 1][c].getValue()) {
                    board[r][c].doubleValue();
                    board[r - 1][c].emptyCell();
                    score += board[r][c].getValue();
                }
            }
        }
        shiftCellsDown();
        return score;
    }

    private void shiftCellsDown() {
        for(int c = 0; c < num_cols; c++) {
            for (int r = num_rows - 1; r >= 0; r--) {
                if(!board[r][c].isEmpty()) {
                    int value = board[r][c].getValue();
                    int row = r + 1;
                    while(row <= num_rows - 1 && board[row][c].isEmpty()) {
                        board[row][c].setValue(value);
                        board[row - 1][c].emptyCell();
                        row++;
                    }
                }
            }
        }
    }

    private int moveLeft() {
        int score = 0;
        shiftCellsLeft();
        for(int r = 0; r < num_rows; r++) {
            for(int c = 0; c < num_cols - 1; c++) {
                if(!board[r][c].isEmpty() && board[r][c].getValue() == board[r][c + 1].getValue()) {
                    board[r][c].doubleValue();
                    board[r][c + 1].emptyCell();
                    score += board[r][c].getValue();
                }
            }
        }
        shiftCellsLeft();
        return score;
    }

    private void shiftCellsLeft() {
        for(int r = 0; r < num_rows; r++) {
            for (int c = 0; c < num_cols; c++) {
                if(!board[r][c].isEmpty()) {
                    int value = board[r][c].getValue();
                    int col = c - 1;
                    while(col >= 0 && board[r][col].isEmpty()) {
                        board[r][col].setValue(value);
                        board[r][col + 1].emptyCell();
                        col--;
                    }
                }
            }
        }
    }

    private int moveRight() {
        int score = 0;
        shiftCellsRight();
        for(int r = 0; r < num_rows; r++) {
            for(int c = num_cols - 1; c > 0; c--) {
                if(!board[r][c].isEmpty() && board[r][c].getValue() == board[r][c - 1].getValue()) {
                    board[r][c].doubleValue();
                    board[r][c - 1].emptyCell();
                    score += board[r][c].getValue();
                }
            }
        }
        shiftCellsRight();
        return score;
    }

    private void shiftCellsRight() {
        for(int r = 0; r < num_rows; r++) {
            for (int c = num_cols - 1; c >= 0; c--) {
                if(!board[r][c].isEmpty()) {
                    int value = board[r][c].getValue();
                    int col = c + 1;
                    while(col <= num_cols - 1 && board[r][col].isEmpty()) {
                        board[r][col].setValue(value);
                        board[r][col - 1].emptyCell();
                        col++;
                    }
                }
            }
        }
    }

    public void print() {
        int character_width_of_board = (num_cols * 6) + 1;
        System.out.println("-".repeat(character_width_of_board));
        for(int r = 0; r < num_rows; r++) {
            System.out.print("|");
            for(int c = 0; c < num_cols; c++) {
                System.out.printf("%5s|", board[r][c].getPrintValue());
            }
            System.out.println();
            System.out.println("-".repeat(character_width_of_board));
        }
    }
}
