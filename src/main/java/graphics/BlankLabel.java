package graphics;

import javax.swing.*;
import java.awt.*;

public class BlankLabel extends JLabel {
    public BlankLabel(int size) {
        super("blank");
        setFont(new Font("Arial", Font.BOLD, size));
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
    }
}
