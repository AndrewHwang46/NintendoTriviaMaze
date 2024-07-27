package Model;

//refactor class
public final class Questions {

//    private static SQLiteDataSource initialDatabase() {
//        SQLiteDataSource ds = null;
//        try {
//            ds = new SQLiteDataSource();
//            ds.setUrl("jdbc:sqlite:questions.db");
//        } catch (final Exception e) {
//            e.printStackTrace();
//        }
//        return ds;
//    }
//
//    private static void createQuestionTable(final SQLiteConnection theDS) {
//        String query = "create table if not exists questions ( " +
//                       "question text unique not null, " +
//                       "answer text unique not null, " +
//                       "primary key(question), " +
//                       "foreign key(answer))";
//        try (Connection conn = theDS.getConnection();
//             Statement stmt = conn.createStatement(); ) {
//            int rv = stmt.executeUpdate(query);
//        } catch (final SQLException e) {
//            e.printStackTrace();
//            System.exit(0);
//        }
//    }
//
//    static void insertValues(final SQLiteConnection theDS,
//                                      final String theQuery) {
//        try (Connection conn = theDS.getConnection();
//             Statement stmt = conn.createStatement(); ) {
//            int rv = stmt.executeUpdate(theQuery);
//        } catch (final SQLException e) {
//            e.printStackTrace();
//            System.exit(0);
//        }
//    }
//
//    public static void main(String[] args) {
//        SQLiteDataSource datasource = initialDatabase();
//        createQuestionTable(datasource);
//
//        SQLiteDataSource ds = null;
//
//        try {
//            ds = new SQLiteDataSource();
//            ds.setUrl("jdbc:sqlite:questions.db");
//            //check if successful
//            System.out.println("Successful");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(0);
//        }
//
//        String query = "create table if not exists questions ( " +
//                       "question text unique not null, " +
//                       "answer text unique not null )";
//
//        try (Connection conn = ds.getConnection();
//            Statement stmt = conn.createStatement(); ) {
//            int rv = stmt.executeUpdate(query);
//            //check if it was updated
//            System.out.println("executeUpdate returned " + rv);
//        } catch (final SQLException e) {
//            e.printStackTrace();
//            System.exit(0);
//        }
//
//        //already created
////        String query1 = "insert into questions (question, answer) values ('What does the NES stand for?', 'Nintendo Entertainment System')";
////        String query2 = "insert into questions (question, answer) values ('What is the first Nintendo console?', 'Color TV-Game')";
////
////        try (Connection conn = ds.getConnection();
////             Statement stmt = conn.createStatement(); ) {
////            int rv = stmt.executeUpdate(query1);
////            //check
////            System.out.println("executeUpdate returned " + rv);
////            rv = stmt.executeUpdate(query2);
////            //check
////            System.out.println("executeUpdate returned " + rv);
////        } catch (final SQLException e) {
////            e.printStackTrace();
////            System.exit(0);
////        }
//
//        query = "select * from questions";
//         try (Connection conn = ds.getConnection();
//              Statement stmt = conn.createStatement(); ) {
//
//             ResultSet rs = stmt.executeQuery(query);
//
//             while (rs.next()) {
//                 String question = rs.getString("question");
//                 String answer = rs.getString("answer");
//                 System.out.println("Result: Question = " + question +
//                                    ", Answer = " + answer);
//             }
//         } catch (final SQLException e) {
//             e.printStackTrace();
//             System.exit(0);
//         }
//    }
}
