package com.example.weatherapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button button_login;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
    private Button button_signup;
    @FXML
    private Button button_forgotpassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.logInUser(event, tf_username.getText(), tf_password.getText());
            }
        });

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "Signup.fxml", "Sign Up!", null, null, null, null, null, null, null, null, null, null);
            }
        });
        button_forgotpassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage stage = (Stage) button_forgotpassword.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Recoverypassword.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 733, 425); // Adjust the width and height as needed
                    stage.setTitle("Reset Your Account Password");
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
