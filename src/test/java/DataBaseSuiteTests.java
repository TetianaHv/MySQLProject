import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseSuiteTests {
    static final String URL = "jdbc:mysql://127.0.0.1:3306/";
    static final String DBNAME = "DATABASE_TEST";
    static final String USER = "root";
    static final String PASS = "5913tanya";

    Connection connection = null;
    Statement statement = null;

    @Test
    public void createDBTest() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);

            statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE " + DBNAME);
            System.out.println("Database created successfully.");

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

    @Test(dependsOnMethods = {"createDBTest"})
    public void createDBTableTest() {
        try {
            connection = DriverManager.getConnection(URL + DBNAME, USER, PASS);

            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE CONTACTS " +
                    "(id INTEGER not NULL, " +
                    " firstName VARCHAR(255), " +
                    " lastName VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))");
            System.out.println("Table created successfully.");

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

    @Test(dependsOnMethods = {"createDBTableTest"})
    public void insertIntoDBTableTest() {
        try {
            connection = DriverManager.getConnection(URL + DBNAME, USER, PASS);

            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO CONTACTS " +
                    "VALUES (1, 'Anna', 'Schmidt', 18)");
            statement.executeUpdate("INSERT INTO CONTACTS " +
                    "VALUES (2, 'Maria', 'Schmidt', 22)");
            statement.executeUpdate("INSERT INTO CONTACTS " +
                    "VALUES (3, 'David', 'Schmidt', 33)");
            statement.executeUpdate("INSERT INTO CONTACTS " +
                    "VALUES (4, 'Oleg', 'Schmidt', 21)");
            statement.executeUpdate("INSERT INTO CONTACTS " +
                    "VALUES (5, 'Tanya', 'Schmidt', 9)");
            System.out.println("Records inserted into the table.");

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

    @Test(dependsOnMethods = {"insertIntoDBTableTest"})
    public void selectInsertedValuesTest() {
        try {
            connection = DriverManager.getConnection(URL + DBNAME, USER, PASS);

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CONTACTS");

            List allRows = new ArrayList();
            int numberColumns = 4;
            while (resultSet.next()) {
                String[] currentRow = new String[numberColumns];
                for (int i = 1; i <= numberColumns; i++) {
                    currentRow[i - 1] = resultSet.getString(i);
                    System.out.print(currentRow[i - 1] + " ");
                }
                System.out.println();
                allRows.add(currentRow);
            }
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

    @Test(dependsOnMethods = {"selectInsertedValuesTest"})
    public void updateDBTableTest() {
        try {
            connection = DriverManager.getConnection(URL + DBNAME, USER, PASS);

            statement = connection.createStatement();
            statement.executeUpdate("UPDATE CONTACTS " +
                    "SET lastName = 'Johnson' WHERE id=5");
            System.out.println("Records updated into the table.");

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

    @Test(dependsOnMethods = {"updateDBTableTest"})
    public void selectUpdatedValuesTest() {
        try {
            connection = DriverManager.getConnection(URL + DBNAME, USER, PASS);

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CONTACTS");

            List allRows = new ArrayList();
            int numberColumns = 4;
            while (resultSet.next()) {
                String[] currentRow = new String[numberColumns];
                for (int i = 1; i <= numberColumns; i++) {
                    currentRow[i - 1] = resultSet.getString(i);
                    System.out.print(currentRow[i - 1] + " ");
                }
                System.out.println();
                allRows.add(currentRow);
            }
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

    @Test(dependsOnMethods = {"selectUpdatedValuesTest"})
    public void deleteDBTableRowsTest() {
        try {
            connection = DriverManager.getConnection(URL + DBNAME, USER, PASS);

            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM CONTACTS " +
                    "WHERE id in(1,3,4)");
            System.out.println("Record deleted into the table.");

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

    @Test(dependsOnMethods = {"deleteDBTableRowsTest"})
    public void selectValuesAfterDeletionTest() {
        try {
            connection = DriverManager.getConnection(URL + DBNAME, USER, PASS);

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CONTACTS");

            List allRows = new ArrayList();
            int numberColumns = 4;
            while (resultSet.next()) {
                String[] currentRow = new String[numberColumns];
                for (int i = 1; i <= numberColumns; i++) {
                    currentRow[i - 1] = resultSet.getString(i);
                    System.out.print(currentRow[i - 1] + " ");
                }
                System.out.println();
                allRows.add(currentRow);
            }
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

    @Test(dependsOnMethods = {"selectValuesAfterDeletionTest"})
    public void verifyDBTableExistsTest() {
        try {
            connection = DriverManager.getConnection(URL + DBNAME, USER, PASS);
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, "CONTACTS", null);
            if (rs.next()) {
                System.out.println("Table CONTACTS exists");
            } else {
                System.out.println("Table CONTACTS does not exist");
            }
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

    @Test(dependsOnMethods = {"verifyDBTableExistsTest"})
    public void dropDBTableTest() {
        try {
            connection = DriverManager.getConnection(URL + DBNAME, USER, PASS);

            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE CONTACTS");
            System.out.println("Table deleted successfully.");

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

    @Test(dependsOnMethods = {"dropDBTableTest"})
    public void verifyDBTableWasDroppedTest() {
        try {
            connection = DriverManager.getConnection(URL + DBNAME, USER, PASS);
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, "CONTACTS", null);
            if (rs.next()) {
                System.out.println("Table CONTACTS exists");
            } else {
                System.out.println("Table CONTACTS does not exist");
            }

        } catch (
                SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test(dependsOnMethods = {"verifyDBTableWasDroppedTest"})
    public void verifyDBExistsTest() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            if (connection != null) {
                ResultSet rs = connection.getMetaData().getCatalogs();

                while (rs.next()) {
                    String catalogs = rs.getString(1);
                    if (DBNAME.equals(catalogs)) {
                        System.out.println("Database " + DBNAME + " exists");
                        break;
                    }
                }
            } else {
                System.out.println("Unable to create connection");
            }
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

    @Test(dependsOnMethods = {"verifyDBExistsTest"})
    public void dropDBTest() {
        try {
            connection = DriverManager.getConnection(URL + DBNAME, USER, PASS);

            statement = connection.createStatement();
            statement.executeUpdate("DROP DATABASE " + DBNAME);
            System.out.println("Database deleted successfully.");

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

    @Test(dependsOnMethods = {"dropDBTest"})
    public void verifyDBDroppedTest() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            if (connection != null) {
                ResultSet rs = connection.getMetaData().getCatalogs();

                while (rs.next()) {
                    String catalogs = rs.getString(1);
                    if (DBNAME.equals(catalogs)) {
                        System.out.println("Database " + DBNAME + " exists");
                        break;
                    }
                }
            } else {
                System.out.println("Unable to create connection");
            }
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
