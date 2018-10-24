/*
 * Copyright (c) 2018.
 */

package weatherapp.domain.dbmodel;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */

public class Address {

    private String street;
    private String apartment;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String country;

    public Address() {

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public Address cloneAddress() {
        //create a new empty Address object
        Address address = new Address();
        address.setCity(this.getCity());
        address.setPhoneNumber(this.getPhoneNumber());
        address.setState(this.getState());
        address.setZipCode(this.getZipCode());
        address.setApartment(this.getApartment());
        address.setStreet(this.getStreet());
        //return the cloned address
        return address;
    }

    @Override
    public String toString() {
        return "AddressRest{" +
                "street='" + street + '\'' +
                ", apartment='" + apartment + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
