package Test;

import Model.Room;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class test the Room class
 *
 * @author Andrew Hwang
 * @version 1
 */
class RoomTest {

    /**
     * testingRoom is a Room object that is used to test.
     */
    Room testingRoom;

    /**
     * This test checks if the setRandomDoorInRoom
     * is actually equal by check if two rooms Door list
     * are not unique to one another.
     */
    @Test
    void setRandomDoorInRoom() {
        testingRoom = new Room();
        testingRoom.setRandomDoorInRoom();
        Room room2 = new Room();
        room2.setRandomDoorInRoom();

        assertNotEquals(testingRoom.getMyDoorInRoomList(), room2.getMyDoorInRoomList());
    }

    /**
     * This test checks if the getRoomMovement method
     * correctly gets the movement of the room, by checking
     * if the given boolean equals false.
     */
    @Test
    void getRoomMovement() {
        testingRoom = new Room();
        assertFalse(testingRoom.getRoomMovement());
    }

    /**
     * This test checks if the setRoomMovement method
     * correctly sets the movement of the room, by checking
     * if the room movement boolean is false once set.
     */
    @Test
    void setRoomMovement() {
        testingRoom = new Room();
        testingRoom.setRoomMovement(false);
        assertFalse(testingRoom.getRoomMovement());
    }

    /**
     * This test checks if the movementAvailable methods
     * correctly gets the movement available methods.
     */
    @Test
    void movementAvailable() {
        testingRoom = new Room();
        assertFalse(testingRoom.getRoomMovement());
    }

}