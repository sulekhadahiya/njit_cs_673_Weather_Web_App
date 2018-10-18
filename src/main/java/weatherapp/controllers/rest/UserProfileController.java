/*
 * Copyright (c) 2018.
 */

package weatherapp.controllers.rest;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import weatherapp.domain.dbmodel.UserProfile;
import weatherapp.domain.restmodel.UserProfileRest;
import weatherapp.services.UserProfileService;

@RequestMapping(value = "/userprofile", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class UserProfileController {

    private UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping(value = "/create")
    public UserProfileRest createUserProfile(@RequestBody UserProfileRest userProfileRest) {
        UserProfile userProfile = userProfileRest.userProfileRestToUserProfile();
        UserProfile createdUserProfile1 = this.userProfileService.createUserProfile(userProfile);
        return UserProfileRest.userprofileToUserProfileRest(createdUserProfile1);
    }

    @DeleteMapping(value = "/remove")
    public UserProfile deleteUserProfile(String email) {
        return this.userProfileService.deleteUserProfile(email);
    }
}
