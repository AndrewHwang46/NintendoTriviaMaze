package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The MainMenuGUI class represents the main menu of the Trivia Maze game.
 * It extends JPanel and contains buttons for starting the game, loading a saved game,
 * viewing instructions, and exiting the game.
 */
public class MainMenuGUI extends JPanel {
    /** Button to start a new game. */
    private JButton myStartButton;

    /** Button to load a saved game. */
    private JButton myLoadButton;

    /** Button to display game instructions. */
    private JButton myInstructions;

    /** Button to exit the game. */
    private JButton myExitButton;

    /** Label displaying the game title. */
    private JLabel myTitleLabel;

    /** Reference to the main GameFrame. */
    private GameFrame gameFrame;

    /**
     * Constructs a new MainMenuGUI with the specified GameFrame.
     *
     * @param theGameFrame The GameFrame that contains this MainMenuGUI
     */
    public MainMenuGUI(GameFrame theGameFrame) {
        gameFrame = theGameFrame;
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

    /**
     * Creates a JButton with the specified text and action listener.
     *
     * @param theText The text to display on the button
     * @param theListener The ActionListener to attach to the button
     * @return A new JButton instance
     */
    private JButton createButton(String theText, ActionListener theListener) {
        JButton button = new JButton(theText);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.addActionListener(theListener);
        return button;
    }
}
