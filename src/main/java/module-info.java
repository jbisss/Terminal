module com.example.terminal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.controllers to javafx.fxml;
    exports com.example.controllers;
}