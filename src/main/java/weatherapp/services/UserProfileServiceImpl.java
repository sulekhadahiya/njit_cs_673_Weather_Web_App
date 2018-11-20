/*
 * Copyright (c) 2018.
 */

package weatherapp.services;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import weatherapp.domain.dbmodel.UserProfile;
import weatherapp.exception.ProfilePhotoDoesNotExistException;
import weatherapp.exception.ProfilePhotoNotReceivedException;
import weatherapp.exception.UserProfileAlreadyExistException;
import weatherapp.exception.UserProfileDoesNotExistException;
import weatherapp.repositories.UserProfileRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private static final Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);
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
        UserProfile userProfileByEmail = this.getUserProfileByEmail(userProfile.getEmail());
        if (Objects.nonNull(userProfileByEmail)) {
            throw new UserProfileAlreadyExistException(String.format("User profile corresponding to this %s already exist.", userProfileByEmail.getEmail()));
        }
        UserProfile savedUserProfile = this.userProfileRepository.insert(userProfile);
        return savedUserProfile;
    }

    @Override
    public UserProfile getUserProfileByEmail(String email) {
        UserProfile userProfile = this.userProfileRepository.findByEmail(email);
        return userProfile;
    }

    @Override
    public UserProfile updateUserProfileByEmail(String email, UserProfile userProfile) {
        UserProfile userProfileByEmail = this.getUserProfileByEmail(email);
        if (Objects.isNull(userProfileByEmail)) {
            throw new UserProfileDoesNotExistException(String.format("User Profile does not Exist. Please create a user first using email %s or %s", userProfile.getEmail(), email));
        }

        userProfile.setId(userProfileByEmail.getId());
        //Otherwise the photo will be replaced by some unknown photo, use separate Rest endpoint to update photo.
        userProfile.setProfilePhoto(userProfileByEmail.getProfilePhoto());
        UserProfile updatedUserProfile = this.userProfileRepository.save(userProfile);
        return updatedUserProfile;
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
            throw new UserProfileDoesNotExistException("User profile corresponding to this email address does not exist");
        } else {
            if (Objects.nonNull(userProfile.getProfilePhoto())) {
                String profilePhotoKey = userProfile.getProfilePhoto();
                DeleteObjectRequest deleteRequest = new DeleteObjectRequest(bucketName, profilePhotoKey);
                amazonS3Client.deleteObject(deleteRequest);
            }
            userProfileRepository.delete(userProfile);
        }
        return userProfile;
    }

    @Override
    public UserProfile saveProfilePhoto(MultipartFile profilePhoto, String email) throws IOException {

        if (Objects.isNull(profilePhoto)) {
            new ProfilePhotoNotReceivedException("Profile Photo was not uploaded.");
        }

        if (Objects.isNull(email) || email.isEmpty() || email.length() == 0) {
            new IllegalArgumentException("Email not sent.");
        }

        UserProfile userProfilebyEmail = this.getUserProfileByEmail(email);
        if (Objects.isNull(userProfilebyEmail)) {
            throw new UserProfileDoesNotExistException("User profile corresponding to this email address does not exist");
        }

        byte[] bytes = profilePhoto.getBytes();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(bytes.length);

        String key = email + "-" + profilePhoto.getOriginalFilename();
        PutObjectRequest request = new PutObjectRequest(bucketName,
                key, new ByteArrayInputStream(bytes), objectMetadata);

        PutObjectResult putObjectResult = amazonS3Client.putObject(request);
        logger.info(putObjectResult.toString());
        userProfilebyEmail.setProfilePhoto(key);
        UserProfile updatedUserProfile = this.userProfileRepository.save(userProfilebyEmail);
        return updatedUserProfile;
    }

    @Override
    public byte[] getUserProfilePhotoByEmail(String email) throws IOException {
        UserProfile userProfile = this.getUserProfileByEmail(email);
        if (Objects.isNull(userProfile)) {
            throw new UserProfileDoesNotExistException("User profile corresponding to this email address does not exist");
        }
        if (Objects.isNull(userProfile.getProfilePhoto())) {
            throw new ProfilePhotoDoesNotExistException(String.format("User Photo for this profile (%s) does not exist.", userProfile.getEmail()));
        }
        GetObjectRequest userProfilePhotoRequest = new GetObjectRequest(bucketName, userProfile.getProfilePhoto());
        S3Object userProfilePhoto = amazonS3Client.getObject(userProfilePhotoRequest);
        S3ObjectInputStream objectContent = userProfilePhoto.getObjectContent();
        byte[] photoBytes = objectContent.readAllBytes();
        return photoBytes;
    }

    @Override
    public UserProfile updateMarkSafeInUserProfile(String existingEmail, boolean markSafe) {
        UserProfile userProfile = userProfileRepository.findByEmail(existingEmail);
        if (Objects.isNull(userProfile)) {
            throw new UserProfileDoesNotExistException("User profile corresponding to this email address does not exist");
        }

        userProfile.setMarkSafe(markSafe);
        UserProfile savedUserProfile = userProfileRepository.save(userProfile);
        return savedUserProfile;
    }
}