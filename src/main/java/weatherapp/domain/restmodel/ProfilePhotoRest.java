/*
 * Copyright (c) 2018.
 */

package weatherapp.domain.restmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author sulekha
 * njit_cs_673_Weather_Web_App, 2018
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfilePhotoRest {
    private MultipartFile profilePhoto;
    private String email;

    public ProfilePhotoRest() {
    }

    public MultipartFile getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(MultipartFile profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ProfilePhotoRest{" +
                "profilePhoto=" + profilePhoto.getOriginalFilename() +
                ", email='" + email + '\'' +
                '}';
    }
}