package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDoor implements Door{
    private boolean myLockedDoor;
    private boolean myAttempt;
    //private List<Question> myQuestions;
    private List<Answer> myAnswers;

    protected AbstractDoor() {
        myLockedDoor = false;
        myAttempt = false;

        // Do List in a bit
        myAnswers = new ArrayList<Answer>();
    }

    @Override
    public boolean getStateOfDoor() {
        return myAttempt;
    }

    @Override
    public void setStateOfDoor(final boolean theState) {
        //Do later
//        if (theState) {
//            myAttempt = true;
//            //check for locked door
//        } else {
//            myAttempt = false;
//        }
        myAttempt = theState;
    }


}
