/*
 * Copyright (c) 2018.
 */

package weatherapp.domain.restmodel;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import weatherapp.domain.dbmodel.Address;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressRest {

    private String street;
    private String apartment;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String country;
    private LocationCoordinatesRest locationCoordinatesRest = new LocationCoordinatesRest();


    public AddressRest() {

    }

    public static AddressRest addressToAddressRestConverter(Address address) {

        AddressRest addressRest = new AddressRest();
        addressRest.setApartment(address.getApartment());
        addressRest.setCity(address.getCity());
        addressRest.setPhoneNumber(address.getPhoneNumber());
        addressRest.setZipCode(address.getZipCode());
        addressRest.setCountry(address.getCountry());
        addressRest.setStreet(address.getStreet());
        LocationCoordinatesRest locationCoordinatesRest = LocationCoordinatesRest.locationCoordinatesToLocationCoordinatesRest(address.getLocationCoordinates());
        addressRest.setLocationCoordinatesRest(locationCoordinatesRest);
        return addressRest;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
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

    public Address addressRestToAddressConverter() {
        //create a new empty Address object
        Address address = new Address();
        address.setCity(this.getCity());
        address.setPhoneNumber(this.getPhoneNumber());
        address.setState(this.getState());
        address.setZipCode(this.getZipCode());
        address.setApartment(this.getApartment());
        address.setStreet(this.getStreet());
        address.setLocationCoordinates(this.locationCoordinatesRest.locationCoordinatesRestToLocationCoordinates());
        //return the cloned address
        return address;
    }
}
