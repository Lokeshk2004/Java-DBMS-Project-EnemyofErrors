package com.example.weatherapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String city_name,String temperature,String humidity,String pressure,String visibility,String wind,String dewpoint,String uvindex,String moonphase,String airquality){
        Parent root=null;
        if(city_name!=null){
            try{
                FXMLLoader loader =new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                CitysController citysController = loader.getController();
                citysController.setUserInformation(city_name,temperature,humidity,pressure,visibility,wind,dewpoint,uvindex,moonphase,airquality);
            } catch (IOException e){
                e.printStackTrace();
            }
        }else{
            try{
                root=FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }
    public static void signUpUser(ActionEvent event,String username,String password,String city_name){
        Connection connection =null;
        PreparedStatement psInsert=null;
        PreparedStatement psCheckUserExists=null;
        ResultSet resultSet=null;
        try{
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherinfo","root","lokesh@sql");
            psCheckUserExists=connection.prepareStatement("SELECT * FROM user WHERE  username=?");
            psCheckUserExists.setString(1,username);
            resultSet=psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            }else{
                psInsert = connection.prepareStatement("INSERT INTO user (username,password,city_name)VALUES (?,?,?)");
                psInsert.setString(1,username);
                psInsert.setString(2,password);
                psInsert.setString(3,city_name);
                psInsert.executeUpdate();
                changeScene(event,"Citys.fxml","Welcome!",username,city_name,null,null,null,null,null,null,null,null);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(resultSet!=null){
                try{
                    resultSet.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(psCheckUserExists!=null){
                try{
                    psCheckUserExists.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(psInsert!=null){
                try{
                    psInsert.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try{
                    connection.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void logInUser(ActionEvent event,String username,String password){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherinfo","root","lokesh@sql");
            preparedStatement=connection.prepareStatement("SELECT password,city_name FROM user WHERE username=?");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database!");
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else{
                while(resultSet.next()){
                    String retrievedPassword=resultSet.getString("password");
                    String retrievedCity=resultSet.getString("city_name");
                    if(retrievedPassword.equals(password)){
                        changeScene(event,"Citys.fxml","Welcome!",retrievedCity,null,null,null,null,null,null,null,null,null);
                    }else{
                        System.out.println("Passwords did not match!");
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incrrect!");
                        alert.show();
                    }

                }

            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            if(resultSet!=null){
                try {
                    resultSet.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try{
                    preparedStatement.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try{
                    connection.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static WeatherInformation displayweatherinfo(String tf_city) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherinfo","root","lokesh@sql");
            preparedStatement=connection.prepareStatement("SELECT * FROM weather WHERE city_name=?");
            preparedStatement.setString(1,tf_city);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new WeatherInformation(
                        resultSet.getString("temperature"),
                        resultSet.getString("humidity"),
                        resultSet.getString("pressure"),
                        resultSet.getString("visibility"),
                        resultSet.getString("wind"),
                        resultSet.getString("dewPoint"),
                        resultSet.getString("UVIndex"),
                        resultSet.getString("moonPhase"),
                        resultSet.getString("airQuality")

                );
            }
            return null;
        }catch(SQLException e){
            return null;
        }finally{
            if (connection != null) {
                try {
                    resultSet.close();
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
