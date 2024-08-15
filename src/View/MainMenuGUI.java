package View;

import Model.GameSaveAndLoad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuGUI extends JPanel {
    private JButton startButton, loadButton, myInstructions, exitButton;
    private JLabel titleLabel;
    private GameSaveAndLoad myGameSaveAndLoad;


    public MainMenuGUI(GameFrame gameFrame) {
        myGameSaveAndLoad = new GameSaveAndLoad();
        setLayout(new GridLayout(5, 1, 10, 10));

        titleLabel = new JLabel("Trivia Maze", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        startButton = createButton("Start", e -> gameFrame.startGame());
        loadButton = createButton("Load Save", e -> {
            if(myGameSaveAndLoad.getSaved()) {
                myGameSaveAndLoad.loadGame();
            }
        });
        myInstructions = createButton("Instructions", e -> {
            JOptionPane.showMessageDialog(gameFrame, "This is a trivia maze based " +
                    "on Nintendo games.To start the game you must press the start button above. \n" +
                    "Once the game has started you must transverse the maze and answer the question \n" +
                    "correctly to pass through each door. The are three types of doors, multiple choice, \n" +
                    "true/false, and short response. If you get the answer wrong then the door locks.\n" +
                    "You must reach the end before you lock all the doors.");
        });
        exitButton = createButton("Exit", e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?", "Confirm Exit",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        add(titleLabel);
        add(startButton);
        add(loadButton);
        add(myInstructions);
        add(exitButton);

    }



    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.addActionListener(listener);
        return button;
    }
}