package constant;

import java.util.Set;

public class Constants {
    public final static int EMPTY_CELL_VALUE = -1;
    public final static int MINIMUM_SIZE = 2;
    public final static int MAXIMUM_SIZE = 8;
    public final static double CHANCE_OF_FOUR = 0.25;
    public final static int TWO = 2;
    public final static int FOUR = 4;
    public final static String UP_CHARACTER = "i";
    public final static String DOWN_CHARACTER = "m";
    public final static String LEFT_CHARACTER = "j";
    public final static String RIGHT_CHARACTER = "k";
    public final static Set<String> INPUT_KEYS = Set.of(UP_CHARACTER, DOWN_CHARACTER, LEFT_CHARACTER, RIGHT_CHARACTER);
    public final static int WINDOW_SIZE = 800;
    public final static String GAME_TITLE = "2048";
    public final static int SPACE_BETWEEN_CELLS = 20;
    public final static int MARGIN_SIZE = 120;
}
