/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */

package Test;

import Model.ShortQNADoor;
import Model.TrueOrFalseDoor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * TrueOrFalseDoorTest tests for the variables being sent into the
 * constructor's parameters. Tests whether if the doors constructed
 * are equal or not.
 *
 * @author Noah Ogilvie
 * @version 3.0
 */
final class TrueOrFalseDoorTest {

    /**
     * myDoor is a TrueOrFalseDoor private field.
     */
    private TrueOrFalseDoor myDoor;

    /**
     * setUp() method initializes the global private fields.
     */
    @BeforeEach
    void setUp() {
        final String question = "Is GitHub complicated?";
        final String answer = "True";
        final String notAnswer = "False";
        myDoor = new TrueOrFalseDoor(answer, question, notAnswer);
    }

    /**
     * answerTest() method checks if the variable passed
     * to the constructor was correct.
     */
    @Test
    void answerTest() {
        final String answer = "True";
        assertEquals(myDoor.getAnswer(), answer);
    }

    /**
     * questionTest() method checks if the variable passed
     * to the constructor was correct.
     */
    @Test
    void questionTest() {
        final String question = "Is GitHub complicated?";
        assertEquals(myDoor.getQuestion(), question);
    }

    /**
     * notAnswerTest() method checks if the variable passed
     * to the constructor was correct.
     */
    @Test
    void notAnswerTest() {
        final String notAnswer = "False";
        assertEquals(myDoor.getWrongAnswer(), notAnswer);
    }

    /**
     * wrongAnswerTest() method checks if the variable
     * passed does not match the given variable.
     */
    @Test
    void wrongAnswerTest() {
        final String wrongAnswer = "False";
        assertNotEquals(myDoor.getAnswer(), wrongAnswer);
    }

    /**
     * wrongNotAnswerTest() method checks if the variable
     * passed does not match the given variable.
     */
    @Test
    void wrongNotAnswerTest() {
        final String wrongNotAnswer = "True";
        assertNotEquals(myDoor.getWrongAnswer(), wrongNotAnswer);
    }

    /**
     * wrongQuestionTest() method checks if the variable passed
     * does not match the given variable.
     */
    @Test
    void wrongQuestionTest() {
        final String question = "Did Kanye West win the Grammy Awards?";
        assertNotEquals(myDoor.getQuestion(), question);
    }

    /**
     * equalsTest() method checks if the same type of doors constructed
     * with the same variables are equal to each other.
     */
    @Test
    void equalsTest() {
        final String question = "Is GitHub complicated?";
        final String answer = "True";
        final String notAnswer = "False";
        assertEquals(new TrueOrFalseDoor(answer, question, notAnswer), myDoor);
    }

    /**
     * equalsTest() method checks if the same type of doors constructed
     * with the same variables have the same hash code.
     */
    @Test
    void hashCodeTest() {
        final String question = "Is GitHub complicated?";
        final String answer = "True";
        final String notAnswer = "False";
        assertEquals(new TrueOrFalseDoor(answer, question, notAnswer), myDoor);
    }

    /**
     * notEqualsTest() method checks if the same type of doors constructed
     * with different variables are not the same.
     */
    @Test
    void notEqualsTest() {
        final String question = "Is GitHub complicated?";
        final String answer = "False";
        final String notAnswer = "True";
        assertNotEquals(new TrueOrFalseDoor(question, answer, notAnswer), myDoor);
    }

    /**
     * notEqualsTest2() method checks if different of doors
     * constructed are not the same.
     */
    @Test
    void notEqualsTest2() {
        final String question = "Is GitHub complicated?";
        final String answer = "True";
        assertNotEquals(new ShortQNADoor(question, answer), myDoor);
    }

    /**
     * notHashCodeTest() method checks if the same type of door construct
     * with different variables do not have the same hash code.
     */
    @Test
    void notHashCodeTest() {
        final String question = "Is GitHub complicated?";
        final String answer = "False";
        final String notAnswer = "True";
        assertNotEquals(new TrueOrFalseDoor(question, answer, notAnswer).hashCode(),
                        myDoor.hashCode());
    }

    /**
     * notHashCodeTest2() method checks if different of doors
     * constructed do not have the same hash code.
     */
    @Test
    void notHashCodeTest2() {
        final String question = "Is GitHub complicated?";
        final String answer = "True";
        assertNotEquals(new ShortQNADoor(answer, question).hashCode(),
                        myDoor.hashCode());
    }
}
