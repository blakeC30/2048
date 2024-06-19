package game;

import java.util.Scanner;

public class Controller {
    private Board board;

    public void run() {
        createBoard();
    }

    private void createBoard(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int num_rows = scan.nextInt();

        System.out.print("Enter number of columns: ");
        int num_cols = scan.nextInt();

        board = new Board(num_rows, num_cols);
        board.printBoard();
    }
}
