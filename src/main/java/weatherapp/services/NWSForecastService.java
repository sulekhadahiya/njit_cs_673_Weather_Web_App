package weatherapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;
import weatherapp.resources.WeatherReport;
import weatherapp.utils.CoordPair;

import java.util.ArrayList;
import java.util.List;

public class NWSForecastService implements ForecastService {

    private final String INFO_URL = "https://api.weather.gov/points/%f,%f";
    private final String CURRENT_URL = "https://api.weather.gov/points/%f,%f/forecast";
    private final String HOURLY_URL = "https://api.weather.gov/points/%f,%f/forecast/hourly";

    private RestTemplate rest = new RestTemplate();

    @Override
    public WeatherReport getCurrentWeather(CoordPair coords) throws RuntimeException {
        JsonNode info_root = rest.getForObject(String.format(INFO_URL, coords.getLatitude(), coords.getLongitude()), JsonNode.class);
        JsonNode data_root = rest.getForObject(String.format(CURRENT_URL, coords.getLatitude(), coords.getLongitude()), JsonNode.class);

        WeatherReport report = new WeatherReport();

        // TODO: populate weather report

        return report;
    }

    @Override
    public List<WeatherReport> getHourlyWeather(CoordPair coords) throws RuntimeException {
        JsonNode info_root = rest.getForObject(String.format(INFO_URL, coords.getLatitude(), coords.getLongitude()), JsonNode.class);
        JsonNode data_root = rest.getForObject(String.format(HOURLY_URL, coords.getLatitude(), coords.getLongitude()), JsonNode.class);

        List<WeatherReport> reports = new ArrayList<>(24);

        // TODO: populate weather report

        return reports;
    }

    @Override
    public List<WeatherReport> get5DayWeather(CoordPair coords) throws RuntimeException {
        JsonNode info_root = rest.getForObject(String.format(INFO_URL, coords.getLatitude(), coords.getLongitude()), JsonNode.class);
        JsonNode data_root = rest.getForObject(String.format(CURRENT_URL, coords.getLatitude(), coords.getLongitude()), JsonNode.class);

        List<WeatherReport> reports = new ArrayList<>(10);

        // TODO: populate weather report

        return reports;
    }

    @Override
    public List<WeatherReport> get10DayWeather(CoordPair coords) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This API does not support 10-day weather reports");
    }
}
