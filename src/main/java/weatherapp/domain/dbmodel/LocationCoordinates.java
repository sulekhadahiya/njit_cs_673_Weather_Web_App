/*
 * Copyright (c) 2018.
 */

package weatherapp.domain.dbmodel;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */
public class LocationCoordinates {
    private String longitude;
    private String latitude;

    public LocationCoordinates() {
    }

    public LocationCoordinates(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
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
        return "LocationCoordinates{" +
                "longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }

    public LocationCoordinates cloneLocationCoordinates() {
        LocationCoordinates locationCoordinates = new LocationCoordinates(this.longitude, this.latitude);
        return locationCoordinates;
    }
}
