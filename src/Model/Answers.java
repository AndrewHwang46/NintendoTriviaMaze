import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteDataSource;

//how to do unit testing on this wtf
public final class Answers {

    //maybe dont use a main()? discuss with others
    public static void main(final String[] args) {
        SQLiteDataSource datasource = initializeDatabase();
        createTables(datasource);
        insertManyValues(datasource, originalValues());
    }

    private static SQLiteDataSource initializeDatabase() {
        SQLiteDataSource ds = null;
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:answers.db");
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return ds;
    }

    /**
     * Maybe have multiple tables for different types of questions
     * like multiple choice, true or false, and whatnot? - Tell team later
     */
    private static void createTables(final SQLiteDataSource theDS) {
        String query = "create table if not exist answers (" +
                       "answer text unique not null, " +
                       "not1 text not null," +
                       "not2 text not null," +
                       "not3 text not null," +
                       "primary key(answer))";
        String query1 = "create table if not exist TorF (" +
                        "answer text unique not null, " +
                        "notanswer text unique not null," +
                        "primary key(answer))";
        //discuss this during meeting
        String query2 = "create table if not exist imgAnswers (" +
                        "answer (what datatype?) not null primary key)";
        try (Connection conn = theDS.getConnection();
             Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate(query);
            //rv = stmt.executeUpdate(query1);
        } catch (final SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    static void insertSingleValues(final SQLiteDataSource theDS,
                                   final String theQuery) {
        try (Connection conn = theDS.getConnection();
             Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate(theQuery);
        } catch (final SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    static void insertManyValues(final SQLiteDataSource theDS,
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

    private static List<String> originalValues() {
        ArrayList<String> values = new ArrayList<String>();
        values.add("insert into `answers` ('answer', 'not1', 'not2', 'not3') values" +
                   "('Nintendo Entertainment System', 'Nintendo Electronic System', 'Nintendo Electric Service', 'Nintendo Entertainment Service')," +
                   "('Color TV-Game', 'NES', 'GameCube', 'Nintendo 64')," +
                   "('Nintendo DS', 'Nintendo Switch', 'Wii', 'GameBoy')," +
                   "('Mario', 'Pikachu', 'Princess Zelda', 'Sonic')," +
                   "('Mario', 'Donkey Kong', 'Popeyes', 'Little Mac')," +
                   "('Nintendo once owned the Seattle Mariners', 'Super Mario was created in 1983', 'The Wii was released before the PlayStation 3', 'Nintendo of America is headquartered in San Francisco, California')," +
                   "('Mario Kart 8 Deluxe', 'Pokemon Sword and Shield', 'Minecraft', 'Super Smash Bros. Ultimate')," +
                   "('Jumpman', 'Plumber', 'Red Luigi', 'Red')," +
                   "('Mewtwo', 'Arceus', 'Dialga', 'Kyogre')," +
                   "('Zekrom', 'Palkia', 'Regigigas', 'Reshiram')," +
                   "('Meowth', 'Eskins', 'Conffin', 'Litten')," +
                   "('12', '26', '81', '45')," +
                   "('9', '12', '6', '15')," +
                   "('1889', '1975', '1948', '1917')," +
                   "('Direction pad', 'Joysticks', 'Trigger buttons', 'Handheld game consoles')," +
                   "('Wii Fit Trainer', 'Pokemon Trainer', 'Captain Falcon', 'Sheik'),");
        //potential other tables needed to populate
        //values.add();
        return values;
    }
}
