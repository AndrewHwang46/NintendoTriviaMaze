/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */

package Model;

/**
 * ShortQNADoor class is a child class of AbstractDoor. Its purpose is to be
 * a separate door that holds features of short questions with answer inputs options.
 *
 * @author Noah Ogilvie
 */
public class ShortQNADoor extends AbstractDoor {

    /**
     * ShortQNADoor() is a public constructor that passes its parameters into the parent's constructor.
     * @param theAnswer     the answer to the question.
     * @param theQuestion   the question for the door.
     */
    public ShortQNADoor(final String theAnswer, final String theQuestion) {
        super(theAnswer, theQuestion);
    }

    /**
     * isCorrect() method checks whether the input of the player is correct.
     * @param theChoice     the input from the player
     *
     * @return a boolean value depending on the input from the player.
     */
    public boolean isCorrect(final String theChoice) {
        return theChoice.equalsIgnoreCase(getAnswer());
    }
}
