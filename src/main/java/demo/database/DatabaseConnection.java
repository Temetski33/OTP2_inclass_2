package demo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/fuel_calculator_localization";
    private static final String USER = "fueluser";
    // Read password from environment variable
    private static final String PASSWORD = System.getenv("FUELCALC_DB_PASSWORD");
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
