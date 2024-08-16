package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuGUI extends JPanel {
    private JButton myStartButton, myLoadButton, myInstructions, myExitButton;
    private JLabel myTitleLabel;
    private GameFrame gameFrame;

    // for Jian
    public MainMenuGUI(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        setLayout(new GridLayout(5, 1, 10, 10));

        myTitleLabel = new JLabel("Trivia Maze", SwingConstants.CENTER);
        myTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        myStartButton = createButton("Start", e -> gameFrame.startGame());
        myLoadButton = createButton("Load Save", e -> gameFrame.loadGame());
        myInstructions = createButton("Instructions", e -> {
            JOptionPane.showMessageDialog(gameFrame, "This is a trivia maze based " +
                    "on Nintendo games. To start the game you must press the start button above. \n" +
                    "Once the game has started you must traverse the maze and answer the question \n" +
                    "correctly to pass through each door. There are three types of doors: multiple choice, \n" +
                    "true/false, and short response. If you get the answer wrong then the door locks.\n" +
                    "You must reach the end before you lock all the doors.");
        });
        myExitButton = createButton("Exit", e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?", "Confirm Exit",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        add(myTitleLabel);
        add(myStartButton);
        add(myLoadButton);
        add(myInstructions);
        add(myExitButton);
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.addActionListener(listener);
        return button;
    }
}

//package View;
//
//import Model.GameSaveAndLoad;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionListener;
//
//public class MainMenuGUI extends JPanel {
//    private JButton myStartButton, myLoadButton, myInstructions, myExitButton;
//    private JLabel myTitleLabel;
//    private GameSaveAndLoad myGameSaveAndLoad = new GameSaveAndLoad();
//
//
//    public MainMenuGUI(GameFrame gameFrame) {
//        setLayout(new GridLayout(5, 1, 10, 10));
//
//        myTitleLabel = new JLabel("Trivia Maze", SwingConstants.CENTER);
//        myTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
//
//        myStartButton = createButton("Start", e -> gameFrame.startGame());
//        myLoadButton = createButton("Load Save", e -> {
//            if(myGameSaveAndLoad.getSaved() == Boolean.TRUE) {
//                myGameSaveAndLoad.loadGame();
//            }
//        });
//        myInstructions = createButton("Instructions", e -> {
//            JOptionPane.showMessageDialog(gameFrame, "This is a trivia maze based " +
//                    "on Nintendo games.To start the game you must press the start button above. \n" +
//                    "Once the game has started you must transverse the maze and answer the question \n" +
//                    "correctly to pass through each door. The are three types of doors, multiple choice, \n" +
//                    "true/false, and short response. If you get the answer wrong then the door locks.\n" +
//                    "You must reach the end before you lock all the doors.");
//        });
//        myExitButton = createButton("Exit", e -> {
//            int confirm = JOptionPane.showConfirmDialog(this,
//                    "Are you sure you want to exit?", "Confirm Exit",
//                    JOptionPane.YES_NO_OPTION);
//            if (confirm == JOptionPane.YES_OPTION) {
//                System.exit(0);
//            }
//        });
//
//        add(myTitleLabel);
//        add(myStartButton);
//        add(myLoadButton);
//        add(myInstructions);
//        add(myExitButton);
//
//    }
//
//
//
//    private JButton createButton(String text, ActionListener listener) {
//        JButton button = new JButton(text);
//        button.setFont(new Font("Arial", Font.PLAIN, 14));
//        button.addActionListener(listener);
//        return button;
//    }
//}