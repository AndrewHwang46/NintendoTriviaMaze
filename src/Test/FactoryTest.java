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

public class FactoryTest {

    private Factory myFactory;

    private List<AbstractDoor> myDoorsList;

    private SQLiteDataSource myDataSource;

    private AnswersAndQuestionsDB myDatabase;

    @BeforeEach
    public void setUp() {
        this.myFactory = new Factory();
        this.myDataSource = new SQLiteDataSource();
        this.myDatabase = new AnswersAndQuestionsDB();
        this.myDoorsList = new ArrayList<>(this.myFactory.getListOfDoors());

        this.myDataSource = this.myDatabase.getDataSource();
        this.myDataSource = this.myDatabase.getDataSource();
        this.myDatabase.getOriginalTables(this.myDataSource);
        this.myDatabase.getOriginalValues(this.myDataSource);
    }

    @Test
    public void multipleQNATest() {
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
                List<String> notanswer = new ArrayList<>();
                String prompt = rs.getString("prompt");
                String answer = rs.getString("answer");
                String not1 = rs.getString("not1");
                String not2 = rs.getString("not2");
                String not3 = rs.getString("not3");

                notanswer.add(not1);
                notanswer.add(not2);
                notanswer.add(not3);

                MultipleQNADoor temp = new MultipleQNADoor(answer, prompt, notanswer);
                expected.add(temp);
            }
        } catch (final SQLException e) {
            System.out.print("Unable to query: " + e.getMessage());
        }
        assertEquals(expected, multipleQNA);
    }

    @Test
    public void shortQNATest() {
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

    @Test
    public void trueOrFalseTest() {
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

    @Test
    public void allDoorsTest() {
        List<AbstractDoor> expected = new ArrayList<>();

        final String query1 = "SELECT am.answer, am.not1, am.not2, am.not3, qm.prompt " +
                             "FROM answersMultiple AS am " +
                             "JOIN questionsMultiple AS qm ON am.answer = qm.answer;";

        try (Connection conn = myDataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query1);

            while (rs.next()) {
                List<String> notanswer = new ArrayList<>();
                String prompt = rs.getString("prompt");
                String answer = rs.getString("answer");
                String not1 = rs.getString("not1");
                String not2 = rs.getString("not2");
                String not3 = rs.getString("not3");

                notanswer.add(not1);
                notanswer.add(not2);
                notanswer.add(not3);

                MultipleQNADoor temp = new MultipleQNADoor(answer, prompt, notanswer);
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
