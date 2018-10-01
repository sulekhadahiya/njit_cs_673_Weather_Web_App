package weatherapp.services;

import weatherapp.utils.CoordPair;
import weatherapp.utils.exceptions.LocationNotFoundException;

public interface GeolocationService {
    CoordPair locationToCoords(String location) throws LocationNotFoundException;
}
