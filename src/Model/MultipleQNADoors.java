package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Noah Ogilvie
 */
public final class MultipleQNADoors extends AbstractDoors {

    private final List<String> myOtherOptions;

    public MultipleQNADoors(final String theAnswer,
                            final String theQuestion,
                            final List<String> theOtherOptions) {
        super(theAnswer, theQuestion);
        this.myOtherOptions = new ArrayList<String>(theOtherOptions);
    }

    public final List<String> getOtherOptions() {
        return new ArrayList<String>(myOtherOptions);
    }
}
