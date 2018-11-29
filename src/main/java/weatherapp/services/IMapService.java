/*
 * Copyright (c) 2018.
 */

package weatherapp.services;

import weatherapp.utils.CoordPair;

import java.util.List;

public interface IMapService {
    // Get URL for current radar map.
    String getRadarMapURL(CoordPair coords, boolean animated) throws RuntimeException;

    // Get URLs for current temperature maps.
    List<String> getTempMapURLs() throws RuntimeException;
}
