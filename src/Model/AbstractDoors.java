package model;

/**
 *
 * @author Noah Ogilvie
 */
public abstract class AbstractDoors implements Doors {

    private final String myAnswer;
    private final String myQuestion;
    private boolean myUserAttempt;
    private boolean myLockedDoor;

    protected AbstractDoors(final String theAnswer,
                            final String theQuestion) {
        myUserAttempt = false;
        myLockedDoor = false;

        myAnswer = theAnswer;
        myQuestion = theQuestion;
    }

    @Override
    public final boolean getStateOfDoor() {
        return myLockedDoor;
    }

    @Override
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

    @Override
    public final String getAnswer() {
        return myAnswer;
    }

    @Override
    public final String getQuestion() {
        return myQuestion;
    }
}
