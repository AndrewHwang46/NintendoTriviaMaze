package View;

import Model.Player;
import Model.GameSettings;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Status
 *
 * @author Andrew Hwang
 * @version 1
 */
public class StatusGUI extends JPanel implements PropertyChangeListener {
    private JLabel myScoreLabel;
    private JLabel myLevelLabel;
    private Player myPlayer;

    public StatusGUI(Player thePlayer) {
        this.myPlayer = thePlayer;
        setPreferredSize(new Dimension(GameSettings.SCREEN_WIDTH, 40));
        setBackground(new Color(50, 50, 50));
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        initComponents();
        styleComponents();

        thePlayer.addPropertyChangeListener(this);
    }

    private void initComponents() {
        myScoreLabel = new JLabel("Score: " + myPlayer.getMyScore());

        add(myScoreLabel);
    }

    private void styleComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color textColor = new Color(200, 200, 200);

        myScoreLabel.setFont(labelFont);
        myScoreLabel.setForeground(textColor);
        

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "score":
                updateScore((Integer) evt.getNewValue());
                break;
        }
    }

    public void updateScore(int score) {
        myScoreLabel.setText("Score: " + score);
    }

}