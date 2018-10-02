package weatherapp.services;

import weatherapp.utils.CoordPair;
import weatherapp.utils.exceptions.LocationNotFoundException;

public interface GeolocationService {
    // Get the coordinates associated with a street address.
    CoordPair locationToCoords(String location) throws LocationNotFoundException;
}
