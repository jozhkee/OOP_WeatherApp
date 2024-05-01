package com.example.weather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAPI {
    private String apiKey;
    private Gson gson;

    public WeatherAPI(String apiKey) {
        this.apiKey = apiKey;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public String getWeatherData(String city) throws Exception {
        String apiUrl = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city;

        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        Object jsonObject = gson.fromJson(response.toString(), Object.class);
        return gson.toJson(jsonObject);

    }
}
