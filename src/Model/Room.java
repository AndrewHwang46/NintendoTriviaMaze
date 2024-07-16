package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Room {
    private Door myDoor;
    private Wall myWall;
    private boolean myRoomMovement;
    private ArrayList<door>  myArrayOfDoors;

    //Currently an empty constructor
    public Room (Door theDoor, Wall theWall) {
        myRoomMovement = true;
        myDoor = theDoor;
        myWall = theWall;
        myArrayOfDoors = new ArrayList<door>();
    }

    public Door getDoor() {
        return myDoor;
    }

    public void setDoor(Door theDoor){
        myDoor = theDoor;
    }

    public boolean movementAvalible() {
        return myRoomMovement;
    }


}
