package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static final String URL = "jdbc:h2:./study;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASS = "s3cr37";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}