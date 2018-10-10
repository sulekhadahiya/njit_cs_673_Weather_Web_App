package weatherapp.services;

import org.junit.Test;
import weatherapp.resources.WeatherReport;
import weatherapp.utils.CoordPair;

import java.util.List;

import static org.junit.Assert.*;

public class DarkSkyForecastServiceTest {

    private static DarkSkyForecastService service = new DarkSkyForecastService();
    private static CoordPair testPoint = new CoordPair(40.776, -74.165, "Newark, NJ");

    @Test
    public void getCurrentWeather() {
        WeatherReport report = service.getCurrentWeather(testPoint);

        assertNotNull(report.timestamp);

        assertNotEquals(0, report.summary.length());
    }

    @Test
    public void getHourlyWeather() {
        List<WeatherReport> reports = service.getHourlyWeather(testPoint);
        assertEquals(24, reports.size());

        for (WeatherReport report : reports) {
            assertNotNull(report.timestamp);

            assertNotEquals(0, report.summary.length());
        }
    }

    @Test
    public void get5DayWeather() {
        List<WeatherReport> reports = service.get5DayWeather(testPoint);
        assertEquals(5, reports.size());

        for (WeatherReport report : reports) {
            assertNotNull(report.timestamp);

            assertNotEquals(0, report.summary.length());
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void get10DayWeather() {
        List<WeatherReport> report = service.get10DayWeather(testPoint);
    }
}