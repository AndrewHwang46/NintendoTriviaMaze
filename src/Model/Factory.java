/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */

package Model;

import org.sqlite.SQLiteDataSource;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Factory class is a class that acts like a factory for the creation of
 * different types of doors. All doors are held inside a list.
 *
 * @author Noah Ogilvie
 * @version 3.0
 */
public final class Factory implements Serializable {

    /**
     * serialVersionUID is the serializable constant for the implementation of Serializable.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * myDataSource is a SQLiteDataSource private field.
     */
    private final SQLiteDataSource myDataSource;

    /**
     * myListOfDoors is a list data structure private field that holds all types of doors.
     */
    private final List<AbstractDoor> myListOfDoors;

    /**
     * Factory() is a public constructor initializing the private fields.
     */
    public Factory() {
        AnswersAndQuestionsDB database = new AnswersAndQuestionsDB();
        this.myDataSource = database.getDataSource();
        database.getOriginalTables(this.myDataSource);
        database.getOriginalValues(this.myDataSource);
        this.myListOfDoors = new ArrayList<>();
    }

    /**
     * createShortQNADoors() method grabs the short question and answer from the
     * SQLite database into a door. Each construction of a door is appended into
     * the data structure private field.
     */
    private void createShortQNADoors() {
        final String query = "select *" +
                             "from shortQuestions;";

        try (Connection conn = this.myDataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String question = rs.getString("shortPrompt");
                String answer = rs.getString("shortAnswer");

                ShortQNADoor temp = new ShortQNADoor(answer, question);

                this.myListOfDoors.add(temp);
            }
        } catch (final SQLException e) {
            System.out.println("Unable to create short QNA doors: " + e.getMessage());
        }
    }

    /**
     * createTrueOrFalseDoors() method grabs the true or false questions from the
     * SQLite database into a door. Each construction of a door is appended into
     * the data structure private field.
     */
    private void createTrueOrFalseDoors() {

        final String query = "select *" +
                             "from TorF;";

        try (Connection conn = this.myDataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                String questions = rs.getString("questions");
                String answer = rs.getString("answerTorF");
                String not1 = rs.getString("notanswer");

                TrueOrFalseDoor temp = new TrueOrFalseDoor(answer, questions, not1);

                this.myListOfDoors.add(temp);
            }
        } catch (final SQLException e) {
            System.out.println("Unable to create true or false doors: " + e.getMessage());
        }
    }

    /**
     * createMultipleQNADoors() method grabs the multiple choice questions from the
     * SQLite database into a door. Each construction of a door is appended into
     * the data structure private field.
     */
    private void createMultipleQNADoors() {
        final String query = "select am.answer, am.not1, am.not2, am.not3, qm.prompt " +
                             "from answersMultiple as am " +
                             "join questionsMultiple as qm on am.answer = qm.answer;";

        try (Connection conn = this.myDataSource.getConnection();
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

                this.myListOfDoors.add(temp);
            }
        } catch (final SQLException e) {
            System.out.println("Unable to create multiple QNA doors: " + e.getMessage());
        }
    }

    /**
     * getListOfDoors() method is a getter method for the data structure private field.
     *
     * @return the total list of all doors.
     */
    public List<AbstractDoor> getListOfDoors() {
        createMultipleQNADoors();
        createTrueOrFalseDoors();
        createShortQNADoors();
        return new ArrayList<>(this.myListOfDoors);
    }
}
