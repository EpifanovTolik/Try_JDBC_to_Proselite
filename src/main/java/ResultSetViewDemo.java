import java.sql.*;

public class ResultSetViewDemo {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/my_db";

    static final String USER = "root";
    static final String PASSWORD = "admin";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        Class.forName(JDBC_DRIVER);

        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Creating statement...");

        try {
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_UPDATABLE
            );
            String SQL = "SELECT * FROM developers";
            ResultSet resultSet = statement.executeQuery(SQL);

            System.out.println("Moving cursor to the last position...");
            resultSet.last();

            System.out.println("Getting record (by name)...");
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String specialty = resultSet.getString("specialty");
            int salary = resultSet.getInt("salary");

            System.out.println("Last record in result set:");
            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);
            System.out.println("\n=========================\n");

            System.out.println("Moving cursor to previous row...");
            resultSet.previous();

            System.out.println("Getting record...");
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            specialty = resultSet.getString("specialty");
            salary = resultSet.getInt("salary");

            System.out.println("Previous record:");
            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);
            System.out.println("\n=========================\n");

            System.out.println("Moving cursor to the first row...");
            resultSet.first();

            System.out.println("Getting record...");
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            specialty = resultSet.getString("specialty");
            salary = resultSet.getInt("salary");

            System.out.println("First record:");
            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);
            System.out.println("\n=========================\n");

            System.out.println("Adding record...");
            SQL = "INSERT INTO developers VALUES (5, 'Mike', 'PHP', 1500)";
            statement.executeUpdate(SQL);

            SQL = "SELECT * FROM developers";
            resultSet = statement.executeQuery(SQL);
            resultSet.last();

            System.out.println("Getting record (by index)...");
            id = resultSet.getInt(1);
            name = resultSet.getString(2);
            specialty = resultSet.getString(3);
            salary = resultSet.getInt(4);

            System.out.println("Last record:");
            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Specialty: " + specialty);
            System.out.println("Salary: $" + salary);
            System.out.println("\n=========================\n");


            System.out.println("Full list of records (by name):");
            SQL = "SELECT * FROM developers";
            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                specialty = resultSet.getString("specialty");
                salary = resultSet.getInt("salary");

                System.out.println("id: " + id);
                System.out.println("Name: " + name);
                System.out.println("Specialty: " + specialty);
                System.out.println("Salary: $" + salary);
                System.out.println("\n=========================\n");

            }
        } catch (SQLException e) {
            e.printStackTrace();
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
