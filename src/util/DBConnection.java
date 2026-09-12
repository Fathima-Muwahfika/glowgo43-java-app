
package util;

import java.sql.Connection;
import java.sql.DriverManager;

// DBConnection handles database connectivity
// This class provides a single point to get database connection
public class DBConnection {

    // Database URL, username, and password
    private static final String URL =
        "jdbc:mysql://localhost:3306/glowgo43?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "";

    // Returns a Connection object to interact with MySQL database
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}


