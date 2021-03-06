import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnectionTest {
    public static void main(String[] args) {
        Connection conn1 = null;
        Connection conn2 = null;
        Connection conn3 = null;

        try {
            //connect way #1
            String url1 = "jdbc:mysql://127.0.0.1:3306/DATABASE_TEST";
            String user = "root";
            String password = "5913tanya";

            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test1");
            }

            //connect way #2
            String url2 = "jdbc:mysql://127.0.0.1:3306/DATABASE_TEST?user=root&password=5913tanya";
            conn2 = DriverManager.getConnection(url2);
            if (conn2 != null) {
                System.out.println("Connected to the database test2");
            }

            //connect way #3
            String url3 = "jdbc:mysql://127.0.0.1:3306/DATABASE_TEST";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "5913tanya");

            conn3 = DriverManager.getConnection(url3, info);
            if (conn3 != null) {
                System.out.println("Connected to the database test3");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        } finally {
            try {
                conn1.close();
                conn2.close();
                conn3.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
