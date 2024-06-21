package ai;

import static constant.Constants.*;

import game.Board;
import graphics.GameComponent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;

public class AIGame extends JFrame {
    private final Board board;
    private final GameComponent game;

    public AIGame() {
        super(GAME_TITLE);
        board = new Board(AI_GAME_SIZE, AI_GAME_SIZE);
        board.addRandomValue();
        board.addRandomValue();

        game = new GameComponent(board);
        game.setVisible(true);
        this.add(game);
        this.setSize(WINDOW_SIZE, WINDOW_SIZE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void run() {
        while (!board.isGameOver()) {
            switch (getMove()) {
                case "UP" -> {
                    if (board.isValidMove(UP_CHARACTER)) {
                        game.setScore(game.getScore() + board.move(UP_CHARACTER));
                        board.addRandomValue();
                        game.repaint();
                    }
                }
                case "DOWN" -> {
                    if (board.isValidMove(DOWN_CHARACTER)) {
                        game.setScore(game.getScore() + board.move(DOWN_CHARACTER));
                        board.addRandomValue();
                        game.repaint();
                    }
                }
                case "LEFT" -> {
                    if (board.isValidMove(LEFT_CHARACTER)) {
                        game.setScore(game.getScore() + board.move(LEFT_CHARACTER));
                        board.addRandomValue();
                        game.repaint();
                    }
                }
                case "RIGHT" -> {
                    if (board.isValidMove(RIGHT_CHARACTER)) {
                        game.setScore(game.getScore() + board.move(RIGHT_CHARACTER));
                        board.addRandomValue();
                        game.repaint();
                    }
                }
                case null -> {}
                default -> throw new IllegalStateException("Unexpected value");
            }
        }
    }

    private String getMove() {
        StringBuilder output = new StringBuilder();
        try {
            boolean try_again = true;
            while (try_again) {
                try_again = false;
                String command = "python3 ./src/main/java/ai/ai.py " + board;
                System.out.println("python3 ./src/main/java/ai/ai.py " + board);
                Process process = Runtime.getRuntime().exec(command);

                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(process.getInputStream()));

                output.append(reader.readLine());

                int exitVal = process.waitFor();
                if (exitVal == 0) {
                    System.out.println("Success!");
                    System.out.println(output);
                    if (!AI_MOVES.contains(output.toString())) try_again = true;
                    else return output.toString();

                } else {
                    System.out.println("Failed!");
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
