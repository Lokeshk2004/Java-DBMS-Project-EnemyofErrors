package com.example.weatherapp;
import java.sql.*;

public class Jdbc {
    public static void main(String args[]) throws Exception{
        String url = "jdbc:mysql://localhost:3306/weatherinfo";
        String user = "root";
        String pwd = "lokesh@sql";
        Connection con = DriverManager.getConnection(url,user,pwd);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from weather ");
        while(rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getInt(3));
        }
    }
}