/*
 * Copyright (c) 2018.
 */

package weatherapp.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordPairTest {
    private CoordPair pair;

    @Before
    public void beforeEachTest() {
        this.pair = new CoordPair(-50.0, 50.0);
    }

    @Test
    public void getLatitude() {
        assertEquals(-50.0, this.pair.getLatitude(), 0.0);
    }

    @Test
    public void setLatitude() {
        this.pair.setLatitude(45.0);
        assertEquals(45.0, this.pair.getLatitude(), 0.0);
    }

    @Test
    public void getLongitude() {
        assertEquals(50.0, this.pair.getLongitude(), 0.0);
    }

    @Test
    public void setLongitude() {
        this.pair.setLongitude(-45.0);
        assertEquals(-45.0, this.pair.getLongitude(), 0.0);
    }
}