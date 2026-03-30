package demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
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
    private Label lblWeight;

    @FXML
    private Label lblHeight;

    @FXML
    private TextField tfWeight;


    @FXML
    private TextField tfHeight;

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
            lblWeight.setText(rb.getString("weight"));
            lblHeight.setText(rb.getString("height"));
            btnCalculate.setText(rb.getString("calculate"));

            // show the time
            displayLocalTime(locale);

        }catch(MissingResourceException e) {
            e.printStackTrace();
            lblResult.setText("Error loading resources error");
        }
    }




    public void onCalculateClick(ActionEvent actionEvent) {
        if (tfWeight == null || tfHeight == null) {
            System.err.println("TextFields are empty");
            return;
        }

        try {
            // Parse weight and height values
            double weight = Double.parseDouble(tfWeight.getText());
            double height = Double.parseDouble(tfHeight.getText());

            // Convert height from centimeters to meters
            height = height / 100.0;  // Convert to meters

            System.out.println("Weight: " + weight + " kg, Height: " + height + " m");

            // Calculate BMI using the formula: BMI = weight / (height * height)
            double bmi = weight / (height * height);
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

    public void onURClick(ActionEvent actionEvent) {
        setLanguage(new Locale("ur", "PA"));

    }

    public void onVIClick(ActionEvent actionEvent) {
        setLanguage(new Locale("vi", "VI"));

    }

    // Display the time

    private void displayLocalTime(Locale locale) {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss", locale);
        String formattedTime = currentTime.format(formatter);
        lblLocalTime.setText(rb.getString("localTime") + " " + formattedTime);
    }
}