package model;

/**
 * Doors interface
 *
 * @author Noah Ogilvie
 */
public interface Doors {

    //Is there a need for this for the GUI?
    static final int SIZE = 10;

    boolean getStateOfDoor();
    void setStateOfDoor(final boolean theState);
    String getAnswer();
    String getQuestion();
    boolean getUserAttempted();
    void setUserAttempted(final boolean theUserAttempted);
}
