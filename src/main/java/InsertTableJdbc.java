import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTableJdbc {
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

            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            System.out.println("Creating table in selected database...");
            statement = connection.createStatement();

            String SQL = "INSERT INTO PROSELYTE_JDBC_DB.developers (name, specialty, salary) VALUES " +
                    "('Proselyte', 'Java', '2000'), " +
                    "('Peter','C++', '3000')," +
                    "('AsyaSmile', 'UI/UX', '2000')";


            statement.executeUpdate(SQL);
            System.out.println("Table successfully created...");
        }finally {
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        }
    }
}