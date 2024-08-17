package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The TypeWriterEFX class provides a typewriter-like text animation effect for JLabels.
 * It extends JLabel and uses a Timer to gradually reveal text character by character.
 *
 * @author Jian Azul
 * @version 1
 */
public class TypeWriterEFX extends JLabel {
    /** Timer used to control the typewriter animation. */
    private Timer myTimer;

    /** The complete text to be displayed. */
    private String myFullText;

    /** The current index of the character being displayed. */
    private int myIndex = 0;

    /**
     * Constructs a new TypeWriterEFX with the specified text and horizontal alignment.
     *
     * @param text The text to be displayed with the typewriter effect
     * @param horizontalAlignment The horizontal alignment of the text within the label
     */
    public TypeWriterEFX(String text, int horizontalAlignment) {
        super("", horizontalAlignment);
        this.myFullText = text;
        setFont(new Font("Arial", Font.BOLD, 24)); // You can customize this

        myTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (myIndex < myFullText.length()) {
                    setText(myFullText.substring(0, myIndex + 1));
                    myIndex++;
                } else {
                    myTimer.stop();
                }
            }
        });
    }

    /**
     * Starts the typewriter effect animation.
     * This method resets the text and begins revealing characters one by one.
     */
    public void startEffect() {
        myIndex = 0;
        setText("");
        myTimer.start();
    }

    /**
     * Stops the typewriter effect animation.
     * This method immediately displays the full text.
     */
    public void stopEffect() {
        myTimer.stop();
        setText(myFullText);
    }

    /**
     * Sets new text for the typewriter effect and resets the animation.
     *
     * @param theText The new text to be displayed with the typewriter effect
     */
    public void setTypewriterText(String theText) {
        this.myFullText = theText;
        myIndex = 0;
        setText("");
    }
}
