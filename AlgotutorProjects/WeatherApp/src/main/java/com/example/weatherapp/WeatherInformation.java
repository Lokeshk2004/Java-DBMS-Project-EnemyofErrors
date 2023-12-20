package com.example.weatherapp;

public class WeatherInformation {
    String temperature;
    String humidity;
    String pressure;
    String visibility;
    String wind;
    String dewPoint;
    String UVIndex;
    String moonPhase;
    String airQuality;

    WeatherInformation(
            String temperature,
            String humidity,
            String pressure,
            String visibility,
            String wind,
            String dewPoint,
            String UVIndex,
            String moonPhase,
            String airQuality
    ) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.visibility = visibility;
        this.wind= wind;
        this.dewPoint=dewPoint;
        this.UVIndex=UVIndex;
        this.moonPhase=moonPhase;
        this.airQuality=airQuality;
    }
}
