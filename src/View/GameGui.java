package View;
//this is the gui file stuff

import javax.swing.*;
import java.awt.*;
public class GameGui extends JFrame {
    private JPanel mazePanel;
    private JPanel questionPanel;
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel scoreLabel;

    public GameGui() {
        setTitle("Trivia Maze");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Maze Panel
        mazePanel = new JPanel();
        mazePanel.setBackground(Color.LIGHT_GRAY);
        mazePanel.setPreferredSize(new Dimension(400, 400));
        add(mazePanel, BorderLayout.CENTER);

        // Question Panel
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        questionLabel = new JLabel("Question goes here");
        answerField = new JTextField(20);
        submitButton = new JButton("Submit");
        scoreLabel = new JLabel("Score: 0");

        questionPanel.add(questionLabel);
        questionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        questionPanel.add(answerField);
        questionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        questionPanel.add(submitButton);
        questionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        questionPanel.add(scoreLabel);

        add(questionPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameGui triviaMaze = new GameGui();
            triviaMaze.setVisible(true);
        });
    }
}
