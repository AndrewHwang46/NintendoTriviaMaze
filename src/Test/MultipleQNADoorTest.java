/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */
package Test;

import Model.MultipleQNADoor;
import Model.ShortQNADoor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * MultipleQNADoorTest tests for the variables being sent into the
 * constructor's parameters. Tests whether if the doors constructed
 * are equal or not.
 *
 * @author Noah Ogilvie
 * @version 4.0
 */
final class MultipleQNADoorTest {

    /**
     * myDoor is a MultipleQNADoor private field.
     */
    private MultipleQNADoor myDoor;

    /**
     * myOptions is a list data structure private field that holds
     * different options from the actual answer.
     */
    private List<String> myOptions;

    /**
     * setUp() method is used to initialize the global private variables.
     */
    @BeforeEach
    void setUp() {
        myOptions = new ArrayList<>();
        myOptions.add("very cold");
        myOptions.add("waterbottle");
        myOptions.add("ice cream");
        final String answer = "Good life.";
        final String question = "What are you when no stress?";
        myDoor = new MultipleQNADoor(answer, question, myOptions);
    }

    /**
     * answerTest() checks whether if the variable passed into the
     * constructor's parameter matches correctly.
     */
    @Test
    void answerTest() {
        final String answer = "Good life.";
        assertEquals(myDoor.getAnswer(), answer);
    }

    /**
     * wrongAnswerTest() checks whether if the variable passed into
     * the constructor's parameter does not match with the given
     * variable.
     */
    @Test
    void wrongAnswerTest() {
        final String answer = "Good lifes.";
        assertNotEquals(myDoor.getAnswer(), answer);
    }

    /**
     * questionTest() checks whether if the variable passed into the
     * constructor's parameter matches correctly.
     */
    @Test
    void questionTest() {
        final String question = "What are you when no stress?";
        assertEquals(myDoor.getQuestion(), question);
    }

    /**
     * wrongQuestionTest() checks whether if the variable passed into
     * the constructor's parameter does not match with the given
     * variable.
     */
    @Test
    void wrongQuestionTest() {
        final String question = "What are you when no stresses?";
        assertNotEquals(myDoor.getQuestion(), question);
    }

    /**
     * otherOptionsTest() checks whether if the list data structure
     * passed into the constructor's parameter matches correctly.
     */
    @Test
    void otherOptionsTest() {
        List<String> otherOptions = new ArrayList<>(myOptions);
        assertEquals(otherOptions, myDoor.getOtherOptions());
    }

    /**
     * wrongOtherOptionsTest1() checks whether if the variable passed
     * into the constructor's parameter does not match with the given
     * variable.
     */
    @Test
    void wrongOtherOptionsTest1() {
        List<String> otherOptions = new ArrayList<>();
        otherOptions.add("Kanye West");
        otherOptions.add("Travis Scott");
        otherOptions.add("Jay-Z");
        otherOptions.add("Lil Wayne");
        assertNotEquals(otherOptions, myDoor.getOtherOptions());
    }

    /**
     * wrongOtherOptionsTest2() checks whether if the variable passed
     * into the constructor's parameter does not match with the given
     * variable. Currently, the data structure contains null.
     */
    @Test
    void wrongOtherOptionsTest2() {
        List<String> otherOptions = new ArrayList<>();
        assertNotEquals(otherOptions, myDoor.getOtherOptions());
    }

    /**
     * wrongOtherOptionsTest3() checks whether if the variable passed
     * into the constructor's parameter does not match with the given
     * variable. Currently, the data structure contains some of the
     * correct values.
     */
    @Test
    void wrongOtherOptionsTest3() {
        List<String> otherOptions = new ArrayList<>();
        otherOptions.add("very cold");
        otherOptions.add("waterbottle");
        assertNotEquals(otherOptions, myDoor.getOtherOptions());
    }

    /**
     * wrongOtherOptionsTest4() checks whether if the variable passed
     * into the constructor's parameter does not match with the given
     * variable. Currently, the data structure contains all the
     * correct values but there is another value passed.
     */
    @Test
    void wrongOtherOptionsTest4() {
        List<String> otherOptions = new ArrayList<>();
        otherOptions.add("very cold");
        otherOptions.add("waterbottle");
        otherOptions.add("ice cream");
        otherOptions.add("fireworks");
        assertNotEquals(otherOptions, myDoor.getOtherOptions());
    }

    /**
     * sameDoorTest() checks whether if constructed the same door,
     * then both doors should be equal.
     */
    @Test
    void sameDoorTest() {
        List<String> decentOptions = new ArrayList<>(myOptions);
        final String answer = "Good life.";
        final String question = "What are you when no stress?";
        assertEquals(new MultipleQNADoor(answer, question, decentOptions), myDoor);
    }

    /**
     * notSameDoorTest1() checks whether if constructed the same type of door
     * and the String variables passed incorrectly are not the same
     * door as myDoor.
     */
    @Test
    void notSameDoorTest1() {
        List<String> decent = new ArrayList<>(myOptions);
        final String answer = "Good life.";
        final String question = "What are you when no stress?";
        assertNotEquals(new MultipleQNADoor(question, answer, decent), myDoor);
    }

    /**
     * notSameDoorTest2() checks whether if constructed the same type of door
     * and the String variables are repeated, therefore are not the same
     * door as myDoor.
     */
    @Test
    void notSameDoorTest2() {
        List<String> options = new ArrayList<>(myOptions);
        final String answer = "Good life.";
        assertNotEquals(new MultipleQNADoor(answer, answer, options), myDoor);
    }

    /**
     * notSameDoorTest1() checks whether if constructed the same type of door
     * and the String variables matches the actual variables, but the data
     * structure does not contain the correct values.
     */
    @Test
    void notSameDoorTest3() {
        List<String> decentOptions = new ArrayList<>();
        decentOptions.add("very cold");
        final String answer = "Good life.";
        final String question = "What are you when no stress?";
        assertNotEquals(new MultipleQNADoor(answer, question, decentOptions), myDoor);
    }

    /**
     * notEqualsTest() method checks if two different types of doors
     * are not equal.
     */
    @Test
    void notEqualsTest() {
        final String question = "Is GitHub complicated?";
        final String answer = "True";
        assertNotEquals(myDoor, new ShortQNADoor(answer, question));
    }

    /**
     * sameHashCodeTest() method checks if two of the same doors have
     * equal hash codes.
     */
    @Test
    void sameHashCodeTest() {
        List<String> decentOptions = new ArrayList<>(myOptions);
        final String answer = "Good life.";
        final String question = "What are you when no stress?";
        assertEquals(new MultipleQNADoor(answer, question, decentOptions).hashCode(), myDoor.hashCode());
    }

    /**
     * differentHashCodeTest() method checks if two different doors do
     * not have equal hash codes.
     */
    @Test
    void differentHashCodeTest() {
        List<String> decentOptions = new ArrayList<>(myOptions);
        final String question = "What are you when no stress?";
        assertNotEquals(new MultipleQNADoor(question, question, decentOptions).hashCode(), myDoor.hashCode());
    }

    /**
     * differentHashCodeTest2() method checks if two different types of doors
     * do not have equal hash codes.
     */
    @Test
    void differentHashCodeTest2() {
        final String question = "Is GitHub complicated?";
        final String answer = "True";
        assertNotEquals(myDoor.hashCode(), new ShortQNADoor(answer, question).hashCode());
    }
}
