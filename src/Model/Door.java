package Model;

public interface Door {

    public static final int SIZE = 10;

    boolean getStateOfDoor();
    void setStateOfDoor(final boolean theState);
    String questionAnswer();
    String getDBquestion();
}
