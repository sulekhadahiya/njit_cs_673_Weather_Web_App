/*
 * Copyright (c) 2018.
 */

package weatherapp.domain.restmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.multipart.MultipartFile;
import weatherapp.domain.dbmodel.LocationCoordinates;
import weatherapp.domain.dbmodel.LocationPhoto;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationPhotoRest {
    private MultipartFile locationPhoto;
    private String locationName;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String country;
    private String longitude;
    private String latitude;
    private String email;
    private String url;
    private ZonedDateTime creationTime;

    public LocationPhotoRest() {
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public MultipartFile getLocationPhoto() {
        return locationPhoto;
    }

    public void setLocationPhoto(MultipartFile locationPhoto) {
        this.locationPhoto = locationPhoto;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static LocationPhotoRest convertLocationPhotoToLocationPhotoRest(LocationPhoto locationPhoto) {
        LocationPhotoRest locationPhotoRest = new LocationPhotoRest();
        locationPhotoRest.setCity(locationPhoto.getCity());
        locationPhotoRest.setCountry(locationPhoto.getCountry());
        locationPhotoRest.setLongitude(locationPhoto.getLocationCoordinates().getLongitude());
        locationPhotoRest.setLatitude(locationPhoto.getLocationCoordinates().getLatitude());
        locationPhotoRest.setPhoneNumber(locationPhoto.getPhoneNumber());
        locationPhotoRest.setState(locationPhoto.getState());
        locationPhotoRest.setStreet(locationPhoto.getStreet());
        locationPhotoRest.setLocationName(locationPhoto.getLocationName());
        locationPhotoRest.setZipCode(locationPhoto.getZipCode());
        locationPhotoRest.setEmail(locationPhoto.getEmail());
        locationPhotoRest.setUrl(locationPhoto.getUrl());
//        locationPhotoRest.setCreationTime(locationPhoto.getCreationTime());
        return locationPhotoRest;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocationPhoto convertToLocationPhoto() {
        LocationPhoto locationPhoto = new LocationPhoto();
        locationPhoto.setCity(this.getCity());
        locationPhoto.setCountry(this.getCountry());
        locationPhoto.setLocationCoordinates(new LocationCoordinates(this.longitude, this.latitude));
        locationPhoto.setPhoneNumber(this.phoneNumber);
        locationPhoto.setState(this.state);
        locationPhoto.setStreet(this.street);
        locationPhoto.setLocationName(this.locationName);
        locationPhoto.setZipCode(this.zipCode);
        locationPhoto.setEmail(this.email);
        locationPhoto.setUrl(this.url);
//        locationPhoto.setCreationTime(this.creationTime);
        return locationPhoto;
    }

    @Override
    public String toString() {
        String originalFilename = null;

        if (Objects.nonNull(locationPhoto)) {
            originalFilename = locationPhoto.getOriginalFilename();
        }
        return "LocationPhotoRest{" +
                "locationPhoto=" + originalFilename +
                ", locationName='" + locationName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", country='" + country + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", email='" + email + '\'' +
                ", url='" + url + '\'' +
                ", creationTime='" + creationTime + '\'' +
                '}';
    }
}
