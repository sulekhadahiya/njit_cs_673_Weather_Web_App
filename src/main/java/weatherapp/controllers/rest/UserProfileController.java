/*
 * Copyright (c) 2018.
 */

package weatherapp.controllers.rest;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import weatherapp.domain.dbmodel.UserProfile;
import weatherapp.domain.restmodel.ProfilePhotoRest;
import weatherapp.domain.restmodel.UserProfileRest;
import weatherapp.exception.ProfilePhotoDoesNotExistException;
import weatherapp.exception.ProfilePhotoNotReceivedException;
import weatherapp.exception.UserProfileAlreadyExist;
import weatherapp.exception.UserProfileDoesNotExist;
import weatherapp.services.UserProfileService;

import java.util.Map;
import java.util.Objects;

@RequestMapping(value = "/userprofile", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class UserProfileController {
    private Logger logger = LoggerFactory.getLogger(UserProfileController.class);
    private UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping(value = "/create-user-profile")
    @ResponseStatus(HttpStatus.CREATED)
    public UserProfileRest createUserProfile(@RequestBody UserProfileRest userProfileRest) {
        UserProfile userProfile = userProfileRest.userProfileRestToUserProfile();
        UserProfile createdUserProfile = this.userProfileService.createUserProfile(userProfile);
        return UserProfileRest.userprofileToUserProfileRest(createdUserProfile);
    }


    @GetMapping(value = "/get-user-profile/{email}/")
    public UserProfileRest getUserProfile(@PathVariable(value = "email") String email) {
        UserProfile userProfile = this.userProfileService.getUserProfileByEmail(email);
        if (Objects.isNull(userProfile)) {
            throw new UserProfileDoesNotExist("User Profile does not Exist.");
        }
        return UserProfileRest.userprofileToUserProfileRest(userProfile);
    }

    @PutMapping(value = "/update-user-profile/{existingEmail}/")
    public UserProfileRest updateUserProfile(@PathVariable(value = "existingEmail") String existingEmail, @RequestBody UserProfileRest userProfileRest) {
        UserProfile userProfile = userProfileRest.userProfileRestToUserProfile();
        UserProfile savedUserProfile = this.userProfileService.updateUserProfileByEmail(existingEmail, userProfile);
        return UserProfileRest.userprofileToUserProfileRest(savedUserProfile);
    }

    @DeleteMapping(value = "/remove-user-profile")
    public UserProfileRest deleteUserProfile(String email) {
        UserProfile deletedUserProfile = this.userProfileService.deleteUserProfile(email);
        UserProfileRest userProfileRest = UserProfileRest.userprofileToUserProfileRest(deletedUserProfile);
        return userProfileRest;
    }

    @PostMapping(value = "/upload-profile-photo", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public UserProfileRest uploadUserPhoto(ProfilePhotoRest profilePhotoRest) throws Exception {
        logger.info(profilePhotoRest.toString());
        UserProfile savedUserProfile = this.userProfileService.saveProfilePhoto(profilePhotoRest.getProfilePhoto(), profilePhotoRest.getEmail());
        UserProfileRest userProfileRest = UserProfileRest.userprofileToUserProfileRest(savedUserProfile);
        return userProfileRest;
    }

    @GetMapping(value = "/get-profile-photo/{email}", consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getUserPhotoByEmail(@RequestParam(value = "email") String email) throws Exception {
        logger.info("Request received for Profile photo for email : " + email);
        byte[] userProfilePhotoBytesByEmail = this.userProfileService.getUserProfilePhotoByEmail(email);

        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(userProfilePhotoBytesByEmail, headers, HttpStatus.OK);
        return responseEntity;
    }

    @ExceptionHandler({UserProfileDoesNotExist.class, ProfilePhotoNotReceivedException.class,
            ProfilePhotoDoesNotExistException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, String> handleUserProfileDoesNotExist(Exception e) {
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler({UserProfileAlreadyExist.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public Map<String, String> handleUserProfileAlreadyExist(Exception e) {
        return Map.of("error", e.getMessage());
    }

}