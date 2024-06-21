package constant;

import java.awt.*;
import java.util.Map;
import java.util.Set;

public class Constants {
    public static final int EMPTY_CELL_VALUE = -1;
    public static final int MINIMUM_SIZE = 2;
    public static final int MAXIMUM_SIZE = 6;
    public static final double CHANCE_OF_FOUR = 0.25;
    public static final int TWO = 2;
    public static final int FOUR = 4;
    public static final String UP_CHARACTER = "i";
    public static final String DOWN_CHARACTER = "m";
    public static final String LEFT_CHARACTER = "j";
    public static final String RIGHT_CHARACTER = "k";
    public static final Set<String> INPUT_KEYS =
            Set.of(UP_CHARACTER, DOWN_CHARACTER, LEFT_CHARACTER, RIGHT_CHARACTER);
    public static final int WINDOW_SIZE = 800;
    public static final String GAME_TITLE = "2048";
    public static final int SPACE_BETWEEN_CELLS = 20;
    public static final int MARGIN_SIZE = 120;
    public static final int BOARD_COMPONENT_SIZE = WINDOW_SIZE - (MARGIN_SIZE * 2);
    public static final Map<Integer, Color> VAL_TO_CELL_COLOR =
            Map.ofEntries(
                    Map.entry(-1, new Color(134, 124, 116)),
                    Map.entry(2, new Color(239, 227, 220)),
                    Map.entry(4, new Color(203, 190, 172)),
                    Map.entry(8, new Color(239, 181, 128)),
                    Map.entry(16, new Color(241, 159, 108)),
                    Map.entry(32, new Color(239, 140, 103)),
                    Map.entry(64, new Color(237, 116, 73)),
                    Map.entry(128, new Color(236, 204, 125)),
                    Map.entry(256, new Color(235, 201, 111)),
                    Map.entry(512, new Color(203, 168, 85)),
                    Map.entry(1024, new Color(203, 167, 75)),
                    Map.entry(2048, new Color(220, 177, 71)),
                    Map.entry(4096, new Color(233, 122, 112)),
                    Map.entry(8192, new Color(233, 103, 96)),
                    Map.entry(16384, new Color(220, 93, 97)),
                    Map.entry(32768, new Color(123, 172, 212)),
                    Map.entry(65536, new Color(99, 145, 212)),
                    Map.entry(131072, new Color(43, 98, 165)));
}
