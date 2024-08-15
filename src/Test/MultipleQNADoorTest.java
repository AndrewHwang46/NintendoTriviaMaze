package Test;

import Model.MultipleQNADoor;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public final class MultipleQNADoorTest {

    private MultipleQNADoorTest myDoor;

    @BeforeEach
    public void setUp() {
        List<String> decentOptions= new ArrayList<>();
        decentOptions.add("very cold");
        decentOptions.add("waterbottle");
        decentOptions.add("ice cream");
        myDoor = new MultipleQNADoor("Good life",
                "What happens when you have no stress?",
                decentOptions);
    }
}
