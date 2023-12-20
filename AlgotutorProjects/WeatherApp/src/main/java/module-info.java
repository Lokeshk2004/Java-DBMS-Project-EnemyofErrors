module com.example.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.weatherapp to javafx.fxml;
    exports com.example.weatherapp;
}