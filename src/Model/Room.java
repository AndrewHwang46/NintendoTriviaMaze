package Model;

import java.io.Serializable;
import java.util.Random;

public class Room implements Serializable {
    private boolean myRoomMovement;
    private final AbstractDoor[] myArrayOfDoors;
    private final int myMaxRoom = 4;
    private final Random myRandom = new Random();


    //Currently an empty constructor
    public Room (AbstractDoor theDoor) {
        myRoomMovement = true;

        myArrayOfDoors = new AbstractDoor[myMaxRoom];
        for (int i = 0; i < myMaxRoom; i++) {
            int randomDoor = myRandom.nextInt(myMaxRoom);

            myArrayOfDoors[i] = new TrueOrFalseDoor();
        }
    }


    public boolean getRoomMovement() {
        return myRoomMovement;
    }

    public void setRoomMovement(boolean theRoomMovement) {
        myRoomMovement = theRoomMovement;
    }

    public void movementAvalible() {
        for (AbstractDoor myArrayOfDoor : myArrayOfDoors) {
            if (!myArrayOfDoor.getStateOfDoor()) {
                myRoomMovement = false;
            }else {
                myRoomMovement = true;
            }

        }

    }

    public void setMyArrayOfDoors(AbstractDoor[] theArrayOfDoors) {
        if (theArrayOfDoors.length != myMaxRoom) {
            throw new IllegalArgumentException("Doors do not have the same length");
        }
        for (int i = 0; i < theArrayOfDoors.length; i++) {
            myArrayOfDoors[i] = theArrayOfDoors[i];
        }
    }



}
