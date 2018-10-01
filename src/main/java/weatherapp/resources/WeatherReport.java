package weatherapp.resources;

import weatherapp.utils.CoordPair;

import java.util.Date;

public class WeatherReport {
    // Metadata
    public CoordPair coords;
    public String name;
    public Date timestamp;

    // Weather information
    public double temp;
    public String windSpeed;
    public String windDir;
    public String shortForecast;
    public String longForecast;
}
