package weatherapp.services;

import org.springframework.web.client.RestTemplate;
import weatherapp.resources.WeatherReport;
import weatherapp.utils.CoordPair;

import java.util.List;

public class WeatherBitForecastService implements ForecastService {

    private final String BASE_URL = "http://api.weatherbit.io/v2.0/";

    private RestTemplate restTemplate = new RestTemplate();

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
    public List<WeatherReport> get10DayWeather(CoordPair coords) throws Exception {
        return null;
    }
}
