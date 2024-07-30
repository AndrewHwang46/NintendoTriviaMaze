package model;

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

    private SQLiteDataSource myDataSource;
    private AnswersAndQuestionsDB myDatabase;
    private List<MultipleQNADoors> myMultipleQNADoors;
    private List<TrueOrFalseDoors> myTrueOrFalseDoors;

    public Factory() {
        myDatabase = new AnswersAndQuestionsDB();
        myDataSource = myDatabase.getDataSource();
        myDatabase.getOriginalTables(myDataSource);
        myDatabase.getOriginalValues(myDataSource);
        myMultipleQNADoors = new ArrayList<MultipleQNADoors>();
        myTrueOrFalseDoors = new ArrayList<TrueOrFalseDoors>();
    }

    private final void createMultipleQNADoors() {
        final String query = "select am.answer, am.not1, am.not2, am.not3, qm.prompt" +
                             "from answersMultiple as am" +
                             "join questionsMultiple as qm on am.answer = qm.answer;";

        try (Connection conn = myDataSource.getConnection();
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

                MultipleQNADoors temp = new MultipleQNADoors(answer, prompt, notanswer);

                myMultipleQNADoors.add(temp);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    private final void createTrueOrFalseDoors() {
        final String query = "select *" +
                             "from TorF;";

        try (Connection conn = myDataSource.getConnection();
             Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                String questions = rs.getString("questions");
                String answer = rs.getString("answerTorF");
                String not1 = rs.getString("notanswer");

                TrueOrFalseDoors temp = new TrueOrFalseDoors(answer, questions, not1);

                myTrueOrFalseDoors.add(temp);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    public final List<MultipleQNADoors> getMultipleQNADoors() {
        createMultipleQNADoors();
        return new ArrayList<MultipleQNADoors>(myMultipleQNADoors);
    }

    public final List<TrueOrFalseDoors> getTrueOrFalseDoors() {
        createTrueOrFalseDoors();
        return new ArrayList<TrueOrFalseDoors>(myTrueOrFalseDoors);
    }
}
