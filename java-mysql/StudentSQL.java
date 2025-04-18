import java.sql.*;

public class StudentSQL {

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the initial connection to create the database
            con = DriverManager.getConnection("jdbc:mysql://mysql:3306?useSSL=false&allowPublicKeyRetrieval=true", "root", "change-me");

            stmt = con.createStatement();

            // Create the database if it does not exist
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS cdac";
            stmt.executeUpdate(createDatabaseSQL);

            // Close the initial connection
            stmt.close();
            con.close();

            // Establish a new connection to the created database
            con = DriverManager.getConnection("jdbc:mysql://mysql:3306/cdac?useSSL=false&allowPublicKeyRetrieval=true", "root", "change-me");

            // Create a table if it does not exist
            stmt = con.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(50), "
                    + "age INT)";
            stmt.executeUpdate(createTableSQL);

            // Insert data into the table
            pstmt = con.prepareStatement("INSERT INTO students (name, age) VALUES (?, ?)");
            pstmt.setString(1, "John Doe");
            pstmt.setInt(2, 20);
            pstmt.executeUpdate();

            // Query the table
            rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
