import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SelectDBJdbc {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost/proselyte_jdbc_db";

    static final String USER = "postgres";
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