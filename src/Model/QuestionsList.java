package Model;

import java.util.ArrayList;

/**
 * This class stores the list of Doors to make sure there are no duplicate
 * questions in each room in the maze.
 *
 * @version 1
 * @author Andrew Hwang
 */
public class QuestionsList {
    /**
     * myFactory is an instance of the Factory class lets you grab the list of question.
     */
    private final Factory myFactory;

    /**
     *myUnusedQuestion is list of all the unused questions left.
     */
    private ArrayList<AbstractDoor> myUnusedQuestion;


    /**
     * This method is the constructor for the QuestionList class.
     */
    QuestionsList() {
        myFactory = new Factory();
        myUnusedQuestion = new ArrayList<>(myFactory.getListOfDoors());
    }

    /**
     * This method returns the list of unused questions.
     * @param theIndex is an integer and the index of teh desired questions.
     * @return is the door that holds the specific question.
     */
    public AbstractDoor getUnusedQuestion(int theIndex) {
        return myUnusedQuestion.get(theIndex);
    }

    /**
     * This method sets the list of unused questions.
     * @param usedQuestion is an arraylist of questions to be stored.
     */
    public void setUsedQuestion(ArrayList<AbstractDoor> usedQuestion) {
        myUnusedQuestion = usedQuestion;
    }

    /**
     * This method removes a question at a given index to make
     * sure there are no duplicates.
     * @param theIndex is an int and the index that need to be removed.
     */
    public void removeDuplicates(int theIndex) {
        myUnusedQuestion.remove(theIndex);
    }

    /**
     * This method returns the size of the list of unused questions.
     * @return is an int and the size of the list of unused questions.
     */
    public int getListSize() {
        return myUnusedQuestion.size();
    }
}
