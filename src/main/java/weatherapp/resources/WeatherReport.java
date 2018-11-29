/*
 * Copyright (c) 2018.
 */

package weatherapp.resources;

import weatherapp.utils.CoordPair;

import java.time.ZonedDateTime;

public class WeatherReport {
    // Metadata
    public CoordPair coords;
    public String name;
    public ZonedDateTime timestamp;

    // Temperature data
    public int temp;
    public int temp_low;
    public int temp_high;
    public int temp_feels_like;
    public int dew_point;

    // Wind data
    public int wind_speed;
    public String wind_direction;

    // Other data
    public int humidity;
    public int pressure;

    // Precipitation
    public int precipitation_probability;

    // Summary
    public String summary;

    @Override
    public String toString() {
        return String.format("<WeatherReport(coords: %s, " +
                        "temp: %d, temp_feels_like: %d, " +
                        "wind_speed: %d, wind_direction: \"%s\", humidity: %d, dew_point: %d, pressure: %d, " +
                        "precipitation_probability: %d, summary: \"%s\", timestamp: %s)>",
                this.coords.toString(),
                this.temp, this.temp_feels_like,
                this.wind_speed, this.wind_direction, this.humidity, this.dew_point, this.pressure,
                this.precipitation_probability,
                this.summary, this.timestamp.toString());
    }
}
