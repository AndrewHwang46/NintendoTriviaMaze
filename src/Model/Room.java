package Model;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * This class holds a holds 4 doors for each room created.
 * This class also hands if the room is locked off.
 *
 * @version 1
 * @author Andrew Hwang
 */
public class Room implements Serializable {

    /**
     * myRoomMovement is a boolean and is whether the Room has any doors open.
     */
    private boolean myRoomMovement;

    /**
     * myFactory is an instance of Factory.
     */
    private final Factory myFactory;

    /**
     * MAX_DOORS is an int and the max amount of doors in a single room.
     */
    private static final int MAX_DOORS = 4;

    /**
     * myRandom is a random then is used to pick a random door
     */
    private final Random myRandom = new Random();

    /**
     * myDoorInRoomList is a list of the doors in the room.
     */
    private AbstractDoor[] myDoorInRoomList;

    /**
     * myUnusedQuestionList is an instance of QuestionList and is ued to
     * add doors into myDoorInRoomList.
     */
    private QuestionsList myUnusedQuestionsList = new QuestionsList();


    /**
     * This is the constructor of the Room class.
     */
    public Room () {
        myRoomMovement = true;
        myFactory = new Factory();
        myDoorInRoomList = new AbstractDoor[MAX_DOORS];
        setRandomDoorInRoom();
    }

    /**
     * This method sets random doors to the room by using a random and getting a random index of
     * the size of the list in QuestionList. Then assigning them into the room.
     */
    public void setRandomDoorInRoom() {
        for (int i = 0; i < MAX_DOORS; i++) {
            if (myUnusedQuestionsList.getListSize() != 0) {
                int index = myRandom.nextInt(myUnusedQuestionsList.getListSize()-1);
                myDoorInRoomList[i] = myUnusedQuestionsList.getUnusedQuestion(index);
                myUnusedQuestionsList.removeDuplicates(index);
            }
        }

    }

    /**
     * This method gets the room movement boolean, but first checks if movement is available.
     */
    public boolean getRoomMovement() {
        movementAvailable();
        return myRoomMovement;
    }

    /**
     * This method takes a boolean and sets myRoomMovement.
     * @param theRoomMovement is a boolean and is
     *                        whether the room is false or true.
     */
    public void setRoomMovement(boolean theRoomMovement) {
        myRoomMovement = theRoomMovement;
    }

    /**
     * movementAvailable checks if the there is movement available in the room.
     */
    public void movementAvailable() {
        for (AbstractDoor myArrayOfDoor : myDoorInRoomList) {
            if (!myArrayOfDoor.getStateOfDoor()) {
                myRoomMovement = false;
            }else {
                myRoomMovement = true;
            }

        }

    }

    /**
     * This method returns the list of the doors in the room.
     * @return is an AbstractDoor list and is where the doors
     * in the room are held.
     */
    public AbstractDoor[] getMyDoorInRoomList() {
        return myDoorInRoomList;
    }

    /**
     * Overrides the toString to pass the doors in each room.
     * @return is a string of each door is in the Room
     * with their attempt and door state status.
     */
    @Override
    public String toString() {
        StringBuilder roomString = new StringBuilder();
        for(int i = 0; i < MAX_DOORS; i++) {
            roomString.append(i).append("=").append(myDoorInRoomList[i].getQuestion())
                    .append(myDoorInRoomList[i].getUserAttempted())
                    .append(myDoorInRoomList[i].getStateOfDoor());
        }
        return roomString.toString();
    }

}
