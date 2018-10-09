package weatherapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;
import weatherapp.resources.WeatherReport;
import weatherapp.utils.CoordPair;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WeatherBitForecastService implements IForecastService {

    private final String BASE_URL = "http://api.weatherbit.io/v2.0/forecast/daily?lat=%f&lon=%f&days=10&key=%s";
    private final String API_KEY = "fde1b1119c354d5a941acd8c294b8b17";

    private RestTemplate rest = new RestTemplate();

    @Override
    public WeatherReport getCurrentWeather(CoordPair coords) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This API does not support current weather reports");
    }

    @Override
    public List<WeatherReport> getHourlyWeather(CoordPair coords) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This API does not support hourly weather reports");
    }

    @Override
    public List<WeatherReport> get5DayWeather(CoordPair coords) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This API does not support 5-day weather reports");
    }

    @Override
    public List<WeatherReport> get10DayWeather(CoordPair coords) throws RuntimeException {
        JsonNode root = rest.getForObject(String.format(BASE_URL, coords.getLatitude(), coords.getLongitude(), API_KEY), JsonNode.class);

        double lat = root.get("lat").asDouble();
        double lon = root.get("lon").asDouble();

        // Report location name
        String city = root.get("city_name").asText();
        String state = root.get("state_code").asText();
        String name = city + ", " + state;

        ZoneId timezone = ZoneId.of(root.get("timezone").asText());

        List<WeatherReport> reports = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            WeatherReport report = new WeatherReport();

            JsonNode data = root.get("data").get(i);

            // Metadata
            report.coords = new CoordPair(lat, lon);
            report.name = name;

            // Timestamp
            report.timestamp = ZonedDateTime.ofInstant(Instant.ofEpochSecond(data.get("ts").asInt()), timezone);

            // Weather information (temp is given in C)
            report.temp = (int)Math.floor(((data.get("temp").asDouble() * 9.0) / 5.0) + 32.0);
            report.windSpeed = new Double(data.get("wind_spd").asText()).toString() + " mph";
            report.windDir = data.get("wind_cdir").asText();
            report.shortForecast = data.get("weather").get("description").asText();
            report.longForecast = "";

            reports.add(i, report);
        }

        return reports;
    }
}
