package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {
    private GameManager gameManager;
    private JMenuBar myMenuBar;
    private JMenuItem mySaveItem, myLoadItem, myExitItem;
    private JButton myResumeButton;
    private GamePanel myGamePanel;
    private MainMenuGUI myMainMenuGUI;
    private StatusGUI myStatusGUI;
    private CardLayout cardLayout;
    private JPanel contentPanel;

    private static final int FRAME_WIDTH = GameSettings.SCREEN_WIDTH;
    private static final int FRAME_HEIGHT = GameSettings.SCREEN_HEIGHT + 30;

    // for Jian

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

    private void initComponents() {
        myGamePanel = new GamePanel(this, gameManager);
        myMainMenuGUI = new MainMenuGUI(this);
        myStatusGUI = new StatusGUI(gameManager.getPlayer());

        contentPanel.add(myMainMenuGUI, "MainMenu");
        contentPanel.add(myGamePanel, "Game");

        add(myStatusGUI, BorderLayout.NORTH);

        cardLayout.show(contentPanel, "MainMenu");
    }

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

    private void menuBarListener() {
        mySaveItem.addActionListener(e -> saveGame());
        myLoadItem.addActionListener(e -> loadGame());
        myExitItem.addActionListener(e -> showExitDialog());
    }

    public void startGame() {
        cardLayout.show(contentPanel, "Game");
        myGamePanel.startGame();
        myGamePanel.requestFocusInWindow();
    }

    public void resumeGame() {
        cardLayout.show(contentPanel, "Game");
        myGamePanel.resumeGame();
        myGamePanel.requestFocusInWindow();
    }

    public void returnToMainMenu() {
        cardLayout.show(contentPanel, "MainMenu");
    }

    private void saveGame() {
        GameSaveAndLoad gameSaveAndLoad = new GameSaveAndLoad(gameManager.getMaze(), gameManager.getPlayer());
        boolean saved = gameSaveAndLoad.saveGame();
        if (saved) {
            JOptionPane.showMessageDialog(this, "Game saved successfully!", "Save Game", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save game.", "Save Game", JOptionPane.ERROR_MESSAGE);
        }
    }

    void loadGame() {
        GameSaveAndLoad gameSaveAndLoad = new GameSaveAndLoad();
        boolean loaded = gameSaveAndLoad.loadGame();
        if (loaded) {
            JOptionPane.showMessageDialog(this, "Game loaded successfully!", "Load Game", JOptionPane.INFORMATION_MESSAGE);

            // Replace the entire Maze object in the GameManager
            gameManager.setMaze(gameSaveAndLoad.getMaze());

            // Update the Player state
            gameManager.getPlayer().updateState(gameSaveAndLoad.getPlayer());

            // Update the GamePanel and other UI elements
            myGamePanel.setGameManager(gameManager);
            updateGameState();
            resumeGame();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to load game.", "Load Game", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateGameState() {
        myGamePanel.setMaze(gameManager.getMaze());
        myGamePanel.setPlayer(gameManager.getPlayer());
        updateScore(gameManager.getPlayer().getMyScore());
    }

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

    public void showGameOverDialog(boolean playerWon) {
        String message = playerWon ? "Congratulations! You've won the game!" : "Game Over. Better luck next time!";
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

    private void resetGame() {
        gameManager.resetMaze();
        myGamePanel.resetGame();
        startGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myResumeButton) {
            resumeGame();
        }
    }

    public void updateScore(int score) {
        myStatusGUI.updateScore(score);
    }

    public void setIcon() {
        ImageIcon icon = new ImageIcon("src/Resources/NintendoIcon.png");
        setIconImage(icon.getImage());
    }
}

