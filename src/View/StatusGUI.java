package View;

import Model.Player;
import Model.GameSettings;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StatusGUI extends JPanel implements PropertyChangeListener {
    private JLabel scoreLabel;
    private JLabel remainingQuestionsLabel;
    private JLabel levelLabel;
    private JProgressBar healthBar;
    private Player player;

    public StatusGUI(Player player) {
        this.player = player;
        setPreferredSize(new Dimension(GameSettings.SCREEN_WIDTH, 40));
        setBackground(new Color(50, 50, 50));
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        initComponents();
        styleComponents();

        player.addPropertyChangeListener(this);
    }

    private void initComponents() {
        scoreLabel = new JLabel("Score: 0");
        remainingQuestionsLabel = new JLabel("Questions: 0");
        levelLabel = new JLabel("Level: 1");

        add(scoreLabel);
        add(remainingQuestionsLabel);
        add(levelLabel);
    }

    private void styleComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color textColor = new Color(200, 200, 200);

        scoreLabel.setFont(labelFont);
        scoreLabel.setForeground(textColor);

        remainingQuestionsLabel.setFont(labelFont);
        remainingQuestionsLabel.setForeground(textColor);

        levelLabel.setFont(labelFont);
        levelLabel.setForeground(textColor);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "score":
                updateScore((Integer) evt.getNewValue());
                break;
            case "remainingQuestions":
                updateRemainingQuestions((Integer) evt.getNewValue());
                break;
            case "level":
                updateLevel((Integer) evt.getNewValue());
                break;
        }
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void updateRemainingQuestions(int remaining) {
        remainingQuestionsLabel.setText("Questions: " + remaining);
    }

    public void updateLevel(int level) {
        levelLabel.setText("Level: " + level);
    }

}