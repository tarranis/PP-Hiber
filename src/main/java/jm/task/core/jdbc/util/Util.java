package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONNECTIONURL = "jdbc:mysql://localhost:3306/testdb";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(CONNECTIONURL, USERNAME, PASSWORD);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
