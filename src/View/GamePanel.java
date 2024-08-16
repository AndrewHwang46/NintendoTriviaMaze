package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements Runnable, ActionListener {
    private GameManager gameManager;
    private Thread myGameThread;
    private Game myGame;
    private SoundManager mySound;
    private Keyboard myKeyboard;
    private JButton[] myDoorButtons;
    private JTextArea myQuestionArea;
    private JTextField myAnswerField;
    private JButton mySubmitButton;
    private Room myCurrentRoom;

    private static final int TILE_SIZE = GameSettings.TILE_SIZE;

    // for Jian
    public GamePanel(GameFrame gameFrame, GameManager gameManager) {
        this.gameManager = gameManager;
        this.setPreferredSize(new Dimension(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        setLayout(null);
        initGame();
    }

    private void initGame() {
        myKeyboard = new Keyboard();
        addKeyListener(myKeyboard);
        mySound = new SoundManager();
        myGame = new Game(this, myKeyboard, gameManager);
        initUI();
    }

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

    private void positionComponents() {
        // Position door buttons
        myDoorButtons[0].setBounds(GameSettings.SCREEN_WIDTH / 2 - 50, 50, 100, 30);
        myDoorButtons[1].setBounds(GameSettings.SCREEN_WIDTH - 130, GameSettings.SCREEN_HEIGHT / 2 - 15, 100, 30);
        myDoorButtons[2].setBounds(GameSettings.SCREEN_WIDTH / 2 - 50, GameSettings.SCREEN_HEIGHT - 80, 100, 30);
        myDoorButtons[3].setBounds(30, GameSettings.SCREEN_HEIGHT / 2 - 15, 100, 30);

        // Position question components
        myQuestionArea.setBounds(100, 100, GameSettings.SCREEN_WIDTH - 200, 100);
        myAnswerField.setBounds(100, 210, GameSettings.SCREEN_WIDTH - 200, 30);
        mySubmitButton.setBounds(GameSettings.SCREEN_WIDTH / 2 - 50, 250, 100, 30);
    }

    public void startGame() {
        myGameThread = new Thread(this);
        myGameThread.start();
    }

    public void resumeGame() {
        // Implement resume game logic here
    }

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

    private void update() {
        if (myGame != null) {
            myGame.update();
        } else {
            System.err.println("Game not initialized!");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (myGame != null) {
            myGame.draw(g2);
        }
        g2.dispose();
    }

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

    private void checkAnswer() {
        AbstractDoor door = myCurrentRoom.getMyDoorInRoomList()[0]; // Assuming we're checking the first door
        boolean playerAnswer = myAnswerField.getText().equalsIgnoreCase(door.getAnswer());
        gameManager.getPlayer().scoreChanger(playerAnswer);
        door.setStateOfDoor(!playerAnswer);
        door.setUserAttempted(true);

        myQuestionArea.setVisible(false);
        myAnswerField.setVisible(false);
        mySubmitButton.setVisible(false);

        enterRoom(myCurrentRoom); // Refresh the room view
    }

    private void enterRoom(Room room) {
        myCurrentRoom = room;
        AbstractDoor[] doors = room.getMyDoorInRoomList();
        for (int i = 0; i < doors.length; i++) {
            myDoorButtons[i].setVisible(doors[i] != null && !doors[i].getUserAttempted());
        }
    }

    public void musicBackground(final int theIndex) {
        mySound.setFile(theIndex);
        mySound.play();
        mySound.loop();
    }

    public void setMaze(Maze newMaze) {
        gameManager.setMaze(newMaze);
        updateMazeDisplay();
        updateCurrentRoom();
    }

    public void setPlayer(Player newPlayer) {
        gameManager.getPlayer().updateState(newPlayer);
        updatePlayerPosition();
        updatePlayerStats();
    }

    private void updateMazeDisplay() {
        // Clear existing maze display
        removeAll();

        // Redraw the maze based on the new maze object
        Room[][] mazeMap = gameManager.getMaze().getMyMap();
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

    private JPanel createRoomPanel(Room room) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(GameSettings.TILE_SIZE, GameSettings.TILE_SIZE));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return panel;
    }

    private void updateCurrentRoom() {
        int roomX = gameManager.getPlayer().getX() / GameSettings.TILE_SIZE;
        int roomY = gameManager.getPlayer().getY() / GameSettings.TILE_SIZE;
        myCurrentRoom = gameManager.getMaze().getMyMap()[roomY][roomX];
        updateDoorButtons();
    }

    private void updateDoorButtons() {
        AbstractDoor[] doors = myCurrentRoom.getMyDoorInRoomList();
        for (int i = 0; i < doors.length; i++) {
            myDoorButtons[i].setVisible(doors[i] != null && !doors[i].getUserAttempted());
        }
    }

    private void updatePlayerPosition() {
        repaint();
    }

    private void updatePlayerStats() {
        if (getParent() instanceof GameFrame) {
            GameFrame parentFrame = (GameFrame) getParent();
            parentFrame.updateScore(gameManager.getPlayer().getMyScore());
        }
    }
    public void resetGame() {
        gameManager.resetMaze();
        Player.resetPlayer();
        myCurrentRoom = null;
        for (JButton button : myDoorButtons) {
            button.setVisible(false);
        }
        myQuestionArea.setVisible(false);
        myAnswerField.setVisible(false);
        mySubmitButton.setVisible(false);
        updatePlayerPosition();
        gameManager.getPlayer().setMyScore(0);
        for (Room[] row : gameManager.getMaze().getMyMap()) {
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

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
        myGame.setGameManager(gameManager);
    }
}


//package View;
//
//import Model.*;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class GamePanel extends JPanel implements Runnable, ActionListener {
//
//    private Thread myGameThread;
//    private Game myGame;
//    private SoundManager mySound;
//    private Player myPlayer;
//    private Maze myMaze;
//    private Keyboard myKeyboard;
//    private JButton[] myDoorButtons;
//    private JTextArea myQuestionArea;
//    private JTextField myAnswerField;
//    private JButton mySubmitButton;
//    private Room myCurrentRoom;
//
//    private static final int TILE_SIZE = GameSettings.TILE_SIZE;
//
//    public GamePanel(GameFrame gameFrame) {
//        this.setPreferredSize(new Dimension(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT));
//        this.setBackground(Color.black);
//        this.setDoubleBuffered(true);
//        this.setFocusable(true);
//
//        setLayout(null);
//        initGame();
//    }
//
//    private void initGame() {
//        myKeyboard = new Keyboard();
//        addKeyListener(myKeyboard);
//        myPlayer = Player.getInstance();
//        myMaze.resetMaze("/Resources/Map/ScaleDownMaze.txt");
//        mySound = new SoundManager();
//        myGame = new Game(this, myKeyboard);
//        initUI();
//    }
//
//    private void initUI() {
//        myDoorButtons = new JButton[4];
//        for (int i = 0; i < 4; i++) {
//            myDoorButtons[i] = new JButton("Door " + (i + 1));
//            myDoorButtons[i].addActionListener(this);
//            myDoorButtons[i].setVisible(false);
//            add(myDoorButtons[i]);
//        }
//
//        myQuestionArea = new JTextArea();
//        myQuestionArea.setEditable(false);
//        myQuestionArea.setVisible(false);
//        add(myQuestionArea);
//
//        myAnswerField = new JTextField();
//        myAnswerField.setVisible(false);
//        add(myAnswerField);
//
//        mySubmitButton = new JButton("Submit");
//        mySubmitButton.addActionListener(this);
//        mySubmitButton.setVisible(false);
//        add(mySubmitButton);
//
//        positionComponents();
//    }
//
//    private void positionComponents() {
//        // Position door buttons
//        myDoorButtons[0].setBounds(GameSettings.SCREEN_WIDTH / 2 - 50, 50, 100, 30);
//        myDoorButtons[1].setBounds(GameSettings.SCREEN_WIDTH - 130, GameSettings.SCREEN_HEIGHT / 2 - 15, 100, 30);
//        myDoorButtons[2].setBounds(GameSettings.SCREEN_WIDTH / 2 - 50, GameSettings.SCREEN_HEIGHT - 80, 100, 30);
//        myDoorButtons[3].setBounds(30, GameSettings.SCREEN_HEIGHT / 2 - 15, 100, 30);
//
//        // Position question components
//        myQuestionArea.setBounds(100, 100, GameSettings.SCREEN_WIDTH - 200, 100);
//        myAnswerField.setBounds(100, 210, GameSettings.SCREEN_WIDTH - 200, 30);
//        mySubmitButton.setBounds(GameSettings.SCREEN_WIDTH / 2 - 50, 250, 100, 30);
//    }
//
//    public void startGame() {
//        myGameThread = new Thread(this);
//        myGameThread.start();
//    }
//
//    public void resumeGame() {
//        // Implement resume game logic here
//    }
//
//    @Override
//    public void run() {
//        double drawInterval = 1000000000 / GameSettings.FPS;
//        double delta = 0;
//        long lastTime = System.nanoTime();
//        long currentTime;
//
//        while (myGameThread != null) {
//            currentTime = System.nanoTime();
//
//            delta += (currentTime - lastTime) / drawInterval;
//            lastTime = currentTime;
//
//            if (delta >= 1) {
//                update();
//                repaint();
//                delta--;
//            }
//        }
//    }
//
//    private void update() {
//        if (myGame != null) {
//            myGame.update();
//        } else {
//            System.err.println("Game not initialized!");
//        }
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//        if (myGame != null) {
//            myGame.draw(g2);
//        }
//        g2.dispose();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() instanceof JButton) {
//            JButton clickedButton = (JButton) e.getSource();
//            if (clickedButton == mySubmitButton) {
//                checkAnswer();
//            } else {
//                for (int i = 0; i < myDoorButtons.length; i++) {
//                    if (clickedButton == myDoorButtons[i]) {
//                        showQuestion(i);
//                        break;
//                    }
//                }
//            }
//        }
//    }
//
//    private void showQuestion(int doorIndex) {
//        AbstractDoor door = myCurrentRoom.getMyDoorInRoomList()[doorIndex];
//        myQuestionArea.setText(door.getQuestion());
//        myQuestionArea.setVisible(true);
//        myAnswerField.setVisible(true);
//        mySubmitButton.setVisible(true);
//        for (JButton button : myDoorButtons) {
//            button.setVisible(false);
//        }
//    }
//
//    private void checkAnswer() {
//        AbstractDoor door = myCurrentRoom.getMyDoorInRoomList()[0]; // Assuming we're checking the first door
//        boolean playerAnswer = myAnswerField.getText().equalsIgnoreCase(door.getAnswer());
//        myPlayer.scoreChanger(playerAnswer);
//        door.setStateOfDoor(!playerAnswer);
//        door.setUserAttempted(true);
//
//        myQuestionArea.setVisible(false);
//        myAnswerField.setVisible(false);
//        mySubmitButton.setVisible(false);
//
//        enterRoom(myCurrentRoom); // Refresh the room view
//    }
//
//    private void enterRoom(Room room) {
//        myCurrentRoom = room;
//        AbstractDoor[] doors = room.getMyDoorInRoomList();
//        for (int i = 0; i < doors.length; i++) {
//            myDoorButtons[i].setVisible(doors[i] != null && !doors[i].getUserAttempted());
//        }
//    }
//
//    public void musicBackground(final int theIndex) {
//        mySound.setFile(theIndex);
//        mySound.play();
//        mySound.loop();
//    }
//
//    public void setMaze(Maze newMaze) {
//        this.myMaze = newMaze;
//        updateMazeDisplay();
//        updateCurrentRoom();
//    }
//
//    public void setPlayer(Player newPlayer) {
//        this.myPlayer = newPlayer;
//        updatePlayerPosition();
//        updatePlayerStats();
//    }
//
//    private void updateMazeDisplay() {
//        // Clear existing maze display
//        removeAll();
//
//        // Redraw the maze based on the new maze object
//        Room[][] mazeMap = myMaze.getMyMap();
//        for (int y = 0; y < mazeMap.length; y++) {
//            for (int x = 0; x < mazeMap[y].length; x++) {
//                if (mazeMap[y][x] != null) {
//                    JPanel roomPanel = createRoomPanel(mazeMap[y][x]);
//                    add(roomPanel);
//                }
//            }
//        }
//        initUI();
//
//        revalidate();
//        repaint();
//    }
//
//    private JPanel createRoomPanel(Room room) {
//        JPanel panel = new JPanel();
//        panel.setPreferredSize(new Dimension(GameSettings.TILE_SIZE, GameSettings.TILE_SIZE));
//        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        return panel;
//    }
//
//    private void updateCurrentRoom() {
//        int roomX = myPlayer.getX() / GameSettings.TILE_SIZE;
//        int roomY = myPlayer.getY() / GameSettings.TILE_SIZE;
//        myCurrentRoom = myMaze.getMyMap()[roomY][roomX];
//        updateDoorButtons();
//    }
//
//    private void updateDoorButtons() {
//        AbstractDoor[] doors = myCurrentRoom.getMyDoorInRoomList();
//        for (int i = 0; i < doors.length; i++) {
//            myDoorButtons[i].setVisible(doors[i] != null && !doors[i].getUserAttempted());
//        }
//    }
//
//    private void updatePlayerPosition() {
//        repaint();
//    }
//
//    private void updatePlayerStats() {
//        if (getParent() instanceof GameFrame) {
//            GameFrame parentFrame = (GameFrame) getParent();
//            parentFrame.updateScore(myPlayer.getMyScore());
//        }
//    }
//
//    public void resetGame() {
//        Player.resetPlayer();
//        myPlayer = Player.getInstance();
//        myMaze.resetMaze("/Resources/Map/ScaleDownMaze.txt");
//        //myMaze = Maze.getMazeSingleton("/Resources/Map/ScaleDownMaze.txt");
//        myCurrentRoom = null;
//        for (JButton button : myDoorButtons) {
//            button.setVisible(false);
//        }
//        myQuestionArea.setVisible(false);
//        myAnswerField.setVisible(false);
//        mySubmitButton.setVisible(false);
//        updatePlayerPosition();
//        myPlayer.setMyScore(0);
//        for (Room[] row : myMaze.getMyMap()) {
//            for (Room room : row) {
//                if (room != null) {
//                    for (AbstractDoor door : room.getMyDoorInRoomList()) {
//                        if (door != null) {
//                            door.setStateOfDoor(false);
//                            door.setUserAttempted(false);
//                        }
//                    }
//                }
//            }
//        }
//        if (mySound != null) {
//            mySound.stop();
//            musicBackground(0);
//        }
//        if (myGameThread != null) {
//            myGameThread.interrupt();
//        }
//        startGame();
//        repaint();
//    }
//}