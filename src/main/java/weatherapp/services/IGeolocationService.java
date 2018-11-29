/*
 * Copyright (c) 2018.
 */

package weatherapp.services;

import weatherapp.utils.CoordPair;
import weatherapp.utils.exceptions.LocationNotFoundException;

public interface IGeolocationService {
    // Get the coordinates associated with a street address.
    CoordPair locationToCoords(String location) throws LocationNotFoundException;
}
