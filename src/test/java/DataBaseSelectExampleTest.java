import java.sql.*;

public class DataBaseSelectExampleTest {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            String url = "jdbc:mysql://127.0.0.1:3306/DATABASE_TEST";
            String user = "root";
            String password = "5913tanya";

            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database test");
            }

            Statement statement = connection.createStatement();
            //select
            ResultSet resultSet = statement.executeQuery("SELECT * FROM schema1.student;");

            //only names
            String[] arr = null;
            while (resultSet.next()) {
                String em = resultSet.getString("name");
                arr = em.split("\n");
                for (int i = 0; i < arr.length; i++) {
                    System.out.println(arr[i]);
                }
            }

//            print all data from rows
//            List allRows = new ArrayList();
//            int numberColumns = 4;
//            while (resultSet.next()) {
//                String[] currentRow = new String[numberColumns];
//                for (int i = 1; i <= numberColumns; i++) {
//                    currentRow[i - 1] = resultSet.getString(i);
//                    System.out.println(currentRow[i - 1] + " ");
//                }
//                System.out.println();
//                allRows.add(currentRow);
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
