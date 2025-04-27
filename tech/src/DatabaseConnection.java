import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private String url = "jdbc:mysql://localhost:3306/faculty_management";
    private String user = "root";
    private String password = "1427";
    private Connection con;

    private void driverConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't find database driver: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        driverConnection();
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
        }
        return con;
    }
}
