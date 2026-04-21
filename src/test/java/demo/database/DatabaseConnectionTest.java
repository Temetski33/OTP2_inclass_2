package demo.database;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Disabled("Disabled in CI because no DB is available")
public class DatabaseConnectionTest {
    @Test
    void testGetConnectionDoesNotThrow() {
        assertDoesNotThrow(() -> DatabaseConnection.getConnection());
    }

}
