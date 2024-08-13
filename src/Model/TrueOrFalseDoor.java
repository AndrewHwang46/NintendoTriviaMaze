package Model;

import java.io.Serializable;

/**
 *
 * @author Noah Ogilvie
 */
public final class TrueOrFalseDoor extends AbstractDoor {

    private final String myWrongAnswer;

    public TrueOrFalseDoor(final String theAnswer,
                           final String theQuestion,
                           final String theWrongAnswer) {
        super(theAnswer, theQuestion);
        this.myWrongAnswer = theWrongAnswer;
    }

    public final String getWrongAnswer() {
        return this.myWrongAnswer;
    }

}
