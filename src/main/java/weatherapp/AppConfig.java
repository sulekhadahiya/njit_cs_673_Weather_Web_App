package weatherapp;

import weatherapp.services.ForecastService;
import weatherapp.services.GeolocationService;
import weatherapp.services.NWSForecastService;
import weatherapp.services.WeatherBitForecastService;

public class AppConfig {
    public static final ForecastService shortForecastService = new NWSForecastService();
    public static final ForecastService longForecastService = new WeatherBitForecastService();
    public static final GeolocationService geolocationService = null;
}
