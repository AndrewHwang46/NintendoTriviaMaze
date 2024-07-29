package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteDataSource;

public final class AnswersAndQuestionsDB {

    private String myUrl;
    //was used to test methods
//    public static void main(String[] args) {
//        SQLiteDataSource ds = initializeDatabase();
//        createTables(ds);
//        insertManyValues(ds, originalValues());
//
//        final String query = "select distinct * from questionsMultiple";
//
//        try (Connection conn = ds.getConnection();
//             Statement stmt = conn.createStatement(); ) {
//
//            ResultSet rs = stmt.executeQuery(query);
//
//            int i = 1;
//            while (rs.next()) {
////                String question = rs.getString("questions");
////                String answer = rs.getString("answerTorF");
////                String notanswer = rs.getString("notanswer");
////                System.out.println(question + " " +  answer + " " + notanswer);
//                String question = rs.getString("prompt");
//                String answer = rs.getString("answer");
//                System.out.println("Question: " + question + ", Answer: " + answer + " Number: " + i);
//                i++;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    private AnswersAndQuestionsDB() {
        myUrl = "jdbc:sqlite:AnswersAndQuestions.db";
    }

    /**
     * initializeDatabse() method is used to initialize the AnswersAndQuestions database.
     * @return the initialization of the AnswersAndQuestions database.
     */
    private final SQLiteDataSource initializeDatabase() {
        SQLiteDataSource ds = null;
        try {
            ds = new SQLiteDataSource();
            ds.setUrl(myUrl);
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return ds;
    }

    /**
     * createOriginalTables() method is used to create the tables for the AnswersAndQuestions database.
     * @param theDS         the SQLite datasource
     */
     private final void createOriginalTables(final SQLiteDataSource theDS) {
        final String query = "create table if not exists answersMultiple (" +
                             "answer text unique not null, " +
                             "not1 text unique not null," +
                             "not2 text unique not null," +
                             "not3 text unique not null," +
                             "primary key(answer))";

        final String query1 = "create table if not exists TorF (" +
                              "questions text unique not null, " +
                              "answerTorF text not null, " +
                              "notanswer text not null," +
                              "primary key(answerTorF))";

//        String query2 = "create table if not exist imgAnswers (" +
//                        "answer (what datatype?) not null primary key)";

        final String query3 = "create table if not exists questionsMultiple (" +
                              "answer text unique not null, " +
                              "prompt text unique not null, " +
                              "foreign key(answer) references answersMultiple(answer)" +
                              "on delete cascade " +
                              "on update cascade, " +
                              "primary key(prompt))";

        try (Connection conn = theDS.getConnection();
             Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate(query);
            rv = stmt.executeUpdate(query1);
            rv = stmt.executeUpdate(query3);
        } catch (final SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * insertingSingleQuery() method allows the user to conduct a single query.
     * @param theDS         the SQLite datasource
     * @param theQuery      the String query
     */
    final void insertSingleQuery(final SQLiteDataSource theDS,
                                 final String theQuery) {
        try (Connection conn = theDS.getConnection();
             Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate(theQuery);
        } catch (final SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * insertManyQueries() method allows the user to conduct multiple queries.
     * @param theDS         the SQLite datasource
     * @param theQueries    the List containing String queries
     */
    final void insertManyQueries(final SQLiteDataSource theDS,
                                 final List<String> theQueries) {
        try (Connection conn = theDS.getConnection();
             Statement stmt = conn.createStatement(); ) {
            for (final String query : theQueries) {
                int rv = stmt.executeUpdate(query);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * originalValues() contains the original values for the tables in the database.
     * @return an array list that contains each table values in each index.
     */
    private final List<String> originalValues() {
        ArrayList<String> values = new ArrayList<String>();

        values.add("insert or ignore into `answersMultiple` (`answer`, `not1`, `not2`, `not3`) values" +
                   "('Nintendo Entertainment System', 'Nintendo Electronic System', 'Nintendo Electric Service', 'Nintendo Entertainment Service')," + //1
                   "('Color TV-Game', 'NES', 'GameCube', 'Nintendo 64')," + //2
                   "('Nintendo DS', 'Nintendo Switch', 'Wii', 'GameBoy')," + //3
                   "('Mario', 'Donkey Kong', 'Popeyes', 'Little Mac')," + //4
                   "('Nintendo once owned the Seattle Mariners', 'Super Mario was created in 1983', 'The Wii was released before the PlayStation 3', 'Nintendo of America is headquartered in San Francisco, California')," + //5
                   "('Mario Kart 8 Deluxe', 'Pokemon Sword and Shield', 'Minecraft', 'Super Smash Bros. Ultimate')," + //6
                   "('Jumpman', 'Plumber', 'Red Luigi', 'Red')," + //7
                   "('Mewtwo', 'Arceus', 'Dialga', 'Kyogre')," + //8
                   "('Zekrom', 'Palkia', 'Regigigas', 'Reshiram')," + //9
                   "('Meowth', 'Eskins', 'Conffin', 'Litten')," + //10
                   "('12', '26', '81', '45')," + //11
                   "('9', '12', '6', '15')," + //12
                   "('1889', '1975', '1948', '1917')," + //13
                   "('Direction pad', 'Joysticks', 'Trigger buttons', 'Handheld game consoles')," + //14
                   "('Wii Fit Trainer', 'Pokemon Trainer', 'Captain Falcon', 'Sheik');"); //15

        values.add("insert or ignore into `questionsMultiple` (`answer`, `prompt`) values" +
                   "('Nintendo Entertainment System', 'What does the NES stand for?')," + //1
                   "('Color TV-Game', 'What is the first Nintendo console?')," + //2
                   "('Nintendo DS', 'What is Nintendo''s best-selling console?')," + //3
                   "('Mario', 'Who was the first mascot of Nintendo?')," + //4
                   "('Nintendo once owned the Seattle Mariners', 'What is true in the given options below?')," + //5
                   "('Mario Kart 8 Deluxe', 'What is the most sold Nintendo game?')," + //6
                   "('Jumpman', 'What was Mario''s original name in Donkey Kong?')," + //7
                   "('Mewtwo', 'Who is one of the first legendary Pokemon?')," + //8
                   "('Zekrom', 'Which Pokemon is used by the Hero of Ideals?')," + //9
                   "('Meowth', 'What Pokemon companion before Wobbuffet was part of the Team Rocket trio?')," + //10
                   "('12', 'How many characters were featured in the first Super Smash Bros video game')," + //11
                   "('9', 'How many mini-games are there in Wii Party?')," + //12
                   "('1889', 'When was the founding of Nintendo?')," + //13
                   "('Direction pad', 'Which invention was Nintendo the first to invent?')," + //14
                   "('Wii Fit Trainer', 'Who said the phrase, \"Let''s get fired up!,\" in Super Smash Bros. Ultimate?');"); //15

        values.add("insert or ignore into `TorF` (`questions`, `answerTorF`, `notanswer`) values" +
                "('Was Mario designed after a Washingtonian from Everett who was a Landlord', 'True', 'False')," +
                "('In Super Smash Melee and in Super Smash Brawl, were you able to play Master Hand?', 'True', 'False')," +
                "('Is Nintendo the oldest video game company?', 'True', 'False');");

        return values;
    }

    /**
     * getDataSource() method is used to grab the AnswersAndQuestions database.
     * @return the intialization of the SQLite database.
     */
    public final SQLiteDataSource getDataSource() {
        return initializeDatabase();
    }

    /**
     * getOriginalTables() method is used to create the tables for the AnswersAndQuestions database.
     * @param theDS     the SQLite datasource
     */
    public final void getOriginalTables(final SQLiteDataSource theDS) {
        createOriginalTables(theDS);
    }

    /**
     * getOriginalValues() method is used to insert the values for the AnswersAndQuestions database.
     * @param theDS     the SQLite datasource.
     */
    public final void getOriginalValues(final SQLiteDataSource theDS) {
        insertManyQueries(theDS, originalValues());
    }

    /**
     * getUrl() method is used to get the url of the database.
     * @return the string of the url.
     */
    public final String getUrl() {
        return myUrl;
    }

    /**
     * getValues() method is used to get the arraylist of the original values.
     * @return the list of the original values.
     */
    public final List<String> getValues() {
        return originalValues();
    }
}
