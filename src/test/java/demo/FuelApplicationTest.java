package demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuelApplicationTest {

    @Test
    void testFXMLExists() {
        var url = FuelApplication.class.getResource("fuel-view.fxml");
        assertNotNull(url, "fuel-view.fxml should exist in resources");
    }
}
