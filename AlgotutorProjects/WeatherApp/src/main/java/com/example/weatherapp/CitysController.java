package com.example.weatherapp;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CitysController implements Initializable {

    @FXML
    private Button button_logout;

    @FXML
    private Label label_temperature;
    @FXML
    private Label label_humidity;
    @FXML
    private Label label_pressure;
    @FXML
    private Label label_visibility;
    @FXML
    private Label label_wind;
    @FXML
    private Label label_dewPoint;
    @FXML
    private Label label_uvindex;
    @FXML
    private Label label_moonphase;
    @FXML
    private Label label_airquality;
    @FXML
    private Label label_city;
    @FXML
    private TextField tf_city;

    public CitysController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                DBUtils.changeScene(event,"Login.fxml","Log in!",null,null,null,null,null,null,null,null,null,null);
            }
        });
    }

    public void setUserInformation(String city_name,String temperature,String humidity,String pressure,String visibility,String wind,String dewpoint,String uvindex,String moonphase,String airquality){
        //label_city.setText(city_name);

        // label_temperature.setText(temperature);
        //label_humidity.setText(humidity);
        // label_pressure.setText(pressure);
        // label_visibility.setText(visibility);
        //label_wind.setText(wind);
        // label_dewpoint.setText(dewpoint);
        //label_uvindex.setText(uvindex);
        // label_moonphase.setText(moonphase);
        // label_airquality.setText(airquality);
    }

    @FXML
    void displayInfo(ActionEvent event) {
        System.out.println("displayInfo is executed");
        WeatherInformation info = DBUtils.displayweatherinfo(tf_city.getText());
        if (info == null) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Can't find information for the given city.");
            alert.show();
            return;
        }

        label_temperature.setText(info.temperature);
        label_humidity.setText(info.humidity);
        label_pressure.setText(info.pressure);
        label_visibility.setText(info.visibility);
        label_wind.setText(info.wind);
        label_dewPoint.setText(info.dewPoint);
        label_uvindex.setText(info.UVIndex);
        label_moonphase.setText(info.moonPhase);
        label_airquality.setText(info.airQuality);
//        String temperature= null;
//        try {
//            temperature = ;
//            String humidity=rs.getString("humidity");
//            String pressure=rs.getString("pressure");
//            String visibility=rs.getString("visibility");
//            String wind=rs.getString("wind");
//            String dewPoint=rs.getString("dewPoint");
//            String uvindex=rs.getString("uvindex");
//            String moonphase= rs.getString("moonphase");
//            String airQuality=rs.getString("airQuality");
//
//
//            label_temperature.setText(temperature);
//            label_humidity.setText(humidity);
//            label_pressure.setText(pressure);
//            label_visibility.setText(visibility);
//            label_wind.setText(wind);
//            label_dewpoint.setText(dewPoint);
//            label_uvindex.setText(uvindex);
//            label_moonphase.setText(moonphase);
//            label_airquality.setText(airQuality);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }


}


