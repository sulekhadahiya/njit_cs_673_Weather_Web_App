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
import weatherapp.domain.dbmodel.UserProfile;

import javax.validation.constraints.NotNull;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileRest {

    private NameRest name;
    @NotNull
    private String email;
    private Map<String, AddressRest> addresses = new HashMap<>();
    private List<String> favouriteCities = new ArrayList<>();
    private String profilePhoto;
    private boolean markSafe;

    public UserProfileRest() {
    }

    public static UserProfileRest userprofileToUserProfileRest(UserProfile userProfile) {

        UserProfileRest userProfileRest = new UserProfileRest();
        userProfileRest.setEmail(userProfile.getEmail());
        userProfileRest.setName(NameRest.nameToNameRestConverter(userProfile.getName()));
        userProfileRest.setAddresses(addressMapToAddressRestMap(userProfile.getAddresses()));
        userProfileRest.setFavouriteCities(userProfile.getFavouriteCities());
        userProfileRest.setProfilePhoto(userProfile.getProfilePhoto());
        userProfileRest.setMarkSafe(userProfile.isMarkSafe());
        return userProfileRest;
    }

    public static Map<String, AddressRest> addressMapToAddressRestMap(Map<String, Address> addressMap) {

        Map<String, AddressRest> addressRestMap = new HashMap<>();
        Set<String> setOfkeys = addressMap.keySet();
        Iterator<String> iterator = setOfkeys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Address address = addressMap.get(key);
            AddressRest addressRest = AddressRest.addressToAddressRestConverter(address);
            addressRestMap.put(key, addressRest);
        }
        return addressRestMap;

    }

    public NameRest getName() {
        return name;
    }

    public void setName(NameRest name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, AddressRest> getAddresses() {
        return addresses;
    }

    public void setAddresses(Map<String, AddressRest> addresses) {
        this.addresses = addresses;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public List<String> getFavouriteCities() {
        return favouriteCities;
    }

    public void setFavouriteCities(List<String> favouriteCities) {
        this.favouriteCities = favouriteCities;
    }

    public boolean isMarkSafe() {
        return markSafe;
    }

    public void setMarkSafe(boolean markSafe) {
        this.markSafe = markSafe;
    }

    public UserProfile userProfileRestToUserProfile() {

        UserProfile userProfile = new UserProfile();
        userProfile.setEmail(UserProfileRest.this.getEmail());
        NameRest nameRest = UserProfileRest.this.getName();
        userProfile.setName(nameRest.nameRestToNameConverter());
        userProfile.setAddresses(addressRestMapToAddressMap());
        userProfile.setFavouriteCities(UserProfileRest.this.getFavouriteCities());
        userProfile.setMarkSafe(UserProfileRest.this.isMarkSafe());
        return userProfile;
    }

    public Map<String, Address> addressRestMapToAddressMap() {
        Map<String, Address> addressMap = new HashMap<>();
        Map<String, AddressRest> addressRestMap = this.getAddresses();
        Set<String> setOfkeys = addressRestMap.keySet();
        Iterator<String> iterator = setOfkeys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            AddressRest addressRest = addressRestMap.get(key);
            Address address = addressRest.addressRestToAddressConverter();
            addressMap.put(key, address);
        }

        return addressMap;
    }
}