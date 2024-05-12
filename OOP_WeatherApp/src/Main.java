//TODO STAGE 3: GUI

import com.example.weather.WeatherAPI;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        String apiKey = "6387ff5ad2df4600bd584558240105";
        WeatherAPI weatherAPI = new WeatherAPI(apiKey);
        InputHandler inputHandler = new InputHandler();
        boolean isExit = false;
        System.out.println("Welcome to Balkonas!");
        System.out.println("To exit from the app at any time just enter exit!");
        while (!isExit) {
            try {
                String city = inputHandler.getCity();
                String jsonData = weatherAPI.getWeatherData(city);
                WeatherData weatherData = gson.fromJson(jsonData, WeatherData.class);
                if (!(weatherData.getLocation().getCountry().equals("Lithuania"))) {
                    throw new CustomException("Invalid Country");
                }
                System.out.println("Basic information:\n" + weatherData.getBasicInfo());
                int option = -1;
                System.out.println();
                System.out.println("Please enter a number corresponding to information you want to retrieve:");
                inputHandler.printOptionTable();
                while (option != 12) {
                    option = inputHandler.getOption();
                    switch (option) {
                        case 0:
                            throw new CustomException("EXIT");
                        case 1:
                            System.out.println("Advanced information:\n" + weatherData.getAdvancedInfo());
                            break;
                        case 2:
                            System.out.println("---------------------------------------------------------");
                            System.out.printf("| %-20s | %-30s |\n", "Wind speed: ",
                                    weatherData.getWindSpeed() + " kph");
                            System.out.println("---------------------------------------------------------");
                            break;
                        case 3:
                            System.out.println("---------------------------------------------------------");
                            System.out.printf("| %-20s | %-30s |\n", "Pressure: ", weatherData.getPressure() + " hPa");
                            System.out.println("---------------------------------------------------------");
                            break;
                        case 4:
                            System.out.println("---------------------------------------------------------");
                            System.out.printf("| %-20s | %-30s |\n", "Humidity: ", weatherData.getHumidity() + " %");
                            System.out.println("---------------------------------------------------------");
                            break;
                        case 5:
                            System.out.println("---------------------------------------------------------");
                            System.out.printf("| %-20s | %-30s |\n", "Precipitation: ",
                                    weatherData.getPrecipitation() + " mm");
                            System.out.println("---------------------------------------------------------");
                            break;
                        case 6:
                            System.out.println("---------------------------------------------------------");
                            System.out.printf("| %-20s | %-30s |\n", "Cloud cover percentage: ",
                                    weatherData.getCloudCover() + " %");
                            System.out.println("---------------------------------------------------------");
                            break;
                        case 7:
                            System.out.println("---------------------------------------------------------");
                            System.out.printf("| %-20s | %-30s |\n", "\"Feels like\" temperature: ",
                                    weatherData.getFeelsLike() + " °C");
                            System.out.println("---------------------------------------------------------");
                            break;
                        case 8:
                            System.out.println("---------------------------------------------------------");
                            System.out.printf("| %-20s | %-30s |\n", "Visibility: ",
                                    weatherData.getVisibility() + " km");
                            System.out.println("---------------------------------------------------------");
                            break;
                        case 9:
                            System.out.println("---------------------------------------------------------");
                            System.out.printf("| %-20s | %-30s |\n", "UV index: ", weatherData.getUVIndex());
                            System.out.println("---------------------------------------------------------");
                            break;
                        case 10:
                            System.out.println("---------------------------------------------------------");
                            System.out.printf("| %-20s | %-30s |\n", "Wind gust", weatherData.getGust() + " kph");
                            System.out.println("---------------------------------------------------------");
                            break;
                        case 11:
                            System.out.println("---------------------------------------------------------");
                            System.out.printf("| %-20s | %-30s |\n", "Wind direction: ",
                                    weatherData.getWindDirection());
                            System.out.println("---------------------------------------------------------");
                            break;
                        case 12:
                            if (System.getProperty("os.name").startsWith("Windows")) {
                                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            } else {
                                Runtime.getRuntime().exec("clear");
                            }
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
