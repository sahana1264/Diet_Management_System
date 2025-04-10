package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL ="jdbc:mysql://localhost:3306/diet_management_system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Sahana@2005";
    private static Connection conn = null;

    static {        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("jdbc driver connected");
        } catch (ClassNotFoundException | SQLException e) {
             System.out.println("jdbc driver not connected");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
