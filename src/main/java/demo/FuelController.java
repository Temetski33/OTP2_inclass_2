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
            lblDistance.setText(rb.getString("distance"));
            lblConsumption.setText(rb.getString("consumption"));
            btnCalculate.setText(rb.getString("calculate"));

            // show the time
            displayLocalTime(locale);

        }catch(MissingResourceException e) {
            e.printStackTrace();
            lblResult.setText("Error loading resources error");
        }
    }




    public void onCalculateClick(ActionEvent actionEvent) {
        if (tfDistance == null || tfConsumption == null || tfPrice == null) {
            System.err.println("TextFields are empty");
            return;
        }

        try {
            // Parse weight and height values
            double distance = Double.parseDouble(tfDistance.getText());
            double consumption = Double.parseDouble(tfConsumption.getText());
            double price = Double.parseDouble(tfPrice.getText());

            // Convert consumption from per 100km to per 1km
            consumption = consumption / 100.0;

            System.out.println("Distance: " + distance + " km, Consumption: " + consumption + " " + ", Price: " + price);

            // Calculate total fuel
            double totalFuel = distance * consumption;
            System.out.println("Total fuel raw: " + totalFuel);

            // Format the fuel value to 2 decimal places
            DecimalFormat df = new DecimalFormat("#0.00");
            String totalFuelString = df.format(totalFuel);
            System.out.println("Formatted fuel: " + totalFuelString);

            // Calculate total cost
            double totalCost = totalFuel * price;
            System.out.println("Total cost raw: " + totalCost);

            // Format the cost value to 2 decimal places
            String totalCostString = df.format(totalCost);
            System.out.println("Formatted cost: " + totalCostString);

            // Fuel value to the result message
            lblResult.setText(rb.getString("result") + " " + totalFuelString);

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