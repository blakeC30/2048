package graphics;

import game.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static constant.Constants.*;

public class GameComponent extends JPanel implements KeyListener {
    private final int num_rows;
    private final int num_cols;
    private Board board;
    private int score;
    private boolean is_game_over;

    public GameComponent(int num_rows, int num_cols) {
        this.num_rows = num_rows;
        this.num_cols = num_cols;
        board = new Board(num_rows, num_cols);
        board.addRandomValue();
        board.addRandomValue();
        score = 0;
        is_game_over = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("IN PAINT COMPONENT");
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WINDOW_SIZE,WINDOW_SIZE);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 40));
//        score = 100000;
        g.drawString(String.valueOf(score), 390 - ((String.valueOf(score).length() - 1) * 10), 75);

        int larger_dimension = Math.max(num_rows, num_cols);
        int cell_size = (WINDOW_SIZE - (SPACE_BETWEEN_CELLS * larger_dimension - 1) - (MARGIN_SIZE * 2 + (larger_dimension * 10))) / Math.max(num_rows, num_cols);

        g.setFont(new Font("Arial", Font.BOLD, 30));
        for(int r = 0; r < num_rows; r++) {
            for(int c = 0; c < num_cols; c++) {
                int x_coordinate = (MARGIN_SIZE + (num_cols * (num_cols) - 2)) + (c * (cell_size + SPACE_BETWEEN_CELLS));
                int y_coordinate = (MARGIN_SIZE + (num_rows * (num_rows) - 2)) + (r * (cell_size + SPACE_BETWEEN_CELLS));
                g.setColor(Color.GRAY);
                g.fillRect(x_coordinate, y_coordinate, cell_size, cell_size);
                g.setColor(Color.BLACK);
                int value = board.getCellValue(r, c);
                if(value != EMPTY_CELL_VALUE)
                    g.drawString(String.valueOf(value),x_coordinate + cell_size / 2, y_coordinate + cell_size / 2);

            }
        }

        if(is_game_over) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("GAME OVER", 200, 725);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!is_game_over) {
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
        }
        else if(e.getKeyCode() == KeyEvent.VK_N) {
            board = new Board(num_rows, num_cols);
            board.addRandomValue();
            board.addRandomValue();
            is_game_over = false;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
