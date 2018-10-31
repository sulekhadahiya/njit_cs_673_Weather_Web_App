/*
 * Copyright (c) 2018.
 */

package weatherapp.domain.restmodel;

import weatherapp.domain.dbmodel.LocationCoordinates;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */
public class LocationCoordinatesRest {
    private String longitude;
    private String latitude;

    public LocationCoordinatesRest() {
    }

    public LocationCoordinatesRest(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static LocationCoordinatesRest locationCoordinatesToLocationCoordinatesRest(LocationCoordinates locationCoordinates) {
        LocationCoordinatesRest locationCoordinatesRest = new LocationCoordinatesRest(locationCoordinates.getLongitude(), locationCoordinates.getLatitude());
        return locationCoordinatesRest;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "LocationCoordinatesRest{" +
                "longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }

    public LocationCoordinates locationCoordinatesRestToLocationCoordinates() {
        LocationCoordinates locationCoordinates = new LocationCoordinates(this.longitude, this.latitude);
        return locationCoordinates;
    }
}
