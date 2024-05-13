package com.example.weather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The WeatherAPI class provides functionality to fetch weather data for a
 * specific city using the WeatherAPI service.
 * It requires an API key for authentication.
 */

public class WeatherAPI {
    private String apiKey;
    private Gson gson;

    public WeatherAPI(String apiKey) {
        this.apiKey = apiKey;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Retrieves weather data for the specified city.
     *
     * @param city The name of the city for which weather data is requested.
     * @return A JSON string containing the weather data for the specified city.
     * @throws Exception If an error occurs during the API call or data retrieval.
     */

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
