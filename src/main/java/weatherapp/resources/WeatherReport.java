package weatherapp.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import weatherapp.utils.CoordPair;

import java.time.ZonedDateTime;

public class WeatherReport {
    // Metadata
    public CoordPair coords;
    public String name;
    public ZonedDateTime timestamp;

    // Weather information
    public double temp;
    public String windSpeed;
    public String windDir;
    public String shortForecast;
    public String longForecast;

    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
