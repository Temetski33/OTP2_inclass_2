Before running the application, create the database manually in MySQL or MariaDB:

CREATE DATABASE fuel_calculator_localization
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

And create user:

CREATE USER 'fueluser'@'localhost' IDENTIFIED BY 'mypassword';
GRANT ALL PRIVILEGES ON fuel_calculator_localization.* TO 'fueluser'@'localhost';
FLUSH PRIVILEGES;

