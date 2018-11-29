/*
 * Copyright (c) 2018.
 */

package weatherapp.domain.dbmodel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */
@Document
public class LocationPhoto {
    @TextScore
    Float score;
    @Id
    private String id;
    @TextIndexed
    private String locationName;
    @TextIndexed
    private String street;
    @TextIndexed
    private String city;
    @TextIndexed
    private String state;
    @TextIndexed
    private String zipCode;
    @TextIndexed
    private String phoneNumber;
    @TextIndexed
    private String country;
    @TextIndexed
    private LocationCoordinates locationCoordinates;
    @TextIndexed
    @Indexed(sparse = true)
    private String savedLocationPhotoKeyName;
    private String eTag;
    @Indexed
    private String email;
    @TextIndexed
    private String url;
    @TextIndexed
    private String creationTime;

    public LocationPhoto() {
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
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

    public LocationCoordinates getLocationCoordinates() {
        return locationCoordinates;
    }

    public void setLocationCoordinates(LocationCoordinates locationCoordinates) {
        this.locationCoordinates = locationCoordinates;
    }

    public String getSavedLocationPhotoKeyName() {
        return savedLocationPhotoKeyName;
    }

    public void setSavedLocationPhotoKeyName(String savedLocationPhotoKeyName) {
        this.savedLocationPhotoKeyName = savedLocationPhotoKeyName;
    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "LocationPhoto{" +
                "score=" + score +
                ", id='" + id + '\'' +
                ", locationName='" + locationName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", country='" + country + '\'' +
                ", locationCoordinates=" + locationCoordinates +
                ", savedLocationPhotoKeyName='" + savedLocationPhotoKeyName + '\'' +
                ", eTag='" + eTag + '\'' +
                ", email='" + email + '\'' +
                ", URL='" + url + '\'' +
                ", creationTime='" + creationTime + '\'' +
                '}';
    }
}
