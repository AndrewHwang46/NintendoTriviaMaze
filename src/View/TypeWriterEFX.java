package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypeWriterEFX extends JFrame {
    private JLabel myTitleLabel;
    private Timer timer;
    private String fullText = "Trivia Maze";
    private int index = 0;

    public TypeWriterEFX() {
        setTitle("Typewriter Effect Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLayout(new FlowLayout());

        myTitleLabel = new JLabel("", SwingConstants.CENTER);
        myTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(myTitleLabel);

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < fullText.length()) {
                    myTitleLabel.setText(fullText.substring(0, index + 1));
                    index++;
                } else {
                    timer.stop();
                }
            }
        });

        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TypeWriterEFX().setVisible(true);
            }
        });
    }
}
