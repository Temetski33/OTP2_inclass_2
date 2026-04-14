package demo;

import demo.model.CalculationRecord;
import demo.service.CalculationService;
import demo.service.LocalizationService;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FuelControllerTest {

    static {
        try {
            javafx.application.Platform.startup(() -> {});
        } catch (IllegalStateException ignored) {
            // Toolkit already initialized
        }
    }

    private FuelController controller;

    private CalculationService calculationService;
    private LocalizationService localizationService;

    private Label lblResult;
    private TextField tfDistance;
    private TextField tfConsumption;
    private TextField tfPrice;

    @BeforeEach
    void setUp() {
        controller = new FuelController();

        calculationService = mock(CalculationService.class);
        localizationService = mock(LocalizationService.class);

        TestUtils.setField(controller, "calculationService", calculationService);
        TestUtils.setField(controller, "localizationService", localizationService);

        // Required UI fields
        TestUtils.setField(controller, "lblTitle", new Label());
        TestUtils.setField(controller, "lblDistance", new Label());
        TestUtils.setField(controller, "lblConsumption", new Label());
        TestUtils.setField(controller, "lblPrice", new Label());
        TestUtils.setField(controller, "btnCalculate", new Button());
        TestUtils.setField(controller, "lblLocalTime", new Label());

        // Fields used in calculation
        lblResult = new Label();
        tfDistance = new TextField();
        tfConsumption = new TextField();
        tfPrice = new TextField();

        TestUtils.setField(controller, "lblResult", lblResult);
        TestUtils.setField(controller, "tfDistance", tfDistance);
        TestUtils.setField(controller, "tfConsumption", tfConsumption);
        TestUtils.setField(controller, "tfPrice", tfPrice);

        when(localizationService.getString("result")).thenReturn("Result:");
        when(localizationService.getString("invalid")).thenReturn("Invalid input");
        when(localizationService.getString("localTime")).thenReturn("Local time:");
        when(localizationService.getString("title")).thenReturn("Title");
        when(localizationService.getString("distance")).thenReturn("Distance");
        when(localizationService.getString("consumption")).thenReturn("Consumption");
        when(localizationService.getString("price")).thenReturn("Price");
        when(localizationService.getString("calculate")).thenReturn("Calculate");

        controller.initialize();
    }


    @Test
    void testOnCalculateClick_validInput() {
        controller.initialize();

        tfDistance.setText("100");
        tfConsumption.setText("5");
        tfPrice.setText("2");

        controller.onCalculateClick(null);

        assertEquals("Result: 5,00, 10,00", lblResult.getText());

        ArgumentCaptor<CalculationRecord> captor = ArgumentCaptor.forClass(CalculationRecord.class);
        verify(calculationService).saveCalculation(captor.capture());

        CalculationRecord record = captor.getValue();
        assertEquals(100, record.getDistance());
        assertEquals(0.05, record.getConsumption());
        assertEquals(2, record.getPrice());
        assertEquals(5.0, record.getTotalFuel());
        assertEquals(10.0, record.getTotalCost());
    }

    @Test
    void testOnCalculateClick_invalidInput() {
        tfDistance.setText("abc");

        controller.onCalculateClick(null);

        assertEquals("Invalid input", lblResult.getText());
        verify(calculationService, never()).saveCalculation(any());
    }
}
