package graphics;

import game.Board;

import javax.swing.*;

import java.awt.*;

import static constant.Constants.*;

public class BoardComponent extends JComponent {
    private final int num_rows;
    private final int num_cols;
    private final Board board;

    public BoardComponent(Board board) {
        super();
        this.board = board;
        this.num_rows = board.getNumRows();
        this.num_cols = board.getNumCols();
        setAlignmentX(MARGIN_SIZE);
        setAlignmentX(MARGIN_SIZE);
    }

    public void paintBoard(Graphics g) {
        int cell_size_x = (BOARD_COMPONENT_SIZE - (SPACE_BETWEEN_CELLS * (num_cols - 1))) / num_cols;
        int cell_size_y = (BOARD_COMPONENT_SIZE - (SPACE_BETWEEN_CELLS * (num_rows - 1))) / num_rows;
        g.setFont(new Font("Arial", Font.BOLD, 30));
        for(int r = 0; r < num_rows; r++) {
            for(int c = 0; c < num_cols; c++) {
                int x_coordinate = MARGIN_SIZE + (c * (cell_size_x + SPACE_BETWEEN_CELLS));
                int y_coordinate = MARGIN_SIZE + (r * (cell_size_y + SPACE_BETWEEN_CELLS));
                int value = board.getCellValue(r, c);
                g.setColor(VAL_TO_CELL_COLOR.get(value));
                g.fillRoundRect(x_coordinate, y_coordinate, cell_size_x, cell_size_y, 15, 15);
                if(value != EMPTY_CELL_VALUE) {
                    if(value == TWO || value == FOUR)
                        g.setColor(new Color(119,109,101));
                    else
                        g.setColor(new Color(253,243,240));
                    int value_x = x_coordinate + (cell_size_x / 2) - (String.valueOf(value).length() * 9);
                    int value_y = y_coordinate + (cell_size_y / 2) + 10;
                    g.drawString(String.valueOf(value), value_x, value_y);
                }

            }
        }
    }
}
