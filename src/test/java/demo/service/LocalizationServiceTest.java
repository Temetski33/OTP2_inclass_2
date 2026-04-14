package demo.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LocalizationServiceTest {
    @Test
    void testLoadStrings() {
        LocalizationService service = new LocalizationService();
        assertDoesNotThrow(() -> service.loadStrings("en_US"));
    }

    @Test
    void testGetString() {
        LocalizationService service = new LocalizationService();
        service.loadStrings("en_US");
        assertNotNull(service.getString("title"));
    }

}
