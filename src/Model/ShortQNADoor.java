package model;

public class ShortQNADoor extends AbstractDoor {

    public ShortQNADoor(final String theAnswer, final String theQuestion) {
        super(theAnswer, theQuestion);
    }

    public boolean isCorrect(final String theChoice) {
        return theChoice.equalsIgnoreCase(getAnswer());
    }
}
