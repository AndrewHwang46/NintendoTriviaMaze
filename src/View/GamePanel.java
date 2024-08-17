package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GamePanel class represents the main game panel in the Trivia Maze game.
 * It handles the game loop, user interface components, and game state updates.
 *
 * @author Andrew Hwang
 * @author Jian Azul
 * @version 1
 */
public class GamePanel extends JPanel implements Runnable, ActionListener {
    /** The game manager responsible for handling game logic and state. */
    private GameManager myGameManager;

    /** The thread responsible for running the game loop. */
    private Thread myGameThread;

    /** The main game object. */
    private Game myGame;

    /** Manages sound effects and background music. */
    private SoundManager mySound;

    /** Handles keyboard input. */
    private Keyboard myKeyboard;

    /** Array of buttons representing doors in the current room. */
    private JButton[] myDoorButtons;

    /** Text area for displaying trivia questions. */
    private JTextArea myQuestionArea;

    /** Text field for user to input answers to trivia questions. */
    private JTextField myAnswerField;

    /** Button to submit answers to trivia questions. */
    private JButton mySubmitButton;

    /** The current room the player is in. */
    private Room myCurrentRoom;

    /** The size of each tile in the game. */
    private static final int TILE_SIZE = GameSettings.TILE_SIZE;

    /**
     * Constructs a new GamePanel with the specified GameFrame and GameManager.
     *
     * @param theGameFrame The main game frame
     * @param theGameManager The game manager
     */
    public GamePanel(GameFrame theGameFrame, GameManager theGameManager) {
        myGameManager = theGameManager;
        setPreferredSize(new Dimension(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT));
        setBackground(Color.black);
        setDoubleBuffered(true);
        setFocusable(true);

        setLayout(null);
        initGame();
    }

    /**
     * Initializes the game components.
     */
    private void initGame() {
        myKeyboard = new Keyboard();
        addKeyListener(myKeyboard);
        mySound = new SoundManager();
        myGame = new Game(this, myKeyboard, myGameManager);
        initUI();
    }

    /**
     * Initializes the user interface components.
     */
    private void initUI() {
        myDoorButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            myDoorButtons[i] = new JButton("Door " + (i + 1));
            myDoorButtons[i].addActionListener(this);
            myDoorButtons[i].setVisible(false);
            add(myDoorButtons[i]);
        }

        myQuestionArea = new JTextArea();
        myQuestionArea.setEditable(false);
        myQuestionArea.setVisible(false);
        add(myQuestionArea);

        myAnswerField = new JTextField();
        myAnswerField.setVisible(false);
        add(myAnswerField);

        mySubmitButton = new JButton("Submit");
        mySubmitButton.addActionListener(this);
        mySubmitButton.setVisible(false);
        add(mySubmitButton);

        positionComponents();
    }

    /**
     * Positions the UI components on the panel.
     */
    private void positionComponents() {
        myDoorButtons[0].setBounds(GameSettings.SCREEN_WIDTH / 2 - 50, 50, 100, 30);
        myDoorButtons[1].setBounds(GameSettings.SCREEN_WIDTH - 130, GameSettings.SCREEN_HEIGHT / 2 - 15, 100, 30);
        myDoorButtons[2].setBounds(GameSettings.SCREEN_WIDTH / 2 - 50, GameSettings.SCREEN_HEIGHT - 80, 100, 30);
        myDoorButtons[3].setBounds(30, GameSettings.SCREEN_HEIGHT / 2 - 15, 100, 30);

        myQuestionArea.setBounds(100, 100, GameSettings.SCREEN_WIDTH - 200, 100);
        myAnswerField.setBounds(100, 210, GameSettings.SCREEN_WIDTH - 200, 30);
        mySubmitButton.setBounds(GameSettings.SCREEN_WIDTH / 2 - 50, 250, 100, 30);
    }

    /**
     * Starts the game thread.
     */
    public void startGame() {
        myGameThread = new Thread(this);
        myGameThread.start();
    }

    /**
     * The main game loop.
     */
    @Override
    public void run() {
        double drawInterval = 1000000000 / GameSettings.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (myGameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    /**
     * Updates the game state.
     */
    private void update() {
        if (myGame != null) {
            myGame.update();
        } else {
            System.err.println("Game not initialized!");
        }
    }

    /**
     * Paints the game components.
     *
     * @param g The Graphics object to paint on
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (myGame != null) {
            myGame.draw(g2);
        }
        g2.dispose();
    }

    /**
     * Handles button click events.
     *
     * @param e The ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton clickedButton = (JButton) e.getSource();
            if (clickedButton == mySubmitButton) {
                checkAnswer();
            } else {
                for (int i = 0; i < myDoorButtons.length; i++) {
                    if (clickedButton == myDoorButtons[i]) {
                        showQuestion(i);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Displays the question for the selected door.
     *
     * @param doorIndex The index of the selected door
     */
    private void showQuestion(int doorIndex) {
        AbstractDoor door = myCurrentRoom.getMyDoorInRoomList()[doorIndex];
        myQuestionArea.setText(door.getQuestion());
        myQuestionArea.setVisible(true);
        myAnswerField.setVisible(true);
        mySubmitButton.setVisible(true);
        for (JButton button : myDoorButtons) {
            button.setVisible(false);
        }
    }

    /**
     * Checks the player's answer to the current question.
     */
    private void checkAnswer() {
        AbstractDoor door = myCurrentRoom.getMyDoorInRoomList()[0];
        boolean playerAnswer = myAnswerField.getText().equalsIgnoreCase(door.getAnswer());
        myGameManager.getPlayer().scoreChanger(playerAnswer);
        door.setStateOfDoor(!playerAnswer);
        door.setUserAttempted(true);

        myQuestionArea.setVisible(false);
        myAnswerField.setVisible(false);
        mySubmitButton.setVisible(false);

        enterRoom(myCurrentRoom); // Refresh the room view
    }

    /**
     * Updates the UI when entering a new room.
     *
     * @param room The room being entered
     */
    private void enterRoom(Room room) {
        myCurrentRoom = room;
        AbstractDoor[] doors = room.getMyDoorInRoomList();
        for (int i = 0; i < doors.length; i++) {
            myDoorButtons[i].setVisible(doors[i] != null && !doors[i].getUserAttempted());
        }
    }

    /**
     * Plays background music.
     *
     * @param theIndex The index of the music track to play
     */
    public void musicBackground(final int theIndex) {
        mySound.setFile(theIndex);
        mySound.play();
        mySound.loop();
    }

    /**
     * Sets a new maze and updates the display.
     *
     * @param newMaze The new maze to set
     */
    public void setMaze(Maze newMaze) {
        myGameManager.setMaze(newMaze);
        updateMazeDisplay();
        updateCurrentRoom();
    }

    /**
     * Sets a new player and updates the display.
     *
     * @param newPlayer The new player to set
     */
    public void setPlayer(Player newPlayer) {
        myGameManager.getPlayer().updateState(newPlayer);
        updatePlayerPosition();
        updatePlayerStats();
    }

    /**
     * Updates the maze display.
     */
    private void updateMazeDisplay() {
        removeAll();

        Room[][] mazeMap = myGameManager.getMaze().getMyMap();
        for (int y = 0; y < mazeMap.length; y++) {
            for (int x = 0; x < mazeMap[y].length; x++) {
                if (mazeMap[y][x] != null) {
                    JPanel roomPanel = createRoomPanel(mazeMap[y][x]);
                    add(roomPanel);
                }
            }
        }
        initUI();

        revalidate();
        repaint();
    }

    /**
     * Creates a panel representing a room in the maze.
     *
     * @param room The room to create a panel for
     * @return A JPanel representing the room
     */
    private JPanel createRoomPanel(Room room) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(GameSettings.TILE_SIZE, GameSettings.TILE_SIZE));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return panel;
    }

    /**
     * Updates the current room based on the player's position.
     */
    private void updateCurrentRoom() {
        int roomX = myGameManager.getPlayer().getX() / GameSettings.TILE_SIZE;
        int roomY = myGameManager.getPlayer().getY() / GameSettings.TILE_SIZE;
        myCurrentRoom = myGameManager.getMaze().getMyMap()[roomY][roomX];
        updateDoorButtons();
    }

    /**
     * Updates the visibility of door buttons based on the current room.
     */
    private void updateDoorButtons() {
        AbstractDoor[] doors = myCurrentRoom.getMyDoorInRoomList();
        for (int i = 0; i < doors.length; i++) {
            myDoorButtons[i].setVisible(doors[i] != null && !doors[i].getUserAttempted());
        }
    }

    /**
     * Updates the player's position on the display.
     */
    private void updatePlayerPosition() {
        repaint();
    }

    /**
     * Updates the player's statistics on the display.
     */
    private void updatePlayerStats() {
        if (getParent() instanceof GameFrame) {
            GameFrame parentFrame = (GameFrame) getParent();
            parentFrame.updateScore(myGameManager.getPlayer().getMyScore());
        }
    }

    /**
     * Resets the game to its initial state.
     */
    public void resetGame() {
        myGameManager.resetMaze();
        Player.resetPlayer();
        myCurrentRoom = null;
        for (JButton button : myDoorButtons) {
            button.setVisible(false);
        }
        myQuestionArea.setVisible(false);
        myAnswerField.setVisible(false);
        mySubmitButton.setVisible(false);
        updatePlayerPosition();
        myGameManager.getPlayer().setMyScore(0);
        for (Room[] row : myGameManager.getMaze().getMyMap()) {
            for (Room room : row) {
                if (room != null) {
                    for (AbstractDoor door : room.getMyDoorInRoomList()) {
                        if (door != null) {
                            door.setStateOfDoor(false);
                            door.setUserAttempted(false);
                        }
                    }
                }
            }
        }
        if (mySound != null) {
            mySound.stop();
            musicBackground(0);
        }
        if (myGameThread != null) {
            myGameThread.interrupt();
        }
        startGame();
        repaint();
    }

    /**
     * Sets a new GameManager and updates the game accordingly.
     *
     * @param myGameManager The new GameManager to set
     */
    public void setMyGameManager(GameManager myGameManager) {
        this.myGameManager = myGameManager;
        myGame.setMyGameManager(myGameManager);
    }
}
