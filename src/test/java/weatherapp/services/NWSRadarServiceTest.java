package weatherapp.services;

import org.junit.Test;
import weatherapp.utils.CoordPair;

import static org.junit.Assert.*;

public class NWSRadarServiceTest {
    private static NWSRadarService service = new NWSRadarService();
    private static CoordPair testPoint = new CoordPair(40.776, -74.165, "Newark, NJ");
    private static String expectedStatic = "https://radar.weather.gov/ridge/lite/N0R/DIX_0.png";
    private static String expectedAnimated = "https://radar.weather.gov/ridge/lite/N0R/DIX_loop.gif";

    @Test
    public void getRadarMapURLStatic() {
        String radarURL = service.getRadarMapURL(testPoint, false);
        assertEquals(expectedStatic, radarURL);
    }

    @Test
    public void getRadarMapURLAnimated() {
        String radarURL = service.getRadarMapURL(testPoint, true);
        assertEquals(expectedAnimated, radarURL);
    }

}