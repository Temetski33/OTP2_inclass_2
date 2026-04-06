Database Setup (MySQL / MariaDB)

This application uses MySQL to store:

    Localization strings for four languages

    Calculation history records

Follow these steps before running the application.

1. Create the database
   sql

CREATE DATABASE fuel_calculator_localization
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

2. Create the application user
   sql

CREATE USER 'fueluser'@'localhost' IDENTIFIED BY 'mypassword';
GRANT ALL PRIVILEGES ON fuel_calculator_localization.* TO 'fueluser'@'localhost';
FLUSH PRIVILEGES;

3. Run the schema (adds tables)

mysql -u fueluser -p fuel_calculator_localization < schema.sql

4. Insert localization data

mysql -u fueluser -p fuel_calculator_localization < localization_data.sql

