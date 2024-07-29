package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.sqlite.SQLiteDataSource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class AnswerAndQuestionsDBTest {

    private List<String> myValues;
    private SQLiteDataSource myDataSource;
    private AnswersAndQuestionsDB myDatabase;

    @BeforeEach
    void setup() {
        myDatabase = new AnswersAndQuestionsDB();
        myDataSource = myDatabase.getDataSource();

        List<String> myValues = new ArrayList<>(myDatabase.getValues());
    }

    //Need to delete? ask team members
    @Test
    public void initializeDatabaseTest() throws SQLException{
        SQLiteDataSource db = myDatabase.getDataSource();
        assertEquals(db.getConnection(), myDataSource.getConnection());
    }

    @Test
    public void originalValuesTest() {
        myDatabase.getOriginalTables(myDataSource);
        myDatabase.getOriginalValues(myDataSource);

        String value = "";

        final String query = "select * from TorF";
        try (Connection conn = myDataSource.getConnection();
             Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String question = rs.getString("questions");
                String answer = rs.getString("answerTorF");
                String notanswer = rs.getString("notanswer");
                StringBuilder sb = new StringBuilder();
                sb.append("Question: ");
                sb.append(question);
                sb.append(", Answer: ");
                sb.append(answer);
                sb.append(", NotAnswer: ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(value, sb.toString());
    }

    @Test
    public void testOriginalValuesFirst() {
        assertEquals(myValues.size(), myDatabase.getValues().size());
    }

    @Test
    public void testOriginalValuesSecond() {
        boolean value = true;
        for (int i = 0; i < myValues.size(); i++) {
            if (!myValues.get(i).equals(myDatabase.getValues())) {
                value = false;
            }
        }
        assertTrue(value);
    }
}
