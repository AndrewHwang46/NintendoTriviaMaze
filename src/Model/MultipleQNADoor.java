package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Noah Ogilvie
 */
public final class MultipleQNADoor extends AbstractDoor {

    private final List<String> myOtherOptions;

    public MultipleQNADoor(final String theAnswer,
                           final String theQuestion,
                           final List<String> theOtherOptions) {
        super(theAnswer, theQuestion);
        this.myOtherOptions = new ArrayList<String>(theOtherOptions);
    }

    public final List<String> getOtherOptions() {
        return new ArrayList<String>(this.myOtherOptions);
    }
}
