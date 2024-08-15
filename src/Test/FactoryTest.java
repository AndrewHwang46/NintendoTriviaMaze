/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */
package Test;

import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * FactoryTest class is testing for the construction of each door correctly.
 *
 * @author Noah Ogilvie
 * @version 1.0
 */
final class FactoryTest {

    /**
     * myDoorsList is a list data structure private field that holds
     * the complete total of doors found in the game.
     */
    private List<AbstractDoor> myDoorsList;

    /**
     * myDataSource is a SQLiteDataSource private field that holds
     * the connection into SQLite.
     */
    private SQLiteDataSource myDataSource;

    /**
     * setUp() method initializes the global private fields.
     */
    @BeforeEach
    void setUp() {
        Factory myFactory = new Factory();
        this.myDataSource = new SQLiteDataSource();
        AnswersAndQuestionsDB myDatabase = new AnswersAndQuestionsDB();
        this.myDoorsList = new ArrayList<>(myFactory.getListOfDoors());

        this.myDataSource = myDatabase.getDataSource();
        this.myDataSource = myDatabase.getDataSource();
        myDatabase.getOriginalTables(this.myDataSource);
        myDatabase.getOriginalValues(this.myDataSource);
    }

    /**
     * multipleQNATest() method checks whether if all the MultipleQNADoor
     * is created and sent into the list data structure.
     */
    @Test
    void multipleQNATest() {
        List<AbstractDoor> multipleQNA = new ArrayList<>();
        for (AbstractDoor door : this.myDoorsList) {
            if (door instanceof MultipleQNADoor) {
                multipleQNA.add(door);
            }
        }

        List<AbstractDoor> expected = new ArrayList<>();

        final String query = "SELECT am.answer, am.not1, am.not2, am.not3, qm.prompt " +
                             "FROM answersMultiple AS am " +
                             "JOIN questionsMultiple AS qm ON am.answer = qm.answer;";

        try (Connection conn = myDataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                List<String> notAnswer = new ArrayList<>();
                String prompt = rs.getString("prompt");
                String answer = rs.getString("answer");
                String not1 = rs.getString("not1");
                String not2 = rs.getString("not2");
                String not3 = rs.getString("not3");

                notAnswer.add(not1);
                notAnswer.add(not2);
                notAnswer.add(not3);

                MultipleQNADoor temp = new MultipleQNADoor(answer, prompt, notAnswer);
                expected.add(temp);
            }
        } catch (final SQLException e) {
            System.out.print("Unable to query: " + e.getMessage());
        }
        assertEquals(expected, multipleQNA);
    }

    /**
     * shortQNATest() method checks whether if all the ShortQNADoor
     * is created and sent into the list data structure.
     */
    @Test
    void shortQNATest() {
        List<AbstractDoor> shortQNA = new ArrayList<>();
        for (AbstractDoor door : this.myDoorsList) {
            if (door instanceof ShortQNADoor) {
                shortQNA.add(door);
            }
        }

        List<AbstractDoor> expected = new ArrayList<>();

        final String query = "SELECT * FROM shortQuestions";

        try (Connection conn = this.myDataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String question = rs.getString("shortPrompt");
                String answer = rs.getString("shortAnswer");

                ShortQNADoor temp = new ShortQNADoor(answer, question);

                expected.add(temp);
            }
        } catch (final SQLException e) {
            System.out.print("Unable to query: " + e.getMessage());
        }
        assertEquals(expected, shortQNA);
    }

    /**
     * trueOrFalseTest() method checks whether if all the TrueOrFalseDoor
     * is created and sent into the list data structure.
     */
    @Test
    void trueOrFalseTest() {
        List<AbstractDoor> trueOrFalse = new ArrayList<>();
        for (AbstractDoor door : this.myDoorsList) {
            if (door instanceof TrueOrFalseDoor) {
                trueOrFalse.add(door);
            }
        }

        List<AbstractDoor> expected = new ArrayList<>();

        final String query = "SELECT * FROM TorF";

        try (Connection conn = this.myDataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String question = rs.getString("questions");
                String answer = rs.getString("answerTorF");
                String not1 = rs.getString("notanswer");

                TrueOrFalseDoor temp = new TrueOrFalseDoor(answer, question, not1);

                expected.add(temp);
            }
        } catch (final SQLException e) {
            System.out.print("Unable to query: " + e.getMessage());
        }

        assertEquals(expected, trueOrFalse);
    }

    /**
     * allDoorsTest() method checks whether if all the door classes
     * are created and sent into the list data structure.
     */
    @Test
    void allDoorsTest() {

        List<AbstractDoor> expected = new ArrayList<>();

        final String query1 = "SELECT am.answer, am.not1, am.not2, am.not3, qm.prompt " +
                             "FROM answersMultiple AS am " +
                             "JOIN questionsMultiple AS qm ON am.answer = qm.answer;";

        try (Connection conn = myDataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query1);

            while (rs.next()) {
                List<String> notAnswer = new ArrayList<>();
                String prompt = rs.getString("prompt");
                String answer = rs.getString("answer");
                String not1 = rs.getString("not1");
                String not2 = rs.getString("not2");
                String not3 = rs.getString("not3");

                notAnswer.add(not1);
                notAnswer.add(not2);
                notAnswer.add(not3);

                MultipleQNADoor temp = new MultipleQNADoor(answer, prompt, notAnswer);
                expected.add(temp);
            }
        } catch (final SQLException e) {
            System.out.print("Unable to query: " + e.getMessage());
        }

        final String query2 = "SELECT * FROM TorF";

        try (Connection conn = this.myDataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query2);

            while (rs.next()) {
                String question = rs.getString("questions");
                String answer = rs.getString("answerTorF");
                String not1 = rs.getString("notanswer");

                TrueOrFalseDoor temp = new TrueOrFalseDoor(answer, question, not1);

                expected.add(temp);
            }
        } catch (final SQLException e) {
            System.out.print("Unable to query: " + e.getMessage());
        }

        final String query3 = "SELECT * FROM shortQuestions";

        try (Connection conn = this.myDataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query3);

            while (rs.next()) {
                String question = rs.getString("shortPrompt");
                String answer = rs.getString("shortAnswer");

                ShortQNADoor temp = new ShortQNADoor(answer, question);

                expected.add(temp);
            }
        } catch (final SQLException e) {
            System.out.print("Unable to query: " + e.getMessage());
        }

        assertEquals(expected, this.myDoorsList);
    }
}
