/*
 * Copyright (c) 2018.
 */

package weatherapp.domain.restmodel;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */
public class LocationPhotoRest {
    private MultipartFile profilePhoto;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String country;
    private LocationCoordinatesRest locationCoordinatesRest;

    public LocationPhotoRest() {
    }

    public MultipartFile getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(MultipartFile profilePhoto) {
        this.profilePhoto = profilePhoto;
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

    @Override
    public String toString() {
        return "LocationPhotoRest{" +
                "profilePhoto=" + profilePhoto.getOriginalFilename() +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", country='" + country + '\'' +
                ", locationCoordinatesRest=" + locationCoordinatesRest +
                '}';
    }
}
