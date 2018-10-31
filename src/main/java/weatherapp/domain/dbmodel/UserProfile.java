/*
 * Copyright (c) 2018.
 */

package weatherapp.domain.dbmodel;

/**
 * * UserProfileRest object is an entity and it will store information about a user.
 *
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class UserProfile {

    @Id
    private String id;
    private Name name;
    @Indexed(unique = true)
    private String email;
    private Map<String, Address> addresses = new HashMap<>();
    private List<String> favouriteCities = new ArrayList<>();
    private String profilePhoto;

    public UserProfile() {

    }

    /**
     * @param addressType it represents the user given name to this address and it act as a key in address map
     * @param address     new address that user wants to save
     */
    public void addAddresses(String addressType, Address address) {
        this.addresses.put(addressType, address);

    }

    public void addFavouriteCities(String favouriteCities) {
        this.favouriteCities.add(favouriteCities);

    }

    public Name getName() {
        return this.name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Address> getAddresses() {
        Map<String, Address> addressMap = new HashMap<>();
        Set<String> keys = addresses.keySet();
        Iterator<String> keyIterator = keys.iterator();
        while (keyIterator.hasNext()) {
            //get the next key from iterator
            String key = keyIterator.next();
            //get the address corresponding to the key
            Address address = this.addresses.get(key);
            //clone the address fields to new address object
            Address newaddress = address.cloneAddress();
            //save key and new address inside the map.
            addressMap.put(key, newaddress);
        }
        //return the reference of addressMap
        return addressMap;
    }

    public void setAddresses(Map<String, Address> addresses) {
        Map<String, Address> addressMap = new HashMap<>();
        Set<String> keySet = addresses.keySet();
        Iterator<String> keysetIterator = keySet.iterator();
        while (keysetIterator.hasNext()) {
            String key = keysetIterator.next();
            Address address = addresses.get(key);
            Address newAddress = address.cloneAddress();
            addressMap.put(key, newAddress);
        }
        this.addresses = addressMap;

    }

    public List<String> getFavouriteCities() {
        return List.copyOf(this.favouriteCities);
    }

    public void setFavouriteCities(List<String> favouriteCities) {
        List<String> cityList = new ArrayList<>();
        Iterator<String> listIterator = favouriteCities.iterator();
        while (listIterator.hasNext()) {
            String city = listIterator.next();
            cityList.add(city);
        }
        this.favouriteCities = cityList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", email='" + email + '\'' +
                ", addresses=" + addresses +
                ", favouriteCities=" + favouriteCities +
                ", profilePhoto='" + profilePhoto + '\'' +
                '}';
    }
}