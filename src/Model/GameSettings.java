package Model;

public class GameSettings {
    /** Original Tile size */
    public static final int ORIGINAL_TILE_SIZE = 32;
    /** Scale factor */
    public static final int SCALE = 2;
    /** Tile size with scale factor */
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    /** Max screen columns */
    public static final int MAX_SCREEN_COLUMN = 16;
    /** Max screen rows */
    public static final int MAX_SCREEN_ROW = 12;
    /** Screen width */
    public static final int SCREEN_WIDTH = 54 * MAX_SCREEN_COLUMN;
    /** Screen height */
    public static final int SCREEN_HEIGHT = 54 * MAX_SCREEN_ROW;
    /** Max world columns */
    public static final int MAX_WORLD_COLUMN = 76;
    /** Max world rows */
    public static final int MAX_WORLD_ROW = 72;
    /** FPS */
    public static final int FPS = 60;

}