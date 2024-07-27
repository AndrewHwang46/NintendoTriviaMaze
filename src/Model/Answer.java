package Model;

import java.sql.*;

public class Answer {


    //Basic infrastructure for database getting
    public static void main(final String[] args) {
        //Establist a connection
        final String url = null;
        final String user = null;
        final String password = null;

        // Initializing database stuff
        Connection conct = null;
        Statement statemt = null;
        ResultSet retset = null;

        try {
            conct = DriverManager.getConnection(url, user, password);
            statemt = conct.createStatement();
            //execute SELECT * FROM ANSWER
            retset = statemt.executeQuery("");

            if (retset.next()) {
                System.out.println(retset.getString(1));
            }
        } catch (final SQLException e) {
            System.out.println("Something went wrong");
        }
    }

//    public String getAnswer() {
//
//    }
}
