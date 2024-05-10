import com.example.weather.WeatherAPI;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        String apiKey = "6387ff5ad2df4600bd584558240105";
        WeatherAPI weatherAPI = new WeatherAPI(apiKey);
        InputHandler inputHandler = new InputHandler();
        boolean isExit = false;
        System.out.println("Welcome to our Weather App!");
        System.out.println("To exit from the app at any time just enter exit!");
        while (!isExit) {
            try {
                String city = inputHandler.getCity();
                String jsonData = weatherAPI.getWeatherData(city);
                WeatherData weatherData = gson.fromJson(jsonData, WeatherData.class);

                System.out.println("Basic information : " + weatherData.getBasicInfo());
                int option = -1;
                while (option != 12) {
                    option = inputHandler.getOption();
                    switch (option) {
                        case 0:
                            throw new CustomException("EXIT");
                        case 1:
                            System.out.println("Advanced information: " + weatherData.getAdvancedInfo());
                            break;
                        case 2:
                            System.out.println("Wind speed: " + weatherData.getWindSpeed());
                            break;
                        case 3:
                            System.out.println("Pressure: " + weatherData.getPressure());
                            break;
                        case 4:
                            System.out.println("Humidity: " + weatherData.getHumidity());
                            break;
                        case 5:
                            System.out.println("Precipitation: " + weatherData.getPrecipitation());
                            break;
                        case 6:
                            System.out.println("Cloud cover percentage: " + weatherData.getCloudCover());
                            break;
                        case 7:
                            System.out.println("\"Feels like\" temperature: " + weatherData.getFeelsLike());
                            break;
                        case 8:
                            System.out.println("Visibility: " + weatherData.getVisibility());
                            break;
                        case 9:
                            System.out.println("UV index: " + weatherData.getUVIndex());
                            break;
                        case 10:
                            System.out.println("Cloud gust: " + weatherData.getGust());
                            break;
                        case 11:
                            System.out.println("Wind direction: " + weatherData.getWindDirection());
                            break;
                        default:
                            break;
                    }
                }

            } catch (Exception e) {
                ExceptionHandler.handleException(e);
                continue;
            }

        }
        inputHandler.close();
    }
}
