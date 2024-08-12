package Model;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class Room implements Serializable {
    private boolean myRoomMovement;
    private final Factory myFactory;
    private static final int MAX_ROOM = 4;
    private final Random myRandom = new Random();
    private AbstractDoor[] myDoorInRoomList;
    private ArrayList<AbstractDoor> myDoorTotalList;
    private QuestionsList myUnusedQuestionsList = new QuestionsList();


    //Currently an empty constructor
    public Room () {
        myRoomMovement = true;
        myFactory = new Factory();
        myDoorInRoomList = new AbstractDoor[MAX_ROOM];
        myDoorTotalList = new ArrayList<>(myFactory.getListOfDoors());

    }

    public void setRandomDoorInRoom() {
        for (int i = 0; i < MAX_ROOM; i++) {
            if (myUnusedQuestionsList.getListSize() != 0) {
                int index = myRandom.nextInt(myUnusedQuestionsList.getListSize()-1);
                myDoorInRoomList[i] = myUnusedQuestionsList.getUsedQuestion(index);
                myUnusedQuestionsList.removeDuplicates(index);
            }
        }

    }

    public boolean getRoomMovement() {
        return myRoomMovement;
    }

    public void setRoomMovement(boolean theRoomMovement) {
        myRoomMovement = theRoomMovement;
    }

    public void movementAvalible() {
        for (AbstractDoor myArrayOfDoor : myDoorTotalList) {
            if (!myArrayOfDoor.getStateOfDoor()) {
                myRoomMovement = false;
            }else {
                myRoomMovement = true;
            }

        }

    }

    public void setMyArrayOfDoors(AbstractDoor[] theArrayOfDoors) {
        if (theArrayOfDoors.length != MAX_ROOM) {
            throw new IllegalArgumentException("Doors do not have the same length");
        }
        for (int i = 0; i < theArrayOfDoors.length; i++) {
            myDoorTotalList.set(i, theArrayOfDoors[i]);
        }
    }

    public AbstractDoor[] getMyDoorInRoomList() {
        return myDoorInRoomList;
    }

    @Override
    public String toString() {
        StringBuilder roomString = new StringBuilder();
        for(int i = 0; i < MAX_ROOM; i++) {
            roomString.append(i).append("=").append(myDoorTotalList.get(i))
                    .append(myDoorInRoomList[i].getUserAttempted())
                    .append(myDoorInRoomList[i].getStateOfDoor());
        }
        return roomString.toString();
    }

}
