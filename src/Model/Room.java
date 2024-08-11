package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Room implements Serializable {
    private boolean myRoomMovement;
    private final Factory myFactory = new Factory();
    private static final int MAX_ROOM = 4;
    private final Random myRandom = new Random();
    private AbstractDoor[] myDoorInRoomList = new AbstractDoor[MAX_ROOM-1];
    private ArrayList<AbstractDoor> myDoorTotalList = new ArrayList<>();


    //Currently an empty constructor
    public Room () {
        myRoomMovement = true;
        myDoorTotalList = new ArrayList<>(myFactory.getListOfDoors());

    }

    public void setRandomDoorInRoom() {
        for (int i = 0; i < MAX_ROOM-1; i++) {
            int index = myRandom.nextInt(myDoorTotalList.size()-1);
            myDoorInRoomList[i] = myDoorTotalList.get(index);
            myDoorTotalList.remove(index);
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
