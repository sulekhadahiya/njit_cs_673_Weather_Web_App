/*
 * Copyright (c) 2018.
 */

package weatherapp.services;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */

import org.springframework.stereotype.Service;
import weatherapp.domain.dbmodel.UserProfile;
import weatherapp.repositories.UserProfileRepository;

import java.util.Objects;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    // UserProfileService needs to access repository layer so that it can save user profile data
    // in database
    private UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    /**
     * declare a method that will create a new user profile it will save profile in database
     * and returns the saved user profile.
     *
     * @return
     */
    @Override
    public UserProfile createUserProfile(UserProfile userProfile) {
        UserProfile savedUserProfile = this.userProfileRepository.insert(userProfile);
        return savedUserProfile;
    }

    /**
     * declare a method that will delete a user profile
     *
     * @param email
     * @return
     */
    @Override
    public UserProfile deleteUserProfile(String email) {
        UserProfile userProfile = this.userProfileRepository.findByEmail(email);
        if (Objects.isNull(userProfile)) {
            throw new RuntimeException("User profile corresponding to this email address does not exist");
        } else {
            userProfileRepository.delete(userProfile);
        }
        return userProfile;
    }
}
