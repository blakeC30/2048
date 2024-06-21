package game;

import static constant.Constants.*;

import java.util.Scanner;

public class TerminalGame {
    private Board board;
    private int score;

    public TerminalGame() {
        this.score = 0;
    }

    public void run() {
        createBoard();
        while (!board.isGameOver()) {
            score = score + move();
            board.addRandomValue();
            board.print();
            System.out.println("Score: " + score);
        }
    }

    private void createBoard() {
        int num_rows = askForSize("Enter number of rows: ");
        int num_cols = askForSize("Enter number of columns: ");

        board = new Board(num_rows, num_cols);
        board.addRandomValue();
        board.addRandomValue();
        board.print();
    }

    private int askForSize(String command) {
        Scanner scan = new Scanner(System.in);
        int size = -1;

        while (size == -1) {
            System.out.print(command);
            size = scan.nextInt();

            if (size < MINIMUM_SIZE || size > MAXIMUM_SIZE) {
                System.out.println("Size must be an between 2 and 10 inclusive. Try again.");
                size = -1;
            }
        }
        return size;
    }

    private int move() {
        Scanner scan = new Scanner(System.in);
        String input = null;
        int score = 0;
        while (input == null) {
            System.out.print("Input move: ");
            input = scan.next();

            if (!INPUT_KEYS.contains(input)) {
                System.out.println("Invalid character for move. Try again.");
                input = null;
            } else {
                switch (input) {
                    case UP_CHARACTER -> System.out.println("Up");
                    case DOWN_CHARACTER -> System.out.println("Down");
                    case LEFT_CHARACTER -> System.out.println("Left");
                    case RIGHT_CHARACTER -> System.out.println("Right");
                }

                if (!board.isValidMove(input)) {
                    System.out.println("Nothing moved. Try again.");
                    input = null;
                } else score = board.move(input);
            }
        }
        return score;
    }
}
