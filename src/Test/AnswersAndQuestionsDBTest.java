/*
 * Nintendo Trivia Maze Game
 */
package Test;

import Model.AnswersAndQuestionsDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * AnswersAndQuestionsDBTest class tests for the SQLite connection, creation of tables,
 * inserted values, and being able to pull those values out through a proper connection.
 *
 * @author Noah Ogilvie
 * @version 5.0
 */
final class AnswerAndQuestionsDBTest {

    private SQLiteDataSource myDataSource;

    @BeforeEach
    void setup() {
        AnswersAndQuestionsDB database = new AnswersAndQuestionsDB();
        this.myDataSource = database.getDataSource();
        database.getOriginalTables(this.myDataSource);
        database.getOriginalValues(this.myDataSource);
    }

    /**
     * multipleQuestionTableTest() method checks if the questionsMultiple table exists
     * and the appropriate values are inserted correctly.
     */
    @Test
    void multipleQuestionTableTest() {

        final String expected = "What does the NES stand for? Nintendo Entertainment System\n" +
                "What is the first Nintendo console? Color TV-Game\n" +
                "What is Nintendo's best-selling console? Nintendo DS\n" +
                "Who was the first mascot of Nintendo? Mario\n" +
                "What is true in the given options below? Nintendo once owned the Seattle Mariners\n" +
                "What is the most sold Nintendo game? Mario Kart 8 Deluxe\n" +
                "What was Mario's original name in Donkey Kong? Jumpman\n" +
                "Who is one of the first legendary Pokemon? Mewtwo\n" +
                "Which Pokemon is used by the Hero of Ideals? Zekrom\n" +
                "What Pokemon companion before Wobbuffet was part of the Team Rocket trio? Meowth\n" +
                "How many characters were featured in the first Super Smash Bros video game 12\n" +
                "How many mini-games are there in Wii Party? 9\n" +
                "When was the founding of Nintendo? 1889\n" +
                "Which invention was Nintendo the first to invent? Direction pad\n" +
                "Who said the phrase, \"Let's get fired up!,\" in Super Smash Bros. Ultimate? Wii Fit Trainer\n";

        String query = "SELECT *" +
                       "FROM questionsMultiple;";

        StringBuilder sb = new StringBuilder();

        try (Connection conn = this.myDataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String prompt = rs.getString("prompt");
                String answer = rs.getString("answer");
                sb.append(prompt);
                sb.append(" ");
                sb.append(answer);
                sb.append("\n");
            }
        } catch (final SQLException e) {
            System.out.println("Unable to query" + e.getMessage() + e.getErrorCode());
        }
        assertEquals(sb.toString(), expected);
    }

    /**
     * multipleAnswerTableTest() method checks if the answersMultiple table exists
     * and the appropriate values are inserted correctly.
     */
    @Test
    void multipleAnswerTableTest() {

        final String expected = "Nintendo Entertainment System Nintendo Electronic " +
                "System Nintendo Electric Service Nintendo Entertainment Service\n" +
                "Color TV-Game NES GameCube Nintendo 64\n" +
                "Nintendo DS Nintendo Switch Wii GameBoy\n" +
                "Mario Donkey Kong Popeyes Little Mac\n" +
                "Nintendo once owned the Seattle Mariners Super Mario was created in 1983 The Wii " +
                "was released before the PlayStation 3 Nintendo of America is headquartered " +
                "in San Francisco, California\n" +
                "Mario Kart 8 Deluxe Pokemon Sword and Shield Minecraft Super Smash Bros. Ultimate\n" +
                "Jumpman Plumber Red Luigi Red\n" +
                "Mewtwo Arceus Dialga Kyogre\n" +
                "Zekrom Palkia Regigigas Reshiram\n" +
                "Meowth Eskins Conffin Litten\n" +
                "12 26 81 45\n" +
                "9 12 6 15\n" +
                "1889 1975 1948 1917\n" +
                "Direction pad Joysticks Trigger buttons Handheld game consoles\n" +
                "Wii Fit Trainer Pokemon Trainer Captain Falcon Sheik\n";

        String query = "SELECT *" +
                       "FROM answersMultiple;";

        StringBuilder sb = new StringBuilder();

        try (Connection conn = myDataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String answer = rs.getString("answer");
                String not1 = rs.getString("not1");
                String not2 = rs.getString("not2");
                String not3 = rs.getString("not3");

                sb.append(answer);
                sb.append(" ");
                sb.append(not1);
                sb.append(" ");
                sb.append(not2);
                sb.append(" ");
                sb.append(not3);
                sb.append("\n");
            }
        } catch (final SQLException e) {
            System.out.println("Unable to query" + e.getMessage() + e.getErrorCode());
        }

        assertEquals(expected, sb.toString());
    }

    /**
     * trueOrFalseTest() method checks if the TorF table exists
     * and the appropriate values are inserted correctly.
     */
    @Test
    void trueOrFalseTest() {
        final String expected = "Was Mario designed after a Washingtonian from Everett who was a " +
                "Landlord True False\n" +
                "In Super Smash Melee and in Super Smash Brawl, were you able to play Master Hand? True False\n" +
                "Is Nintendo the oldest video game company? True False\n" +
                "Is Pokemon Trainer in the roster of characters in Super Smash Bros. Ultimate? True False\n" +
                "Is Charmander a water type Pokemon? False True\n" +
                "Done Donkey Kong have a son? True False\n" +
                "Does the blue shell in Mario Kart 8 Deluxe goes for the last place racer? False True\n" +
                "Are there a total of three Splatoon games? (As of August 10th of 2024) True False\n" +
                "The main character in the Pikmin Games is named Captain Olimar? True False\n" +
                "Does the Mario franchise have a mobile game? True False\n" +
                "Is Kirby red? False True\n" +
                "Can you play Pokemon Legends: Arceus on the Nintendo 3DS? False True\n" +
                "Was Popo the original name of Kirby? False True\n" +
                "Is Kirby's Dream Land the first Kirby game? True False\n" +
                "Is Mario Kart: Super Circuit the first Mario Kart video game? False True\n";

        final String query = "SELECT *" +
                             "FROM TorF;";

        StringBuilder sb = new StringBuilder();

        try (Connection conn = myDataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String questions = rs.getString("questions");
                String answerTorF = rs.getString("answerTorF");
                String notAnswer = rs.getString("notanswer");
                sb.append(questions);
                sb.append(" ");
                sb.append(answerTorF);
                sb.append(" ");
                sb.append(notAnswer);
                sb.append("\n");
            }
        } catch (final SQLException e) {
            System.out.println("Unable to query" + e.getMessage() + e.getErrorCode());
        }
        assertEquals(expected, sb.toString());
    }

    @Test
    void shortQuestionsTest() {
        final String expected = "";


    }
}
