package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypeWriterEFX extends JLabel {
    private Timer timer;
    private String fullText;
    private int index = 0;

    public TypeWriterEFX(String text, int horizontalAlignment) {
        super("", horizontalAlignment);
        this.fullText = text;
        setFont(new Font("Arial", Font.BOLD, 24)); // You can customize this

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < fullText.length()) {
                    setText(fullText.substring(0, index + 1));
                    index++;
                } else {
                    timer.stop();
                }
            }
        });
    }

    public void startEffect() {
        index = 0;
        setText("");
        timer.start();
    }

    public void stopEffect() {
        timer.stop();
        setText(fullText);
    }

    public void setTypewriterText(String theText) {
        this.fullText = theText;
        index = 0;
        setText("");
    }
}
