package Model;

public class GameSettings {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int TILE_SIZE = 32;
    public static final int FPS = 60;
    public static final int MAX_WORLD_COLUMN = 76;
    public static final int MAX_WORLD_ROW = 72;

    // Maze settings
    public static final int MAZE_WIDTH = 25; // SCREEN_WIDTH / TILE_SIZE
    public static final int MAZE_HEIGHT = 18; // SCREEN_HEIGHT / TILE_SIZE

    // Player settings
    public static final int PLAYER_SPEED = 4;

    // Game states
    public static final int MAIN_MENU = 0;
    public static final int PLAYING = 1;
    public static final int PAUSED = 2;
    public static final int GAME_OVER = 3;

}