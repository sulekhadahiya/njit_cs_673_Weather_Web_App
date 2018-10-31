/*
 * Copyright (c) 2018.
 */

package weatherapp.services;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */

import org.springframework.web.multipart.MultipartFile;
import weatherapp.domain.dbmodel.UserProfile;

public interface UserProfileService {
    /**
     * declare a method that will create a new user profile it will save profile in database
     * and returns the saved user profile.
     *
     * @return
     */
    UserProfile createUserProfile(UserProfile userProfile);

    UserProfile getUserProfileByEmail(String email);

    UserProfile updateUserProfileByEmail(String existingEmail, UserProfile userProfile);

    /**
     * declare a method that will delete a user profile
     *
     * @return
     */
    UserProfile deleteUserProfile(String email);

    UserProfile saveProfilePhoto(MultipartFile profilePhoto, String email) throws Exception;

    byte[] getUserProfilePhotoByEmail(String email) throws Exception;


}