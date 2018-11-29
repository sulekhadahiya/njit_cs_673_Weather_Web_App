/*
 * Copyright (c) 2018.
 */

package weatherapp.services;

import org.junit.Test;
import weatherapp.utils.CoordPair;

import static org.junit.Assert.assertEquals;

public class MapQuestGeolocationServiceTest {
    private static MapQuestGeolocationService service = new MapQuestGeolocationService();
    private static String testLocation = "Newark, NJ";

    @Test
    public void locationToCoords() {
        CoordPair result = service.locationToCoords(testLocation);
        assertEquals(40.735657, result.getLatitude(), 0.0);
        assertEquals(-74.172367, result.getLongitude(), 0.0);
    }
}