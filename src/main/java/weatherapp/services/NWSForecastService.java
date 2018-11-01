package weatherapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;
import weatherapp.resources.WeatherReport;
import weatherapp.utils.CoordPair;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class NWSForecastService implements IForecastService {

    private final String BASE_URL = "https://api.weather.gov/points/%f,%f";

    private RestTemplate rest = new RestTemplate();

    @Override
    public WeatherReport getCurrentWeather(CoordPair coords) throws RuntimeException {
        JsonNode info_root = rest.getForObject(String.format(BASE_URL, coords.getLatitude(), coords.getLongitude()), JsonNode.class);
        JsonNode data_root = rest.getForObject(info_root.get("properties").get("forecast").asText(), JsonNode.class);

        // Report coordinates
        double lat = info_root.get("properties").get("relativeLocation").get("geometry").get("coordinates").get(1).asDouble();
        double lon = info_root.get("properties").get("relativeLocation").get("geometry").get("coordinates").get(0).asDouble();

        // Report location name
        String city = info_root.get("properties").get("relativeLocation").get("properties").get("city").asText();
        String state = info_root.get("properties").get("relativeLocation").get("properties").get("state").asText();
        String name = city + ", " + state;

        JsonNode data_node = data_root.get("properties").get("periods").get(0);
        WeatherReport report = createWeatherReport(data_node, lat, lon, name);
        return report;
    }

    @Override
    public List<WeatherReport> getHourlyWeather(CoordPair coords) throws RuntimeException {
        JsonNode info_root = rest.getForObject(String.format(BASE_URL, coords.getLatitude(), coords.getLongitude()), JsonNode.class);
        JsonNode data_root = rest.getForObject(info_root.get("properties").get("forecastHourly").asText(), JsonNode.class);

        // Report coordinates
        double lat = info_root.get("properties").get("relativeLocation").get("geometry").get("coordinates").get(1).asDouble();
        double lon = info_root.get("properties").get("relativeLocation").get("geometry").get("coordinates").get(0).asDouble();

        // Report location name
        String city = info_root.get("properties").get("relativeLocation").get("properties").get("city").asText();
        String state = info_root.get("properties").get("relativeLocation").get("properties").get("state").asText();
        String name = city + ", " + state;

        List<WeatherReport> reports = new ArrayList<>(24);

        // We only want the first 24 entries (24-hours).
        for (int i = 0; i < 24; i++) {
            JsonNode data_node = data_root.get("properties").get("periods").get(i);
            WeatherReport report = createWeatherReport(data_node, lat, lon, name);
            reports.add(i, report);
        }

        return reports;
    }

    @Override
    public List<WeatherReport> get5DayWeather(CoordPair coords) throws RuntimeException {
        JsonNode info_root = rest.getForObject(String.format(BASE_URL, coords.getLatitude(), coords.getLongitude()), JsonNode.class);
        JsonNode data_root = rest.getForObject(info_root.get("properties").get("forecast").asText(), JsonNode.class);

        // Report coordinates
        double lat = info_root.get("properties").get("relativeLocation").get("geometry").get("coordinates").get(1).asDouble();
        double lon = info_root.get("properties").get("relativeLocation").get("geometry").get("coordinates").get(0).asDouble();

        // Report location name
        String city = info_root.get("properties").get("relativeLocation").get("properties").get("city").asText();
        String state = info_root.get("properties").get("relativeLocation").get("properties").get("state").asText();
        String name = city + ", " + state;

        List<WeatherReport> reports = new ArrayList<>(10);

        // We only want the first 10 entries (5 days, 2 entries per day).
        for (int i = 0; i < 10; i++) {
            JsonNode data_node = data_root.get("properties").get("periods").get(i);
            WeatherReport report = createWeatherReport(data_node, lat, lon, name);
            reports.add(i, report);
        }

        return reports;
    }

    @Override
    public List<WeatherReport> get10DayWeather(CoordPair coords) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This API does not support 10-day weather reports");
    }

    private WeatherReport createWeatherReport(JsonNode data_node, double lat, double lon, String name) {
        WeatherReport report = new WeatherReport();

        report.coords = new CoordPair(lat, lon);
        report.name = name;
        report.timestamp = ZonedDateTime.parse(data_node.get("startTime").asText());

        report.temp = data_node.get("temperature").asInt();
        report.wind_speed = Integer.parseInt(data_node.get("windSpeed").asText().split(" ")[0]);
        report.wind_direction = data_node.get("windDirection").asText();

        report.summary = data_node.get("shortForecast").asText();

        return report;
    }
}
