package weatherapp.services;

import weatherapp.utils.CoordPair;

public interface IRadarService {
    // Get URL for current radar map.
    String getRadarMapURL(CoordPair coords, boolean animated) throws RuntimeException;
}
