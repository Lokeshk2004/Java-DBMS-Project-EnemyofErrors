package com.example.weatherapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class RecoveryController implements Initializable {
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;
    @FXML
    private Button button_login;
    @FXML
    private Button button_reset;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = tf_username.getText();
                String newPassword = pf_password.getText();
                Connection connection = null;
                PreparedStatement psCheckUserExists = null;
                PreparedStatement psUpdate = null;
                ResultSet resultSet = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherinfo", "root", "lokesh@sql");
                    psCheckUserExists = connection.prepareStatement("SELECT * FROM user WHERE  username=?");
                    psCheckUserExists.setString(1, username);
                    resultSet = psCheckUserExists.executeQuery();
                    if (resultSet.isBeforeFirst()) {
                        psUpdate = connection.prepareStatement("UPDATE user SET password=? WHERE username=?");
                        psUpdate.setString(1, newPassword);
                        psUpdate.setString(2, username);
                        int updatedRows = psUpdate.executeUpdate();

                        if (updatedRows > 0) {
                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setContentText("Password updated successfully!");
                            successAlert.show();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Failed to update password!");
                            alert.show();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (psUpdate != null) {
                            psUpdate.close();
                        }
                        if (psCheckUserExists != null) {
                            psCheckUserExists.close();
                        }
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "Login.fxml", "Log in!", null, null, null, null, null, null, null, null, null, null);
            }
        });
    }
}
