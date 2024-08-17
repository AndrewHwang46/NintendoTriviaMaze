package View;

import Model.Player;
import Model.GameSettings;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The StatusGUI class represents the status panel in the Trivia Maze game.
 * It displays the player's current score and updates in real-time.
 *
 * @author Andrew Hwang
 * @version 1
 */
public class StatusGUI extends JPanel implements PropertyChangeListener {
    /** Label to display the player's current score. */
    private JLabel myScoreLabel;

    /** Label to display the current level (not currently used). */
    private JLabel myLevelLabel;

    /** Reference to the Player object to track score changes. */
    private Player myPlayer;

    /**
     * Constructs a new StatusGUI with the specified Player.
     *
     * @param thePlayer The Player object whose status will be displayed
     */
    public StatusGUI(Player thePlayer) {
        this.myPlayer = thePlayer;
        setPreferredSize(new Dimension(GameSettings.SCREEN_WIDTH, 40));
        setBackground(new Color(50, 50, 50));
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        initComponents();
        styleComponents();

        thePlayer.addPropertyChangeListener(this);
    }

    /**
     * Initializes the components of the status panel.
     */
    private void initComponents() {
        myScoreLabel = new JLabel("Score: " + myPlayer.getMyScore());
        add(myScoreLabel);
    }

    /**
     * Applies styling to the components of the status panel.
     */
    private void styleComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color textColor = new Color(200, 200, 200);

        myScoreLabel.setFont(labelFont);
        myScoreLabel.setForeground(textColor);
    }

    /**
     * Handles property change events from the Player object.
     * Updates the displayed score when the player's score changes.
     *
     * @param evt The PropertyChangeEvent object containing the change details
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "score":
                updateScore((Integer) evt.getNewValue());
                break;
        }
    }

    /**
     * Updates the displayed score.
     *
     * @param score The new score to display
     */
    public void updateScore(int score) {
        myScoreLabel.setText("Score: " + score);
    }
}
