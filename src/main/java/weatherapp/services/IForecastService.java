package weatherapp.services;

import weatherapp.resources.WeatherReport;
import weatherapp.utils.CoordPair;

import java.util.List;

public interface IForecastService {
    // Get the current weather forecast.
    WeatherReport getCurrentWeather(CoordPair coords) throws Exception;

    // Get the hourly weather forecast for 24 hours.
    List<WeatherReport> getHourlyWeather(CoordPair coords) throws Exception;

    // Get the daily weather forecast for 5 days.
    List<WeatherReport> get5DayWeather(CoordPair coords) throws Exception;

    // Get the daily weather forecast for 10 days.
    List<WeatherReport> get10DayWeather(CoordPair coords) throws Exception;

    // Get historical weather reports.
    WeatherReport getHistoricalWeather(CoordPair coords, long timestamp) throws Exception;
}
