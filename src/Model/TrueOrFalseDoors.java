package Model;

/**
 *
 * @author Noah Ogilvie
 */
public final class TrueOrFalseDoors extends AbstractDoors {

    private final String myWrongAnswer;

    public TrueOrFalseDoors(final String theAnswer,
                            final String theQuestion,
                            final String theWrongAnswer) {
        super(theAnswer, theQuestion);
        myWrongAnswer = theWrongAnswer;
    }

    public final String getWrongAnswer() {
        return myWrongAnswer;
    }

}
