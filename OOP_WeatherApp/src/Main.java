import com.example.weather.WeatherAPI;

public class Main {
    public static void main(String[] args) {

        String apiKey = "6387ff5ad2df4600bd584558240105";
        WeatherAPI weatherAPI = new WeatherAPI(apiKey);
        InputHandler inputHandler = new InputHandler();
        boolean isExit = false;
        System.out.println("Welcome to our Weather App!");
        System.out.println("To exit from the app at any time just enter exit!");
        while (!isExit) {
            try {
                String city = inputHandler.getCity();
                String weatherData = weatherAPI.getWeatherData(city);
                System.out.println("Full Weather data for " + city + ":");
                System.out.println(weatherData);
            } catch (Exception e) {
                ExceptionHandler.handleException(e);
                continue;
            }

        }
        inputHandler.close();
    }
}
