package game;

import static game.Constants.EMPTY_CELL_VALUE;

public class Board {
    private final int num_rows;
    private final int num_cols;
    private Cell[][] board;

    public Board(int size) {
        this.num_rows = size;
        this.num_cols = size;
        populateBoard();
    }

    public Board(int num_rows, int num_cols) {
        this.num_rows = num_rows;
        this.num_cols = num_cols;
        populateBoard();
    }

    public int getNumRows() {
        return num_rows;
    }

    public int getNumCols() {
        return num_cols;
    }

    private void populateBoard() {
        board = new Cell[num_rows][num_cols];
        for(int r = 0; r < num_rows; r++) {
            for(int c = 0; c < num_cols; c++) {
                board[r][c] = new Cell(EMPTY_CELL_VALUE);
            }
        }
    }

    public void printBoard() {
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
