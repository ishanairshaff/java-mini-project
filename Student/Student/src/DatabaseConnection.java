import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/faculty_management";
    private static final String user = "root";
    private static final String password = "12345";
    private static Connection con;

    private static void driverConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Database Driver Loaded Successfully...");
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't find database driver: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (con == null) { // To avoid creating multiple connections
            driverConnection();
            try {
                con = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to the database.");
            } catch (SQLException e) {
                System.out.println("Couldn't connect to database: " + e.getMessage());
            }
        }
        return con;
    }
}
