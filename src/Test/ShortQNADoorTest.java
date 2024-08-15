/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */
package Test;

import Model.ShortQNADoor;
import Model.TrueOrFalseDoor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ShortQNADoorTest tests for the variables being sent into the
 * constructor's parameters. Tests whether if the doors constructed
 * are equal or not.
 *
 * @author Noah Ogilvie
 * @version 3.0
 */
final class ShortQNADoorTest {

    /**
     * myDoor is a ShortQNADoor private field.
     */
    private ShortQNADoor myDoor;

    /**
     * setUp() method initializes the global private fields.
     */
    @BeforeEach
    void setUp() {
        final String answer = "Kanye West";
        final String question = "Who made Graduation?";
        myDoor = new ShortQNADoor(answer, question);
    }

    /**
     * answerTest() method checks if the variable
     * passed to the constructor was correct.
     */
    @Test
    void answerTest() {
        final String answer = "Kanye West";
        assertEquals(myDoor.getAnswer(), answer);
    }

    /**
     * questionTest() method checks if the variable
     * passed to the constructor was correct.
     */
    @Test
    void questionTest() {
        final String question = "Who made Graduation?";
        assertEquals(myDoor.getQuestion(), question);
    }

    /**
     * wrongAnswerTest() method checks if the variable
     * passed does not match the given variable.
     */
    @Test
    void wrongAnswerTest() {
        final String answer = "Drake";
        assertNotEquals(myDoor.getAnswer(), answer);
    }

    /**
     * wrongQuestionTest() method checks if the variable
     * passed does not match the given variable.
     */
    @Test
    void wrongQuestionTest() {
        final String question = "";
        assertNotEquals(myDoor.getQuestion(), question);
    }

    /**
     * isCorrectTest() method checks if the correct answer is
     * passed.
     */
    @Test
    void isCorrectTest() {
        final String answer = "Kanye West";
        assertTrue(myDoor.isCorrect(answer));
    }

    /**
     * isNotCorrectTest() method checks if an answer is passed,
     * but it is not correct.
     */
    @Test
    void isNotCorrectTest() {
        final String answer = "Dr. Dre";
        assertFalse(myDoor.isCorrect(answer));
    }

    /**
     * equalsTest() method checks if both constructed doors with
     * the same passed variables are equal to each other.
     */
    @Test
    void equalsTest() {
        final String answer = "Kanye West";
        final String question = "Who made Graduation?";
        assertEquals(myDoor, new ShortQNADoor(answer, question));
    }

    /**
     * notEqualsTest() method checks if two different ShortQNADoors
     * are not equal.
     */
    @Test
    void notEqualsTest() {
        final String answer = "Kanye West";
        assertNotEquals(myDoor, new ShortQNADoor(answer, answer));
    }

    /**
     * notEqualsTest2() method checks if different of doors
     * constructed are not the same.
     */
    @Test
    void notEqualsTest2() {
        final String question = "Kanye West made Donda?";
        final String answer = "True";
        final String notAnswer = "False";
        assertNotEquals(new TrueOrFalseDoor(question, answer, notAnswer), myDoor);
    }

    /**
     * sameHashCodeTest() method checks if two of the same doors have the
     * same hash codes.
     */
    @Test
    void sameHashCodeTest() {
        final String answer = "Kanye West";
        final String question = "Who made Graduation?";
        assertEquals(myDoor.hashCode(), new ShortQNADoor(answer, question).hashCode());
    }

    /**
     * differentHashCodeTest() method checks if two of the same type of doors
     * do not equal in their hash codes.
     */
    @Test
    void differentHashCodeTest() {
        final String answer = "Kanye West";
        assertNotEquals(myDoor.hashCode(), new ShortQNADoor(answer, answer).hashCode());
    }

    /**
     * differentHashCodeTest2() method checks if two different doors
     * do not have the same hash code.
     */
    @Test
    void differentHashCodeTest2() {
        final String question = "Kanye West made Donda?";
        final String answer = "True";
        final String notAnswer = "False";
        assertNotEquals(new TrueOrFalseDoor(question, answer, notAnswer), myDoor);
    }
}
