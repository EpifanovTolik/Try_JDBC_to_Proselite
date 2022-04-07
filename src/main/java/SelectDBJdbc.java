import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SelectDBJdbc {
    static final String DATABASE_URL = "jdbc:mysql://localhost/PROSELYTE_JDBC_DB";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    static final String USER = "root";
    static final String PASSWORD = "admin";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;

        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            System.out.println("Connection to " + DATABASE_URL + " successfully established.");
        }finally {
            if(connection!=null){
                connection.close();
            }
        }
    }
}