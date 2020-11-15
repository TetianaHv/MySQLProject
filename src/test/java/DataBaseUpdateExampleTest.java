import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseUpdateExampleTest {
    public static void main(String[] args) {
        Connection conn1 = null;

        try {
            String url1 = "jdbc:mysql://127.0.0.1:3306/schema1";
            String user = "user";
            String password = "user";

            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test1");
            }

            Statement statement1 = conn1.createStatement();
            statement1.executeUpdate("UPDATE schema1.student SET age = 13 WHERE id = 1;");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            {
                try {
                    conn1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
