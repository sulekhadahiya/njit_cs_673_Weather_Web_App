/*
 * Copyright (c) 2018.
 */

package weatherapp.services;

import org.junit.Test;
import weatherapp.utils.CoordPair;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class NWSMapServiceTest {
    private static NWSMapService service = new NWSMapService();
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

    @Test
    public void getTempMapURLs() {
        List<String> tempMapURLS = service.getTempMapURLs();
        assertEquals(tempMapURLS.size(), 8);
    }
}