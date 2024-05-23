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
        setSize(700, 500);
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

        weatherInfoTextArea.setPreferredSize(new Dimension(500, 200));

        Font monospacedFont = new Font("Lucida Console", Font.PLAIN, 14);
        weatherInfoTextArea.setFont(monospacedFont);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        optionComboBox = new JComboBox<>();
        optionComboBox.addItem("Select Option"); // Default value
        bottomPanel.add(optionComboBox);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        centerPanel.add(scrollPane, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("balconyImg.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        centerPanel.add(imageLabel, gbc);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

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
        weatherInfoTextArea.setText(info);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WeatherAppGUI app = new WeatherAppGUI();
            app.setVisible(true);
        });
    }
}
