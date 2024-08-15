package View;

import Model.GameSaveAndLoad;
import Model.GameSettings;
import Model.Maze;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {

    //    private JMenuBar myMenuBar;
//    private JMenuItem mySaveItem;
//    private JMenuItem myLoadItem;
//    private JMenuItem myExitItem;
//    private JButton myResumeButton;
//    public GameSaveAndLoad myGameSaveLoad;
//    private GamePanel myGamePanel;
//
//    private static final int BORDER= 15;
//
//    //REFER TO COLOR SWATCH
//    private static final Color LIGHTEST_GREEN = new Color(155,188,15);
//    private static final Color LIGHTER_GREEN = new Color(139,172,115);
//    private static final Color GREEN = new Color(48,98,48);
//    private static final Color DARKER_GREEN = new Color(15,56,15);
//    private static final Color DARKEST_GREEN = new Color(15,56,15);
//
//
//    public GameFrame(){
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setResizable(false);
//        pack();
//        setLocationRelativeTo(null);
//        setVisible(true);
//        myGamePanel = new GamePanel();
////      myGameSaveLoad
////      myGamePanel.musicBackground(0);
//
//
//
//    }
//
//    public GamePanel getGamePanel(){
//        return myGamePanel;
//    }
//
//    public void endPanel(){
//
//    }
//
//    private void showDialog(final JPanel thePanel) {
//        JDialog dialog = new JDialog(this, "Dialog", true);
//        dialog.getContentPane().add(thePanel);
//        dialog.setUndecorated(true);
//        dialog.pack();
//        dialog.setLocationRelativeTo(this);
//        dialog.setVisible(true);
//    }
//
//    private void initMenuBar() {
//
//        myMenuBar = new JMenuBar();
//
//        JMenu myGameMenu = new JMenu("Game");
//        JMenu myHelpMenu = new JMenu("Help");
//        mySaveItem = new JMenuItem("Save");
//        myLoadItem = new JMenuItem("Load");
//        myExitItem = new JMenuItem("Exit");
//        myMenuBar.add(myGameMenu);
//        myMenuBar.add(myHelpMenu);
//
//        myGameMenu.add(mySaveItem);
//        myGameMenu.add(myLoadItem);
//        myGameMenu.add(myExitItem);
//        setJMenuBar(myMenuBar);
//        menuBarListener();
//
//    }
//    private void menuBarListener() {
//        myExitItem.addActionListener(theEvent -> showDialog(new ExitPanel()));
////        mySaveItem.addActionListener(e -> GameSaveAndLoad.saveGame());
////        myLoadItem.addActionListener(e -> GameSaveAndLoad.loadGame());
//    }
//    class ExitPanel extends JPanel {
//        public ExitPanel() {
//            setBackground(GREEN);
//            JLabel exitLabel1 = new JLabel("CONFIRM EXIT");
//            exitLabel1.setForeground(DARKEST_GREEN);
//            JLabel exitLabel2 = new JLabel("UNSAVED PROGRESS WILL BE LOST");
//            exitLabel2.setForeground(DARKER_GREEN);
//
//            JPanel exitPanel1 = new JPanel();
//            exitPanel1.setOpaque(false);
//            exitPanel1.add(exitLabel1);
//            JPanel exitPanel2 = new JPanel();
//            exitPanel2.setOpaque(false);
//            exitPanel2.add(exitLabel2);
//
//            setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER,BORDER));
//            setLayout(new GridLayout(4, 1, 10, 10));
//            add(exitPanel1);
//            add(exitPanel2);
//
//            JButton exitButton = new JButton("EXIT");
//            exitButton.setBackground(LIGHTEST_GREEN);
//            exitButton.setForeground(LIGHTER_GREEN);
//            exitButton.setBorder(BorderFactory.createLineBorder(DARKEST_GREEN, 2));
//            exitButton.addActionListener(theEvent -> dispose());
//            add(exitButton);
//            add(myResumeButton);
//        }
//    }
    private JMenuBar myMenuBar;
    private JMenuItem mySaveItem, myLoadItem, myExitItem;
    private JButton myResumeButton;
    private GameSaveAndLoad myGameSaveLoad;
    private GamePanel myGamePanel;
    private MainMenuGUI myMainMenuGUI;
    private StatusGUI myStatusGUI;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Player myPlayer;
    private Maze myMaze;
    private Keyboard myKeyboard;
    private Game myGame;

    private static final int FRAME_WIDTH = GameSettings.SCREEN_WIDTH;
    private static final int FRAME_HEIGHT = GameSettings.SCREEN_HEIGHT + 30; // Extra height for status panel

    public GameFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Trivia Maze");

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
        myMainMenuGUI = new MainMenuGUI(this);
        myGamePanel = new GamePanel(this);

        Player player = Player.getInstance();
        myStatusGUI = new StatusGUI(player);

        contentPanel.add(myMainMenuGUI, "MainMenu");
        contentPanel.add(myGamePanel, "Game");

        add(myStatusGUI, BorderLayout.NORTH);

        cardLayout.show(contentPanel, "MainMenu");
    }


//    public GameFrame() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setResizable(false);
//        setTitle("Trivia Maze");
//
//        contentPanel = new JPanel(new CardLayout());
//
//        myGamePanel = new GamePanel(this);
//        add(myGamePanel);
//
//        initComponents();
//        pack();
//        setLocationRelativeTo(null);
//        setVisible(true);
//
//        myGamePanel.startGame();
//    }
//
//    private void initComponents() {
//
//        myMainMenuGUI = new MainMenuGUI(this);
//        myGamePanel = new GamePanel(this);
//
//        Player player = Player.getInstance();
//        myStatusGUI = new StatusGUI(player);
//
//        contentPanel.add(myMainMenuGUI, "MainMenu");
//        contentPanel.add(myGamePanel, "Game");
//
//        add(myStatusGUI, BorderLayout.NORTH);
//
//        cardLayout.show(contentPanel, "MainMenu");
//        myGameSaveLoad = new GameSaveAndLoad();
//
//        myMainMenuGUI = new MainMenuGUI(this);
//        myGamePanel = new GamePanel(this);
//
//        Player player = Player.getInstance();
//        myStatusGUI = new StatusGUI(player);
//
//        contentPanel.add(myMainMenuGUI, "MainMenu");
//        contentPanel.add(myGamePanel, "Game");
//
//        ((CardLayout) contentPanel.getLayout()).show(contentPanel, "MainMenu");
//
//        myResumeButton = new JButton("Resume");
//        myResumeButton.addActionListener(this);
//    }

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

        Player player = Player.getInstance();
        myStatusGUI = new StatusGUI(player);
        add(myStatusGUI, BorderLayout.NORTH);
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
        boolean saved = myGameSaveLoad.saveGame();
        if (saved) {
            JOptionPane.showMessageDialog(this, "Game saved successfully!", "Save Game", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save game.", "Save Game", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadGame() {
        boolean loaded = myGameSaveLoad.loadGame();
        if (loaded) {
            JOptionPane.showMessageDialog(this, "Game loaded successfully!", "Load Game", JOptionPane.INFORMATION_MESSAGE);
            myGamePanel.setMaze(myGameSaveLoad.getMaze());
            myGamePanel.setPlayer(myGameSaveLoad.getPlayer());
            updateGameState();
            resumeGame();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to load game.", "Load Game", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateGameState() {
        // Update any necessary game state after loading
        // For example, update the UI with the new player position, score, etc.
        myGamePanel.setMaze(myGameSaveLoad.getMaze());
        myGamePanel.setPlayer(myGameSaveLoad.getPlayer());
        updateScore(myGameSaveLoad.getPlayer().getMyScore());
        // Add any other necessary updates
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

    public void updateRemainingQuestions(int remaining) {
        myStatusGUI.updateRemainingQuestions(remaining);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            GameFrame game = new GameFrame();
//            game.setVisible(true);
//        });
//    }

}