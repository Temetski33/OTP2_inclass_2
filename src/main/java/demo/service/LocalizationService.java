package demo.service;

import demo.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LocalizationService {

    private Map<String, String> cache = new HashMap<>();
    private String currentLanguage;

    private static final String QUERY =
            "SELECT `key`, value FROM localization_strings WHERE language = ?";

    public void loadStrings(String language) {
        cache.clear();               // Clear old values
        currentLanguage = language;  // Store selected language

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY)) {

            stmt.setString(1, language);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String key = rs.getString("key");
                    String value = rs.getString("value");
                    cache.put(key, value);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error loading localization strings: " + e.getMessage());
        }
    }


    public String getString(String key) {
        if (cache.isEmpty()) {
            System.err.println("Warning: Localization strings not loaded. Call loadStrings(language) first.");
        }
        return cache.getOrDefault(key, "??" + key + "??");
    }


    public Set<String> getAllKeys() {
        return cache.keySet();
    }
}
