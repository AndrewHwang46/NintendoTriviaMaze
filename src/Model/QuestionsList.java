package Model;

import java.util.ArrayList;

public class QuestionsList {
    private Factory myFactory;
    private ArrayList<AbstractDoor> myUsedQuestion;


    QuestionsList() {
        myFactory = new Factory();
        myUsedQuestion = new ArrayList<>(myFactory.getListOfDoors());
    }

    public AbstractDoor getUsedQuestion(int theIndex) {
        return myUsedQuestion.get(theIndex);
    }

    public void setUsedQuestion(ArrayList<AbstractDoor> usedQuestion) {
        myUsedQuestion = usedQuestion;
    }

    public void removeDuplicates(int i) {
        myUsedQuestion.remove(i);
    }
    public int getListSize() {
        return myUsedQuestion.size();
    }
}
