package Model;

import java.io.Serializable;

/**
 *
 * @author Noah Ogilvie
 */
public abstract class AbstractDoor implements Serializable {

    private final String myAnswer;
    private final String myQuestion;
    private boolean myUserAttempt;
    private boolean myLockedDoor;


    //Changed Something

    protected AbstractDoor(final String theAnswer,
                           final String theQuestion) {
        myUserAttempt = false;
        myLockedDoor = false;
        myAnswer = theAnswer;
        myQuestion = theQuestion;
    }

    public final boolean getStateOfDoor() {
        return myLockedDoor;
    }

    public final void setStateOfDoor(final boolean theState) {
        myLockedDoor = theState;
        if (myLockedDoor) {
            myUserAttempt = true;
        }
    }

    public final boolean getUserAttempted() {
        return myUserAttempt;
    }

    public final void setUserAttempted(final boolean theUserAttempted) {
        myUserAttempt = theUserAttempted;
    }

    public final String getAnswer() {
        return myAnswer;
    }

    public final String getQuestion() {
        return myQuestion;
    }
}
