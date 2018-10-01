package weatherapp.services;

import weatherapp.resources.WeatherReport;
import weatherapp.utils.CoordPair;

import java.util.List;

public interface ForecastService {
    // Get the current weather forecast.
    WeatherReport getCurrentWeather(CoordPair coords) throws RuntimeException;

    // Get the hourly weather forecast for 24 hours.
    List<WeatherReport> getHourlyWeather(CoordPair coords) throws RuntimeException;

    // Get the daily weather forecast for 5 days.
    List<WeatherReport> get5DayWeather(CoordPair coords) throws RuntimeException;

    // Get the daily weather forecast for 10 days.
    List<WeatherReport> get10DayWeather(CoordPair coords) throws RuntimeException;
}
