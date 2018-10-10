package weatherapp.services;

import org.junit.Test;
import weatherapp.resources.WeatherReport;
import weatherapp.utils.CoordPair;

import java.util.List;

import static org.junit.Assert.*;

public class WeatherBitForecastServiceTest {
    private static WeatherBitForecastService service = new WeatherBitForecastService();
    private static CoordPair testPoint = new CoordPair(39.0693, -94.6716);

    @Test(expected = UnsupportedOperationException.class)
    public void getCurrentWeather() {
        WeatherReport reports = service.getCurrentWeather(testPoint);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getHourlyWeather() {
        List<WeatherReport> reports = service.getHourlyWeather(testPoint);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void get5DayWeather() {
        List<WeatherReport> reports = service.get5DayWeather(testPoint);
    }

    @Test
    public void get10DayWeather() {
        List<WeatherReport> reports = service.get10DayWeather(testPoint);
        assertEquals(10, reports.size());

        for (WeatherReport report : reports) {
            assertEquals(39.07, report.coords.getLatitude(), 0.0);
            assertEquals(-94.67, report.coords.getLongitude(), 0.0);
            assertEquals("Mission, KS", report.name);
            assertNotNull(report.timestamp);

            assertNotEquals(0, report.summary.length());
        }

//        for (WeatherReport report : reports) {
//            System.out.println(report);
//        }
    }
}