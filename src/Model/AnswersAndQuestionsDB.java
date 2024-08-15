/*
 * Nintendo Trivia Maze Game
 */
package Model;

import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * AnswersAndQuestionsDB class contains the connect and the database for the
 * trivia maze game application.
 *
 * @author Noah Ogilvie
 * @version 4.0
 */
public final class AnswersAndQuestionsDB {

    /**
     * myUrl is a private field holding the url for the database.
     */
    private final String myUrl;

    /**
     * AnswersAndQuestionsDB() constructor passes the database url value for the private field.
     */
    public AnswersAndQuestionsDB() {
        this.myUrl = "jdbc:sqlite:AnswersAndQuestions.db";
    }

    /**
     * initializeDatabse() method is used to initialize the AnswersAndQuestions database.
     *
     * @return the initialization of the AnswersAndQuestions database.
     */
    private SQLiteDataSource initializeDatabase() {
        SQLiteDataSource ds = null;
        try {
            ds = new SQLiteDataSource();
            ds.setUrl(myUrl);
        } catch (final Exception e) {
            System.out.println("Initialization of database failed: " +  e.getMessage());
            System.exit(0);
        }
        return ds;
    }

    /**
     * createOriginalTables() method is used to create the tables for the AnswersAndQuestions database.
     * @param theDS         the SQLite datasource
     */
    private void createOriginalTables(final SQLiteDataSource theDS) {
        final String query = "create table if not exists answersMultiple (" +
                             "answer text unique not null, " +
                             "not1 text unique not null," +
                             "not2 text unique not null," +
                             "not3 text unique not null," +
                             "primary key(answer));";

        final String query1 = "create table if not exists TorF (" +
                              "questions text unique not null, " +
                              "answerTorF text not null, " +
                              "notanswer text not null," +
                              "primary key(questions));";

        final String query3 = "create table if not exists questionsMultiple (" +
                              "answer text unique not null, " +
                              "prompt text unique not null, " +
                              "foreign key(answer) references answersMultiple(answer)" +
                              "on delete cascade " +
                              "on update cascade, " +
                              "primary key(prompt));";

        final String query4 = "create table if not exists shortQuestions (" +
                              "shortAnswer text unique not null, " +
                              "shortPrompt text unique not null, " +
                              "primary key(shortAnswer));";

        try (Connection conn = theDS.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            stmt.executeUpdate(query1);
            stmt.executeUpdate(query3);
            stmt.executeUpdate(query4);
        } catch (final SQLException e) {
            System.out.println("Unable to create tables: " +  e.getMessage());

            System.exit(0);
        }
    }

    /**
     * insertingSingleQuery() method allows the user to conduct a single query.
     * @param theDS         the SQLite datasource
     * @param theQuery      the String query
     */
    public void insertSingleQuery(final SQLiteDataSource theDS, final String theQuery) {
        try (Connection conn = theDS.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(theQuery);
        } catch (final SQLException e) {
            System.out.println("Unable to query: " +  e.getMessage());
            System.exit(0);
        }
    }

    /**
     * insertManyQueries() method allows the user to conduct multiple queries.
     * @param theDS         the SQLite datasource
     * @param theQueries    the List containing String queries
     */
    public void insertManyQueries(final SQLiteDataSource theDS, final List<String> theQueries) {
        try (Connection conn = theDS.getConnection();
             Statement stmt = conn.createStatement()) {
            for (final String query : theQueries) {
                stmt.executeUpdate(query);
            }
        } catch (final SQLException e) {
            System.out.println("Unable to query: " +  e.getMessage());
            System.exit(0);
        }
    }

    /**
     * originalValues() contains the original values for the tables in the database.
     *
     * @return an array list that contains each table values in each index.
     */
    private List<String> originalValues() {
        ArrayList<String> values = new ArrayList<>();

        values.add("insert or ignore into `answersMultiple` (`answer`, `not1`, `not2`, `not3`) values " +
                "('Nintendo Entertainment System', 'Nintendo Electronic System', 'Nintendo Electric Service', 'Nintendo Entertainment Service'), " + //1
                "('Color TV-Game', 'NES', 'GameCube', 'Nintendo 64'), " + //2
                "('Nintendo DS', 'Nintendo Switch', 'Wii', 'GameBoy'), " + //3
                "('Mario', 'Donkey Kong', 'Popeyes', 'Little Mac'), " + //4
                "('Nintendo once owned the Seattle Mariners', 'Super Mario was created in 1983', 'The Wii was released before the PlayStation 3', 'Nintendo of America is headquartered in San Francisco, California'), " + //5
                "('Mario Kart 8 Deluxe', 'Pokemon Sword and Shield', 'Minecraft', 'Super Smash Bros. Ultimate'), " + //6
                "('Jumpman', 'Plumber', 'Red Luigi', 'Red'), " + //7
                "('Mewtwo', 'Arceus', 'Dialga', 'Kyogre'), " + //8
                "('Zekrom', 'Palkia', 'Regigigas', 'Reshiram'), " + //9
                "('Meowth', 'Eskins', 'Conffin', 'Litten'), " + //10
                "('12', '26', '81', '45'), " + //11
                "('9', '12', '6', '15'), " + //12
                "('1889', '1975', '1948', '1917'), " + //13
                "('Direction pad', 'Joysticks', 'Trigger buttons', 'Handheld game consoles'), " + //14
                "('Wii Fit Trainer', 'Pokemon Trainer', 'Captain Falcon', 'Sheik');"); //15

        values.add("insert or ignore into `questionsMultiple` (`answer`, `prompt`) values " +
                "('Nintendo Entertainment System', 'What does the NES stand for?'), " + //1
                "('Color TV-Game', 'What is the first Nintendo console?'), " + //2
                "('Nintendo DS', 'What is Nintendo''s best-selling console?'), " + //3
                "('Mario', 'Who was the first mascot of Nintendo?'), " + //4
                "('Nintendo once owned the Seattle Mariners', 'What is true in the given options below?'), " + //5
                "('Mario Kart 8 Deluxe', 'What is the most sold Nintendo game?'), " + //6
                "('Jumpman', 'What was Mario''s original name in Donkey Kong?'), " + //7
                "('Mewtwo', 'Who is one of the first legendary Pokemon?'), " + //8
                "('Zekrom', 'Which Pokemon is used by the Hero of Ideals?'), " + //9
                "('Meowth', 'What Pokemon companion before Wobbuffet was part of the Team Rocket trio?'), " + //10
                "('12', 'How many characters were featured in the first Super Smash Bros video game'), " + //11
                "('9', 'How many mini-games are there in Wii Party?'), " + //12
                "('1889', 'When was the founding of Nintendo?'), " + //13
                "('Direction pad', 'Which invention was Nintendo the first to invent?'), " + //14
                "('Wii Fit Trainer', 'Who said the phrase, \"Let''s get fired up!,\" in Super Smash Bros. Ultimate?');"); //15

        values.add("insert or ignore into `TorF` (`questions`, `answerTorF`, `notanswer`) values " +
                "('Was Mario designed after a Washingtonian from Everett who was a Landlord', 'True', 'False'), " + //1
                "('In Super Smash Melee and in Super Smash Brawl, were you able to play Master Hand?', 'True', 'False'), " + //2
                "('Is Nintendo the oldest video game company?', 'True', 'False'), " + //3

                //new data added
                "('Is Pokemon Trainer in the roster of characters in Super Smash Bros. Ultimate?', 'True', 'False'), " + //4
                "('Is Charmander a water type Pokemon?', 'False', 'True'), " + //5
                "('Done Donkey Kong have a son?', 'True', 'False'), " + //6
                "('Does the blue shell in Mario Kart 8 Deluxe goes for the last place racer?', 'False', 'True'), " + //7
                "('Are there a total of three Splatoon games? (As of August 10th of 2024)', 'True', 'False'), " + //8
                "('The main character in the Pikmin Games is named Captain Olimar?', 'True', 'False'), " + //9
                "('Does the Mario franchise have a mobile game?', 'True', 'False'), " + //10
                "('Is Kirby red?', 'False', 'True'), " + //11
                "('Can you play Pokemon Legends: Arceus on the Nintendo 3DS?', 'False', 'True'), " + //12
                "('Was Popo the original name of Kirby?', 'False', 'True'), " + //13
                "('Is Kirby''s Dream Land the first Kirby game?', 'True', 'False'), " + //14
                "('Is Mario Kart: Super Circuit the first Mario Kart video game?', 'False', 'True');"); //15

        //new data added
        values.add("insert or ignore into `shortQuestions` (`shortAnswer`, `shortPrompt`) values " +
                "('Yoshi', 'What was the name of the lizard/dragon Mario rides?'), " + //1
                "('Barrel', 'What did Donkey Kong throw in the Mario vs. Donkey Kong game?'), " + //2
                "('Green', 'What color is Yoshi?'), " + //3
                "('Purple', 'What color is Waluigi'), " + //4
                "('Tom Nook', 'What is the name of the owner of Nook''s Homes in Animal Crossing New Leaf?'), " + //5
                "('Raccon', 'What animal is Tom Nook?'), " + //6
                "('Blue', 'What is the general color of zero-suit Samus?'), " + //7
                "('Peach', 'How is Mario trying to save in Super Mario Odyssey?'), " + //8
                "('Toad', 'What is the name of the red Toadstool?'), " + //9
                "('Toadette', 'What is the name of the pink Toadstool?'), " + //10
                "('Pikachu', 'What Pokemon was Ash''s first Pokemon?'), " + //11
                "('King Dedede', 'Who is the main antagonist in the Kirby games?'), " + //12
                "('Link', 'What is the name of the protagonist in the Zelda games?'), " + //13
                "('Torchic', 'What Pokemon was the fire starter in the 3rd generation of Pokemon?');"); //14

        return values;
    }

    /**
     * getDataSource() method is used to grab the AnswersAndQuestions database.
     *
     * @return the intialization of the SQLite database.
     */
    public SQLiteDataSource getDataSource() {
        return initializeDatabase();
    }

    /**
     * getOriginalTables() method is used to create the tables for the AnswersAndQuestions database.
     * @param theDS     the SQLite datasource
     */
    public void getOriginalTables(final SQLiteDataSource theDS) {
        createOriginalTables(theDS);
    }

    /**
     * getOriginalValues() method is used to insert the values for the AnswersAndQuestions database.
     * @param theDS     the SQLite datasource.
     */
    public void getOriginalValues(final SQLiteDataSource theDS) {
        insertManyQueries(theDS, new ArrayList<>(originalValues()));
    }

    /**
     * getUrl() method is used to get the url of the database.
     *
     * @return the string of the url.
     */
    public String getUrl() {
        return myUrl;
    }

    /**
     * getValues() method is used to get the arraylist of the original values.
     *
     * @return the list of the original values.
     */
    public List<String> getValues() {
        return new ArrayList<>(originalValues());
    }
}
