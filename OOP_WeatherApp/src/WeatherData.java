
public class WeatherData {
    private Location location;
    private Current current;

    public String getBasicInfo() {
        StringBuilder table = new StringBuilder();

        String[] labels = { "Name", "Region", "Country", "Latitude", "Longitude", "Timezone", "Local Time",
                "Temperature",
                "Condition" };
        String[] values = { location.getName(), location.getRegion(), location.getCountry(),
                String.valueOf(location.getLat()), String.valueOf(location.getLon()), location.getTz_id(),
                location.getLocaltime(), current.getTemp_c() + "°C", current.getCondition().getText() };

        int labelColumnWidth = 20;
        int valueColumnWidth = 30;

        table.append("-".repeat(labelColumnWidth + 3) + "-" + "-".repeat(valueColumnWidth + 1)).append("\n");

        for (int i = 0; i < labels.length; i++) {
            String formattedLabel = String.format("%-" + labelColumnWidth + "s", labels[i]);
            String formattedValue = String.format("%-" + valueColumnWidth + "s", values[i]);
            table.append("| ").append(formattedLabel).append("| ").append(formattedValue).append("|\n");
        }

        table.append("-".repeat(labelColumnWidth + 3) + "-" + "-".repeat(valueColumnWidth + 1));

        return table.toString();
    }

    public String getAdvancedInfo() {

        StringBuilder table = new StringBuilder();

        String[] labels = { "Wind speed", "Pressure", "Humidity", "Precipitation", "Cloud cover", "Feels like",
                "Visibility", "UV", "Wind gust", "Wind direction" };
        String[] values = { current.getWind_kph() + " kph", current.getPressure_mb() + " hPa",
                current.getHumidity() + " %", current.getPrecip_mm() + " mm",
                current.getCloud() + " %", current.getFeelslike_c() + "°C",
                current.getVis_km() + " km", String.valueOf(current.getUv()), current.getGust_kph() + " kph",
                current.getWind_dir() };

        int labelColumnWidth = 20;
        int valueColumnWidth = 30;

        table.append("-".repeat(labelColumnWidth + 3) + "-" + "-".repeat(valueColumnWidth + 1)).append("\n");

        for (int i = 0; i < labels.length; i++) {
            String formattedLabel = String.format("%-" + labelColumnWidth + "s", labels[i]);
            String formattedValue = String.format("%-" + valueColumnWidth + "s", values[i]);
            table.append("| ").append(formattedLabel).append("| ").append(formattedValue).append("|\n");
        }

        table.append("-".repeat(labelColumnWidth + 3) + "-" + "-".repeat(valueColumnWidth + 1));

        return table.toString();
    }

    public double getWindSpeed() {

        return current.getWind_kph();
    }

    public double getPressure() {

        return current.getPressure_mb();
    }

    public double getHumidity() {

        return current.getHumidity();
    }

    public double getPrecipitation() {

        return current.getPrecip_mm();
    }

    public double getCloudCover() {

        return current.getCloud();
    }

    public double getFeelsLike() {

        return current.getFeelslike_c();
    }

    public double getVisibility() {

        return current.getVis_km();
    }

    public double getUVIndex() {

        return current.getUv();
    }

    public double getGust() {

        return current.getGust_kph();
    }

    public String getWindDirection() {

        return current.getWind_dir();
    }

    public Location getLocation() {
        return location;
    }

    public Current getCurrent() {
        return current;
    }

}

class Location {
    private String name;
    private String region;
    private String country;
    private double lat;
    private double lon;
    private String tz_id;
    private String localtime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getTz_id() {
        return tz_id;
    }

    public void setTz_id(String tz_id) {
        this.tz_id = tz_id;
    }

    public String getLocaltime() {
        return localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }

    // TODO: make this more appealing
    @Override
    public String toString() {
        return "name=" + name + ", region=" + region + ", country=" + country + ", latitude=" + lat + ", longitude="
                + lon + ", timezone=" + tz_id + ", local time=" + localtime;
    }

}

class Current {
    private String last_updated;
    private double temp_c;
    private double is_day;
    private Condition condition;
    private double wind_kph;
    private String wind_dir;
    private double pressure_mb;
    private double precip_mm;
    private double humidity;
    private double cloud;
    private double feelslike_c;
    private double vis_km;
    private double uv;
    private double gust_kph;

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }

    public double getIs_day() {
        return is_day;
    }

    public void setIs_day(double is_day) {
        this.is_day = is_day;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public double getWind_kph() {
        return wind_kph;
    }

    public void setWind_kph(double wind_kph) {
        this.wind_kph = wind_kph;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public double getPressure_mb() {
        return pressure_mb;
    }

    public void setPressure_mb(double pressure_mb) {
        this.pressure_mb = pressure_mb;
    }

    public double getPrecip_mm() {
        return precip_mm;
    }

    public void setPrecip_mm(double precip_mm) {
        this.precip_mm = precip_mm;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getCloud() {
        return cloud;
    }

    public void setCloud(double cloud) {
        this.cloud = cloud;
    }

    public double getFeelslike_c() {
        return feelslike_c;
    }

    public void setFeelslike_c(double feelslike_c) {
        this.feelslike_c = feelslike_c;
    }

    public double getVis_km() {
        return vis_km;
    }

    public void setVis_km(double vis_km) {
        this.vis_km = vis_km;
    }

    public double getUv() {
        return uv;
    }

    public void setUv(double uv) {
        this.uv = uv;
    }

    public double getGust_kph() {
        return gust_kph;
    }

    public void setGust_kph(double gust_kph) {
        this.gust_kph = gust_kph;
    }

    // TODO: make this more appealing
    @Override
    public String toString() {
        return "last_updated=" + last_updated + ", temp_c="
                + temp_c + ", is_day=" + is_day + ", condition=" + condition + ", wind_speed=" + wind_kph
                + ", wind_dir=" + wind_dir + ", pressure=" + pressure_mb + ", precip_mm=" + precip_mm + ", humidity="
                + humidity
                + ", cloud cover percentage=" + cloud + ", feels like=" + feelslike_c + ", visiblity=" + vis_km
                + ", uv=" + uv
                + ", wind gust=" + gust_kph;
    }

}

class Condition {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}