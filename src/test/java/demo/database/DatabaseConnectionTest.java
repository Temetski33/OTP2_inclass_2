package demo.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DatabaseConnectionTest {
    @Test
    void testGetConnectionDoesNotThrow() {
        assertDoesNotThrow(() -> DatabaseConnection.getConnection());
    }

}
