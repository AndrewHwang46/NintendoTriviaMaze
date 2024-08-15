/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */

package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * MultipleQNADoor class is a child class of AbstractDoor. Its purpose is to be
 * a separate door that holds features of multiple choice options.
 *
 * @author Noah Ogilvie
 * @version 2.0
 */
public final class MultipleQNADoor extends AbstractDoor {

    /**
     * myOtherOptions is a list data structure private fields that hold the incorrect answer to the question
     */
    private final List<String> myOtherOptions;

    /**
     * MultipleQNADoor() is a public constructor that initializes the class' private fields
     * and calls the super keyword to access the parent's class constructor.
     * @param theAnswer         the answer to the question
     * @param theQuestion       the question for the door
     * @param theOtherOptions   the different options in a list data structure
     */
    public MultipleQNADoor(final String theAnswer,
                           final String theQuestion,
                           final List<String> theOtherOptions) {
        super(theAnswer, theQuestion);
        this.myOtherOptions = new ArrayList<>(theOtherOptions);
    }

    /**
     * getOtherOptions() method grabs the list data structure private field with the incorrect answers.
     * @return the list data structure private field with the incorrect answers.
     */
    public List<String> getOtherOptions() {
        return new ArrayList<>(this.myOtherOptions);
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

        if (!(theOther instanceof MultipleQNADoor otherMultipleQNA)) {
            return false;
        }

        return super.equals(theOther) &&
                this.myOtherOptions.equals(otherMultipleQNA.myOtherOptions);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 97;
        hash = 31 * hash + super.hashCode();
        hash = 31 * hash + this.myOtherOptions.hashCode();
        return hash;
    }
}
