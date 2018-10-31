/*
 * Copyright (c) 2018.
 */

package weatherapp.domain.dbmodel;

import weatherapp.domain.restmodel.LocationCoordinatesRest;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */
public class LocationPhoto {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String country;
    private LocationCoordinatesRest locationCoordinatesRest;
    private String s3PhotoKeyName;

    public LocationPhoto() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocationCoordinatesRest getLocationCoordinatesRest() {
        return locationCoordinatesRest;
    }

    public void setLocationCoordinatesRest(LocationCoordinatesRest locationCoordinatesRest) {
        this.locationCoordinatesRest = locationCoordinatesRest;
    }

    public String getS3PhotoKeyName() {
        return s3PhotoKeyName;
    }

    public void setS3PhotoKeyName(String s3PhotoKeyName) {
        this.s3PhotoKeyName = s3PhotoKeyName;
    }

    @Override
    public String toString() {
        return "LocationPhoto{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", country='" + country + '\'' +
                ", locationCoordinatesRest=" + locationCoordinatesRest +
                ", s3PhotoKeyName='" + s3PhotoKeyName + '\'' +
                '}';
    }
}
