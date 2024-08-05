package Model;

import java.util.ArrayList;

public class Room {
    private Door myDoor;
    private boolean myRoomMovement;
    private final Door[] myArrayOfDoors;
    private final int myMaxRoom = 4;


    //Currently an empty constructor
    public Room (Door theDoor) {
        myRoomMovement = true;
        myDoor = theDoor;
        myArrayOfDoors = new Door[myMaxRoom];
        for (int i = 0; i < myMaxRoom; i++) {
            myArrayOfDoors[i] = new Door();
        }
    }

    public Door getDoor() {
        return myDoor;
    }

    public void setDoor(Door theDoor){
        myDoor = theDoor;
    }

    public boolean getRoomMovement() {
        return myRoomMovement;
    }

    public void setRoomMovement(boolean theRoomMovement) {
        myRoomMovement = theRoomMovement;
    }

    public void movementAvalible() {
        for (Door myArrayOfDoor : myArrayOfDoors) {
            if (!myArrayOfDoor.getStateOfDoor()) {
                myRoomMovement = false;
            }else {
                myRoomMovement = true;
            }

        }

    }

    public void setMyArrayOfDoors(Door[] theArrayOfDoors) {
        if (theArrayOfDoors.length != myMaxRoom) {
            throw new IllegalArgumentException("Doors do not have the same length");
        }
        for (int i = 0; i < theArrayOfDoors.length; i++) {
            myArrayOfDoors[i] = theArrayOfDoors[i];
        }
    }


}
