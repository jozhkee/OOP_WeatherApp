package com.example.weather;

public class Main {
    public static void main(String[] args) {

        String apiKey = "6387ff5ad2df4600bd584558240105";
        WeatherAPI weatherAPI = new WeatherAPI(apiKey);
        InputHandler inputHandler = new InputHandler();

        String city = inputHandler.getCity();

        String weatherData = weatherAPI.getWeatherData(city);

        System.out.println("Weather data for " + city + ":");
        System.out.println(weatherData);

        inputHandler.close();
    }
}
