package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GameFrame class represents the main window of the Trivia Maze game.
 * It manages the game's user interface, including the main menu, game panel, and status display.
 * This class also handles game state management, starting, resuming, saving, and loading games.
 *
 * @author Andrew Hwang
 * @author Jian Azul
 * @version 1
 */
public class GameFrame extends JFrame implements ActionListener {
    /** The game manager responsible for handling game logic and state. */
    private GameManager gameManager;

    /** The menu bar for the game frame. */
    private JMenuBar myMenuBar;

    /** Menu item for saving the game. */
    private JMenuItem mySaveItem;

    /** Menu item for loading a saved game. */
    private JMenuItem myLoadItem;

    /** Menu item for exiting the game. */
    private JMenuItem myExitItem;

    /** Button for resuming the game. */
    private JButton myResumeButton;

    /** Panel containing the main game display. */
    private GamePanel myGamePanel;

    /** GUI component for the main menu. */
    private MainMenuGUI myMainMenuGUI;

    /** GUI component for displaying game status. */
    private StatusGUI myStatusGUI;

    /** Layout manager for switching between different panels. */
    private CardLayout cardLayout;

    /** Panel that contains all the different views (main menu, game panel). */
    private JPanel contentPanel;

    /** The width of the game frame. */
    private static final int FRAME_WIDTH = GameSettings.SCREEN_WIDTH;

    /** The height of the game frame. */
    private static final int FRAME_HEIGHT = GameSettings.SCREEN_HEIGHT + 30;

    /**
     * Constructs a new GameFrame, initializing the game window and its components.
     */

    public GameFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Trivia Maze");
        setIcon();
        gameManager = new GameManager();

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        add(contentPanel, BorderLayout.CENTER);

        initComponents();
        initMenuBar();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /**
     * Initializes the main components of the game frame, including the game panel, main menu,
     * and status display.
     */
    private void initComponents() {
        myGamePanel = new GamePanel(this, gameManager);
        myMainMenuGUI = new MainMenuGUI(this);
        myStatusGUI = new StatusGUI(gameManager.getPlayer());

        contentPanel.add(myMainMenuGUI, "MainMenu");
        contentPanel.add(myGamePanel, "Game");

        add(myStatusGUI, BorderLayout.NORTH);

        cardLayout.show(contentPanel, "MainMenu");
    }
    /**
     * Initializes the menu bar and its items.
     */
    private void initMenuBar() {
        myMenuBar = new JMenuBar();

        JMenu myGameMenu = new JMenu("Game");
        JMenu myHelpMenu = new JMenu("Help");
        mySaveItem = new JMenuItem("Save");
        myLoadItem = new JMenuItem("Load");
        myExitItem = new JMenuItem("Exit");

        myGameMenu.add(mySaveItem);
        myGameMenu.add(myLoadItem);
        myGameMenu.add(myExitItem);

        myMenuBar.add(myGameMenu);
        myMenuBar.add(myHelpMenu);

        setJMenuBar(myMenuBar);
        menuBarListener();
    }
    /**
     * Sets up listeners for menu bar items.
     */
    private void menuBarListener() {
        mySaveItem.addActionListener(e -> saveGame());
        myLoadItem.addActionListener(e -> loadGame());
        myExitItem.addActionListener(e -> showExitDialog());
    }
    /**
     * Starts a new game, switching to the game panel and initializing game components.
     */
    public void startGame() {
        cardLayout.show(contentPanel, "Game");
        myGamePanel.startGame();
        myGamePanel.requestFocusInWindow();
    }
    /**
     * Resumes a paused game, switching back to the game panel.
     */
    public void resumeGame() {
        cardLayout.show(contentPanel, "Game");
        myGamePanel.requestFocusInWindow();
    }

    /**
     *
     */
    public void returnToMainMenu() {
        cardLayout.show(contentPanel, "MainMenu");
    }
    /**
     * Saves the current game state.
     */
    private void saveGame() {
        GameSaveAndLoad gameSaveAndLoad = new GameSaveAndLoad(gameManager.getMaze(),
                gameManager.getPlayer());
        boolean saved = gameSaveAndLoad.saveGame();
        if (saved) {
            JOptionPane.showMessageDialog(this, "Game saved successfully!",
                    "Save Game", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save game.",
                    "Save Game", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Loads a previously saved game state.
     */
    void loadGame() {
        GameSaveAndLoad gameSaveAndLoad = new GameSaveAndLoad();
        boolean loaded = gameSaveAndLoad.loadGame();
        if (loaded) {
            JOptionPane.showMessageDialog(this, "Game loaded successfully!",
                    "Load Game", JOptionPane.INFORMATION_MESSAGE);

            gameManager.setMaze(gameSaveAndLoad.getMaze());

            gameManager.getPlayer().updateState(gameSaveAndLoad.getPlayer());

            myGamePanel.setMyGameManager(gameManager);
            updateGameState();
            resumeGame();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to load game.",
                    "Load Game", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Updates the game state after loading a saved game.
     */
    private void updateGameState() {
        myGamePanel.setMaze(gameManager.getMaze());
        myGamePanel.setPlayer(gameManager.getPlayer());
        updateScore(gameManager.getPlayer().getMyScore());
    }
    /**
     * Displays an exit confirmation dialog.
     */
    private void showExitDialog() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit the game?",
                "Exit Game",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    /**
     * Displays a game over dialog and handles the player's choice to play again or return to the main menu.
     *
     * @param thePlayerWon true if the player won, false otherwise
     */
    public void showGameOverDialog(boolean thePlayerWon) {
        String message = thePlayerWon ? "Congratulations! You've won the game!" :
                "Game Over. Better luck next time!";
        int choice = JOptionPane.showConfirmDialog(this,
                message + "\nDo you want to play again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            returnToMainMenu();
        }
    }
    /**
     * Resets the game to its initial state.
     */
    private void resetGame() {
        gameManager.resetMaze();
        myGamePanel.resetGame();
        startGame();
    }
    /**
     * Handles action events, specifically for the resume button.
     *
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myResumeButton) {
            resumeGame();
        }
    }
    /**
     * Updates the player's score in the status GUI.
     *
     * @param score the new score to display
     */
    public void updateScore(int score) {
        myStatusGUI.updateScore(score);
    }
    /**
     * Sets the icon for the game window.
     */
    public void setIcon() {
        ImageIcon icon = new ImageIcon("src/Resources/NintendoIcon.png");
        setIconImage(icon.getImage());
    }
}
