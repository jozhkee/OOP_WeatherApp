import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.example.weather.WeatherAPI;
import com.google.gson.Gson;

public class WeatherAppGUI extends JFrame {
    private JTextArea weatherInfoTextArea;
    private JTextField cityTextField;
    private JButton getWeatherButton;
    private JComboBox<String> optionComboBox;

    private WeatherAPI weatherAPI;
    private Gson gson;
    private WeatherData currentWeatherData;

    public WeatherAppGUI() {
        // Initialize Gson for JSON parsing
        gson = new Gson();

        // Initialize WeatherAPI
        String apiKey = "6387ff5ad2df4600bd584558240105";
        weatherAPI = new WeatherAPI(apiKey);

        // Set up the GUI components
        setTitle("Balkonas Weather App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel();
        JLabel cityLabel = new JLabel("Enter City:");
        cityTextField = new JTextField(20);
        getWeatherButton = new JButton("Get Weather");
        topPanel.add(cityLabel);
        topPanel.add(cityTextField);
        topPanel.add(getWeatherButton);

        weatherInfoTextArea = new JTextArea();
        weatherInfoTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(weatherInfoTextArea);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        optionComboBox = new JComboBox<>();
        optionComboBox.addItem("Select Option"); // Default value
        bottomPanel.add(optionComboBox);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        getWeatherButton.addActionListener(e -> {
            String city = cityTextField.getText().trim();
            if (!city.isEmpty()) {
                displayBasicWeather(city);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a city name.");
            }
        });

        optionComboBox.addActionListener(e -> {
            String selectedOption = (String) optionComboBox.getSelectedItem();
            if (selectedOption != null && !selectedOption.equals("Select Option")) {
                displayDetailedWeather(selectedOption);
            }
        });
    }

    private void displayBasicWeather(String city) {
        try {
            String jsonData = weatherAPI.getWeatherData(city);
            currentWeatherData = gson.fromJson(jsonData, WeatherData.class);
            if (!(currentWeatherData.getLocation().getCountry().equals("Lithuania"))) {
                throw new CustomException("Invalid Country");
            }
            weatherInfoTextArea.setText(currentWeatherData.getBasicInfo());
            populateOptionComboBox();
        } catch (Exception e) {
            String exMessage = ExceptionHandler.handleException(e);
            JOptionPane.showMessageDialog(this, "Error fetching weather data: " + exMessage);

        }
    }

    private void populateOptionComboBox() {
        optionComboBox.removeAllItems();
        optionComboBox.addItem("Select Option");
        optionComboBox.addItem("Show advanced information");
        optionComboBox.addItem("Show wind speed");
        optionComboBox.addItem("Show pressure");
        optionComboBox.addItem("Show Humidity");
        optionComboBox.addItem("Show precipitation");
        optionComboBox.addItem("Show Cloud cover percentage");
        optionComboBox.addItem("Show \"Feels like\" Temperature");
        optionComboBox.addItem("Show visibility");
        optionComboBox.addItem("Show UV index");
        optionComboBox.addItem("Show wind gust");
        optionComboBox.addItem("Show wind direction");
    }

    private void displayDetailedWeather(String selectedOption) {
        String info = "";
        switch (selectedOption) {
            case "Show advanced information":
                info = currentWeatherData.getAdvancedInfo();
                break;
            case "Show wind speed":
                info = "Wind speed: " + currentWeatherData.getWindSpeed() + " kph";
                break;
            case "Show pressure":
                info = "Pressure: " + currentWeatherData.getPressure() + " hPa";
                break;
            case "Show Humidity":
                info = "Humidity: " + currentWeatherData.getHumidity() + " %";
                break;
            case "Show precipitation":
                info = "Precipitation: " + currentWeatherData.getPrecipitation() + " mm";
                break;
            case "Show Cloud cover percentage":
                info = "Cloud cover percentage: " + currentWeatherData.getCloudCover() + " %";
                break;
            case "Show \"Feels like\" Temperature":
                info = "\"Feels like\" temperature: " + currentWeatherData.getFeelsLike() + " °C";
                break;
            case "Show visibility":
                info = "Visibility: " + currentWeatherData.getVisibility() + " km";
                break;
            case "Show UV index":
                info = "UV index: " + currentWeatherData.getUVIndex();
                break;
            case "Show wind gust":
                info = "Wind gust: " + currentWeatherData.getGust() + " kph";
                break;
            case "Show wind direction":
                info = "Wind direction: " + currentWeatherData.getWindDirection();
                break;
        }
        weatherInfoTextArea.setText(info);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WeatherAppGUI app = new WeatherAppGUI();
            app.setVisible(true);
        });
    }
}
