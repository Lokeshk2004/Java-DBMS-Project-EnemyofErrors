package com.example.weatherapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    private Button button_signup;
    @FXML
    private Button button_login;
    @FXML
    private RadioButton rb_bradenburg;
    @FXML
    private RadioButton rb_ketchikan;
    @FXML
    private RadioButton rb_moscow;
    @FXML
    private RadioButton rb_chennai;
    @FXML
    private RadioButton rb_kozhikode;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
          ToggleGroup toggleGroup = new ToggleGroup();
          rb_bradenburg.setToggleGroup(toggleGroup);
          rb_ketchikan.setToggleGroup(toggleGroup);
          rb_moscow.setToggleGroup(toggleGroup);
          rb_chennai.setToggleGroup(toggleGroup);
          rb_kozhikode.setToggleGroup(toggleGroup);
          rb_bradenburg.setSelected(true);
          button_signup.setOnAction(new EventHandler<ActionEvent>() {

              @Override
            public void handle(ActionEvent event){
                String toggleName=((RadioButton) toggleGroup.getSelectedToggle()).getText();
                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()){

                    DBUtils.signUpUser(event,tf_username.getText(),tf_password.getText(),toggleName);
                }else{

                    System.out.println("Please fill in all information");
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up!");
                    alert.show();
                }

            }


        });
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                DBUtils.changeScene(event,"Login.fxml","Log in!",null,null,null,null,null,null,null,null,null,null);
            }
        });
    }
}
