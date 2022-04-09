import java.io.*;
import java.sql.*;

public class StreamingDataDemo {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost/proselyte_jdbc_db";

    static final String USER = "postgres";
    static final String PASSWORD = "admin";


    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            stmt = conn.createStatement();
            createXMLTable(stmt);

            File f = new File("Proselyte_Developer.xml");
            long fileLength = f.length();
            FileInputStream fis = new FileInputStream(f);

            String SQL = "INSERT INTO xml_developer VALUES (?,?)";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, 1);
            pstmt.setAsciiStream(2, fis, (int) fileLength);
            pstmt.execute();

            fis.close();

            SQL = "SELECT Data FROM xml_developer WHERE id=1";
            rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                InputStream xmlInputStream = rs.getAsciiStream(1);
                int c;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((c = xmlInputStream.read()) != -1)
                    bos.write(c);
                System.out.println(bos.toString());
            }
            rs.close();
            stmt.close();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Thank You!");
    }

    public static void createXMLTable(Statement statement) throws SQLException {
        System.out.println("Creating xml_developer table...");
        String SQL = "CREATE TABLE xml_developer " +
                "(id INTEGER, Data LONG)";
        try {
            statement.executeUpdate("DROP TABLE xml_developer");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        statement.executeUpdate(SQL);
    }
}
