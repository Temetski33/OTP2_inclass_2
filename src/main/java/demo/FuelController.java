package demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class FuelController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label lblDistance;

    @FXML
    private Label lblConsumption;

    @FXML
    private Label lblPrice;

    @FXML
    private TextField tfDistance;

    @FXML
    private TextField tfConsumption;

    @FXML
    private TextField tfPrice;

    @FXML
    private Button btnCalculate;

    @FXML
    private Label lblResult;

    @FXML
    private Label lblLocalTime; // New label for showing local time

    private ResourceBundle rb;


    public void initialize() {
        setLanguage(new Locale("en", "US"));

    }

    private void setLanguage(Locale locale) {
        lblResult.setText("");
        try {

            rb = ResourceBundle.getBundle("messages", locale);
            lblDistance.setText(rb.getString("weight"));
            lblConsumption.setText(rb.getString("height"));
            btnCalculate.setText(rb.getString("calculate"));

            // show the time
            displayLocalTime(locale);

        }catch(MissingResourceException e) {
            e.printStackTrace();
            lblResult.setText("Error loading resources error");
        }
    }




    public void onCalculateClick(ActionEvent actionEvent) {
        if (tfDistance == null || tfConsumption == null) {
            System.err.println("TextFields are empty");
            return;
        }

        try {
            // Parse weight and height values
            double distance = Double.parseDouble(tfDistance.getText());
            double consumption = Double.parseDouble(tfConsumption.getText());
            double price = Double.parseDouble(tfPrice.getText());

            // Convert height from centimeters to meters
            consumption = consumption / 100.0;  // Convert to meters

            System.out.println("Weight: " + distance + " kg, Height: " + consumption + " m");

            // Calculate BMI using the formula: BMI = weight / (height * height)
            double bmi = distance / (consumption * consumption);
            System.out.println("Raw BMI: " + bmi);

            // Format the BMI value to 2 decimal places
            DecimalFormat df = new DecimalFormat("#0.00");
            String bmiString = df.format(bmi);
            System.out.println("Formatted BMI: " + bmiString);

            // Directly concatenate the BMI value to the result message
            lblResult.setText(rb.getString("result") + " " + bmiString);

        } catch (NumberFormatException e) {
            // Handle invalid input
            lblResult.setText(rb.getString("invalid"));
        }
    }


    public void onENClick(ActionEvent actionEvent) {
        setLanguage(new Locale("en", "US"));
    }

    public void onFRClick(ActionEvent actionEvent) {
        setLanguage(new Locale("fr", "FR"));

    }

    public void onJAClick(ActionEvent actionEvent) {
        setLanguage(new Locale("ja", "JP"));

    }

    public void onFAClick(ActionEvent actionEvent) {
        setLanguage(new Locale("fa", "IR"));

    }

    // Display the time

    private void displayLocalTime(Locale locale) {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss", locale);
        String formattedTime = currentTime.format(formatter);
        lblLocalTime.setText(rb.getString("localTime") + " " + formattedTime);
    }
}