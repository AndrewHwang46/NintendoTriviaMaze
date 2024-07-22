package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI extends JFrame implements ActionListener {
    private JButton startButton, optionsButton, helpButton, exitButton;
    private JLabel titleLabel;

    public MainMenuGUI() {
        setTitle("Main Menu");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        titleLabel = new JLabel("Trivia Maze", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        startButton = createButton("Start");
        optionsButton = createButton("Options");
        helpButton = createButton("Help");
        exitButton = createButton("Exit");

        add(titleLabel);
        add(startButton);
        add(optionsButton);
        add(helpButton);
        add(exitButton);

        setLocationRelativeTo(null);  // Center the window
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            JOptionPane.showMessageDialog(this, "Starting the application...");
        } else if (e.getSource() == optionsButton) {
            JOptionPane.showMessageDialog(this, "Opening options menu...");
        } else if (e.getSource() == helpButton) {
            JOptionPane.showMessageDialog(this, "Displaying help information...");
        } else if (e.getSource() == exitButton) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?", "Confirm Exit",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenuGUI menu = new MainMenuGUI();
            menu.setVisible(true);
        });
    }
}