package graphics;

import static constant.Constants.*;

import game.Board;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameComponent extends JPanel implements KeyListener {
    private final int num_rows;
    private final int num_cols;
    private Board board;
    private int score;
    private boolean is_game_over;
    private BoardComponent board_component;

    public GameComponent(int num_rows, int num_cols) {
        this.num_rows = num_rows;
        this.num_cols = num_cols;
        board = new Board(num_rows, num_cols);
        board.addRandomValue();
        board.addRandomValue();
        score = 0;
        is_game_over = false;
        board_component = new BoardComponent(board);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_SIZE, WINDOW_SIZE);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(String.valueOf(score), 390 - ((String.valueOf(score).length() - 1) * 10), 75);

        board_component.paintBoard(g);

        if (is_game_over) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("GAME OVER", 210, 750);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (!is_game_over) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> {
                    if (board.isValidMove(UP_CHARACTER)) {
                        score += board.move(UP_CHARACTER);
                        board.addRandomValue();
                        is_game_over = board.isGameOver();
                    }
                }
                case KeyEvent.VK_DOWN -> {
                    if (board.isValidMove(DOWN_CHARACTER)) {
                        score += board.move(DOWN_CHARACTER);
                        board.addRandomValue();
                        is_game_over = board.isGameOver();
                    }
                }
                case KeyEvent.VK_LEFT -> {
                    if (board.isValidMove(LEFT_CHARACTER)) {
                        score += board.move(LEFT_CHARACTER);
                        board.addRandomValue();
                        is_game_over = board.isGameOver();
                    }
                }
                case KeyEvent.VK_RIGHT -> {
                    if (board.isValidMove(RIGHT_CHARACTER)) {
                        score += board.move(RIGHT_CHARACTER);
                        board.addRandomValue();
                        is_game_over = board.isGameOver();
                    }
                }
            }
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_N) {
            board = new Board(num_rows, num_cols);
            board.addRandomValue();
            board.addRandomValue();
            is_game_over = false;
            score = 0;
            board_component = new BoardComponent(board);
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
