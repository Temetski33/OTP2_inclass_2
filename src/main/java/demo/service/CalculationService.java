package demo.service;

import demo.database.DatabaseConnection;
import demo.model.CalculationRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CalculationService {

    private static final String INSERT_SQL =
            "INSERT INTO calculation_records (distance, consumption, price, total_fuel, total_cost, language) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    public void saveCalculation(CalculationRecord record) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {

            stmt.setDouble(1, record.getDistance());
            stmt.setDouble(2, record.getConsumption());
            stmt.setDouble(3, record.getPrice());
            stmt.setDouble(4, record.getTotalFuel());
            stmt.setDouble(5, record.getTotalCost());
            stmt.setString(6, record.getLanguage());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error saving calculation: " + e.getMessage());
        }
    }
}
