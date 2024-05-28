/*
 * The WeatherAppGUI class creates a graphical user interface (GUI) for a weather application.
 *  It allows users to enter a city name, fetch basic weather information, and view detailed weather data for selected options.
 *  The class integrates with a weather API to retrieve and display weather information, and it uses JSON parsing to handle the data.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.example.weather.WeatherAPI;
import com.google.gson.Gson;

// Main class for the Weather App GUI
public class WeatherAppGUI extends JFrame {
    private JTextArea weatherInfoTextArea; // Text area to display weather info
    private JTextField cityTextField; // Text field to input city name
    private JButton getWeatherButton; // Button to get weather data
    private JComboBox<String> optionComboBox; // Combo box for selecting detailed weather options

    private WeatherAPI weatherAPI; // API for fetching weather data
    private Gson gson; // JSON parser
    private WeatherData currentWeatherData; // Stores current weather data

    // Constructor to set up the GUI
    public WeatherAppGUI() {
        gson = new Gson(); // Initialize Gson for JSON parsing

        // Initialize WeatherAPI with API key
        String apiKey = "6387ff5ad2df4600bd584558240105";
        weatherAPI = new WeatherAPI(apiKey);

        // Set up the main frame properties
        setTitle("Balkonas Weather App");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Top panel for city input and button
        JPanel topPanel = new JPanel();
        JLabel cityLabel = new JLabel("Enter City:");
        cityTextField = new JTextField(20);
        getWeatherButton = new JButton("Get Weather");
        topPanel.add(cityLabel);
        topPanel.add(cityTextField);
        topPanel.add(getWeatherButton);

        // Text area to display weather information
        weatherInfoTextArea = new JTextArea();
        weatherInfoTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(weatherInfoTextArea);
        weatherInfoTextArea.setPreferredSize(new Dimension(500, 200));
        Font monospacedFont = new Font("Lucida Console", Font.PLAIN, 14);
        weatherInfoTextArea.setFont(monospacedFont);

        // Bottom panel for options
        JPanel bottomPanel = new JPanel(new FlowLayout());
        optionComboBox = new JComboBox<>();
        optionComboBox.addItem("Select Option"); // Default value
        bottomPanel.add(optionComboBox);

        // Center panel for text area and image
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(scrollPane, gbc);

        // Add image to center panel
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("balconyImg.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(imageLabel, gbc);

        // Add panels to the frame
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        // Add action listener to the "Get Weather" button
        getWeatherButton.addActionListener(e -> {
            String city = cityTextField.getText().trim();
            if (!city.isEmpty()) {
                displayBasicWeather(city); // Display basic weather information
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a city name.");
            }
        });

        // Add action listener to the option combo box
        optionComboBox.addActionListener(e -> {
            String selectedOption = (String) optionComboBox.getSelectedItem();
            if (selectedOption != null && !selectedOption.equals("Select Option")) {
                displayDetailedWeather(selectedOption); // Display detailed weather information
            }
        });
    }

    // Method to display basic weather information
    private void displayBasicWeather(String city) {
        try {
            String jsonData = weatherAPI.getWeatherData(city);
            currentWeatherData = gson.fromJson(jsonData, WeatherData.class);
            if (!(currentWeatherData.getLocation().getCountry().equals("Lithuania"))) {
                throw new CustomException("Invalid Country");
            }
            weatherInfoTextArea.setText(currentWeatherData.getBasicInfo());
            populateOptionComboBox(); // Populate options in combo box
        } catch (Exception e) {
            String exMessage = ExceptionHandler.handleException(e);
            JOptionPane.showMessageDialog(this, "Error fetching weather data: " + exMessage);
        }
    }

    // Method to populate combo box with detailed weather options
    private void populateOptionComboBox() {
        optionComboBox.removeAllItems();
        optionComboBox.addItem("Select Option");
        optionComboBox.addItem("Advanced information");
        optionComboBox.addItem("Wind speed");
        optionComboBox.addItem("Pressure");
        optionComboBox.addItem("Humidity");
        optionComboBox.addItem("Precipitation");
        optionComboBox.addItem("Cloud cover percentage");
        optionComboBox.addItem("\"Feels like\" temperature");
        optionComboBox.addItem("Visibility");
        optionComboBox.addItem("UV index");
        optionComboBox.addItem("Wind gust");
        optionComboBox.addItem("Wind direction");
    }

    // Method to display detailed weather information based on selected option
    private void displayDetailedWeather(String selectedOption) {
        String info = "";
        switch (selectedOption) {
            case "Advanced information":
                info = currentWeatherData.getAdvancedInfo();
                break;
            case "Wind speed":
                info = "Wind speed: " + currentWeatherData.getWindSpeed() + " kph";
                break;
            case "Pressure":
                info = "Pressure: " + currentWeatherData.getPressure() + " hPa";
                break;
            case "Humidity":
                info = "Humidity: " + currentWeatherData.getHumidity() + " %";
                break;
            case "Precipitation":
                info = "Precipitation: " + currentWeatherData.getPrecipitation() + " mm";
                break;
            case "Cloud cover percentage":
                info = "Cloud cover percentage: " + currentWeatherData.getCloudCover() + " %";
                break;
            case "\"Feels like\" temperature":
                info = "\"Feels like\" temperature: " + currentWeatherData.getFeelsLike() + " °C";
                break;
            case "Visibility":
                info = "Visibility: " + currentWeatherData.getVisibility() + " km";
                break;
            case "UV index":
                info = "UV index: " + currentWeatherData.getUVIndex();
                break;
            case "Wind gust":
                info = "Wind gust: " + currentWeatherData.getGust() + " kph";
                break;
            case "Wind direction":
                info = "Wind direction: " + currentWeatherData.getWindDirection();
                break;
        }
        weatherInfoTextArea.setText(info); // Display the selected information
    }

    // Main method to run the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WeatherAppGUI app = new WeatherAppGUI();
            app.setVisible(true);
        });
    }
}
