import java.sql.*;

public class BatchingProcessingDemo {
//------------For Mysql
//    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    static final String DATABASE_URL = "jdbc:mysql://localhost/my_db";
//
//    static final String USER = "root";
//    static final String PASSWORD = "admin";
//---------------------


    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/first_db";

    static final String USER = "postgres";
    static final String PASSWORD = "admin";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        connection.setAutoCommit(false);

        statement = connection.createStatement();

        String SQL = "SELECT * FROM developers";
        ResultSet resultSet = statement.executeQuery(SQL);


        System.out.println("Initial developer's table content:");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            if (name == null) {
                System.out.println("Table is empty.");
                break;
            }
            String specialty = resultSet.getString("specialty");
            int salary = resultSet.getInt("salary");

            System.out.println("\n====================");
            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary : $" + salary);
            System.out.println("====================\n");
        }

        SQL = "INSERT INTO developers VALUES (1, 'Proselyte', 'Java', 3000)";
        statement.addBatch(SQL);
        SQL = "INSERT INTO developers VALUES (2, 'AsyaSmile', 'UI/UX', 2500)";
        statement.addBatch(SQL);
        SQL = "INSERT INTO developers VALUES (3, 'Peter', 'C++', 3000)";
        statement.addBatch(SQL);

        try {


            statement.executeBatch();
            connection.commit();

            SQL = "SELECT * FROM developers";
            resultSet = statement.executeQuery(SQL);

            System.out.println("Final developer's table content:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialty = resultSet.getString("specialty");
                int salary = resultSet.getInt("salary");

                System.out.println("\n====================");
                System.out.println("id: " + id);
                System.out.println("Name: " + name);
                System.out.println("Specialty: " + specialty);
                System.out.println("Salary : $" + salary);
                System.out.println("====================\n");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } finally {
            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        System.out.println("Thank You.");
    }
}