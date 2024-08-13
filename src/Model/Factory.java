package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.sqlite.SQLiteDataSource;

/**
 *
 * @author Noah Ogilvie
 */
public final class Factory {

    private final SQLiteDataSource myDataSource;
    private final List<AbstractDoor> myListOfDoors;

    public Factory() {
        AnswersAndQuestionsDB myDatabase = new AnswersAndQuestionsDB();
        this.myDataSource = myDatabase.getDataSource();
        myDatabase.getOriginalTables(this.myDataSource);
        myDatabase.getOriginalValues(this.myDataSource);
        this.myListOfDoors = new ArrayList<>();
    }

    private final void createShortQNADoors() {
        final String query = "select *" +
                             "from shortQuestions;";

        try (Connection conn = this.myDataSource.getConnection();
             Statement stmt = conn.createStatement(); ) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String question = rs.getString("shortPrompt");
                String answer = rs.getString("shortAnswer");

                ShortQNADoor temp = new ShortQNADoor(answer, question);

                this.myListOfDoors.add(temp);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private final void createTrueOrFalseDoors() {

        final String query = "select *" +
                             "from TorF;";

        try (Connection conn = this.myDataSource.getConnection();
             Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                String questions = rs.getString("questions");
                String answer = rs.getString("answerTorF");
                String not1 = rs.getString("notanswer");

                TrueOrFalseDoor temp = new TrueOrFalseDoor(answer, questions, not1);

                this.myListOfDoors.add(temp);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private final void createMultipleQNADoors() {
        final String query = "select am.answer, am.not1, am.not2, am.not3, qm.prompt " +
                             "from answersMultiple as am " +
                             "join questionsMultiple as qm on am.answer = qm.answer;";

        try (Connection conn = this.myDataSource.getConnection();
             Statement stmt = conn.createStatement(); ) {

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
            e.printStackTrace();
        }
    }

    public final List<AbstractDoor> getListOfDoors() {
        createMultipleQNADoors();
        createTrueOrFalseDoors();
        createShortQNADoors();
//        int i = 1;
//        for (AbstractDoor door : this.myListOfDoors) {
//            System.out.println("Question: " + door.getQuestion() + " Answer: " + door.getAnswer() + " Number: " + i);
//            i++;
//        }
//        int j = 1;
//        for (AbstractDoor door : this.myListOfDoors) {
//            System.out.println("Answer: " + door.getAnswer() + j);
//            j++;
//        }
        return new ArrayList<>(this.myListOfDoors);
    }
}
