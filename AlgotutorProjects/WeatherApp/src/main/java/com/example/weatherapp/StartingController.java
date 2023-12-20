package com.example.weatherapp;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartingController {
    @FXML
    private Button getstarted;
    @FXML
    void tologin(ActionEvent event) throws IOException {
        Stage stage = (Stage) getstarted.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Login!!");
        stage.setScene(scene);

    }
}
