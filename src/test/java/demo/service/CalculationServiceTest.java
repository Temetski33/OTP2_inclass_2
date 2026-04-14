package demo.service;

import demo.model.CalculationRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculationServiceTest {
    @Test
    void testSaveCalculationDoesNotThrow() {
        CalculationService service = new CalculationService();
        CalculationRecord calcRecord = new CalculationRecord(1, 1, 1, 1, 1, "en_US");

        assertDoesNotThrow(() -> service.saveCalculation(calcRecord));
    }

}
