# Balkonas Weather App

Balkonas Weather App is a Java application that allows users to retrieve weather information for various cities. The application utilizes the WeatherAPI to fetch real-time weather data based on user input.

## Features

- Retrieve basic and advanced weather information for a specified city.
- View specific weather parameters such as wind speed, pressure, humidity, etc.

## Usage

1. Clone the repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Navigate to the `Main` class located in the `com.example.weather` package.
4. Run the `Main` class to start the application.
5. Follow the prompts to enter the city name and choose the desired weather information.

## Classes

### 1. Main
The main class of the application responsible for user interaction and handling weather data retrieval.

### 2. WeatherAPI
A class for interfacing with the WeatherAPI to fetch weather data using an API key.

### 3. CustomException
An exception class for handling custom exceptions in the application.

### 4. ExceptionHandler
A class for handling exceptions and providing appropriate error messages to the user.

### 5. InputHandler
A class for handling user input and displaying options for weather information retrieval.

### 6. Util
A utility class containing methods for checking if a string contains digits.

### 7. WeatherData
A class representing weather data retrieved from the API, including location and current weather conditions.

### 8. Location
A class representing geographical location information.

### 9. Current
A class representing the current weather conditions.

### 10. Condition
A class representing weather condition.

### 11. APIKey
A class representing an API key used for accessing weather data.

## Requirements

- Java Development Kit (JDK) 8 or higher
- Internet connection for fetching weather data from the WeatherAPI

## Dependencies

- Gson library for JSON parsing

## License

This project is licensed under the MIT License
