/*
 * Copyright (c) 2018.
 */

package weatherapp.services;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import weatherapp.domain.dbmodel.UserProfile;
import weatherapp.repositories.UserProfileRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    // UserProfileService needs to access repository layer so that it can save user profile data
    // in database
    private UserProfileRepository userProfileRepository;
    private AmazonS3Client amazonS3Client;
    private String bucketName = "sdpm2018/user-profile-images";

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository,
                                  AmazonS3Client amazonS3Client) {
        this.userProfileRepository = userProfileRepository;
        this.amazonS3Client = amazonS3Client;
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

    @Override
    public UserProfile saveProfilePhoto(MultipartFile profilePhoto, String email) throws IOException {

        if (Objects.isNull(profilePhoto)) {
            return null;
        }

        if (Objects.isNull(email) || email.isEmpty() || email.isBlank()) {
            return null;
        }

        byte[] bytes = profilePhoto.getBytes();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(bytes.length);

        PutObjectRequest request = new PutObjectRequest(bucketName,
                email + "-" + profilePhoto.getOriginalFilename(), new ByteArrayInputStream(bytes), objectMetadata);

        amazonS3Client.putObject(request);
        return null;
    }
}
