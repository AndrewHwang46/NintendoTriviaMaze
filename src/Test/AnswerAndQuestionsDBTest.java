package Test;

import Model.AnswersAndQuestionsDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Noah Ogilvie
 */
public final class AnswerAndQuestionsDBTest {

    private SQLiteDataSource myDataSource;

    @BeforeEach
    void setup() {
        AnswersAndQuestionsDB database = new AnswersAndQuestionsDB();
        this.myDataSource = new SQLiteDataSource();
        database.getOriginalTables(this.myDataSource);
        database.getOriginalValues(this.myDataSource);
    }

    @Test
    public void databaseValueTestMultipleQNADoors() {

        final String expected = "";

//        final String query = "select am.answer, am.not1, am.not2, am.not3, qm.prompt " +
//                             "from answersMultiple as am " +
//                             "join questionsMultiple as qm on am.answer = qm.answer;";

        final String query = "select am.answer, am.not1, am.not2, am.not3, qm.prompt " +
                             "from answersMultiple as am " +
                             "join questionsMultiple as qm on am.answer = qm.answer;";
        StringBuilder sb = new StringBuilder();

        try (Connection conn = this.myDataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String prompt = rs.getString("prompt");
                String answer = rs.getString("answer");
                String not1 = rs.getString("not1");
                String not2 = rs.getString("not1");
                String not3 = rs.getString("not3");
                sb.append(prompt);
                sb.append(" ");
                sb.append(answer);
                sb.append(" ");
                sb.append(not1);
                sb.append(" ");
                sb.append(not2);
                sb.append(" ");
                sb.append(not3);
            }
        } catch (final SQLException e) {
            //System.out.println("Unable fetch multiple QNA: " + e.getMessage() + e.getErrorCode());
            e.printStackTrace();
        }
        System.out.println(sb);
        System.out.println(expected);
    }

    @Test
    public void databaseValuesTest2() {

    }
}
