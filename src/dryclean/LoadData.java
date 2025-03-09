package dryclean;

import java.sql.*;

public class LoadData {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;

        try {
            // STEP1 -- Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            // STEP 2 -- Establish a connection
            System.out.println("Establishing a connection");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CyprusDryCleanDB", "drycleanAdmin", "1234");
            System.out.println("Database connected");

            // STEP 3 -- Create a statement
            statement = connection.createStatement();

            // STEP 4 -- Execute a statement
            ResultSet resultSet = statement.executeQuery("select * from Customer");

            // Iterate through the result and print the student names

//            while (resultSet.next())
//                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t"
//                        + resultSet.getString(4));
//
//            resultSet = statement.executeQuery("select * from Customer");
//            // Iterate through the result and print the student names
//            while (resultSet.next())
//                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t"
//                        + resultSet.getString(4));

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }
}
