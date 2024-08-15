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
        this.myUserAttempt = false;
        this.myLockedDoor = false;
        this.myAnswer = theAnswer;
        this.myQuestion = theQuestion;
    }

    /**
     * getStateOfDoor() gets the current state of the door.
     *
     * @return the current state of the door.
     */
    public final boolean getStateOfDoor() {
        return this.myLockedDoor;
    }

    /**
     * setStateOfDoor() sets the state of the door.
     * @param theState      the changed door state
     */
    public final void setStateOfDoor(final boolean theState) {
        this.myLockedDoor = theState;
        if (this.myLockedDoor) {
            this.myUserAttempt = true;
        }
    }

    /**
     * getUserAttempted() gets if the user attempted
     *
     * @return the users attempt.
     */
    public final boolean getUserAttempted() {
        return this.myUserAttempt;
    }

    /**
     * setUserAttempted sets the user attempts
     * @param theUserAttempted     boolean value if the user attempted
     */
    public final void setUserAttempted(final boolean theUserAttempted) {
        this.myUserAttempt = theUserAttempted;
    }

    /**
     * getAnswer() gets the answer.
     *
     * @return the answer.
     */
    public final String getAnswer() {
        return this.myAnswer;
    }

    /**
     * getQuestion() gets the question.
     *
     * @return the question.
     */
    public final String getQuestion() {
        return this.myQuestion;
    }

    @Override
    public boolean equals(final Object theOther) {
        if (this == theOther) {
            return true;
        }

        if (theOther == null) {
            return false;
        }

        if (!(theOther instanceof AbstractDoor)) {
            return false;
        }

        final AbstractDoor otherAbstractDoor = (AbstractDoor) theOther;

        return this.myUserAttempt == otherAbstractDoor.myUserAttempt &&
                this.myLockedDoor == otherAbstractDoor.myLockedDoor &&
                this.myAnswer.equals(otherAbstractDoor.myAnswer) &&
                this.myQuestion.equals(otherAbstractDoor.myQuestion);
    }

    @Override
    public int hashCode() {
        int hash = 97;
        hash = 31 * hash + (this.myUserAttempt ? 1 : 0);
        hash = 31 * hash + (this.myLockedDoor ? 1 : 0);
        hash = 31 * hash + this.myAnswer.hashCode();
        hash = 31 * hash + this.myQuestion.hashCode();
        return hash;
    }
}
