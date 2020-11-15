import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseSuiteTests {
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/";
    static final String USER = "root";
    static final String PASS = "5913tanya";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            statement = connection.createStatement();
            String sql = "CREATE DATABASE STUDENTS";
            statement.executeUpdate(sql);
            System.out.println("Database created successfully...");

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
