package demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("Disabled in CI: requires database")
class FuelApplicationTest {

    @Test
    void testFXMLExists() {
        var url = FuelApplication.class.getResource("fuel-view.fxml");
        assertNotNull(url, "fuel-view.fxml should exist in resources");
    }

    @Test
    void testMainMethodRuns() {
        assertDoesNotThrow(() -> FuelApplication.main(new String[]{}));
    }

}
