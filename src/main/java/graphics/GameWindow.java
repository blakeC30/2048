package graphics;

import static constant.Constants.*;

import java.awt.*;
import javax.swing.*;
import util.TextFieldType;

public class GameWindow extends JFrame {
    private final JPanel start_page;
    private JTextField row_text_field;
    private JTextField col_text_field;
    private final JLabel error_label;

    public GameWindow() {
        super(GAME_TITLE);
        JLabel title =
                new JLabel("2048") {
                    {
                        setForeground(Color.YELLOW);
                        setFont(new Font("Arial", Font.BOLD, 100));
                        setAlignmentX(CENTER_ALIGNMENT);
                        setAlignmentY(CENTER_ALIGNMENT);
                    }
                };

        error_label =
                new JLabel() {
                    {
                        setForeground(Color.RED);
                        setFont(new Font("Arial", Font.BOLD, 20));
                        setAlignmentX(CENTER_ALIGNMENT);
                        setAlignmentY(CENTER_ALIGNMENT);
                    }
                };

        JButton start_game =
                new JButton("Start Game") {
                    {
                        setForeground(Color.BLACK);
                        setBackground(Color.YELLOW);
                        setFont(new Font("Arial", Font.BOLD, 30));
                        setAlignmentX(CENTER_ALIGNMENT);
                        setAlignmentY(CENTER_ALIGNMENT);
                        addActionListener(e -> handleStartGame());
                    }
                };

        start_page = new JPanel();
        start_page.setLayout(new BoxLayout(start_page, BoxLayout.Y_AXIS));
        start_page.setBackground(Color.BLACK);

        start_page.add(new BlankLabel(125));
        start_page.add(title);
        start_page.add(new BlankLabel(25));
        start_page.add(getQueryForSizeContainer("Enter number of rows:", TextFieldType.ROW));
        start_page.add(getQueryForSizeContainer("Enter number of columns:", TextFieldType.COL));
        start_page.add(error_label);
        start_page.add(new BlankLabel(25));
        start_page.add(start_game);
        start_page.add(new BlankLabel(100));

        this.setSize(WINDOW_SIZE, WINDOW_SIZE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(start_page);
        this.setVisible(true);
    }

    private void handleStartGame() {
        if (row_text_field.getText().isEmpty() || col_text_field.getText().isEmpty()) {
            error_label.setText("Fill out both fields");
        } else {
            try {
                int num_rows = Integer.parseInt(row_text_field.getText());
                int num_cols = Integer.parseInt(col_text_field.getText());

                if (num_rows < MINIMUM_SIZE
                        || num_rows > MAXIMUM_SIZE
                        || num_cols < MINIMUM_SIZE
                        || num_cols > MAXIMUM_SIZE) {
                    error_label.setText(
                            "Values must be between "
                                    + MINIMUM_SIZE
                                    + " and "
                                    + MAXIMUM_SIZE
                                    + " inclusive");
                } else {
                    GameComponent game = new GameComponent(num_rows, num_cols);
                    game.setVisible(true);
                    this.add(game);
                    this.addKeyListener(game);
                    this.requestFocus();
                    start_page.setVisible(false);
                }
            } catch (NumberFormatException e) {
                error_label.setText("Fields must contain integers");
            }
        }
    }

    private Container getQueryForSizeContainer(String text, TextFieldType type) {
        JLabel label =
                new JLabel(text) {
                    {
                        setForeground(Color.YELLOW);
                        setFont(new Font("Arial", Font.BOLD, 25));
                        setAlignmentX(RIGHT_ALIGNMENT);
                        setAlignmentY(CENTER_ALIGNMENT);
                    }
                };
        JTextField text_field =
                new JTextField() {
                    {
                        setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2, true));
                        setBackground(Color.GRAY);
                        setForeground(Color.YELLOW);
                        setFont(new Font("Arial", Font.BOLD, 25));
                        setPreferredSize(new Dimension(300, 30));
                        setHorizontalAlignment(CENTER);
                    }
                };
        if (type == TextFieldType.ROW) row_text_field = text_field;
        if (type == TextFieldType.COL) col_text_field = text_field;

        Container main = new Container();
        Container left = new Container();
        Container right = new Container();
        left.setLayout(new FlowLayout(FlowLayout.RIGHT));
        right.setLayout(new FlowLayout(FlowLayout.LEFT));
        main.setLayout(new GridLayout(1, 2, 10, 0));
        left.add(label);
        right.add(text_field);
        main.add(left);
        main.add(right);

        return main;
    }
}
