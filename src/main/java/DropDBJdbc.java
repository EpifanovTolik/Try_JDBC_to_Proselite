import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropDBJdbc {
    static final String DATABASE_URL = "jdbc:mysql://localhost/PROSELYTE_JDBC_DB";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    static final String USER = "root";
    static final String PASSWORD = "admin";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            System.out.println("Connection successfully established.");

            System.out.println("Removing database...");
            statement = connection.createStatement();

            String SQL = "DROP DATABASE PROSELYTE_JDBC_DB";

            statement.executeUpdate(SQL);
            System.out.println("Database successfully removed.");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}