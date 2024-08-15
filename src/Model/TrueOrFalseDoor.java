/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */

package Model;

/**
 * TrueOrFalseDoor class is a child class of AbstractDoor. Its purpose is to be
 * a separate door that holds features of true or false questions.
 *
 * @author Noah Ogilvie
 * @version 2.0
 */
public final class TrueOrFalseDoor extends AbstractDoor {

    /**
     * myWrongAnswer is a String private field that holds the incorrect answer.
     */
    private final String myWrongAnswer;

    /**
     * TrueOrFalseDoor() is a public constructor initializing the class' private field and
     * calling the parent's constructor and passing the appropriate values.
     * @param theAnswer         the answer to the question.
     * @param theQuestion       the question for the door.
     * @param theWrongAnswer    the incorrect answer.
     */
    public TrueOrFalseDoor(final String theAnswer,
                           final String theQuestion,
                           final String theWrongAnswer) {
        super(theAnswer, theQuestion);
        this.myWrongAnswer = theWrongAnswer;
    }

    /**
     * getWrongAnswer() method grabs the incorrect answer.
     *
     * @return the incorrect answer.
     */
    public String getWrongAnswer() {
        return this.myWrongAnswer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object theOther) {
        if (this == theOther) {
            return true;
        }

        if (theOther == null) {
            return false;
        }

        if (!(theOther instanceof TrueOrFalseDoor otherTrueOrFalseDoor)) {
            return false;
        }

        return super.equals(theOther) &&
                this.myWrongAnswer.equals(otherTrueOrFalseDoor.myWrongAnswer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 97;
        hash = 31 * hash + this.myWrongAnswer.hashCode();
        return hash;
    }
}
