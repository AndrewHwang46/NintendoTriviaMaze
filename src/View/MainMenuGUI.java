package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuGUI extends JPanel {
    private JButton startButton, optionsButton, helpButton, exitButton;
    private JLabel titleLabel;

    public MainMenuGUI(GameFrame gameFrame) {
        setLayout(new GridLayout(5, 1, 10, 10));

        titleLabel = new JLabel("Trivia Maze", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        startButton = createButton("Start", e -> gameFrame.startGame());
        optionsButton = createButton("Options", e -> {/* Add options logic */});
        helpButton = createButton("Help", e -> {/* Add help logic */});
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
        add(optionsButton);
        add(helpButton);
        add(exitButton);
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.addActionListener(listener);
        return button;
    }
}