package Test;

import Model.Room;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    Room room;
    @Test
    void setRandomDoorInRoom() {
        room = new Room();
        room.setRandomDoorInRoom();
        Room room2 = new Room();
        room2.setRandomDoorInRoom();

        assertNotEquals(room.getMyDoorInRoomList(), room2.getMyDoorInRoomList());

        //assertEquals();
    }

    @Test
    void getRoomMovement() {
        room = new Room();
        assertFalse(room.getRoomMovement());
    }

    @Test
    void setRoomMovement() {
        room = new Room();
        room.setRoomMovement(true);
        assertFalse(room.getRoomMovement());
    }

    @Test
    void movementAvailable() {
        room = new Room();
        room.setRoomMovement(true);
        assertFalse(room.getRoomMovement());
    }

}