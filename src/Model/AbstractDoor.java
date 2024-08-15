/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */
package Model;

import java.io.Serial;
import java.io.Serializable;

/**
 * AbstractDoor Class is the abstract classes that hold essential
 * methods for the different types of doors found in the maze.
 *
 * @author Noah Ogilvie
 */
public abstract class AbstractDoor implements Serializable {

    /**
     * serialVersionUID is the serializable constant for the implementation of Serializable.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * myAnswer is a String private field that holds the answer to the door's question.
     */
    private final String myAnswer;

    /**
     * myQuestion is a String private field that holds the door's question.
     */
    private final String myQuestion;

    /**
     * myUserAttempt is a boolean private field that holds whether if the user attempted the door.
     */
    private boolean myUserAttempt;

    /**
     * myLockedDoor is a boolean private field that its either locked or not locked.
     */
    private boolean myLockedDoor;

    /**
     * AbstractDoor is the constructor.
     * @param theAnswer     the answer of the question
     * @param theQuestion   the current question
     */
    protected AbstractDoor(final String theAnswer,
                           final String theQuestion) {
        myUserAttempt = false;
        myLockedDoor = false;
        myAnswer = theAnswer;
        myQuestion = theQuestion;
    }

    /**
     * getStateOfDoor() gets the current state of the door.
     *
     * @return the current state of the door.
     */
    public final boolean getStateOfDoor() {
        return myLockedDoor;
    }

    /**
     * setStateOfDoor() sets the state of the door.
     * @param theState      the changed door state
     */
    public final void setStateOfDoor(final boolean theState) {
        myLockedDoor = theState;
        if (myLockedDoor) {
            myUserAttempt = true;
        }
    }

    /**
     * getUserAttempted() gets if the user attempted
     *
     * @return the users attempt.
     */
    public final boolean getUserAttempted() {
        return myUserAttempt;
    }

    /**
     * setUserAttempted sets the user attempts
     * @param theUserAttempted     boolean value if the user attempted
     */
    public final void setUserAttempted(final boolean theUserAttempted) {
        myUserAttempt = theUserAttempted;
    }

    /**
     * getAnswer() gets the answer.
     *
     * @return the answer.
     */
    public final String getAnswer() {
        return myAnswer;
    }

    /**
     * getQuestion() gets the question.
     *
     * @return the question.
     */
    public final String getQuestion() {
        return myQuestion;
    }
}
