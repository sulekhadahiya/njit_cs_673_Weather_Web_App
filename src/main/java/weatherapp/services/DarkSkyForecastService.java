/*
 * Copyright (c) 2018.
 */

package weatherapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;
import weatherapp.resources.WeatherReport;
import weatherapp.utils.CoordPair;
import weatherapp.utils.WindHelper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class DarkSkyForecastService implements IForecastService {

    private final String CURRENT_URL = "https://api.darksky.net/forecast/%s/%f,%f?exclude=minutely,hourly,daily,flags";
    private final String HOURLY_URL = "https://api.darksky.net/forecast/%s/%f,%f?exclude=currently,minutely,daily,flags";
    private final String FIVEDAY_URL = "https://api.darksky.net/forecast/%s/%f,%f?exclude=currently,minutely,hourly,flags";
    private final String HISTORICAL_URL = "https://api.darksky.net/forecast/%s/%f,%f,%d?exclude=currently,hourly,flags";
    private final String API_KEY = "ebf9741c8eb658310ea1cb98175f612c";

    RestTemplate rest = new RestTemplate();

    @Override
    public WeatherReport getCurrentWeather(CoordPair coords) {
        JsonNode root = rest.getForObject(String.format(CURRENT_URL, API_KEY, coords.getLatitude(), coords.getLongitude()), JsonNode.class);

        ZoneId timezone = ZoneId.of(root.get("timezone").asText());

        return createWeatherReport(root.get("currently"), coords, timezone, false);
    }

    @Override
    public List<WeatherReport> getHourlyWeather(CoordPair coords) {
        JsonNode root = rest.getForObject(String.format(HOURLY_URL, API_KEY, coords.getLatitude(), coords.getLongitude()), JsonNode.class);

        ZoneId timezone = ZoneId.of(root.get("timezone").asText());

        List<WeatherReport> reports = new ArrayList<>(24);

        for (int i = 0; i < 24; i++) {
            JsonNode data = root.get("hourly").get("data").get(i);
            reports.add(i, createWeatherReport(data, coords, timezone, false));
        }

        return reports;
    }

    @Override
    public List<WeatherReport> get5DayWeather(CoordPair coords) {
        JsonNode root = rest.getForObject(String.format(FIVEDAY_URL, API_KEY, coords.getLatitude(), coords.getLongitude()), JsonNode.class);

        ZoneId timezone = ZoneId.of(root.get("timezone").asText());

        List<WeatherReport> reports = new ArrayList<>(5);

        for (int i = 0; i < 5; i++) {
            JsonNode data = root.get("daily").get("data").get(i);
            reports.add(i, createWeatherReport(data, coords, timezone, true));
        }

        return reports;
    }

    @Override
    public List<WeatherReport> get10DayWeather(CoordPair coords) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This API does not support 10-day weather reports");
    }

    @Override
    public WeatherReport getHistoricalWeather(CoordPair coords, long timestamp) {
        JsonNode root = rest.getForObject(String.format(HISTORICAL_URL, API_KEY, coords.getLatitude(), coords.getLongitude(), timestamp), JsonNode.class);

        ZoneId timezone = ZoneId.of(root.get("timezone").asText());
        JsonNode data = root.get("daily").get("data").get(0);

        return createWeatherReport(data, coords, timezone, true);
    }

    private WeatherReport createWeatherReport(JsonNode data, CoordPair coords, ZoneId timezone, boolean isDaily) {
        WeatherReport report = new WeatherReport();

        report.coords = coords;
        report.name = coords.getName();
        report.timestamp = ZonedDateTime.ofInstant(Instant.ofEpochSecond(data.get("time").asInt()), timezone);

        if (data.has("temperatureLow")) {
            report.temp_low = (int)Math.round(data.get("temperatureLow").asDouble());
        }

        if (data.has("temperatureHigh")) {
            report.temp_high = (int)Math.round(data.get("temperatureHigh").asDouble());
        }

        if (isDaily) {
            report.temp = Math.round((report.temp_high + report.temp_low) / 2);
        } else {
            if (data.has("temperature")) {
                report.temp = (int) Math.round(data.get("temperature").asDouble());
            }
        }

        if (data.has("apparentTemperatureHigh") && data.has("apparentTemperatureLow")) {
            double high = data.get("apparentTemperatureHigh").asDouble();
            double low = data.get("apparentTemperatureLow").asDouble();
            report.temp_feels_like = (int)Math.round((high + low) / 2);
        } else {
            report.temp_feels_like = (int)Math.round(data.get("apparentTemperature").asDouble());
        }

        if (data.has("dewPoint")) {
            report.dew_point = (int)Math.round(data.get("dewPoint").asDouble());
        }

        if (data.has("windSpeed")) {
            report.wind_speed = (int)Math.round(data.get("windSpeed").asDouble());
        }

        if (data.has("windBearing")) {
            report.wind_direction = WindHelper.bearingToCardinal(data.get("windBearing").asInt());
        }

        if (data.has("humidity")) {
            report.humidity = (int)(data.get("humidity").asDouble() * 100);
        }

        if (data.has("pressure")) {
            report.pressure = (int)Math.round(data.get("pressure").asDouble());
        }

        if (data.has("precipProbability")) {
            report.precipitation_probability = (int)(data.get("precipProbability").asDouble() * 100);
        }

        if (data.has("summary")) {
            report.summary = data.get("summary").asText();
        }

        return report;
    }
}
