package weatherapp.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import weatherapp.utils.CoordPair;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherReport {
    // Metadata
    public CoordPair coords;
    public String name;
    public ZonedDateTime timestamp;

    // Weather information
    public int temp;
    public String windSpeed;
    public String windDir;
    public String shortForecast;
    public String longForecast;

    @Override
    public String toString() {
        return String.format("<WeatherReport(coords: %s, name: \"%s\", timestamp: \"%s\", temp: %d, windSpeed: \"%s\", windDir: \"%s\", shortForecast: \"%s\", longForecast: \"%s\")>",
                this.coords.toString(), this.name, this.timestamp.format(DateTimeFormatter.ISO_ZONED_DATE_TIME),
                this.temp, this.windSpeed, this.windDir, this.shortForecast, this.longForecast);
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
    }
}
